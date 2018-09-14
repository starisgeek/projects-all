package com.yunfenghui.jf.score.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户账户
 * 
 * @author Administrator
 *
 */
public class PartnerAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 商户id
	 */
	private int partnerId;
	/**
	 * 库存积分
	 */
	private int stockScores;
	/**
	 * 白积分余额
	 */
	private int whiteScores;
	/**
	 * 红积分余额
	 */
	private int redScores;
	/**
	 * 消费余额
	 */
	private int balance;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public int getStockScores() {
		return stockScores;
	}

	public void setStockScores(int stockScores) {
		this.stockScores = stockScores;
	}

	public int getWhiteScores() {
		return whiteScores;
	}

	public void setWhiteScores(int whiteScores) {
		this.whiteScores = whiteScores;
	}

	public int getRedScores() {
		return redScores;
	}

	public void setRedScores(int redScores) {
		this.redScores = redScores;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
