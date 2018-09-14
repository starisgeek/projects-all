package com.yunfenghui.jf.score.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 发放白积分完成后通知
 * 
 * @author Administrator
 *
 */
public class WhiteScoreSendRecordNotify implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 记录号
	 */
	private String recordNo;
	/**
	 * 会员id
	 */
	private int memberId;
	/**
	 * 商户id
	 */
	private int partnerId;
	/**
	 * 外部交易号
	 */
	private String outTradeNo;
	/**
	 * 发放积分
	 */
	private int sendScores;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 状态
	 */
	private int status;
	/**
	 * 错误码
	 */
	private String errorCode;

	public String getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getSendScores() {
		return sendScores;
	}

	public void setSendScores(int sendScores) {
		this.sendScores = sendScores;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {
		private final WhiteScoreSendRecordNotify notify;

		private Builder() {
			this.notify = new WhiteScoreSendRecordNotify();
		}

		public Builder recordNo(String recordNo) {
			notify.setRecordNo(recordNo);
			return this;
		}

		public Builder memberId(int memberId) {
			notify.setMemberId(memberId);
			return this;
		}

		public Builder partnerId(int partnerId) {
			notify.setPartnerId(partnerId);
			return this;
		}

		public Builder outTradeNo(String outTradeNo) {
			notify.setOutTradeNo(outTradeNo);
			return this;
		}

		public Builder sendScores(int sendScores) {
			notify.setSendScores(sendScores);
			return this;
		}

		public Builder status(int status) {
			notify.setStatus(status);
			return this;
		}

		public Builder errorCode(String errorCode) {
			notify.setErrorCode(errorCode);
			return this;
		}

		public Builder createTime(Date createTime) {
			notify.setCreateTime(createTime);
			return this;
		}

		public WhiteScoreSendRecordNotify build() {
			return notify;
		}
	}
}
