package com.yunfenghui.erp.stock.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 库存变动明细
 * 
 * @author Administrator
 *
 */
public class StockChangeRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 流水号
	 */
	private String serialNumber;
	/**
	 * 商品id
	 */
	private int goodsId;
	/**
	 * 门店id
	 */
	private int shopId;
	/**
	 * 变动数量
	 */
	private int changeAmount;
	/**
	 * 交易类型
	 */
	private int dealType;
	/**
	 * 源记录
	 */
	private String originalRecordNo;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(int changeAmount) {
		this.changeAmount = changeAmount;
	}

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
	}

	public String getOriginalRecordNo() {
		return originalRecordNo;
	}

	public void setOriginalRecordNo(String originalRecordNo) {
		this.originalRecordNo = originalRecordNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
