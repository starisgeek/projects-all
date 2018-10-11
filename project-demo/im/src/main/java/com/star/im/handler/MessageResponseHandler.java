package com.star.im.handler;

import com.star.im.entity.MessageResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponse> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageResponse msg) throws Exception {
		System.out.println(
				msg.getFromUserId() + ":" + msg.getFromUsername() + " -> " + msg.getMessage());
	}

}
