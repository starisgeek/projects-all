package com.star.im.handler;

import com.star.im.entity.CreateGroupResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponse> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponse msg)
			throws Exception {
		System.out.print("群创建成功,id 为[" + msg.getGroupId() + "], ");
		System.out.println("群里面有:" + msg.getUsernameList());
	}
}
