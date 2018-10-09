package com.yunfenghui.erp.order.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunfenghui.common.Commons;
import com.yunfenghui.common.DateTimes;
import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.order.entity.OrderCloseRequest;
import com.yunfenghui.erp.order.entity.OrderCloseResponse;
import com.yunfenghui.erp.order.entity.OrderCreateRequest;
import com.yunfenghui.erp.order.entity.OrderItemCreateRequest;
import com.yunfenghui.erp.order.entity.OrderPayRequest;
import com.yunfenghui.erp.order.entity.OrderPayResponse;
import com.yunfenghui.erp.order.entity.OrderQueryRequest;
import com.yunfenghui.erp.order.entity.OrderQueryResponse;
import com.yunfenghui.erp.order.entity.OrderRefundRequest;
import com.yunfenghui.erp.order.entity.OrderRefundResponse;
import com.yunfenghui.erp.order.entity.OrderRevokeRequest;
import com.yunfenghui.erp.order.entity.OrderRevokeResponse;
import com.yunfenghui.erp.order.model.Order;
import com.yunfenghui.erp.order.model.OrderItem;
import com.yunfenghui.erp.order.model.RefundRecord;
import com.yunfenghui.erp.order.util.OrderMessageCode;
import com.yunfenghui.erp.pay.service.PayService;
import com.yunfenghui.erp.stock.model.StockFrozenRecord;
import com.yunfenghui.erp.stock.model.StockFrozenRecordItem;
import com.yunfenghui.erp.stock.service.StockService;

public class OrderServiceImpl implements OrderService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private OrderServiceSupport orderServiceSupport;

	private StockService stockService;

	@Override
	public String createOrder(OrderCreateRequest createRequest) throws ERPException {
		String orderNo = generateOrderNo(createRequest.getShopId());
		// freeze stock, throws exception to front if exception
		StockFrozenRecord frozenRecord = buildStockFrozenRecord(orderNo, createRequest);
		stockService.freezeStock(frozenRecord);
		// build and add order
		Order order = buildOrder(orderNo, createRequest);
		try {
			orderServiceSupport.addOrder(order);
		} catch (Exception e) {
			logger.error("Failed to addOrder:{}", orderNo, e);
			try {
				stockService.unfreezeStock(frozenRecord);
			} catch (Exception ee) {
				logger.error("Failed to unfreezeStock, orderNo:{}", order.getOrderNo(), ee);
			}
			throw e;
		}
		return orderNo;
	}

	@Override
	public OrderPayResponse payOrder(OrderPayRequest payRequest) throws ERPException {
		Order order = ensurePayOrder(payRequest);
		// 如果这里出现异常，StockFrozenRecordScanJob会根据StockFrozenRecord查询订单及支付状态
		orderServiceSupport.modifyOrderPayWay(order.getOrderNo(), payRequest.getPayWay());
		payRequest.setPayAmount(order.getTotalAmount());
		OrderPayResponse payResponse = payService(payRequest.getPayWay()).pay(payRequest);
		if (payResponse.isSuccess()) {
			try {
				// 考虑异步或MQ
				handleOnOrderPaySuccess(order);
			} catch (Exception e) {
				logger.error("Failed to handleOnOrderPaySuccess, orderNo:{}", order.getOrderNo());
			}
		}
		return payResponse;
	}

	private void handleOnOrderPaySuccess(Order order) {
		// 1.修改订单状态为"交易成功"并记录订单成功消息到消息表
		orderServiceSupport.modifyOrderStatusIsSuccessAndAddOrderMessage(order);
		// 2.发送消息到MQ(如果失败，OrderMessageScanJob会进行重试)，MQ消息接收者发积分，成功后删除消息表记录。
		orderServiceSupport.sendMessageOnOrderPaySuccess(order);
		// 2.扣减库存和冻结库存，删除冻结记录(如果失败，StockFrozenRecordScanJob会进行重试)
		stockService.decreaseStock(order.getOrderNo());
	}

	@Override
	public OrderQueryResponse queryOrder(OrderQueryRequest request) throws ERPException {
		// 首先查本地订单状态，如果为现金支付，则直接返回
		// 如果不是现金支付，则如果订单状态为待支付，则调用支付服务查询
		Order order = orderServiceSupport.getOrderByNo(request.getOrderNo());
		if (order == null) {
			return null;
		}
		OrderQueryResponse response;
		if (order.getPayWay() == Order.PAY_WAY_UNKNOWN || order.getPayWay() == Order.PAY_WAY_CASH
				|| order.getStatus() != Order.STATUS_WAIT_PAY) {
			response = new OrderQueryResponse();
			response.setTradeNo(request.getOrderNo());
			response.setPayStatus(order.getStatus());
		} else {
			response = orderServiceSupport.select(order.getPayWay()).query(request);
		}
		return response;
	}

	private Order ensurePayOrder(OrderPayRequest payRequest) throws ERPException {
		Order order = orderServiceSupport.getOrderByNo(payRequest.getOrderNo());
		if (order == null) {
			logger.error("Failed to getOrderByNo:{}, not exists", payRequest.getOrderNo());
			throw new ERPException("order not exists");
		}

		if (order.getStatus() != Order.STATUS_WAIT_PAY) {
			logger.error("Failed to pay order:{}, status:{} is not wait pay", order.getStatus());
			throw new ERPException("Order status is " + order.getStatus() + ", cannot pay");
		}
		order.setPayWay(payRequest.getPayWay());
		return order;
	}

	@Override
	public OrderRefundResponse refundOrder(RefundRecord refundRecord) throws ERPException {
		Order order = orderServiceSupport.getFullOrderByNo(refundRecord.getOrderNo());
		if (order == null || order.getStatus() != Order.STATUS_TRADE_SUCCESS) {
			if (order == null) {
				logger.error("queryOrderByNo:{}, but not found", refundRecord.getOrderNo());
			} else {
				logger.error("order status:{}, cannot refund", order.getStatus());
			}

			throw new ERPException(OrderMessageCode.ORDER_NOT_EXISTS_OR_STATUS_ERROR);
		}
		// 1.新增退款记录
		orderServiceSupport.addRefundRecord(refundRecord, order);
		// 2.发送退款请求给第三方支付
		OrderRefundResponse refundResponse;
		if (order.getPayWay() != Order.PAY_WAY_CASH) {
			OrderRefundRequest refundRequest = new OrderRefundRequest();
			refundRequest.setTradeNo(refundRecord.getOrderNo());
			refundRequest.setRefundRequestNo(refundRecord.getRefundRecordNo());
			refundRequest.setRefundAmount(refundRecord.getTotalRefundAmount());
			refundRequest.setRefundReason(refundRecord.getReason());
			refundResponse = orderServiceSupport.select(order.getPayWay()).refund(refundRequest);
			// 3.修改退款记录状态为退款成功，记录退款成功消息
			if (refundResponse.isSuccess()) {
				orderServiceSupport.modifyRefundRecordStatusIsSuccess(
						refundResponse.getOutTradeNo(), refundRecord);
			} else if (refundResponse.isFailed()) {
				orderServiceSupport.rollbackRefundRecord(refundResponse.getOutTradeNo(),
						refundRecord);
			}
		} else {
			refundResponse = new OrderRefundResponse();
		}
		// 4.发送退款成功消息到MQ
		if (refundResponse.isSuccess()) {

		}
		return refundResponse;
	}

	@Override
	public void cancelOrder(String orderNo) throws ERPException {
		// TODO Auto-generated method stub

	}

	@Override
	public OrderRevokeResponse revokeOrder(OrderRevokeRequest revokeRequest) throws ERPException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderCloseResponse closeOrder(OrderCloseRequest request) throws ERPException {
		// TODO Auto-generated method stub
		return null;
	}

	private StockFrozenRecord buildStockFrozenRecord(String orderNo,
			OrderCreateRequest createRequest) {
		StockFrozenRecord frozenRecord = new StockFrozenRecord();
		frozenRecord.setOrderNo(orderNo);
		frozenRecord.setShopId(createRequest.getShopId());
		frozenRecord.setCreateTime(new Date());
		List<StockFrozenRecordItem> items = new ArrayList<>(
				createRequest.getItemCreateRequests().size());
		for (OrderItemCreateRequest itemCreateRequest : createRequest.getItemCreateRequests()) {
			StockFrozenRecordItem item = new StockFrozenRecordItem();
			item.setGoodsId(itemCreateRequest.getGoodsId());
			item.setFrozenQuantity(itemCreateRequest.getQuantity());
			items.add(item);
		}
		frozenRecord.setItems(items);
		return frozenRecord;
	}

	private Order buildOrder(String orderNo, OrderCreateRequest createRequest) {
		return Order.newBuilder().orderNo(orderNo).items(buildOrderItems(orderNo, createRequest))
				.shopId(createRequest.getShopId()).memberId(createRequest.getMemberId())
				.memberName(createRequest.getMemberName())
				.memberPhone(createRequest.getMemberPhone()).payWay(Order.PAY_WAY_UNKNOWN)
				.status(Order.STATUS_WAIT_PAY).totalRefundAmount(0)
				.createUserId(createRequest.getCreateUserId()).createTime(new Date()).build();
	}

	private List<OrderItem> buildOrderItems(String orderNo, OrderCreateRequest createRequest) {
		List<OrderItemCreateRequest> itemCreateRequests = createRequest.getItemCreateRequests();
		List<OrderItem> items = new ArrayList<>(itemCreateRequests.size());
		for (OrderItemCreateRequest itemCreateRequest : itemCreateRequests) {
			items.add(
					OrderItem.newBuilder().orderNo(orderNo).goodsId(itemCreateRequest.getGoodsId())
							.goodsBarcode(itemCreateRequest.getGoodsBarcode())
							.goodsName(itemCreateRequest.getGoodsName())
							.salePrice(itemCreateRequest.getSalePrice())
							.sendScoreRatio(itemCreateRequest.getSendScoreRatio())
							.quantity(itemCreateRequest.getQuantity()).build());
		}
		return items;
	}

	protected String generateOrderNo(int shopId) {
		String timeString = DateTimes.dayStringOf(new Date(), DateTimes.PATTERN_YYMMDDHHMMSS);
		return timeString + StringUtils.leftPad(String.valueOf(shopId), 6, '0')
				+ Commons.randomNumbers(1);
	}

	private PayService payService(int payWay) throws ERPException {
		return orderServiceSupport.select(payWay);
	}
}
