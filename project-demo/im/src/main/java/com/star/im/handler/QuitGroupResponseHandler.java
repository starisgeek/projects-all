package com.star.im.handler;

import com.star.im.entity.QuitGroupResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponse> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponse msg) throws Exception {
		if (msg.isSuccess()) {
			System.out.println("退出群[" + msg.getGroupId() + "]成功!");
		} else {
			System.err.println("退出群[" + msg.getGroupId() + "]失败，原因为：" + msg.getReason());
		}
	}
}
