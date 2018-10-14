package com.star.im.command;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.star.im.entity.LoginRequest;

import io.netty.channel.Channel;

/**
 * 登录命令
 * 
 * @author Administrator
 *
 */
public class LoginCommand implements ConsoleCommand {

	@Override
	public void execute(Scanner scanner, Channel channel) {
		System.out.println("请输入用户名和密码:");
		String username = scanner.next();
		String password = scanner.next();
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername(username);
		loginRequest.setPassword(password);
		channel.writeAndFlush(loginRequest);
		waitForLoginResponse();
	}

	private static void waitForLoginResponse() {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
		}
	}
}
