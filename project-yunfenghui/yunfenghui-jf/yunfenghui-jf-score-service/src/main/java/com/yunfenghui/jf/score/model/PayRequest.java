package com.yunfenghui.jf.score.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付请求
 * 
 * @author Administrator
 *
 */
public class PayRequest implements Serializable {
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
	 * 支付金额
	 */
	private int payAmount;
	/**
	 * 外部交易号
	 */
	private String outerTradeNo;
	/**
	 * 创建时间
	 */
	private Date createTime;
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

	public int getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}

	public String getOuterTradeNo() {
		return outerTradeNo;
	}

	public void setOuterTradeNo(String outerTradeNo) {
		this.outerTradeNo = outerTradeNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
