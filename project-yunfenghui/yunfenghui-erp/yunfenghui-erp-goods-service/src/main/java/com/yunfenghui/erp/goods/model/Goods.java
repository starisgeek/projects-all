package com.yunfenghui.erp.goods.model;

import java.io.Serializable;
import java.util.*;

import com.yunfenghui.common.KeyValue;

/**
 * 商品
 *
 * @author Administrator
 */
public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private int id;
    /**
     * 条形码(单店铺唯一)
     */
    private String barcode;
    /**
     * 名称(单店铺唯一)
     */
    private String name;
    /**
     * 所属门店
     */
    private int shopId;
    /**
     * 所属类别
     */
    private GoodsCategory category;
    /**
     * 品牌
     */
    private GoodsBrand brand;
    /**
     * 单位
     */
    private GoodsUnit unit;
    /**
     * 拼音码
     */
    private String pinyin;
    /**
     * 销售价格
     */
    private int salePrice;

    /**
     * 返还积分比例
     */
    private int scoreRatio;

    /**
     * 状态(1-新建，2-上架，3-下架)
     */
    private int status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;

    /**
     * 新建
     */
    public static final int STATUS_NEW = 0;
    /**
     * 上架
     */
    public static final int STATUS_PUT_AWAY = 1;
    /**
     * 下架
     */
    public static final int STATUS_SOLD_OUT = 2;
//	private static final Map<Integer, KeyValue<Integer, String>> STATUS_MAP = new TreeMap<>();

	/*static {
		STATUS_MAP.put(STATUS_NEW, new KeyValue<>(STATUS_NEW, "新建"));
		STATUS_MAP.put(STATUS_PUT_AWAY, new KeyValue<>(STATUS_PUT_AWAY, "已上架"));
		STATUS_MAP.put(STATUS_SOLD_OUT, new KeyValue<>(STATUS_SOLD_OUT, "已下架"));
	}*/

    private static final KeyValue<Integer, String>[] STATUSES;

    static {
        KeyValue<Integer, String>[] status = new KeyValue[3];
        status[0] = new KeyValue<>(STATUS_NEW, "新建");
        status[1] = new KeyValue<>(STATUS_PUT_AWAY, "已上架");
        status[2] = new KeyValue<>(STATUS_SOLD_OUT, "已下架");

        STATUSES = status;
    }

    public Goods() {
    }

    public Goods(int id) {
        this.id = id;
    }

    public static List<KeyValue<Integer, String>> getStatuses() {
        return Arrays.asList(STATUSES);
    }

    public String getStatusAsString() {
//		if (STATUS_MAP.containsKey(status)) {
//			return STATUS_MAP.get(status).getValue();
//		}
        if (status <= STATUSES.length - 1) {
            return STATUSES[status].getValue();
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getScoreRatio() {
        return scoreRatio;
    }

    public void setScoreRatio(int scoreRatio) {
        this.scoreRatio = scoreRatio;
    }

    public GoodsCategory getCategory() {
        return category;
    }

    public void setCategory(GoodsCategory category) {
        this.category = category;
    }

    public GoodsBrand getBrand() {
        return brand;
    }

    public void setBrand(GoodsBrand brand) {
        this.brand = brand;
    }

    public GoodsUnit getUnit() {
        return unit;
    }

    public void setUnit(GoodsUnit unit) {
        this.unit = unit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
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

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private final Goods goods;

        private Builder() {
            this.goods = new Goods();
        }

        public Builder barcode(String barcode) {
            this.goods.setBarcode(barcode);
            return this;
        }

        public Builder name(String name) {
            this.goods.setName(name);
            return this;
        }

        public Builder shopId(int shopId) {
            this.goods.setShopId(shopId);
            return this;
        }

        public Builder pinyin(String pinyin) {
            this.goods.setPinyin(pinyin);
            return this;
        }

        public Builder salePrice(int salePrice) {
            this.goods.setSalePrice(salePrice);
            return this;
        }

        public Builder scoreRatio(int scoreRatio) {
            this.goods.setScoreRatio(scoreRatio);
            return this;
        }

        public Builder categoryId(int categoryId) {
            this.goods.setCategory(new GoodsCategory(categoryId));
            return this;
        }

        public Builder brandId(int brandId) {
            this.goods.setBrand(new GoodsBrand(brandId));
            return this;
        }

        public Builder unitId(int unitId) {
            this.goods.setUnit(new GoodsUnit(unitId));
            return this;
        }

        public Builder status(int status) {
            this.goods.setStatus(status);
            return this;
        }

        public Builder createTime(Date createTime) {
            this.goods.setCreateTime(createTime);
            return this;
        }

        public Builder remark(String remark) {
            this.goods.setRemark(remark);
            return this;
        }

        public Goods build() {
            return this.goods;
        }
    }
}
