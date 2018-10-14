package com.star.im.handler;

import com.star.im.entity.JoinGroupResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponse> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponse msg) throws Exception {
		if (msg.isSuccess()) {
			System.out.println("加入群[" + msg.getGroupId() + "]成功!");
		} else {
			System.err.println("加入群[" + msg.getGroupId() + "]失败，原因为：" + msg.getReason());
		}
	}

}
