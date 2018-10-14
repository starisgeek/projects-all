package com.star.im.command;

import java.util.Scanner;

import com.star.im.entity.QuitGroupRequest;

import io.netty.channel.Channel;

public class QuitGroupCommand implements ConsoleCommand {
	@Override
	public void execute(Scanner scanner, Channel channel) {
		QuitGroupRequest request = new QuitGroupRequest();
		System.out.print("输入 groupId，退出群聊：");
		request.setGroupId(scanner.next());
		channel.writeAndFlush(request);
	}
}
