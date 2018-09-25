package com.yunfenghui.erp.goods.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yunfenghui.common.DateTimes;
import com.yunfenghui.common.page.Page;
import com.yunfenghui.common.page.PageResult;
import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.goods.model.Goods;
import com.yunfenghui.erp.goods.model.StockRecord;
import com.yunfenghui.erp.goods.model.StockRecordItem;

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

	/**
	 * Stock record method
	 */

	/**
	 * 创建入库单
	 * 
	 * @param record
	 * @throws ERPException
	 */
	void createStockRecord(StockRecord record) throws ERPException;

	/**
	 * 根据no查询入库单
	 * 
	 * @param recordNo
	 * @return
	 */
	StockRecord getStockRecordByNo(String recordNo);

	/**
	 * 根据入库单编号查询入库单明细
	 * 
	 * @param recordNo
	 * @return
	 */
	List<StockRecordItem> getStockRecordItems(String recordNo);

	/**
	 * 生成入库单号
	 * 
	 * @param shopId
	 * @return
	 */
	String generateRecordNo(int shopId);

	/**
	 * 根据条件查询供应商
	 * 
	 * @param shopId
	 * @return
	 */
	PageResult<StockRecord> getStockRecordsBy(StockRecordQuery query, Page page);

	/**
	 * 入库单查询条件
	 * 
	 * @author Administrator
	 *
	 */
	class StockRecordQuery {
		private Integer shopId;
		private String recordNo;
		private Date startTime;
		private Date endTime;

		public Integer getShopId() {
			return shopId;
		}

		public void setShopId(Integer shopId) {
			this.shopId = shopId;
		}

		public String getRecordNo() {
			return recordNo;
		}

		public void setRecordNo(String recordNo) {
			this.recordNo = recordNo;
		}

		public Date getStartTime() {
			return startTime;
		}

		public Date getEarliestStartTime() {
			return startTime != null ? DateTimes.earliestOf(startTime) : null;
		}

		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}

		public Date getEndTime() {
			return endTime;
		}

		public Date getLatestEndTime() {
			return endTime != null ? DateTimes.latestOf(endTime) : null;
		}

		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}

	}
}
