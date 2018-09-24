package com.yunfenghui.erp.user.util;

public interface UserMessageCode {
	/**
	 * 用户手机号已存在
	 */
	String USER_LOGIN_PHONE_EXISTS = "erp.user.service.login.phone.exists";

	/**
	 * 门店超级管理员已存在
	 */
	String USER_SHOP_SUPER_EXISTS = "erp.user.service.shop.super.exists";

	/**
	 * 登录手机或密码错误
	 */
	String USER_LOGIN_PHONE_OR_PASSWORD_IS_WRONG = "erp.user.service.login.phone.or.password.is.wrong";
}
