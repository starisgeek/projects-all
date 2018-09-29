package com.yunfenghui.erp.goods.dao;

import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.goods.model.GoodsAdjustPriceRecord;

@Repository
public interface GoodsAdjustPriceRecordDao {
	/**
	 * 新增调价记录
	 * 
	 * @param record
	 */
	void insertAdjustPriceRecord(GoodsAdjustPriceRecord record);

}
