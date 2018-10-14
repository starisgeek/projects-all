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

	/**
	 * 建群请求
	 */
	byte CREATE_GROUP_REQUEST = 5;

	/**
	 * 建群响应
	 */
	byte CREATE_GROUP_RESPONSE = 6;

	/**
	 * 加入群请求
	 */
	byte JOIN_GROUP_REQUEST = 7;

	/**
	 * 加入群响应
	 */
	byte JOIN_GROUP_RESPONSE = 8;

	/**
	 * 退群请求
	 */
	byte QUIT_GROUP_REQUEST = 9;

	/**
	 * 退群响应
	 */
	byte QUIT_GROUP_RESPONSE = 10;

	/**
	 * 获取群成员列表请求
	 */
	byte LIST_GROUP_MEMBERS_REQUEST = 11;

	/**
	 * 获取群成员列表响应
	 */
	byte LIST_GROUP_MEMBERS_RESPONSE = 12;
}
