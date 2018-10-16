package com.star.im.handler;

import com.star.im.entity.QuitGroupRequest;
import com.star.im.entity.QuitGroupResponse;
import com.star.im.util.SessionManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.group.ChannelGroup;

@Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequest> {
	public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

	private QuitGroupRequestHandler() {
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequest msg) throws Exception {
		String groupId = msg.getGroupId();
		ChannelGroup channelGroup = SessionManager.getChannelGroup(groupId);
		QuitGroupResponse response = new QuitGroupResponse();
		if (channelGroup != null) {
			response.setGroupId(groupId);
			boolean quit = channelGroup.remove(ctx.channel());
			if (quit) {
				response.setSuccess(true);
			} else {
				response.setSuccess(false);
				response.setReason("该用户还未加入群:" + groupId);
			}
		} else {
			response.setSuccess(false);
			response.setReason("群id:" + groupId + "不存在");
		}
		ctx.writeAndFlush(response);
	}

}
