package com.star.im.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.im.entity.MessageResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponse> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageResponse msg) throws Exception {
		logger.info("====================={}======================", msg.getMessage());
	}

}
