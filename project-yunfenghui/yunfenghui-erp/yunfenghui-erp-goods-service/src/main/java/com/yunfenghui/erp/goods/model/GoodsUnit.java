package com.yunfenghui.erp.goods.model;

import java.io.Serializable;

/**
 * 商品单位
 * 
 * @auther zenglh
 * @date 2018-05-17 11:19
 **/

public class GoodsUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private int id;

	/**
	 * 名称
	 */
	private String name;

	public GoodsUnit() {
	}

	public GoodsUnit(int id) {
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
}
