package com.star.im.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.star.im.entity.Session;

import io.netty.channel.Channel;

public class SessionManager {
	private static final ConcurrentMap<String, Channel> userIdAndChannelMap = new ConcurrentHashMap<>();

	public static void bindSession(Session session, Channel channel) {
		userIdAndChannelMap.putIfAbsent(session.getUserId(), channel);
		channel.attr(Attributes.SESSION).setIfAbsent(session);
	}

	public static void unbindSession(Channel channel) {
		if (hasLogin(channel)) {
			userIdAndChannelMap.remove(getSession(channel).getUserId());
			channel.attr(Attributes.SESSION).set(null);
		}
	}

	public static Channel getChannel(String userId) {
		return userIdAndChannelMap.get(userId);
	}

	public static Session getSession(Channel channel) {
		return channel.attr(Attributes.SESSION).get();
	}

	public static boolean hasLogin(Channel channel) {
		return channel.hasAttr(Attributes.SESSION);
	}
}
