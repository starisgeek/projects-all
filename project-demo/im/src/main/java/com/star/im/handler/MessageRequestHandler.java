package com.star.im.handler;

import com.star.im.entity.MessageRequest;
import com.star.im.entity.MessageResponse;
import com.star.im.entity.Session;
import com.star.im.util.SessionManager;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;

@Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequest> {
	public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

	private MessageRequestHandler() {
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageRequest request)
			throws Exception {
		Session session = SessionManager.getSession(ctx.channel());

		MessageResponse response = new MessageResponse();
		response.setFromUserId(session.getUserId());
		response.setFromUsername(session.getUsername());
		response.setMessage(request.getMessage());

		Channel toChannel = SessionManager.getChannel(request.getToUserId());
		if (toChannel != null) {
			toChannel.writeAndFlush(response);
		} else {
			System.err.print("[" + request.getToUserId() + "]不在线,发送失败");
		}

	}
}
