package com.star.im.boot;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.im.entity.MessageRequest;
import com.star.im.handler.LoginResponseHandler;
import com.star.im.handler.MessageResponseHandler;
import com.star.im.handler.PacketDecoder;
import com.star.im.handler.PacketEncoder;
import com.star.im.util.Configs;
import com.star.im.util.LoginUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
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
						ch.pipeline()
								.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
						ch.pipeline().addLast(new PacketDecoder());
						ch.pipeline().addLast(buildLoginResponseHandler(args));
						ch.pipeline().addLast(new MessageResponseHandler());
						ch.pipeline().addLast(new PacketEncoder());
					}
				});
		connect(boot,
				Integer.valueOf(Configs.valueOf(Configs.KEY_IM_CLIENT_CONNECT_MAX_RETRIES)) + 1);
	}

	private static LoginResponseHandler buildLoginResponseHandler(String[] args) {
		return new LoginResponseHandler("star", "123456");
	}

	private static void connect(Bootstrap boot, int retries) {
		if (retries > 0) {
			final ChannelFuture channelFuture = boot.connect(
					Configs.valueOf(Configs.KEY_IM_SERVER_ADDRESS),
					Integer.valueOf(Configs.valueOf(Configs.KEY_IM_SERVER_PORT)));
			channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
				@Override
				public void operationComplete(Future<? super Void> future) throws Exception {
					if (future.isSuccess()) {
						logger.info("Client connect server success");
						startConsoleThread(channelFuture.channel());
					} else {
						logger.error("Client connect server failed, retring connect");
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
				Scanner scanner = null;
				while (!Thread.currentThread().isInterrupted()) {
					if (LoginUtil.hasLogin(channel)) {
						scanner = new Scanner(System.in);
						String line = scanner.nextLine();

						MessageRequest request = new MessageRequest();
						request.setMessage(line);
						channel.writeAndFlush(request);
					}
				}
				if (scanner != null) {
					scanner.close();
				}
			}
		}).start();
	}
}
