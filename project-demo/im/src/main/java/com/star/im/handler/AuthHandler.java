package com.star.im.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.im.util.LoginUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AuthHandler extends ChannelInboundHandlerAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (LoginUtil.hasLogin(ctx.channel())) {
			ctx.pipeline().remove(this);
			super.channelRead(ctx, msg);
		} else {
			ctx.channel().close();
		}
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		logger.info("Client auth success, remove self");
		super.handlerRemoved(ctx);
	}

}
