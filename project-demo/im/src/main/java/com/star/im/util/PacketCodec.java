package com.star.im.util;

import com.star.im.entity.LoginRequest;
import com.star.im.entity.Packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class PacketCodec {
	public static ByteBuf encode(Packet<?> packet) {
		ByteBuf buf = ByteBufAllocator.DEFAULT.ioBuffer();
		buf.writeInt(packet.getMagic());
		buf.writeByte(packet.getVersion());
		buf.writeByte(packet.getSerialize());
		buf.writeByte(packet.getCommand());
		byte[] bodyBytes = Serializer.DEFAULT.serialize(packet.getBody());
		int len = bodyBytes.length;
		buf.writeInt(len);
		buf.writeBytes(bodyBytes);
		return buf;
	}

	public static <T> Packet<T> decode(Class<T> clazz, ByteBuf buf) {
		int magic = buf.readInt();
		byte version = buf.readByte();
		byte serialize = buf.readByte();
		byte command = buf.readByte();
		int len = buf.readInt();
		byte[] bodyBytes = new byte[len];
		buf.readBytes(bodyBytes);
		Packet<T> packet = new Packet<>();
		packet.setMagic(magic);
		packet.setVersion(version);
		packet.setSerialize(serialize);
		packet.setCommand(command);
		packet.setBody(Serializer.DEFAULT.deserialize(clazz, bodyBytes));
		return packet;
	}

	public static void main(String[] args) {
		Packet<LoginRequest> packet = new Packet<>();
		packet.setCommand(Commands.LOGIN_REQUEST);
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("王浩");
		loginRequest.setPassword("123456");
		packet.setBody(loginRequest);

		ByteBuf buf = encode(packet);
		Packet<LoginRequest> decoded = decode(LoginRequest.class, buf);
		System.out.println("username:" + decoded.getBody().getUsername());
	}
}
