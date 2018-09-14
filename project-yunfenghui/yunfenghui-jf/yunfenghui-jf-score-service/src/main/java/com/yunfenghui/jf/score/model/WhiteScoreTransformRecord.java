package com.yunfenghui.jf.score.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 积分转换记录(适用于会员/商户白积分转换红积分，红积分转换余额)
 * 
 * @author Administrator
 *
 */
public class WhiteScoreTransformRecord implements Serializable {
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
