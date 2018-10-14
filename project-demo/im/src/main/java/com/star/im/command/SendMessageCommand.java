package com.star.im.command;

import java.util.Scanner;

import com.star.im.entity.MessageRequest;

import io.netty.channel.Channel;

/**
 * 发送消息命令
 * 
 * @author Administrator
 *
 */
public class SendMessageCommand implements ConsoleCommand {

	@Override
	public void execute(Scanner scanner, Channel channel) {
		String toUserIdAndMessage = scanner.next();
		int idx = toUserIdAndMessage.indexOf(":");
		MessageRequest request = new MessageRequest();
		request.setToUserId(toUserIdAndMessage.substring(0, idx));
		request.setMessage(toUserIdAndMessage.substring(idx + 1, toUserIdAndMessage.length()));
		channel.writeAndFlush(request);
	}

}
