package com.yunfenghui.erp.pay.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunfenghui.common.service.NumberGenerator;
import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.order.dao.OrderDao;
import com.yunfenghui.erp.order.entity.OrderPayRequest;
import com.yunfenghui.erp.order.entity.OrderPayResponse;
import com.yunfenghui.erp.order.entity.OrderQueryRequest;
import com.yunfenghui.erp.order.entity.OrderQueryResponse;
import com.yunfenghui.erp.order.entity.OrderRefundRequest;
import com.yunfenghui.erp.order.entity.OrderRefundResponse;
import com.yunfenghui.erp.order.entity.OrderRevokeRequest;
import com.yunfenghui.erp.order.entity.OrderRevokeResponse;
import com.yunfenghui.erp.order.model.Order;
import com.yunfenghui.erp.order.util.OrderMessageCode;

/**
 * 现金支付实现
 * 
 * @author Administrator
 *
 */
@Service(CashPayService.ID)
public class CashPayService implements PayService {
	public static final String ID = "cashPayService";
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource(name = "numberGenerator")
	private NumberGenerator numberGenerator;
	@Autowired
	private OrderDao orderDao;

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {
			ERPException.class })
	public OrderPayResponse pay(OrderPayRequest request) throws ERPException {
		int updated = orderDao.updateOrderStatusAndOutTradeNo(request.getOrderNo(),
				Order.STATUS_WAIT_PAY, Order.STATUS_TRADE_SUCCESS, null, new Date());
		if (updated != 1) {
			logger.error(
					"Failed to updateOrderStatus, orderNo:{}, expectedStatus:{}, updatedStatus:{}",
					request.getOrderNo(), Order.STATUS_WAIT_PAY, Order.STATUS_TRADE_SUCCESS);
			throw new ERPException(OrderMessageCode.ORDER_NOT_EXISTS_OR_STATUS_ERROR);
		}
		OrderPayResponse response = new OrderPayResponse();
		response.setTradeNo(request.getOrderNo());
		response.setSuccess(true);
		response.setFail(false);
		return response;
	}

	@Override
	public OrderRevokeResponse revoke(OrderRevokeRequest request) throws ERPException {
		return null;
	}

	@Override
	public OrderRefundResponse refund(OrderRefundRequest request) throws ERPException {
		return null;
	}

	@Override
	public OrderQueryResponse query(OrderQueryRequest request) throws ERPException {
		return null;
	}

}
