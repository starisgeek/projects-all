package com.star.im.handler;

import com.star.im.entity.JoinGroupRequest;
import com.star.im.entity.JoinGroupResponse;
import com.star.im.util.SessionManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequest> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequest msg) throws Exception {
		String groupId = msg.getGroupId();
		ChannelGroup channelGroup = SessionManager.getChannelGroup(groupId);
		JoinGroupResponse response = new JoinGroupResponse();
		if (channelGroup != null) {
			channelGroup.add(ctx.channel());
			response.setGroupId(groupId);
			response.setSuccess(true);
		} else {
			response.setSuccess(false);
			response.setReason("群id:" + groupId + "不存在");
		}
		ctx.channel().writeAndFlush(response);
	}

}
