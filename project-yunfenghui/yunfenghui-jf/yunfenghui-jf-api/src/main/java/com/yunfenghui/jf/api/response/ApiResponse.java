package com.yunfenghui.jf.api.response;

public class ApiResponse {
	private final String code;
	private Object content;

	public static final ApiResponse SYSTEM_ERROR = new ApiResponse("", null);

	public ApiResponse(String code) {
		this.code = code;
	}

	public ApiResponse(String code, Object content) {
		this.code = code;
		this.content = content;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public String getCode() {
		return code;
	}

	public static ApiResponse success(Object content) {
		return new ApiResponse(null, content);
	}

	public static ApiResponse fail(String code) {
		return new ApiResponse(code);
	}
}
