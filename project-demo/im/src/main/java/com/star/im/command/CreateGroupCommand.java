package com.star.im.command;

import java.util.Arrays;
import java.util.Scanner;

import com.star.im.entity.CreateGroupRequest;

import io.netty.channel.Channel;

/**
 * 创建群命令
 * 
 * @author Administrator
 *
 */
public class CreateGroupCommand implements ConsoleCommand {
	@Override
	public void execute(Scanner scanner, Channel channel) {
		CreateGroupRequest request = new CreateGroupRequest();
		System.out.print("[拉人群聊]输入 userId 列表，userId 之间英文逗号隔开,");
		String userIdsString = scanner.next();
		String[] userIdArr = userIdsString.split(",");
		request.setUserIdList(Arrays.asList(userIdArr));
		channel.writeAndFlush(request);
	}
}
