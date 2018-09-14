package com.yunfenghui.jf.api.response;

public class WhiteScoreSendResponse {
	private final String tradeNo;
	private final String outTradeNo;

	public WhiteScoreSendResponse(String tradeNo, String outTradeNo) {
		this.tradeNo = tradeNo;
		this.outTradeNo = outTradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

}
