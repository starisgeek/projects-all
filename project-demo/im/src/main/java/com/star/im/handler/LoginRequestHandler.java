package com.star.im.handler;

import com.star.im.entity.LoginRequest;
import com.star.im.entity.LoginResponse;
import com.star.im.entity.Session;
import com.star.im.entity.User;
import com.star.im.util.SessionManager;
import com.star.im.util.UserDao;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequest> {
	public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();
	private UserDao userDao = new UserDao();

	private LoginRequestHandler() {
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LoginRequest request) throws Exception {
		LoginResponse response = new LoginResponse();
		User user = userDao.getUser(request.getUsername(), request.getPassword());
		if (user != null) {
			System.out.println("[" + request.getUsername() + "]登录成功");
			response.setUserId(user.getUserId());
			response.setUsername(request.getUsername());
			response.setSuccess(true);
			SessionManager.bindSession(new Session(user.getUserId(), user.getUsername()),
					ctx.channel());
		} else {
			System.out.println("用户[" + request.getUsername() + "]登录失败,用户名或者密码错误");
			response.setSuccess(false);
			response.setMessage("username or password is wrong.");
		}
		ctx.channel().writeAndFlush(response);
	}

}
