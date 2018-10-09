package com.star.im.server.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.im.server.handler.ServerHandler;
import com.star.im.util.Configs;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class ImServerBootstrap {
	private static final Logger logger = LoggerFactory.getLogger(ImServerBootstrap.class);

	public static void main(String[] args) throws Exception {
		NioEventLoopGroup boss = new NioEventLoopGroup(1);
		NioEventLoopGroup worker = new NioEventLoopGroup();

		ServerBootstrap boot = new ServerBootstrap();
		boot.group(boss, worker).channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<Channel>() {
					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new ServerHandler());
					}
				});
		ChannelFuture f = boot.bind(Integer.valueOf(Configs.valueOf(Configs.KEY_IM_SERVER_PORT)))
				.addListener(new GenericFutureListener<Future<? super Void>>() {
					@Override
					public void operationComplete(Future<? super Void> future) throws Exception {
						if (future.isSuccess()) {
							logger.info("ImServer bootstrap success");
						}
					}
				});
		f.sync();
	}
}
