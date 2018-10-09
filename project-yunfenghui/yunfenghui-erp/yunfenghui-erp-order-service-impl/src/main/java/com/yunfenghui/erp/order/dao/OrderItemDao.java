package com.yunfenghui.erp.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.order.model.OrderItem;

@Repository
public interface OrderItemDao {
	/**
	 * 批量新增OrderItem
	 * 
	 * @param items
	 */
	void batchInsertOrderItems(@Param("items") List<OrderItem> items);
}
