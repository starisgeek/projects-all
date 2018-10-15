package com.star.im.command;

import java.util.Scanner;

import com.star.im.entity.LogoutRequest;

import io.netty.channel.Channel;

/**
 * 登出命令
 * 
 * @author Administrator
 *
 */
public class LogoutCommand implements ConsoleCommand {
	@Override
	public void execute(Scanner scanner, Channel channel) {
		LogoutRequest request = new LogoutRequest();
		channel.writeAndFlush(request);
	}
}
