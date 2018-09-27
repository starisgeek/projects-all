package com.yunfenghui.erp.order.entity;

/**
 * 订单撤销响应
 * 
 * @author Administrator
 *
 */
public class OrderRevokeResponse extends BaseResponse {
	private static final long serialVersionUID = 1L;
	/**
	 * 商户交易号
	 */
	private String tradeNo;
	/**
	 * 第三方交易号
	 */
	private String outTradeNo;
	/**
	 * 是否需要重试
	 */
	private boolean requireRetry;

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public boolean isRequireRetry() {
		return requireRetry;
	}

	public void setRequireRetry(boolean requireRetry) {
		this.requireRetry = requireRetry;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {
		private final OrderRevokeResponse response;

		private Builder() {
			this.response = new OrderRevokeResponse();
		}

		public Builder code(String code) {
			this.response.setCode(code);
			return this;
		}

		public Builder msg(String msg) {
			this.response.setMsg(msg);
			return this;
		}

		public Builder subCode(String subCode) {
			this.response.setSubCode(subCode);
			return this;
		}

		public Builder subMsg(String subMsg) {
			this.response.setSubMsg(subMsg);
			return this;
		}

		public Builder tradeNo(String tradeNo) {
			this.response.setTradeNo(tradeNo);
			return this;
		}

		public Builder outTradeNo(String outTradeNo) {
			this.response.setOutTradeNo(outTradeNo);
			return this;
		}

		public Builder requireRetry(boolean requireRetry) {
			this.response.setRequireRetry(requireRetry);
			return this;
		}

		public OrderRevokeResponse build() {
			return this.response;
		}
	}
}
