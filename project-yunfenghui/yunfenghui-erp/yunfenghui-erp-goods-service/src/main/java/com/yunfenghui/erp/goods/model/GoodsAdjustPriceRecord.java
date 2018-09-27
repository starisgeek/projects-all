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
	/**
	 * 调价原因
	 */
	private String reason;

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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {
		private final GoodsAdjustPriceRecord record;

		private Builder() {
			this.record = new GoodsAdjustPriceRecord();
		}

		public Builder recordNo(String recordNo) {
			this.record.setRecordNo(recordNo);
			return this;
		}

		public Builder shopId(int shopId) {
			this.record.setShopId(shopId);
			return this;
		}

		public Builder goodsId(int goodsId) {
			this.record.setGoods(new Goods(goodsId));
			return this;
		}

		public Builder oldPrice(int oldPrice) {
			this.record.setOldPrice(oldPrice);
			return this;
		}

		public Builder newPrice(int newPrice) {
			this.record.setNewPrice(newPrice);
			return this;
		}

		public Builder createUserId(int createUserId) {
			this.record.setCreateUserId(createUserId);
			return this;
		}

		public Builder createTime(Date createTime) {
			this.record.setCreateTime(createTime);
			return this;
		}

		public Builder reason(String reason) {
			this.record.setReason(reason);
			return this;
		}

		public GoodsAdjustPriceRecord build() {
			return record;
		}
	}
}
