package com.yunfenghui.erp.stock.service;

import java.util.Date;
import java.util.List;

import com.yunfenghui.common.DateTimes;
import com.yunfenghui.common.KeyValue;
import com.yunfenghui.common.page.Page;
import com.yunfenghui.common.page.PageResult;
import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.stock.model.StockRecord;
import com.yunfenghui.erp.stock.model.StockRecordItem;

/**
 * 库存服务接口
 * 
 * @author Administrator
 *
 */
public interface StockService {
	String ID = "stockService";

	/**
	 * 创建入库单
	 * 
	 * @param record
	 * @throws ERPException
	 */
	void createStockRecord(StockRecord record);

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
	 * 根据订单冻结库存数量。用于创建订单时调用。
	 * 增加冻结库存数量。
	 * 
	 * @param orderNo
	 * @param goodsIdAndFreezeQuantityList
	 * @throws ERPException
	 *             订单号已存在。商品不存在。
	 */
	void freezeStock(String orderNo, List<KeyValue<Integer, Integer>> goodsIdAndFreezeQuantityList)
			throws ERPException;

	/**
	 * 解冻库存数量。用于创建订单失败时调用。
	 * 扣减库存冻结数量，删除冻结记录。
	 * 
	 * @param freezeRecordNo
	 */
	void unfreezeStock(String orderNo);

	/**
	 * 根据订单号扣减库存数量。用于订单支付完成时调用。
	 * 扣减库存冻结数量，删除冻结记录及明细，扣减库存数量，添加库存变动明细。
	 * 
	 * @param orderNo
	 */
	void decreaseStock(String orderNo);

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
