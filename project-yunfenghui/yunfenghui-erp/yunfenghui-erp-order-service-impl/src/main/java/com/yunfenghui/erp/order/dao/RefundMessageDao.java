package com.yunfenghui.erp.order.dao;

import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.order.model.RefundMessage;

@Repository
public interface RefundMessageDao {
	/**
	 * 新增RefundMessage
	 * 
	 * @param message
	 */
	void insertRefundMessage(RefundMessage message);
}
