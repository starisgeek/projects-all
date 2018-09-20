package com.yunfenghui.jf.score.model;

import java.io.Serializable;

/**
 * 白积分转换Job
 * 
 * @author Administrator
 *
 */
public class WhiteScoreTransformJob implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 执行日期20180920
	 */
	private int executeDate;
	/**
	 * 执行状态
	 */
	private int status;

	/**
	 * 待执行
	 */
	public static final int STATUS_PENDING = 1;
	/**
	 * 执行完成
	 */
	public static final int STATUS_COMPLETED = 2;

	public int getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(int executeDate) {
		this.executeDate = executeDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
