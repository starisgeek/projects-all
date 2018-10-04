package com.star.im.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationConfigurer {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfigurer.class);
	private static final String CONFIG_LOCATION = "application.properties";

	public static final String KEY_IM_SERVER_ADDRESS = "im.server.address";
	public static final String KEY_IM_SERVER_PORT = "im.server.port";

	private static final Map<String, String> configs;

	static {
		Properties props = new Properties();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				ClassLoader.getSystemResourceAsStream(CONFIG_LOCATION), Charset.forName("UTF-8")));
		try {
			props.load(reader);
			Map<String, String> temp = new HashMap<>(props.size());
			for (Entry<Object, Object> entry : props.entrySet()) {
				temp.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
			}
			configs = Collections.unmodifiableMap(temp);
		} catch (IOException e) {
			logger.error("Failed to read config file:{}", CONFIG_LOCATION, e);
			throw new ExceptionInInitializerError();
		}
	}

	public static String valueOf(String key) {
		return configs.get(key);
	}

	public static void main(String[] args) {
		System.out.println(valueOf("im.server.port"));
	}
}
