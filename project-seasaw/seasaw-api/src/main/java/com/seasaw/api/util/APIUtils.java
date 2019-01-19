package com.seasaw.api.util;

import java.io.IOException;

import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.seasaw.api.model.APIResult;

public class APIUtils {
	private static Logger logger = LoggerFactory.getLogger(APIUtils.class);

	public static void writeResult(ServletResponse response, APIResult result) {
		try {
			response.getWriter().write(JSON.toJSONString(result));
		} catch (IOException e) {
			logger.error("写ApiResult失败", e);
		}
	}
}
