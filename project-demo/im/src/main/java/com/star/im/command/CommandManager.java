package com.star.im.command;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import io.netty.channel.Channel;

/**
 * 命令管理器
 * 
 * @author Administrator
 *
 */
public class CommandManager implements ConsoleCommand {
	private static final Map<String, ConsoleCommand> commands;
	static {
		Map<String, ConsoleCommand> map = new HashMap<>();
		map.put("sendMessage", new SendMessageCommand());
		map.put("createGroup", new CreateGroupCommand());
		map.put("joinGroup", new JoinGroupCommand());
		map.put("quitGroup", new QuitGroupCommand());
		map.put("listGroupMembers", new ListGroupMembersCommand());
		map.put("groupMessage", new GroupMessageCommand());
		map.put("logout", new LogoutCommand());

		commands = Collections.unmodifiableMap(map);
	}

	@Override
	public void execute(Scanner scanner, Channel channel) {
		String commandKey = scanner.next();
		ConsoleCommand command = commands.get(commandKey);
		if (command != null) {
			command.execute(scanner, channel);
		} else {
			System.out.println("错误的命令:" + commandKey);
		}
	}

}
