package com.star.im.handler;

import com.star.im.entity.LogoutResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponse> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, LogoutResponse msg) throws Exception {
		if (msg.isSuccess()) {
			System.out.println("登出成功");
		} else {
			System.out.println("登出失败,原因:" + msg.getReason());
		}
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("连接关闭");
		super.channelInactive(ctx);
	}
}
