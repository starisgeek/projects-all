package com.star.im.handler;

import com.star.im.entity.MessageResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponse> {
	public static final MessageResponseHandler INSTANCE = new MessageResponseHandler();

	private MessageResponseHandler() {
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageResponse msg) throws Exception {
		System.out.println(
				msg.getFromUserId() + ":" + msg.getFromUsername() + " -> " + msg.getMessage());
	}

}
