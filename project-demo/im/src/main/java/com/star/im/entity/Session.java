package com.star.im.entity;

import io.netty.util.AttributeKey;

public class Session {
	private final String userId;
	private final String username;

	public static final AttributeKey<Session> ATTRIBUTE_KEY = AttributeKey.newInstance("session");

	public Session(String userId, String username) {
		this.userId = userId;
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

}
