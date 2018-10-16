package com.star.im.boot;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.im.command.CommandManager;
import com.star.im.command.ConsoleCommand;
import com.star.im.command.LoginCommand;
import com.star.im.handler.ImHandler;
import com.star.im.handler.ImLengthFieldDecoder;
import com.star.im.handler.LoginResponseHandler;
import com.star.im.handler.PacketCodec;
import com.star.im.util.Configs;
import com.star.im.util.SessionManager;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class ImClientBootstrap {
	private static final Logger logger = LoggerFactory.getLogger(ImClientBootstrap.class);

	public static void main(String[] args) {
		NioEventLoopGroup worker = new NioEventLoopGroup();
		Bootstrap boot = new Bootstrap();
		boot.group(worker).channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<Channel>() {
					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new ImLengthFieldDecoder());
						ch.pipeline().addLast(PacketCodec.INSTANCE);
						ch.pipeline().addLast(LoginResponseHandler.INSTANCE);
						ch.pipeline().addLast(ImHandler.INSTANCE);
					}
				});
		connect(boot,
				Integer.valueOf(Configs.valueOf(Configs.KEY_IM_CLIENT_CONNECT_MAX_RETRIES)) + 1);
	}

	private static void connect(Bootstrap boot, int retries) {
		if (retries > 0) {
			boot.connect(Configs.valueOf(Configs.KEY_IM_SERVER_ADDRESS),
					Integer.valueOf(Configs.valueOf(Configs.KEY_IM_SERVER_PORT)))
					.addListener(new GenericFutureListener<Future<? super Void>>() {
						@Override
						public void operationComplete(Future<? super Void> future)
								throws Exception {
							if (future.isSuccess()) {
								logger.info("连接成功,启动控制台线程...");
								startConsoleThread(((ChannelFuture) future).channel());
							} else {
								logger.error("连接失败,重新开始连接...");
								connect(boot, retries - 1);
							}
						}
					});
		} else {
			logger.error("all retry connect failed");
		}
	}

	private static void startConsoleThread(Channel channel) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Scanner scanner = new Scanner(System.in);
				ConsoleCommand loginCommand = new LoginCommand();
				ConsoleCommand commandManager = new CommandManager();
				while (!Thread.currentThread().isInterrupted()) {
					if (!SessionManager.hasLogin(channel)) {
						loginCommand.execute(scanner, channel);
					} else {
						commandManager.execute(scanner, channel);
					}
				}
				if (scanner != null) {
					scanner.close();
				}
			}
		}).start();
	}

}
