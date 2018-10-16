package com.star.im.handler;

import com.star.im.entity.LoginResponse;
import com.star.im.entity.Session;
import com.star.im.util.SessionManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponse> {
	public static final LoginResponseHandler INSTANCE = new LoginResponseHandler();

	private LoginResponseHandler() {
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
