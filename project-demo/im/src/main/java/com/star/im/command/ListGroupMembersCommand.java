package com.star.im.command;

import java.util.Scanner;

import com.star.im.entity.ListGroupMembersRequest;

import io.netty.channel.Channel;

public class ListGroupMembersCommand implements ConsoleCommand {
	@Override
	public void execute(Scanner scanner, Channel channel) {
		ListGroupMembersRequest request = new ListGroupMembersRequest();
		System.out.print("输入 groupId，获取群成员列表：");
		String groupId = scanner.next();
		request.setGroupId(groupId);
		channel.writeAndFlush(request);
	}
}
