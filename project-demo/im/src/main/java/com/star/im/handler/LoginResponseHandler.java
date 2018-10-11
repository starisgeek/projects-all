package com.star.im.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.im.entity.LoginResponse;
import com.star.im.entity.Session;
import com.star.im.util.SessionManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponse> {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("Channel inactive");
		super.channelInactive(ctx);
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginResponse response)
			throws Exception {
		if (response.isSuccess()) {
			SessionManager.bindSession(new Session(response.getUserId(), response.getUsername()),
					ctx.channel());
			System.out.println(
					"[" + response.getUsername() + "]登录成功, userId为: " + response.getUserId());
		} else {
			System.out.println("用户登录失败:" + response.getMessage());
		}
	}

}
