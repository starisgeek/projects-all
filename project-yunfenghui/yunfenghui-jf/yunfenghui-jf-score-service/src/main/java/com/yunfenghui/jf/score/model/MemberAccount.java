package com.yunfenghui.jf.score.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员账号
 * 
 * @author Administrator
 *
 */
public class MemberAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 会员id
	 */
	private int memberId;
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

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
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
