package com.star.im.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.star.im.entity.ListGroupMembersRequest;
import com.star.im.entity.ListGroupMembersResponse;
import com.star.im.entity.Session;
import com.star.im.util.SessionManager;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class ListGroupMembersRequestHandler
		extends SimpleChannelInboundHandler<ListGroupMembersRequest> {

	@Override
	@SuppressWarnings("unchecked")
	protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequest msg)
			throws Exception {
		String groupId = msg.getGroupId();
		ChannelGroup channelGroup = SessionManager.getChannelGroup(groupId);
		List<String> usernameList;
		if (channelGroup != null) {
			usernameList = new ArrayList<>(channelGroup.size());
			for (Channel channel : channelGroup) {
				Session session = SessionManager.getSession(channel);
				usernameList.add(session.getUsername());
			}
		} else {
			usernameList = Collections.EMPTY_LIST;
		}
		ListGroupMembersResponse response = new ListGroupMembersResponse();
		response.setGroupId(groupId);
		response.setUsernameList(usernameList);
		ctx.channel().writeAndFlush(response);
	}

}
