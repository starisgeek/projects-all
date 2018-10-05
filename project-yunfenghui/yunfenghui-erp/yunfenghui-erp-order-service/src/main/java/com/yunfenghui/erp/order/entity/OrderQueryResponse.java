package com.yunfenghui.erp.order.entity;

/**
 * 订单查询响应
 * 
 * @author Administrator
 *
 */
public class OrderQueryResponse extends BaseResponse {
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
	 * 第三方返回的交易状态
	 */
	private String tradeStatus;

	/**
	 * 支付状态
	 */
	private int payStatus;

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

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public int getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(int payStatus) {
		this.payStatus = payStatus;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {
		private final OrderQueryResponse response;

		private Builder() {
			response = new OrderQueryResponse();
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

		public Builder tradeStatus(String tradeStatus) {
			response.setTradeStatus(tradeStatus);
			return this;
		}

		public Builder payStatus(int payStatus) {
			response.setPayStatus(payStatus);
			return this;
		}

		public OrderQueryResponse build() {
			return response;
		}
	}
}
