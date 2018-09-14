package com.yunfenghui.jf.score.model;

import java.io.Serializable;

/**
 * 退款请求
 * 
 * @author Administrator
 *
 */
public class RefundRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 会员id
	 */
	private int memberId;
	/**
	 * 商户id
	 */
	private int partnerId;
	/**
	 * 源支付流水号
	 */
	private String payRecordNo;
	/**
	 * 源外部交易号
	 */
	private String outTradeNo;
	/**
	 * 外部退款单号
	 */
	private String outRefundNo;
	/**
	 * 备注
	 */
	private String remark;

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public String getPayRecordNo() {
		return payRecordNo;
	}

	public void setPayRecordNo(String payRecordNo) {
		this.payRecordNo = payRecordNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
