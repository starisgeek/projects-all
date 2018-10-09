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

	/**
	 * 增加退款金额和数量
	 * 
	 * @param orderNo
	 * @param goodsId
	 * @param refundQuantityIncrement
	 * @param refundAmountIncrement
	 * @return
	 */
	int increaseRefundQuantityAndRefundAmount(@Param("orderNo") String orderNo,
			@Param("goodsId") int goodsId,
			@Param("refundQuantityIncrement") int refundQuantityIncrement,
			@Param("refundAmountIncrement") int refundAmountIncrement);

	/**
	 * 减少退款金额和数量
	 * 
	 * @param orderNo
	 * @param goodsId
	 * @param refundQuantityDecrement
	 * @param refundAmountDecrement
	 * @return
	 */
	int decreaseRefundQuantityAndRefundAmount(@Param("orderNo") String orderNo,
			@Param("goodsId") int goodsId,
			@Param("refundQuantityDecrement") int refundQuantityDecrement,
			@Param("refundAmountDecrement") int refundAmountDecrement);
}
