package com.yunfenghui.erp.pay.service;

import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.order.entity.OrderPayRequest;
import com.yunfenghui.erp.order.entity.OrderPayResponse;
import com.yunfenghui.erp.order.entity.OrderQueryRequest;
import com.yunfenghui.erp.order.entity.OrderQueryResponse;
import com.yunfenghui.erp.order.entity.OrderRefundRequest;
import com.yunfenghui.erp.order.entity.OrderRefundResponse;
import com.yunfenghui.erp.order.entity.OrderRevokeRequest;
import com.yunfenghui.erp.order.entity.OrderRevokeResponse;

/**
 * 支付接口
 * 
 * @author Administrator
 *
 */
public interface PayService {

	/**
	 * 支付
	 * 
	 * @param request
	 * @return
	 * @throws ERPException
	 */
	OrderPayResponse pay(OrderPayRequest request) throws ERPException;

	/**
	 * 撤销
	 * 
	 * @param request
	 * @return
	 * @throws ERPException
	 */
	OrderRevokeResponse revoke(OrderRevokeRequest request) throws ERPException;

	/**
	 * 退款
	 * 
	 * @param request
	 * @return
	 * @throws ERPException
	 */
	OrderRefundResponse refund(OrderRefundRequest request) throws ERPException;

	/**
	 * 查询
	 * 
	 * @param request
	 * @return
	 * @throws ERPException
	 */
	OrderQueryResponse query(OrderQueryRequest request) throws ERPException;
}
