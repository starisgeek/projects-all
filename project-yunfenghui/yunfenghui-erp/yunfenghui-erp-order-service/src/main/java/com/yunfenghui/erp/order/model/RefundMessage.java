package com.yunfenghui.erp.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 退款成功消息
 * 
 * @author Administrator
 *
 */
public class RefundMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 退款记录号
	 */
	private String refundRecordNo;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public String getRefundRecordNo() {
		return refundRecordNo;
	}

	public void setRefundRecordNo(String refundRecordNo) {
		this.refundRecordNo = refundRecordNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
