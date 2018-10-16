package com.star.im.handler;

import java.util.HashMap;
import java.util.Map;

import com.star.im.entity.Packet;
import com.star.im.util.Commands;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class ImHandler extends SimpleChannelInboundHandler<Packet> {
	public static final ImHandler INSTANCE = new ImHandler();

	private ImHandler() {
		handlers = new HashMap<>();

		handlers.put(Commands.MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
		handlers.put(Commands.MESSAGE_RESPONSE, MessageResponseHandler.INSTANCE);
		handlers.put(Commands.CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
		handlers.put(Commands.CREATE_GROUP_RESPONSE, CreateGroupResponseHandler.INSTANCE);
		handlers.put(Commands.JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
		handlers.put(Commands.JOIN_GROUP_RESPONSE, JoinGroupResponseHandler.INSTANCE);
		handlers.put(Commands.QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
		handlers.put(Commands.QUIT_GROUP_RESPONSE, QuitGroupResponseHandler.INSTANCE);
		handlers.put(Commands.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestHandler.INSTANCE);
		handlers.put(Commands.LIST_GROUP_MEMBERS_RESPONSE,
				ListGroupMembersResponseHandler.INSTANCE);
		handlers.put(Commands.GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.INSTANCE);
		handlers.put(Commands.GROUP_MESSAGE_RESPONSE, GroupMessageResponseHandler.INSTANCE);
		handlers.put(Commands.LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
		handlers.put(Commands.LOGOUT_RESPONSE, LogoutResponseHandler.INSTANCE);
	}

	private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlers;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
		byte command = msg.getCommand();
		handlers.get(command).channelRead(ctx, msg);
	}
}
