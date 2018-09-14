package com.yunfenghui.jf.api.request;

/**
 * 白积分发放请求
 * 
 * @author Administrator
 *
 */
public class WhiteScoreSendRequest {
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
	 * 交易类型
	 */
	private int dealType;

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

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
	}

}
