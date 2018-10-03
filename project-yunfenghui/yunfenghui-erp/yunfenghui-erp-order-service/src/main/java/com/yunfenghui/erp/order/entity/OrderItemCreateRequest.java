package com.yunfenghui.erp.order.entity;

import java.io.Serializable;

/**
 * 订单明细创建请求
 * 
 * @author Administrator
 *
 */
public class OrderItemCreateRequest implements Serializable {
	private static final long serialVersionUID = 1L;
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
	 * 售价
	 */
	private int salePrice;
	/**
	 * 数量
	 */
	private int quantity;
	/**
	 * 积分发放比率
	 */
	private int sendScoreRatio;

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

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSendScoreRatio() {
		return sendScoreRatio;
	}

	public void setSendScoreRatio(int sendScoreRatio) {
		this.sendScoreRatio = sendScoreRatio;
	}

}
