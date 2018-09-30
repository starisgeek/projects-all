package com.yunfenghui.erp.stock.model;

import java.io.Serializable;

/**
 * 库存冻结记录明细
 * 
 * @author Administrator
 *
 */
public class StockFrozenRecordItem implements Serializable {
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
	 * 冻结数量
	 */
	private int freezeQuantity;

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

	public int getFreezeQuantity() {
		return freezeQuantity;
	}

	public void setFreezeQuantity(int freezeQuantity) {
		this.freezeQuantity = freezeQuantity;
	}

}
