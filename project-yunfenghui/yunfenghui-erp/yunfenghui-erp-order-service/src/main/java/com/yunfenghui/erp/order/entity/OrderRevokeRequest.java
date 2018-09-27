package com.yunfenghui.erp.order.entity;

import java.io.Serializable;

/**
 * 订单撤销请求。用于撤销支付订单调用
 * 
 * @author Administrator
 *
 */
public class OrderRevokeRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 商户交易号
	 */
	private String tradeNo;

	/**
	 * 第三方交易号
	 */
	private String outTradeNo;

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
