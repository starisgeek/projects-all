package com.yunfenghui.erp.stock.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 库存冻结记录
 * 
 * @author Administrator
 *
 */
public class StockFrozenRecord implements Serializable {
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
	private Date createTime = new Date();

	/**
	 * 冻结记录明细
	 */
	private List<StockFrozenRecordItem> items;

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

	public List<StockFrozenRecordItem> getItems() {
		return items;
	}

	public void setItems(List<StockFrozenRecordItem> items) {
		if (orderNo != null && items != null && !items.isEmpty()) {
			for (StockFrozenRecordItem item : items) {
				item.setOrderNo(orderNo);
			}
		}
		this.items = items;
	}

}
