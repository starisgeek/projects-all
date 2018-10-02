package com.yunfenghui.erp.stock.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.stock.model.StockRecordItem;

@Repository
public interface StockRecordItemDao {
	/**
	 * 批量新增入库单明细
	 * 
	 * @param items
	 */
	void batchInsertStockRecordItems(@Param("items") List<StockRecordItem> items);

	/**
	 * 根据入库单编号查询入库单明细
	 * 
	 * @param recordNo
	 * @return
	 */
	List<StockRecordItem> queryStockRecordItemsBy(@Param("recordNo") String recordNo);
}
