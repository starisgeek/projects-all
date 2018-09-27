package com.yunfenghui.erp.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单支付记录
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
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 第三方交易号
	 */
	private String outTradeNo;
	/**
	 * 支付方式
	 */
	private int payWay;
	/**
	 * 支付金额
	 */
	private int payAmount;
	/**
	 * 支付状态
	 */
	private int payStatus;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date modifyTime;

	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

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

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
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
		private final PayRecord record;

		private Builder() {
			this.record = new PayRecord();
		}

		public Builder recordNo(String recordNo) {
			this.record.setRecordNo(recordNo);
			return this;
		}

		public Builder outTradeNo(String outTradeNo) {
			this.record.setOutTradeNo(outTradeNo);
			return this;
		}

		public Builder orderNo(String orderNo) {
			this.record.setOrderNo(orderNo);
			return this;
		}

		public Builder payWay(int payWay) {
			this.record.setPayWay(payWay);
			return this;
		}

		public Builder payAmount(int payAmount) {
			this.record.setPayAmount(payAmount);
			return this;
		}

		public Builder payStatus(int payStatus) {
			this.record.setPayStatus(payStatus);
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

		public PayRecord build() {
			return this.record;
		}
	}
}
