package com.yunfenghui.erp.goods.model;

import java.io.Serializable;

/**
 * 商品品牌
 * 
 * @author Administrator
 *
 */
public class GoodsBrand implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private int id;

	/**
	 * 名称(门店唯一)
	 */
	private String name;

	/**
	 * 门店id
	 */
	private int shopId;

	public GoodsBrand() {
	}

	public GoodsBrand(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

}
