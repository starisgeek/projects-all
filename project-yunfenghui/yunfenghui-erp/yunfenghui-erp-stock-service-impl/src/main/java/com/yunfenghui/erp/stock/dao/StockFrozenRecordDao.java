package com.yunfenghui.erp.stock.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.stock.model.StockFrozenRecord;

@Repository
public interface StockFrozenRecordDao {
	/**
	 * 新增StockFrozenRecord
	 * 
	 * @param frozenRecord
	 */
	void insertFrozenRecord(StockFrozenRecord frozenRecord);

	/**
	 * 根据订单号删除StockFrozenRecord
	 * 
	 * @param orderNo
	 * @return
	 */
	int deleteFrozenRecord(@Param("orderNo") String orderNo);
}
