package com.yunfenghui.erp.pay.service;

import org.springframework.stereotype.Service;

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
 * 支付宝支付实现
 * 
 * @author Administrator
 *
 */
@Service(AliPayService.ID)
public class AliPayService implements PayService {
	public static final String ID = "aliPayService";

	@Override
	public OrderPayResponse pay(OrderPayRequest request) throws ERPException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderRevokeResponse revoke(OrderRevokeRequest request) throws ERPException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderRefundResponse refund(OrderRefundRequest request) throws ERPException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderQueryResponse query(OrderQueryRequest request) throws ERPException {
		// TODO Auto-generated method stub
		return null;
	}

}
