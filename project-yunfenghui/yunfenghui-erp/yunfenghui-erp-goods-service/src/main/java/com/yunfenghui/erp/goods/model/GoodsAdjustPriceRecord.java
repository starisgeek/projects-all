package com.yunfenghui.erp.goods.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品调价记录
 * 
 * @author Administrator
 *
 */
public class GoodsAdjustPriceRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 记录号
	 */
	private String recordNo;
	/**
	 * 门店id
	 */
	private int shopId;
	/**
	 * 商品
	 */
	private Goods goods;
	/**
	 * 原价
	 */
	private int oldPrice;
	/**
	 * 调整后价格
	 */
	private int newPrice;
	/**
	 * 创建人id
	 */
	private int createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public int getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(int oldPrice) {
		this.oldPrice = oldPrice;
	}

	public int getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(int newPrice) {
		this.newPrice = newPrice;
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

}
