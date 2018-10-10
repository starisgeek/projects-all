package com.star.im.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.im.entity.LoginRequest;
import com.star.im.entity.LoginResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequest> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginRequest request) throws Exception {
		LoginResponse response = new LoginResponse();
		if (validate(request.getUsername(), request.getPassword())) {
			logger.info("validate login request success");
			response.setSuccess(true);
		} else {
			logger.info("validate login request error");
			response.setSuccess(false);
			response.setMessage("username or password is wrong.");
		}
		ctx.channel().writeAndFlush(response);
	}

	private boolean validate(String username, String password) {
		return "star".equals(username) && "123456".equals(password);
	}

}
