package com.star.im.command;

import java.util.Scanner;

import com.star.im.entity.JoinGroupRequest;

import io.netty.channel.Channel;

public class JoinGroupCommand implements ConsoleCommand {

	@Override
	public void execute(Scanner scanner, Channel channel) {
		JoinGroupRequest request = new JoinGroupRequest();
		System.out.print("输入 groupId，加入群聊：");
		request.setGroupId(scanner.next());
		channel.writeAndFlush(request);
	}

}
