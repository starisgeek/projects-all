package com.yunfenghui.erp.goods.service;

import java.io.Serializable;

import com.yunfenghui.common.page.Page;
import com.yunfenghui.common.page.PageResult;
import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.goods.model.Goods;

/**
 * GoodsService
 * 
 * @author Administrator
 *
 */
public interface GoodsService {
	String ID = "goodsService";

	/**
	 * 新建商品
	 *
	 * @param goods
	 * @throws ERPException
	 */
	void createGoods(Goods goods) throws ERPException;

	/**
	 * 新建并上架商品
	 *
	 * @param goods
	 * @throws ERPException
	 */
	void createAndPutAwayGoods(Goods goods) throws ERPException;

	/**
	 * 上架
	 *
	 * @param goodsId
	 * @throws ERPException
	 */
	void putAwayGoods(int goodsId) throws ERPException;

	/**
	 * 下架
	 *
	 * @param goodsId
	 * @throws ERPException
	 */
	void soldOutGoods(int goodsId) throws ERPException;

	/**
	 * 调价
	 * 
	 * @param goodsId
	 * @param shopId
	 * @param newSalePrice
	 * @param createUserId
	 * @param reason
	 * @throws ERPException
	 */
	void adjustSalePrice(int goodsId, int shopId, int newSalePrice, int createUserId, String reason)
			throws ERPException;

	/**
	 * 根据id查询
	 *
	 * @param goodsId
	 * @return
	 */
	Goods getGoodsById(int goodsId);

	/**
	 * 根据条形码查询
	 *
	 * @param code
	 * @return
	 */
	Goods getGoodsByCode(String code);

	/**
	 * 分页查询商品列表
	 *
	 * @param query
	 *            查询条件
	 * @param page
	 *            分页参数
	 * @return 查询结果
	 */
	PageResult<Goods> getGoodsBy(GoodsQuery query, Page page);

	/**
	 * 商品查询条件
	 * 
	 * @author Administrator
	 *
	 */
	class GoodsQuery implements Serializable {
		private static final long serialVersionUID = 1L;
		private Integer shopId;
		private String keyword;

		private Integer status;

		public Integer getShopId() {
			return shopId;
		}

		public void setShopId(Integer shopId) {
			this.shopId = shopId;
		}

		public String getKeyword() {
			return keyword;
		}

		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}
	}

}
