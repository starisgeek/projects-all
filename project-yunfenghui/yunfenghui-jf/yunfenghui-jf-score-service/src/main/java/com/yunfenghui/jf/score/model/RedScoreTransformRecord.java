package com.yunfenghui.jf.score.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 红积分转换余额记录
 * 
 * @author Administrator
 *
 */
public class RedScoreTransformRecord implements Serializable {
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
	 * 转换积分
	 */
	private int transformScores;

	/**
	 * 增加的白积分
	 */
	private int increasedWhiteScores;

	/**
	 * 增加的余额
	 */
	private int increasedBalance;

	/**
	 * 费率(百分之)
	 */
	private int feeRatio;

	/**
	 * 创建时间
	 */
	private Date createTime;

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

	public int getTransformScores() {
		return transformScores;
	}

	public void setTransformScores(int transformScores) {
		this.transformScores = transformScores;
	}

	public int getIncreasedWhiteScores() {
		return increasedWhiteScores;
	}

	public void setIncreasedWhiteScores(int increasedWhiteScores) {
		this.increasedWhiteScores = increasedWhiteScores;
	}

	public int getIncreasedBalance() {
		return increasedBalance;
	}

	public void setIncreasedBalance(int increasedBalance) {
		this.increasedBalance = increasedBalance;
	}

	public int getFeeRatio() {
		return feeRatio;
	}

	public void setFeeRatio(int feeRatio) {
		this.feeRatio = feeRatio;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
