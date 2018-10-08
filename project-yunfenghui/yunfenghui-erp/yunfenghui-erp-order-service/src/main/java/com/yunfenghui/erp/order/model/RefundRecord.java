package com.yunfenghui.erp.order.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 退款记录
 * 
 * @author Administrator
 *
 */
public class RefundRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 退款交易号
	 */
	private String refundRecordNo;
	/**
	 * 第三方退款交易号
	 */
	private String outRefundTradeNo;
	/**
	 * 退款总金额
	 */
	private int totalRefundAmount;

	/**
	 * 门店id
	 */
	private int shopId;
	/**
	 * 待退款/退款成功/退款失败
	 */
	private int refundStatus;
	/**
	 * 创建人id
	 */
	private int createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date modifyTime;

	/**
	 * 退款原因
	 */
	private String reason;

	private List<RefundRecordItem> items;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRefundRecordNo() {
		return refundRecordNo;
	}

	public void setRefundRecordNo(String refundRecordNo) {
		this.refundRecordNo = refundRecordNo;
	}

	public int getTotalRefundAmount() {
		if (totalRefundAmount == 0 && items != null && !items.isEmpty()) {
			for (RefundRecordItem item : items) {
				totalRefundAmount += item.getRefundAmount();
			}
		}
		return totalRefundAmount;
	}

	public void setTotalRefundAmount(int totalRefundAmount) {
		this.totalRefundAmount = totalRefundAmount;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public List<RefundRecordItem> getItems() {
		return items;
	}

	public void setItems(List<RefundRecordItem> items) {
		this.items = items;
	}

	public String getOutRefundTradeNo() {
		return outRefundTradeNo;
	}

	public void setOutRefundTradeNo(String outRefundTradeNo) {
		this.outRefundTradeNo = outRefundTradeNo;
	}

	public int getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(int refundStatus) {
		this.refundStatus = refundStatus;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {
		private final RefundRecord record;

		private Builder() {
			this.record = new RefundRecord();
		}

		public Builder orderNo(String orderNo) {
			this.record.setOrderNo(orderNo);
			return this;
		}

		public Builder refundRecordNo(String refundRecordNo) {
			this.record.setRefundRecordNo(refundRecordNo);
			return this;
		}

		public Builder outRefundTradeNo(String outRefundTradeNo) {
			this.record.setOutRefundTradeNo(outRefundTradeNo);
			return this;
		}

		public Builder totalRefundAmount(int totalRefundAmount) {
			this.record.setTotalRefundAmount(totalRefundAmount);
			return this;
		}

		public Builder shopId(int shopId) {
			this.record.setShopId(shopId);
			return this;
		}

		public Builder refundStatus(int refundStatus) {
			this.record.setRefundStatus(refundStatus);
			return this;
		}

		public Builder createUserId(int createUserId) {
			this.record.setCreateUserId(createUserId);
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

		public Builder reason(String reason) {
			this.record.setReason(reason);
			return this;
		}

		public Builder items(List<RefundRecordItem> items) {
			this.record.setItems(items);
			return this;
		}

		public RefundRecord build() {
			return this.record;
		}
	}

}
