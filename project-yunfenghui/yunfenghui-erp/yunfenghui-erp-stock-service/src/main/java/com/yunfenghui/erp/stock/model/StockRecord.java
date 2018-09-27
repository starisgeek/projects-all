package com.yunfenghui.erp.stock.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yunfenghui.common.DateTimes;

/**
 * 进货记录
 * 
 * @author Administrator
 *
 */
public class StockRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 入库单号
	 */
	private String recordNo;
	/**
	 * 入库单项
	 */
	private List<StockRecordItem> items;
	/**
	 * 门店id
	 */
	private int shopId;
	/**
	 * 创建人id
	 */
	private int createUserId;
	/**
	 * 供应商
	 */
	private int supplierId;
	/**
	 * 总金额(分)
	 */
	private int totalAmount;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 备注
	 */
	private String remark;

	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	public List<StockRecordItem> getItems() {
		return items;
	}

	public void setItems(List<StockRecordItem> items) {
		this.items = items;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getCreateTimeAsString() {
		return createTime != null ? DateTimes.fullDayStringOf(createTime) : null;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
