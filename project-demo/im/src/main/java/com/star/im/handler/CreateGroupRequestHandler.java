package com.star.im.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.star.im.entity.CreateGroupRequest;
import com.star.im.entity.CreateGroupResponse;
import com.star.im.entity.Session;
import com.star.im.util.SessionManager;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequest> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequest msg)
			throws Exception {
		List<String> userIdList = msg.getUserIdList();
		ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
		List<String> usernameList = new ArrayList<>(userIdList.size());
		for (String userId : userIdList) {
			Channel channel = SessionManager.getChannel(userId);
			if (channel != null) {
				channelGroup.add(channel);
				Session session = SessionManager.getSession(channel);
				if (session != null) {
					usernameList.add(session.getUsername());
				}
			}
		}
		String groupId = generateGroupId();
		CreateGroupResponse response = new CreateGroupResponse();
		response.setSuccess(true);
		response.setGroupId(groupId);
		response.setUsernameList(usernameList);

		channelGroup.writeAndFlush(response);
		System.out.print("群创建成功,id 为[" + groupId + "], ");
		System.out.println("群里面有：" + usernameList);
	}

	protected String generateGroupId() {
		Random r = new Random();
		return Integer.toHexString(1000000 + r.nextInt(100000));
	}

}
