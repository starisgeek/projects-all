package com.yunfenghui.erp.stock.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.stock.model.StockFrozenRecordItem;

@Repository
public interface StockFrozenRecordItemDao {
	/**
	 * 批量新增StockFrozenRecordItem
	 * 
	 * @param items
	 */
	void batchInsertFrozenRecordItems(@Param("items") List<StockFrozenRecordItem> items);

	/**
	 * 根据订单号删除StockFrozenRecordItem
	 * 
	 * @param orderNo
	 */
	void deleteFrozenRecordItems(@Param("orderNo") String orderNo);
}
