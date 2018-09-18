package com.yunfenghui.jf.common;

public class JFException extends Exception {
	private static final long serialVersionUID = 1L;

	private final String errorCode;

	private final Object[] args;

	public JFException(String errorCode) {
		this(errorCode, null);
	}

	public JFException(String errorCode, String message) {
		this(errorCode, message, null);
	}

	public JFException(String errorCode, String message, Throwable cause) {
		this(errorCode, message, cause, null);
	}

	public JFException(String errorCode, String message, Throwable cause, Object[] args) {
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
