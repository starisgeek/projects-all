package com.yunfenghui.erp.order.dao;

import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.order.model.RefundRecord;

@Repository
public interface RefundRecordDao {
	/**
	 * 新增RefundRecord
	 * 
	 * @param record
	 */
	void insertRefundRecord(RefundRecord record);
}
