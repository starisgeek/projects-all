package com.star.im.command;

import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * 控制台命令接口
 * 
 * @author Administrator
 *
 */
public interface ConsoleCommand {
	void execute(Scanner scanner, Channel channel);
}
