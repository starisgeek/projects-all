package com.star.im.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.im.entity.LoginRequest;
import com.star.im.entity.LoginResponse;
import com.star.im.entity.Packet;
import com.star.im.util.PacketCodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		Packet packet = PacketCodec.decode(buf);
		if (packet instanceof LoginRequest) {
			LoginRequest loginRequest = (LoginRequest) packet;
			String username = loginRequest.getUsername();
			String password = loginRequest.getPassword();
			logger.info("Server received login request, username:{}", username);
			LoginResponse loginResponse = new LoginResponse();
			if (validate(username, password)) {
				loginResponse.setSuccess(true);
				loginResponse.setMessage("登录成功");
				logger.info("Client:{} request login success", username);
			} else {
				loginResponse.setSuccess(false);
				loginResponse.setMessage("用户名或者密码错误");
				logger.info("Client:{} request login failed, username or password is wrong",
						username);
			}
			ctx.writeAndFlush(PacketCodec.encode(loginResponse));
		}
	}

	private boolean validate(String username, String password) {
		return "star".equals(username) && "123456".equals(password);
	}
}
