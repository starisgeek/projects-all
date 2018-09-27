package com.yunfenghui.erp.order.entity;

import java.io.Serializable;

/**
 * 订单查询请求
 * 
 * @author Administrator
 *
 */
public class OrderQueryRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 商户交易号
	 */
	private String tradeNo;
	/**
	 * 第三方交易号
	 */
	private String outTradeNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

}
