package com.seasaw.api.model;

public class APIResult {
	private String code;
	private String msg;
	private Object data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {
		private final APIResult result;

		private Builder() {
			this.result = new APIResult();
		}

		public Builder code(String code) {
			this.result.setCode(code);
			return this;
		}

		public Builder msg(String msg) {
			this.result.setMsg(msg);
			return this;
		}

		public Builder data(Object data) {
			this.result.setData(data);
			return this;
		}

		public APIResult build() {
			return result;
		}
	}
}
