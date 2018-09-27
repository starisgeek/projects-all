package com.yunfenghui.erp.stock.model;

import java.io.Serializable;

/**
 * 库存
 * 
 * @author Administrator
 *
 */
public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 商品id
	 */
	private int goodsId;
	/**
	 * 库存数量
	 */
	private int quantity;

	/**
	 * 冻结数量
	 */
	private int frozenQuantity;

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getFrozenQuantity() {
		return frozenQuantity;
	}

	public void setFrozenQuantity(int frozenQuantity) {
		this.frozenQuantity = frozenQuantity;
	}

}
