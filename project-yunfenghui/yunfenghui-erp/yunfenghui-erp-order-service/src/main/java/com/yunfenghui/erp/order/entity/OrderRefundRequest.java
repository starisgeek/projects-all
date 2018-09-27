package com.yunfenghui.erp.order.entity;

import java.io.Serializable;

/**
 * 订单退款请求
 * 
 * @author Administrator
 *
 */
public class OrderRefundRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 退款的源商户交易号
	 */
	private String tradeNo;

	/**
	 * 第三方交易号
	 */
	private String outTradeNo;

	/**
	 * 退款金额
	 */
	private int refundAmount;

	/**
	 * 退款请求
	 */
	private String refundRequestNo;

	/**
	 * 退款原因
	 */
	private String refundReason;

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

	public int getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(int refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getRefundRequestNo() {
		return refundRequestNo;
	}

	public void setRefundRequestNo(String refundRequestNo) {
		this.refundRequestNo = refundRequestNo;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

}
