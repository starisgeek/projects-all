package com.star.im.handler;

import com.star.im.entity.LogoutRequest;
import com.star.im.entity.LogoutResponse;
import com.star.im.entity.Session;
import com.star.im.util.SessionManager;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequest> {
	public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();
	
	private LogoutRequestHandler() {}
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LogoutRequest msg) throws Exception {
		Session session = SessionManager.unbindSession(ctx.channel());
		LogoutResponse response = new LogoutResponse();
		if (session != null) {
			response.setSuccess(true);
			System.out.println("[" + session.getUsername() + "]登出成功");
		} else {
			response.setSuccess(false);
			response.setReason("用户未登录");
		}
		ctx.writeAndFlush(response);
		ctx.channel().close();
	}
}
