package com.star.im.handler;

import com.star.im.entity.GroupMessageRequest;
import com.star.im.entity.GroupMessageResponse;
import com.star.im.util.SessionManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.group.ChannelGroup;

@Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequest> {
	public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

	private GroupMessageRequestHandler() {
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequest msg)
			throws Exception {
		String groupId = msg.getGroupId();
		String message = msg.getMessage();

		ChannelGroup channelGroup = SessionManager.getChannelGroup(groupId);
		if (channelGroup != null) {
			String fromUsername = SessionManager.getSession(ctx.channel()).getUsername();
			GroupMessageResponse response = new GroupMessageResponse();
			response.setFromUsername(fromUsername);
			response.setGroupId(groupId);
			response.setMessage(message);
			channelGroup.writeAndFlush(response);
		}
	}

}
