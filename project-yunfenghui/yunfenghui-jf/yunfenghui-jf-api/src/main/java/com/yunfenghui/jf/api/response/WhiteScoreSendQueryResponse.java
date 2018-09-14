package com.yunfenghui.jf.api.response;

public class WhiteScoreSendQueryResponse {
	private String tradeNo;
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
	private long createTime;
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

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
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

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
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
		private final WhiteScoreSendQueryResponse response;

		private Builder() {
			response = new WhiteScoreSendQueryResponse();
		}

		public Builder tradeNo(String tradeNo) {
			response.setTradeNo(tradeNo);
			return this;
		}

		public Builder memberId(int memberId) {
			response.setMemberId(memberId);
			return this;
		}

		public Builder partnerId(int partnerId) {
			response.setPartnerId(partnerId);
			return this;
		}

		public Builder outTradeNo(String outTradeNo) {
			response.setOutTradeNo(outTradeNo);
			return this;
		}

		public Builder sendScores(int sendScores) {
			response.setSendScores(sendScores);
			return this;
		}

		public Builder notifyUrl(String notifyUrl) {
			response.setNotifyUrl(notifyUrl);
			return this;
		}

		public Builder createTime(long createTime) {
			response.setCreateTime(createTime);
			return this;
		}

		public Builder status(int status) {
			response.setStatus(status);
			return this;
		}

		public Builder dealType(int dealType) {
			response.setDealType(dealType);
			return this;
		}

		public WhiteScoreSendQueryResponse build() {
			return response;
		}
	}
}
