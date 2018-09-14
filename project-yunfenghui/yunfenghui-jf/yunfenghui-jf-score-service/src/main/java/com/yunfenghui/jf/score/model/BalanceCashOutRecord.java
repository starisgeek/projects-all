package com.yunfenghui.jf.score.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 余额提现记录(适用于会员/商户)
 * 
 * @author Administrator
 *
 */
public class BalanceCashOutRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 记录号
	 */
	private String recordNo;

	/**
	 * 账户id
	 */
	private int accountId;

	/**
	 * 提现金额
	 */
	private int cashOutAmount;

	/**
	 * 状态
	 */
	private int status;

	/**
	 * 提交时间
	 */
	private Date createDate;

	/**
	 * 处理时间
	 */
	private Date modifyDate;

	/**
	 * 处理人id
	 */
	private int handleUserId;

	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getCashOutAmount() {
		return cashOutAmount;
	}

	public void setCashOutAmount(int cashOutAmount) {
		this.cashOutAmount = cashOutAmount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getHandleUserId() {
		return handleUserId;
	}

	public void setHandleUserId(int handleUserId) {
		this.handleUserId = handleUserId;
	}

}
