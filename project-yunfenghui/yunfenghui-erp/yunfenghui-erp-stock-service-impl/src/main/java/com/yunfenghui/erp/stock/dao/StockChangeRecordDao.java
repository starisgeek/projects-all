package com.yunfenghui.erp.stock.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.stock.model.StockChangeRecord;

@Repository
public interface StockChangeRecordDao {
	/**
	 * 批量新增StockChangeRecord
	 * 
	 * @param records
	 */
	void batchInsertChangeRecords(@Param("records") List<StockChangeRecord> records);
}
