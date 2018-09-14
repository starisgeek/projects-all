package com.yunfenghui.jf.score.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户库存积分充值记录
 * 
 * @author Administrator
 *
 */
public class StockScoreRechargeRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 记录号
	 */
	private String recordNo;

	/**
	 * 商户id
	 */
	private int partnerId;

	/**
	 * 充值金额
	 */
	private int rechargeMoney;

	/**
	 * 增加的库存积分
	 */
	private int increaseStockScores;

	/**
	 * 增加的白积分
	 */
	private int increaseWhiteScores;

	/**
	 * 创建用户id
	 */
	private int createUserId;

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

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public int getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(int rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	public int getIncreaseStockScores() {
		return increaseStockScores;
	}

	public void setIncreaseStockScores(int increaseStockScores) {
		this.increaseStockScores = increaseStockScores;
	}

	public int getIncreaseWhiteScores() {
		return increaseWhiteScores;
	}

	public void setIncreaseWhiteScores(int increaseWhiteScores) {
		this.increaseWhiteScores = increaseWhiteScores;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
