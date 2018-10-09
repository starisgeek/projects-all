package com.star.im.util;

public interface Commands {
	/**
	 * 登录请求
	 */
	byte LOGIN_REQUEST = 1;
	/**
	 * 登录响应
	 */
	byte LOGIN_RESPONSE = 2;
	/**
	 * 消息请求
	 */
	byte MESSAGE_REQUEST = 3;
	/**
	 * 消息响应
	 */
	byte MESSAGE_RESPONSE = 4;
}
