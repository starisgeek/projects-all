package com.star.im.handler;

import com.star.im.entity.GroupMessageResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponse> {
	public static final GroupMessageResponseHandler INSTANCE = new GroupMessageResponseHandler();

	private GroupMessageResponseHandler() {
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponse msg)
			throws Exception {
		System.out.println("[" + msg.getFromUsername() + "]发来消息:" + msg.getMessage());
	}

}
