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
	 * 执行日期(yyyyMMdd)
	 */
	private int transformDate;
	/**
	 * 执行状态
	 */
	private int status = STATUS_PENDING;

	/**
	 * 待执行
	 */
	public static final int STATUS_PENDING = 1;
	/**
	 * 执行完成
	 */
	public static final int STATUS_COMPLETED = 2;

	public int getTransformDate() {
		return transformDate;
	}

	public void setTransformDate(int transformDate) {
		this.transformDate = transformDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
