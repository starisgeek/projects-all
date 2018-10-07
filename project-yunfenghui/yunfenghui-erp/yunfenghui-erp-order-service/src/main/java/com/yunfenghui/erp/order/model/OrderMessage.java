package com.yunfenghui.erp.order.model;

import java.util.Date;

/**
 * 交易成功的订单消息
 * 
 * @author Administrator
 *
 */
public class OrderMessage {
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
