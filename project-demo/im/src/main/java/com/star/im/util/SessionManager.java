package com.star.im.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.star.im.entity.Session;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

public class SessionManager {
	private static final ConcurrentMap<String, Channel> userIdAndChannelMap = new ConcurrentHashMap<>();
	private static final ConcurrentMap<String, ChannelGroup> groupIdAndChannelGroupMap = new ConcurrentHashMap<>();

	public static void bindSession(Session session, Channel channel) {
		userIdAndChannelMap.putIfAbsent(session.getUserId(), channel);
		channel.attr(Session.ATTRIBUTE_KEY).setIfAbsent(session);
	}

	public static Session unbindSession(Channel channel) {
		if (hasLogin(channel)) {
			Session session = getSession(channel);
			userIdAndChannelMap.remove(session.getUserId());
			channel.attr(Session.ATTRIBUTE_KEY).set(null);
			return session;
		} else {
			return null;
		}
	}

	public static Channel getChannel(String userId) {
		return userIdAndChannelMap.get(userId);
	}

	public static Session getSession(Channel channel) {
		return channel.attr(Session.ATTRIBUTE_KEY).get();
	}

	public static boolean hasLogin(Channel channel) {
		return channel.hasAttr(Session.ATTRIBUTE_KEY);
	}

	public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
		groupIdAndChannelGroupMap.putIfAbsent(groupId, channelGroup);
	}

	public static ChannelGroup getChannelGroup(String groupId) {
		return groupIdAndChannelGroupMap.get(groupId);
	}
}
