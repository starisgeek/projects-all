package com.star.im.entity;

/**
 * 登录响应类
 * 
 * @author Administrator
 *
 */
public class LoginResponse {
	/**
	 * 是否登录成功
	 */
	private boolean isSuccess;
	/**
	 * 登录成功或失败信息
	 */
	private String message;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
