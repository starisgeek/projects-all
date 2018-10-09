package com.star.im.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.im.entity.LoginRequest;
import com.star.im.entity.LoginResponse;
import com.star.im.entity.Packet;
import com.star.im.util.PacketCodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String username;
	private final String password;

	public ClientHandler(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("Client:" + this.username + " begin request login server");
		ByteBuf request = PacketCodec.encode(buildLoginRequest());
		ctx.writeAndFlush(request);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		Packet packet = PacketCodec.decode(buf);
		if (packet instanceof LoginResponse) {
			LoginResponse loginResponse = (LoginResponse) packet;
			logger.info("Client:{} login server success?{}", this.username,
					loginResponse.isSuccess());
		}
		super.channelRead(ctx, msg);
	}

	private LoginRequest buildLoginRequest() {
		LoginRequest packet = new LoginRequest();
		packet.setUsername(this.username);
		packet.setPassword(this.password);
		return packet;
	}
}
