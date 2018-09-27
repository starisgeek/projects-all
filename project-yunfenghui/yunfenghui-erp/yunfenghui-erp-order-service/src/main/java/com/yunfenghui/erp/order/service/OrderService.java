package com.yunfenghui.erp.order.service;

import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.order.entity.OrderCloseRequest;
import com.yunfenghui.erp.order.entity.OrderCloseResponse;
import com.yunfenghui.erp.order.entity.OrderCreateRequest;
import com.yunfenghui.erp.order.entity.OrderPayRequest;
import com.yunfenghui.erp.order.entity.OrderPayResponse;
import com.yunfenghui.erp.order.entity.OrderQueryRequest;
import com.yunfenghui.erp.order.entity.OrderQueryResponse;
import com.yunfenghui.erp.order.entity.OrderRefundRequest;
import com.yunfenghui.erp.order.entity.OrderRefundResponse;

public interface OrderService {
	/**
	 * 创建订单
	 * 1.调用库存服务冻结库存，并生成冻结记录。如果这个步骤失败，则抛出异常。
	 * 2.创建订单。如果这个步骤失败，则回滚第一部操作。如果回滚失败，则发消息到MQ重试。
	 * 如果发消息到MQ失败，则改为发本地队列重试。如果宕机，则可以通过人工处理。
	 * 
	 * @param createRequest
	 * @return
	 * @throws ERPException
	 *             如果输入会员电话则可能抛出会员不存在
	 */
	String createOrder(OrderCreateRequest createRequest) throws ERPException;

	/**
	 * 订单支付
	 * 
	 * @param payRequest
	 * @return
	 * @throws ERPException
	 */
	OrderPayResponse payOrder(OrderPayRequest payRequest) throws ERPException;

	/**
	 * 根据订单号和外部交易号查询订单支付状态
	 * 
	 * @param orderNo
	 * @param outTradeNo
	 * @return
	 * @throws ERPException
	 */
	OrderQueryResponse queryOrder(OrderQueryRequest request) throws ERPException;

	/**
	 * 订单退款
	 * 
	 * @param refundRequest
	 * @return 退款订单号
	 * @throws ERPException
	 */
	OrderRefundResponse refundOrder(OrderRefundRequest refundRequest) throws ERPException;

	/**
	 * 取消订单.
	 * 1.修改订单状态为取消。
	 * 2.调用库存服务删除库存冻结记录并增加库存。如果这个步骤失败，则发消息到MQ。如果发消息失败则进入本地队列重试。最后可以人工处理。
	 * 
	 * @param orderNo
	 * @throws ERPException
	 *             订单号不存在。错误的订单状态，不能取消订单。
	 */
	void cancelOrder(String orderNo) throws ERPException;

	/**
	 * 关闭订单
	 * 
	 * @param orderNo
	 * @throws ERPException
	 */
	OrderCloseResponse closeOrder(OrderCloseRequest request) throws ERPException;

}
