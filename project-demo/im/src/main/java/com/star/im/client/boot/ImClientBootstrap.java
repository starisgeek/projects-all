package com.star.im.client.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.im.util.Configs;

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

					}
				});
		ChannelFuture future = boot.connect(Configs.valueOf(Configs.KEY_IM_SERVER_ADDRESS),
				Integer.valueOf(Configs.valueOf(Configs.KEY_IM_SERVER_PORT)));
		future.addListener(new GenericFutureListener<Future<? super Void>>() {
			@Override
			public void operationComplete(Future<? super Void> future) throws Exception {
				if (future.isSuccess()) {
					logger.info("connect server success");
				}
			}
		});
		try {
			future.sync();
			future.channel().closeFuture()
					.addListener(new GenericFutureListener<Future<? super Void>>() {
						@Override
						public void operationComplete(Future<? super Void> future)
								throws Exception {
							logger.info("client connection close");
						}
					}).sync();
		} catch (InterruptedException e) {
			logger.error("Failed to sync", e);
		}
	}
}
