package com.yunfenghui.erp.stock.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * 库存冻结记录
 * 
 * @author Administrator
 *
 */
public class StockFreezeRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 门店id
	 */
	private int shopId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
