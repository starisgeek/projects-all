package com.yunfenghui.jf.score.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员支付退款记录
 * 
 * @author Administrator
 *
 */
public class RefundRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 退款记录号
	 */
	private String recordNo;
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
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 备注
	 */
	private String remark;

	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

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

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {
		private final RefundRecord record;

		private Builder() {
			this.record = new RefundRecord();
		}

		public Builder recordNo(String recordNo) {
			record.setRecordNo(recordNo);
			return this;
		}

		public Builder memberId(int memberId) {
			record.setMemberId(memberId);
			return this;
		}

		public Builder partnerId(int partnerId) {
			record.setPartnerId(partnerId);
			return this;
		}

		public Builder payRecordNo(String payRecordNo) {
			record.setPayRecordNo(payRecordNo);
			return this;
		}

		public Builder outTradeNo(String outTradeNo) {
			record.setOutTradeNo(outTradeNo);
			return this;
		}

		public Builder outRefundNo(String outRefundNo) {
			record.setOutRefundNo(outRefundNo);
			return this;
		}

		public Builder createTime(Date createTime) {
			record.setCreateTime(createTime);
			return this;
		}

		public Builder remark(String remark) {
			record.setRemark(remark);
			return this;
		}

		public RefundRecord build() {
			return record;
		}
	}
}
