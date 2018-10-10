package com.star.im.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.im.entity.MessageRequest;
import com.star.im.entity.MessageResponse;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequest> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageRequest request)
			throws Exception {
		logger.info("====================={}======================", request.getMessage());
		MessageResponse response = new MessageResponse();
		response.setMessage(request.getMessage());
		ctx.channel().writeAndFlush(response);
	}

}
