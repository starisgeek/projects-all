package com.star.im.entity;

import com.star.im.util.Commands;

/**
 * 登录响应类
 * 
 * @author Administrator
 *
 */
public class LoginResponse extends Packet {
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 是否登录成功
	 */
	private boolean isSuccess;
	/**
	 * 登录成功或失败信息
	 */
	private String message;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	@Override
	public byte getCommand() {
		return Commands.LOGIN_RESPONSE;
	}

}
