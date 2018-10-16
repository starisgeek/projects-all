package com.star.im.handler;

import com.star.im.entity.ListGroupMembersResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class ListGroupMembersResponseHandler
		extends SimpleChannelInboundHandler<ListGroupMembersResponse> {
	public static final ListGroupMembersResponseHandler INSTANCE = new ListGroupMembersResponseHandler();

	private ListGroupMembersResponseHandler() {
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponse msg)
			throws Exception {
		System.out.println("群[" + msg.getGroupId() + "]包括成员:" + msg.getUsernameList());
	}

}
