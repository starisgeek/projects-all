package com.yunfenghui.erp.common;

import java.util.Random;

public abstract class Commons {
	public static final int YES = 1;
	public static final int NO = 0;

	public static final int PLATFORM_SHOPID = 0;

	public static final String CATEGORY_SHOP = "shop";
	public static final String CATEGORY_PLATFORM = "platform";

	public static final String URI_SEPARATOR = "/";
	public static final String URI_SUFFIX = ".do";

	public static final String UTF8 = "UTF-8";

	/**
	 * 生成指定长度的随机数字符串
	 * 
	 * @param len
	 * @return
	 */
	public static String randomNumbers(int len) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
}
