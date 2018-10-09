package com.yunfenghui.erp.order.entity;

/**
 * 退款响应
 * 
 * @author Administrator
 *
 */
public class OrderRefundResponse extends BaseResponse {
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
	 * 是否成功
	 */
	private boolean isSuccess;

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

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {
		private final OrderRefundResponse response;

		private Builder() {
			response = new OrderRefundResponse();
		}

		public Builder code(String code) {
			response.setCode(code);
			return this;
		}

		public Builder msg(String msg) {
			response.setMsg(msg);
			return this;
		}

		public Builder subCode(String subCode) {
			response.setSubCode(subCode);
			return this;
		}

		public Builder subMsg(String subMsg) {
			response.setSubMsg(subMsg);
			return this;
		}

		public Builder tradeNo(String tradeNo) {
			response.setTradeNo(tradeNo);
			return this;
		}

		public Builder outTradeNo(String outTradeNo) {
			response.setOutTradeNo(outTradeNo);
			return this;
		}

		public Builder isSuccess(boolean isSuccess) {
			response.setSuccess(isSuccess);
			return this;
		}

		public OrderRefundResponse build() {
			return response;
		}
	}
}
