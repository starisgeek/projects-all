package com.yunfenghui.erp.order.entity;

/**
 * 订单支付响应。适配所有的第三方支付结果
 * 
 * @author Administrator
 *
 */
public class OrderPayResponse extends BaseResponse {
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
	/**
	 * 是否失败
	 */
	private boolean isFail;

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

	public boolean isFail() {
		return isFail;
	}

	public void setFail(boolean isFail) {
		this.isFail = isFail;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {
		private final OrderPayResponse response;

		private Builder() {
			response = new OrderPayResponse();
		}

		public Builder tradeNo(String tradeNo) {
			response.setTradeNo(tradeNo);
			return this;
		}

		public Builder outTradeNo(String outTradeNo) {
			response.setOutTradeNo(outTradeNo);
			return this;
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

		public Builder success(boolean success) {
			response.setSuccess(success);
			return this;
		}

		public Builder fail(boolean fail) {
			response.setFail(fail);
			return this;
		}

		public OrderPayResponse build() {
			return response;
		}
	}
}
