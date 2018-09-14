package com.yunfenghui.jf.score.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户变动流水(适用于会员/商户白积分，红积分，余额变动流水)
 * 
 * @author Administrator
 *
 */
public class AccountChangeRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 流水号(唯一)
	 */
	private String serialNumber;
	/**
	 * 账户id
	 */
	private int accountId;
	/**
	 * 变动数量(增加为正，减少为负)
	 */
	private int changeAmount;
	/**
	 * 交易类型
	 */
	private int dealType;
	/**
	 * 源记录
	 */
	private String originalRecordNo;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 变动类型：会员白积分
	 */
	public static final int CHANGE_TYPE_MEMBER_WHITE_SCORE = 1;
	/**
	 * 变动类型：会员红积分
	 */
	public static final int CHANGE_TYPE_MEMBER_RED_SCORE = 2;
	/**
	 * 变动类型：会员余额
	 */
	public static final int CHANGE_TYPE_MEMBER_BALANCE = 3;
	/**
	 * 变动类型：商户白积分
	 */
	public static final int CHANGE_TYPE_PARTNER_WHITE_SCORE = 11;
	/**
	 * 变动类型：商户红积分
	 */
	public static final int CHANGE_TYPE_PARTNER_RED_SCORE = 12;
	/**
	 * 变动类型：商户余额
	 */
	public static final int CHANGE_TYPE_PARTNER_BALANCE = 13;

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(int changeAmount) {
		this.changeAmount = changeAmount;
	}

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
	}

	public String getOriginalRecordNo() {
		return originalRecordNo;
	}

	public void setOriginalRecordNo(String originalRecordNo) {
		this.originalRecordNo = originalRecordNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {
		private final AccountChangeRecord changeRecord;

		private Builder() {
			changeRecord = new AccountChangeRecord();
		}

		public Builder serialNumber(String serialNumber) {
			changeRecord.serialNumber = serialNumber;
			return this;
		}

		public Builder accountId(int accountId) {
			changeRecord.accountId = accountId;
			return this;
		}

		public Builder changeAmount(int changeAmount) {
			changeRecord.changeAmount = changeAmount;
			return this;
		}

		public Builder dealType(int dealType) {
			changeRecord.dealType = dealType;
			return this;
		}

		public Builder originalRecordNo(String originalRecordNo) {
			changeRecord.originalRecordNo = originalRecordNo;
			return this;
		}

		public Builder createTime(Date createTime) {
			changeRecord.createTime = createTime;
			return this;
		}

		public AccountChangeRecord build() {
			return changeRecord;
		}
	}
}
