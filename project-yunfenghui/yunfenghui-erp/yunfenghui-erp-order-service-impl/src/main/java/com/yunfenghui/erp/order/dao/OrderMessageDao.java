package com.yunfenghui.erp.order.dao;

import com.yunfenghui.erp.order.model.OrderMessage;

public interface OrderMessageDao {
	/**
	 * 新增OrderMessage
	 * 
	 * @param message
	 */
	void insertOrderMessage(OrderMessage message);
}
