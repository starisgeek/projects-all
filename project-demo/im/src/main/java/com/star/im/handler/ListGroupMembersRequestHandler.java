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
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.group.ChannelGroup;

@Sharable
public class ListGroupMembersRequestHandler
		extends SimpleChannelInboundHandler<ListGroupMembersRequest> {
	public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

	private ListGroupMembersRequestHandler() {
	}

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
		ctx.writeAndFlush(response);
	}

}
