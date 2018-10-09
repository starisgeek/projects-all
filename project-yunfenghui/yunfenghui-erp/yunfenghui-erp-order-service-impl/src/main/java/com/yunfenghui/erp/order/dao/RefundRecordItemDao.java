package com.yunfenghui.erp.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.order.model.RefundRecordItem;

@Repository
public interface RefundRecordItemDao {
	/**
	 * 批量新增RefundRecordItem
	 * 
	 * @param items
	 */
	void batchInsertRefundRecordItems(@Param("items") List<RefundRecordItem> items);
}
