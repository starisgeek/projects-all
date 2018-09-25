package com.yunfenghui.erp.goods.model;

import java.io.Serializable;

/**
 * 商品类别
 * 
 * @author wanghao
 *         2018年4月13日
 */
public class GoodsCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private int id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 父类别
	 */
	private GoodsCategory parent;

	public GoodsCategory() {

	}

	public GoodsCategory(int id) {
		this.id = id;
	}

	public GoodsCategory(int id, String name) {
		this.id = id;
		this.name = name;
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

	public GoodsCategory getParent() {
		return parent;
	}

	public void setParent(GoodsCategory parent) {
		this.parent = parent;
	}
}
