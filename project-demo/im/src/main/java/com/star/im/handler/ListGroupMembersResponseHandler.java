package com.star.im.handler;

import com.star.im.entity.ListGroupMembersResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ListGroupMembersResponseHandler
		extends SimpleChannelInboundHandler<ListGroupMembersResponse> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponse msg)
			throws Exception {
		System.out.println("群[" + msg.getGroupId() + "]包括成员:" + msg.getUsernameList());
	}

}
