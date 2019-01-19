package com.seasaw.api.util;

public class APIException extends Exception {
	private static final long serialVersionUID = 1L;

	private final String errorCode;

	private final Object[] args;

	public APIException(String errorCode) {
		this(errorCode, null);
	}

	public APIException(String errorCode, String message) {
		this(errorCode, message, null);
	}

	public APIException(String errorCode, String message, Throwable cause) {
		this(errorCode, message, cause, null);
	}

	public APIException(String errorCode, String message, Throwable cause, Object[] args) {
		super(message, cause);
		this.errorCode = errorCode;
		this.args = args;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Object[] getArgs() {
		return args;
	}

}
