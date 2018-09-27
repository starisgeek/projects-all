package com.yunfenghui.erp.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 退款记录
 * 
 * @author Administrator
 *
 */
public class RefundRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 源商户交易号
	 */
	private String tradeNo;
	/**
	 * 退款交易号
	 */
	private String refundTradeNo;
	/**
	 * 第三方退款交易号
	 */
	private String outRefundTradeNo;
	/**
	 * 退款金额
	 */
	private int refundAmount;
	/**
	 * 待退款/退款成功/退款失败
	 */
	private int refundStatus;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date modifyTime;

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getRefundTradeNo() {
		return refundTradeNo;
	}

	public void setRefundTradeNo(String refundTradeNo) {
		this.refundTradeNo = refundTradeNo;
	}

	public String getOutRefundTradeNo() {
		return outRefundTradeNo;
	}

	public void setOutRefundTradeNo(String outRefundTradeNo) {
		this.outRefundTradeNo = outRefundTradeNo;
	}

	public int getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(int refundAmount) {
		this.refundAmount = refundAmount;
	}

	public int getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(int refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {
		private final RefundRecord record;

		private Builder() {
			this.record = new RefundRecord();
		}

		public Builder tradeNo(String tradeNo) {
			this.record.setTradeNo(tradeNo);
			return this;
		}

		public Builder refundTradeNo(String refundTradeNo) {
			this.record.setRefundTradeNo(refundTradeNo);
			return this;
		}

		public Builder outRefundTradeNo(String outRefundTradeNo) {
			this.record.setOutRefundTradeNo(outRefundTradeNo);
			return this;
		}

		public Builder refundAmount(int refundAmount) {
			this.record.setRefundAmount(refundAmount);
			return this;
		}

		public Builder refundStatus(int refundStatus) {
			this.record.setRefundStatus(refundStatus);
			return this;
		}

		public Builder createTime(Date createTime) {
			this.record.setCreateTime(createTime);
			return this;
		}

		public Builder modifyTime(Date modifyTime) {
			this.record.setModifyTime(modifyTime);
			return this;
		}

		public RefundRecord build() {
			return this.record;
		}
	}

}
