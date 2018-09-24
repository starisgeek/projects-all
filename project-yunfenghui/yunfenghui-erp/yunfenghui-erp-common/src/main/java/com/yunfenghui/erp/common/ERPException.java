package com.yunfenghui.erp.common;

public class ERPException extends Exception {
	private static final long serialVersionUID = 1L;

	private final String errorCode;

	private final Object[] args;

	public ERPException(String errorCode) {
		this(errorCode, null);
	}

	public ERPException(String errorCode, String message) {
		this(errorCode, message, null);
	}

	public ERPException(String errorCode, String message, Throwable cause) {
		this(errorCode, message, cause, null);
	}

	public ERPException(String errorCode, String message, Throwable cause, Object[] args) {
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
