package com.yunfenghui.erp.order.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.order.model.Order;

@Repository
public interface OrderDao {
	/**
	 * 新增订单
	 * 
	 * @param order
	 */
	void insertOrder(Order order);

	/**
	 * 根据订单号查询订单(不包括OrderItem)
	 * 
	 * @param orderNo
	 * @return
	 */
	Order queryOrderByNo(@Param("orderNo") String orderNo);

	/**
	 * 修改支付方式
	 * 
	 * @param orderNo
	 * @param payWay
	 * @param modifyTime
	 * @return
	 */
	int updateOrderPayWay(@Param("orderNo") String orderNo, @Param("payWay") int payWay,
			@Param("modifyTime") Date modifyTime);

	/**
	 * 修改订单状态、外部交易号
	 * 
	 * @param orderNo
	 * @param expectedStatus
	 * @param updatedStatus
	 * @param outTradeNo
	 * @param modifyTime
	 * @return
	 */
	int updateOrderStatusAndOutTradeNo(@Param("orderNo") String orderNo,
			@Param("expectedStatus") int expectedStatus, @Param("updatedStatus") int updatedStatus,
			@Param("outTradeNo") String outTradeNo, @Param("modifyTime") Date modifyTime);
}
