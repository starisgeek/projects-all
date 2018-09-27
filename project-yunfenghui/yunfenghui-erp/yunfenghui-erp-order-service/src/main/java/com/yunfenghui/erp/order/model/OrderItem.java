package com.yunfenghui.erp.order.model;

import java.io.Serializable;

/**
 * 收银订单明细
 * 
 * @author Administrator
 *
 */
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 商品id
	 */
	private int goodsId;
	/**
	 * 商品条码
	 */
	private String goodsBarcode;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 原价
	 */
	private int originalPrice;
	/**
	 * 折扣
	 */
	private int discount;
	/**
	 * 售价
	 */
	private int salePrice;
	/**
	 * 积分发放比率
	 */
	private int sendScoreRatio;
	/**
	 * 发放积分
	 */
	private int sendScores;
	/**
	 * 数量
	 */
	private int quantity;
	/**
	 * 明细金额
	 */
	private int totalAmount;
	/**
	 * 退款数量
	 */
	private int refundQuantity = 0;
	/**
	 * 退款金额
	 */
	private int refundAmount = 0;

	/**
	 * 实际退还积分
	 */
	private int refundScores = 0;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsBarcode() {
		return goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public int getSendScoreRatio() {
		return sendScoreRatio;
	}

	public void setSendScoreRatio(int sendScoreRatio) {
		this.sendScoreRatio = sendScoreRatio;
	}

	public int getSendScores() {
		return sendScores;
	}

	public void setSendScores(int sendScores) {
		this.sendScores = sendScores;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getRefundQuantity() {
		return refundQuantity;
	}

	public void setRefundQuantity(int refundQuantity) {
		this.refundQuantity = refundQuantity;
	}

	public int getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(int refundAmount) {
		this.refundAmount = refundAmount;
	}

	public int getRefundScores() {
		return refundScores;
	}

	public void setRefundScores(int refundScores) {
		this.refundScores = refundScores;
	}

}
