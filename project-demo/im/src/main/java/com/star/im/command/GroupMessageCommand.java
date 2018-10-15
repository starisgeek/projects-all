package com.star.im.command;

import java.util.Scanner;

import com.star.im.entity.GroupMessageRequest;

import io.netty.channel.Channel;

public class GroupMessageCommand implements ConsoleCommand {

	@Override
	public void execute(Scanner scanner, Channel channel) {
		System.out.println("发送群消息");
		String groupId = scanner.next();
		String message = scanner.next();
		GroupMessageRequest request = new GroupMessageRequest();
		request.setGroupId(groupId);
		request.setMessage(message);
		channel.writeAndFlush(request);
	}

}
