package com.star.im.handler;

import com.star.im.entity.JoinGroupResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponse> {
	public static final JoinGroupResponseHandler INSTANCE = new JoinGroupResponseHandler();

	private JoinGroupResponseHandler() {
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponse msg) throws Exception {
		if (msg.isSuccess()) {
			System.out.println("加入群[" + msg.getGroupId() + "]成功!");
		} else {
			System.err.println("加入群[" + msg.getGroupId() + "]失败，原因为：" + msg.getReason());
		}
	}

}
