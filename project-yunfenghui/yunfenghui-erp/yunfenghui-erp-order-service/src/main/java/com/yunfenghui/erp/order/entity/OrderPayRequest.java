package com.yunfenghui.erp.order.entity;

import java.io.Serializable;

/**
 * 订单支付请求
 * 
 * @author Administrator
 *
 */
public class OrderPayRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 支付方式
	 */
	private int payWay;

	/**
	 * 支付金额
	 */
	private int payAmount;
	/**
	 * 授权码
	 */
	private String authCode;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getPayWay() {
		return payWay;
	}

	public void setPayWay(int payWay) {
		this.payWay = payWay;
	}

	public int getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

}
