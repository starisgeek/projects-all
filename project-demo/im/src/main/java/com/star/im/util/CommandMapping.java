package com.star.im.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.star.im.entity.CreateGroupRequest;
import com.star.im.entity.CreateGroupResponse;
import com.star.im.entity.JoinGroupRequest;
import com.star.im.entity.JoinGroupResponse;
import com.star.im.entity.LoginRequest;
import com.star.im.entity.LoginResponse;
import com.star.im.entity.MessageRequest;
import com.star.im.entity.MessageResponse;
import com.star.im.entity.Packet;

public class CommandMapping {
	private static final Map<Byte, Class<? extends Packet>> mapping;

	static {
		Map<Byte, Class<? extends Packet>> map = new HashMap<>();
		map.put(Commands.LOGIN_REQUEST, LoginRequest.class);
		map.put(Commands.LOGIN_RESPONSE, LoginResponse.class);
		map.put(Commands.MESSAGE_REQUEST, MessageRequest.class);
		map.put(Commands.MESSAGE_RESPONSE, MessageResponse.class);
		map.put(Commands.CREATE_GROUP_REQUEST, CreateGroupRequest.class);
		map.put(Commands.CREATE_GROUP_RESPONSE, CreateGroupResponse.class);
		map.put(Commands.JOIN_GROUP_REQUEST, JoinGroupRequest.class);
		map.put(Commands.JOIN_GROUP_RESPONSE, JoinGroupResponse.class);

		mapping = Collections.unmodifiableMap(map);
	}

	public static Class<? extends Packet> packetTypeOf(byte command) {
		return mapping.get(command);
	}
}
