package com.yunfenghui.erp.stock.model;

import java.io.Serializable;

/**
 * 进货记录明细
 * 
 * @author Administrator
 *
 */
public class StockRecordItem implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 入库单号
	 */
	private String recordNo;
	/**
	 * 商品
	 */
	private int goodsId;

	/**
	 * 进价
	 */
	private int buyPrice;
	/**
	 * 进货量
	 */
	private int buyQuantity;
	/**
	 * 赠送量
	 */
	private int presentQuantity;

	/**
	 * 总金额
	 */
	private int totalAmount;

	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public int getPresentQuantity() {
		return presentQuantity;
	}

	public void setPresentQuantity(int presentQuantity) {
		this.presentQuantity = presentQuantity;
	}

	public int getTotalAmount() {
		if (this.totalAmount == 0) {
			this.totalAmount = this.buyPrice * this.buyQuantity;
		}
		return this.totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
}
