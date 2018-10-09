package com.yunfenghui.erp.order.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunfenghui.common.service.NumberGenerator;
import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.order.dao.OrderDao;
import com.yunfenghui.erp.order.dao.OrderItemDao;
import com.yunfenghui.erp.order.dao.OrderMessageDao;
import com.yunfenghui.erp.order.dao.RefundMessageDao;
import com.yunfenghui.erp.order.dao.RefundRecordDao;
import com.yunfenghui.erp.order.dao.RefundRecordItemDao;
import com.yunfenghui.erp.order.model.Order;
import com.yunfenghui.erp.order.model.OrderItem;
import com.yunfenghui.erp.order.model.OrderMessage;
import com.yunfenghui.erp.order.model.RefundMessage;
import com.yunfenghui.erp.order.model.RefundRecord;
import com.yunfenghui.erp.order.model.RefundRecordItem;
import com.yunfenghui.erp.order.util.OrderMessageCode;
import com.yunfenghui.erp.pay.service.AliPayService;
import com.yunfenghui.erp.pay.service.CashPayService;
import com.yunfenghui.erp.pay.service.PayService;
import com.yunfenghui.erp.pay.service.WechatPayService;

@Service(OrderServiceSupport.ID)
public class OrderServiceSupport {
	public static final String ID = "orderServiceSupport";
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource(name = "messageSender")
	private RetriedMessageSender messageSender;
	@Resource(name = CashPayService.ID)
	private PayService cashPayService;
	@Resource(name = AliPayService.ID)
	private PayService aliPayService;
	@Resource(name = WechatPayService.ID)
	private PayService weichatPayService;
	@Resource(name = "numberGenerator")
	private NumberGenerator numberGenerator;
	@Value("#{rocketmq.topic.order.create.failed}")
	private String orderCreateFailedTopic;
	@Value("#{rocketmq.topic.order.pay.success}")
	private String orderPaySuccessTopic;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private OrderMessageDao orderMessageDao;
	@Autowired
	private RefundRecordDao refundRecordDao;
	@Autowired
	private RefundRecordItemDao refundRecordItemDao;
	@Autowired
	private RefundMessageDao refundMessageDao;

	/**
	 * 新增订单
	 * 
	 * @param order
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void addOrder(Order order) {
		orderDao.insertOrder(order);
		orderItemDao.batchInsertOrderItems(order.getItems());
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void modifyOrderPayWay(String orderNo, int payWay) {
		orderDao.updateOrderPayWay(orderNo, payWay, new Date());
	}

	/**
	 * 根据订单号获取订单(不包括OrderItem)
	 * 
	 * @param orderNo
	 * @return
	 */
	public Order getOrderByNo(String orderNo) {
		return orderDao.queryOrderByNo(orderNo);
	}

	/**
	 * 根据订单号获取订单(包括OrderItem)
	 * 
	 * @param orderNo
	 * @return
	 */
	public Order getFullOrderByNo(String orderNo) {
		return orderDao.queryFullOrderByNo(orderNo);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void modifyOrderStatusIsSuccessAndAddOrderMessage(Order order) {
		int updated = 1;
		if (order.getPayWay() != Order.PAY_WAY_CASH) {
			updated = orderDao.updateOrderStatusAndOutTradeNo(order.getOrderNo(),
					Order.STATUS_WAIT_PAY, Order.STATUS_TRADE_SUCCESS, order.getOutTradeNo(),
					new Date());
		}
		if (updated == 1) {
			if (order.getMemberId() > 0) {
				OrderMessage message = new OrderMessage();
				orderMessageDao.insertOrderMessage(message);
			}
		} else {
			logger.error(
					"Failed to updateOrderStatusAndPayWayAndOutTradeNo, orderNo:{}, expectedStatus:{}, updatedStatus:{}",
					order.getOrderNo(), Order.STATUS_WAIT_PAY, Order.STATUS_TRADE_SUCCESS);
		}
	}

	/**
	 * 新增退款记录并更新订单退款金额
	 * 
	 * @param refundRecord
	 * @throws ERPException
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void addRefundRecord(RefundRecord refundRecord, Order order) throws ERPException {
		for (RefundRecordItem item : refundRecord.getItems()) {
			item.setRefundRecordNo(refundRecord.getRefundRecordNo());
			boolean found = false;
			for (OrderItem orderItem : order.getItems()) {
				if (item.getGoodsId() == orderItem.getGoodsId()) {
					if (orderItem.getRefundQuantity() + item.getRefundQuantity() > orderItem
							.getQuantity()) {
						logger.error(
								"Failed to refund, order item goodsId:{} buy quantity:{}, refund quantity:{}, refund record item refund quantity:{}",
								item.getGoodsId(), orderItem.getQuantity(),
								orderItem.getRefundQuantity(), item.getRefundQuantity());
						throw new ERPException(
								OrderMessageCode.REFUND_ORDER_ITEM_QUANTITY_OVERFLOW);
					}
					item.setRefundPrice(orderItem.getSalePrice());
					break;
				}
			}
			if (!found) {
				logger.error("Failed to refund, refund item goodsId:{} not found in order:{}",
						item.getGoodsId(), refundRecord.getOrderNo());
				throw new ERPException(OrderMessageCode.REFUND_ORDER_ITEM_NOT_FOUND_IN_ORDER);
			}
		}

		refundRecord.setRefundRecordNo(numberGenerator.generate());
		refundRecord.setCreateTime(new Date());
		if (order.getPayWay() == Order.PAY_WAY_CASH) {
			refundRecord.setRefundStatus(RefundRecord.REFUND_STATUS_SUCCESS);
		} else {
			refundRecord.setRefundStatus(RefundRecord.REFUND_STATUS_WAIT);
		}
		refundRecordDao.insertRefundRecord(refundRecord);
		refundRecordItemDao.batchInsertRefundRecordItems(refundRecord.getItems());
		int updated;
		for (RefundRecordItem item : refundRecord.getItems()) {
			updated = orderItemDao.increaseRefundQuantityAndRefundAmount(refundRecord.getOrderNo(),
					item.getGoodsId(), item.getRefundQuantity(), item.getRefundAmount());
			if (updated != 1) {
				logger.error(
						"Failed to increaseRefundQuantityAndRefundAmount, orderNo:{}, goodsId:{}, refundQuantity:{}, refundAmount:{}",
						refundRecord.getOrderNo(), item.getGoodsId(), item.getRefundQuantity(),
						item.getRefundAmount());
				throw new ERPException(OrderMessageCode.REFUND_ORDER_ITEM_QUANTITY_OVERFLOW);
			}
		}
		updated = orderDao.increaseOrderTotalRefundAmount(refundRecord.getOrderNo(),
				refundRecord.getTotalRefundAmount());
		if (updated != 1) {
			logger.error("Failed to increaseOrderTotalRefundAmount, orderNo:{}, refundAmount:{}",
					refundRecord.getOrderNo(), refundRecord.getTotalRefundAmount());
			throw new ERPException(OrderMessageCode.REFUND_ORDER_AMOUNT_OVERFLOW);
		}
		if (order.getPayWay() == Order.PAY_WAY_CASH) {
			addRefundMessage(refundRecord.getRefundRecordNo());
		}
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void modifyRefundRecordStatusIsSuccess(String outRefundTradeNo,
			RefundRecord refundRecord) {
		int updated = refundRecordDao.updateRefundRecordStatusAndOutRefundTradeNo(
				refundRecord.getRefundRecordNo(), RefundRecord.REFUND_STATUS_WAIT,
				RefundRecord.REFUND_STATUS_SUCCESS, outRefundTradeNo, new Date());
		if (updated == 1) {
			addRefundMessage(refundRecord.getRefundRecordNo());
		}
	}

	private void addRefundMessage(String refundRecordNo) {
		RefundMessage message = new RefundMessage();
		message.setRefundRecordNo(refundRecordNo);
		message.setCreateTime(new Date());
		refundMessageDao.insertRefundMessage(message);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void rollbackRefundRecord(String outRefundTradeNo, RefundRecord refundRecord) {
		int updated = refundRecordDao.updateRefundRecordStatusAndOutRefundTradeNo(
				refundRecord.getRefundRecordNo(), RefundRecord.REFUND_STATUS_WAIT,
				RefundRecord.REFUND_STATUS_SUCCESS, null, new Date());
		if (updated == 1) {
			for (RefundRecordItem item : refundRecord.getItems()) {
				updated = orderItemDao.decreaseRefundQuantityAndRefundAmount(
						refundRecord.getOrderNo(), item.getGoodsId(), item.getRefundQuantity(),
						item.getRefundAmount());
				if (updated != 1) {
					logger.warn(
							"Failed decreaseRefundQuantityAndRefundAmount, orderNo:{}, goodsId:{}, refundQuantity:{}, refundAmount:{}",
							refundRecord.getOrderNo(), item.getGoodsId(), item.getRefundQuantity(),
							item.getRefundAmount());
				}
			}
			updated = orderDao.decreaseOrderTotalRefundAmount(refundRecord.getOrderNo(),
					refundRecord.getTotalRefundAmount());
			if (updated != 1) {
				logger.warn("Failed to decreaseOrderTotalRefundAmount, orderNo:{}, refundAmount:{}",
						refundRecord.getOrderNo(), refundRecord.getTotalRefundAmount());
			}
		}
	}

	public void sendMessageOnOrderPaySuccess(Order order) {
		if (order.getMemberId() > 0) {
			messageSender.send(order.getOrderNo(), orderPaySuccessTopic);
		}
	}

	public PayService select(int payWay) throws ERPException {
		switch (payWay) {
		case Order.PAY_WAY_CASH:
			return cashPayService;
		case Order.PAY_WAY_ALI:
			return aliPayService;
		case Order.PAY_WAY_WECHAT:
			return weichatPayService;
		default:
			throw new ERPException("Unsupported pay way:" + payWay);
		}
	}
}
