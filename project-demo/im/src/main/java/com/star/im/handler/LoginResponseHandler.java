package com.star.im.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.im.entity.LoginRequest;
import com.star.im.entity.LoginResponse;
import com.star.im.util.LoginUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponse> {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private final String username;
	private final String password;

	public LoginResponseHandler(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// 和ctx.channel().writeAndFlush()区别？
		// ctx.writeAndFlush(buildLoginRequest());
		ctx.channel().writeAndFlush(buildLoginRequest());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginResponse response)
			throws Exception {
		if (response.isSuccess()) {
			LoginUtil.markLogin(ctx.channel());
			logger.info("Client login server success");
		} else {
			logger.info("Client login server failed:{}", response.getMessage());
		}
	}

	private LoginRequest buildLoginRequest() {
		LoginRequest request = new LoginRequest();
		request.setUsername(this.username);
		request.setPassword(this.password);
		return request;
	}

}
