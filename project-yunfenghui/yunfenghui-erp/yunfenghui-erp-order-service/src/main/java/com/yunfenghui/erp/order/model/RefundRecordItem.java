package com.yunfenghui.erp.order.model;

import java.io.Serializable;

/**
 * 退款记录明细
 * 
 * @author Administrator
 *
 */
public class RefundRecordItem implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 退款记录号
	 */
	private String refundRecordNo;
	/**
	 * 商品id
	 */
	private int goodsId;
	/**
	 * 退款价格
	 */
	private int refundPrice;
	/**
	 * 退款数量
	 */
	private int refundQuantity;

	/**
	 * 退款金额
	 */
	private int refundAmount;

	public String getRefundRecordNo() {
		return refundRecordNo;
	}

	public void setRefundRecordNo(String refundRecordNo) {
		this.refundRecordNo = refundRecordNo;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getRefundPrice() {
		return refundPrice;
	}

	public void setRefundPrice(int refundPrice) {
		this.refundPrice = refundPrice;
	}

	public int getRefundQuantity() {
		return refundQuantity;
	}

	public void setRefundQuantity(int refundQuantity) {
		this.refundQuantity = refundQuantity;
	}

	public int getRefundAmount() {
		if (refundAmount == 0) {
			refundAmount = this.refundPrice * this.refundQuantity;
		}
		return refundAmount;
	}

	public void setRefundAmount(int refundAmount) {
		this.refundAmount = refundAmount;
	}

}
