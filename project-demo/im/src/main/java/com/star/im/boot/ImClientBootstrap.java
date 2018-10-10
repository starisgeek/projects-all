package com.star.im.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.im.handler.LoginResponseHandler;
import com.star.im.handler.PacketDecoder;
import com.star.im.handler.PacketEncoder;
import com.star.im.util.Configs;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
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
						ch.pipeline().addLast(new PacketDecoder());
						ch.pipeline().addLast(buildClientHandlerFromCommandLineArgs(args));
						ch.pipeline().addLast(new PacketEncoder());
					}
				});
		connect(boot,
				Integer.valueOf(Configs.valueOf(Configs.KEY_IM_CLIENT_CONNECT_MAX_RETRIES)) + 1);
	}

	private static LoginResponseHandler buildClientHandlerFromCommandLineArgs(String[] args) {
		return new LoginResponseHandler(args[0], args[1]);
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
								logger.info("Client connect server success");
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
}
