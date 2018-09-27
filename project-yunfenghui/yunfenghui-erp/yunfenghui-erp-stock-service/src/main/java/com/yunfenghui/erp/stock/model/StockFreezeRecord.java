package com.yunfenghui.erp.stock.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * 库存冻结记录
 * 
 * @author Administrator
 *
 */
public class StockFreezeRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 创建时间
	 */
	private Date createDate;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
