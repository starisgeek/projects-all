package com.yunfenghui.jf.score.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员余额支付记录
 * 
 * @author Administrator
 *
 */
public class PayRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 支付记录号
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
	 * 支付金额
	 */
	private int payAmount;
	/**
	 * 外部交易号
	 */
	private String outTradeNo;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 总退款金额
	 */
	private int totalRefundAmount;
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

	public int getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}

	

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public int getTotalRefundAmount() {
		return totalRefundAmount;
	}

	public void setTotalRefundAmount(int totalRefundAmount) {
		this.totalRefundAmount = totalRefundAmount;
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
		private final PayRecord record;

		private Builder() {
			this.record = new PayRecord();
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

		public Builder payAmount(int payAmount) {
			record.setPayAmount(payAmount);
			return this;
		}

		public Builder outTradeNo(String outTradeNo) {
			record.setOutTradeNo(outTradeNo);
			return this;
		}

		public Builder totalRefundAmount(int totalRefundAmount) {
			record.setTotalRefundAmount(totalRefundAmount);
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

		public PayRecord build() {
			return record;
		}
	}
}
