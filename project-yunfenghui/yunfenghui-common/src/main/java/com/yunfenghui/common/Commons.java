package com.yunfenghui.common;

import java.util.Random;

public abstract class Commons {
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
