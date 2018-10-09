package com.star.im.entity;

import com.star.im.util.Commands;

/**
 * 登录请求类
 * 
 * @author Administrator
 *
 */
public class LoginRequest extends Packet {
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public byte getCommand() {
		return Commands.LOGIN_REQUEST;
	}

}
