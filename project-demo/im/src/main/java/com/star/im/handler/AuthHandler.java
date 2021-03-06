package com.star.im.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.im.util.SessionManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final AuthHandler INSTANCE = new AuthHandler();

	private AuthHandler() {
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (SessionManager.hasLogin(ctx.channel())) {
			ctx.pipeline().remove(this);
			super.channelRead(ctx, msg);
		} else {
			ctx.channel().close();
		}
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		logger.info("登录成功,删除自己");
		super.handlerRemoved(ctx);
	}

}
