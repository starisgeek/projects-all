package com.yunfenghui.jf.score.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员白积分发放记录(1.从MQ中接收2.从API接收请求)
 * 
 * @author Administrator
 *
 */
public class WhiteScoreSendRecord implements Serializable {
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
	 * 外包交易号
	 */
	private String outTradeNo;
	/**
	 * 发放积分
	 */
	private int sendScores;

	/**
	 * 通知url
	 */
	private String notifyUrl;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	/**
	 * 状态
	 */
	private int status;

	/**
	 * 交易类型
	 */
	private int dealType;
	/**
	 * 错误码
	 */
	private String errorCode;

	/**
	 * 发放中
	 */
	public static final int STATUS_PENDING = 1;
	/**
	 * 发放成功
	 */
	public static final int STATUS_SEND_SUCCESS = 2;
	/**
	 * 发放失败
	 */
	public static final int STATUS_SEND_FAILED = 2;

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

	public int getSendScores() {
		return sendScores;
	}

	public void setSendScores(int sendScores) {
		this.sendScores = sendScores;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
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
		private final WhiteScoreSendRecord record;

		private Builder() {
			record = new WhiteScoreSendRecord();
		}

		public Builder recordNo(String recordNo) {
			record.setRecordNo(recordNo);
			return this;
		}

		public Builder memberId(int memberId) {
			record.setMemberId(memberId);
			return this;
		}

		public Builder partnerId(int partnerId) {
			record.setPartnerId(partnerId);
			return this;
		}

		public Builder outTradeNo(String outTradeNo) {
			record.setOutTradeNo(outTradeNo);
			return this;
		}

		public Builder sendScores(int sendScores) {
			record.setSendScores(sendScores);
			return this;
		}

		public Builder notifyUrl(String notifyUrl) {
			record.setNotifyUrl(notifyUrl);
			return this;
		}

		public Builder createTime(Date createTime) {
			record.setCreateTime(createTime);
			return this;
		}

		public Builder status(int status) {
			record.setStatus(status);
			return this;
		}

		public Builder dealType(int dealType) {
			record.setDealType(dealType);
			return this;
		}

		public WhiteScoreSendRecord build() {
			return record;
		}
	}

	@Override
	public String toString() {
		return "WhiteScoreSendRecord [recordNo=" + recordNo + ", memberId=" + memberId
				+ ", partnerId=" + partnerId + ", outTradeNo=" + outTradeNo + ", sendScores="
				+ sendScores + ", notifyUrl=" + notifyUrl + ", createTime=" + createTime
				+ ", modifyTime=" + modifyTime + ", status=" + status + ", errorCode=" + errorCode
				+ "]";
	}

}
