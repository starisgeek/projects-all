package com.yunfenghui.erp.stock.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageRowBounds;
import com.yunfenghui.erp.stock.model.StockRecord;
import com.yunfenghui.erp.stock.service.StockService.StockRecordQuery;

@Repository
public interface StockRecordDao {
	/**
	 * 新增StockRecord
	 * 
	 * @param record
	 */
	void insertStockRecord(StockRecord record);

	/**
	 * 根据单号查询入库单
	 * 
	 * @param recordNo
	 * @return
	 */
	StockRecord queryStockRecordByNo(@Param("recordNo") String recordNo);

	/**
	 * 根据条件分页查询入库单
	 * 
	 * @param query
	 * @param rowBounds
	 * @return
	 */
	List<StockRecord> queryStockRecordsBy(@Param("query") StockRecordQuery query,
			PageRowBounds rowBounds);
}
