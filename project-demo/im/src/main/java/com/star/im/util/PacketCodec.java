package com.star.im.util;

import com.star.im.entity.LoginRequest;
import com.star.im.entity.Packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class PacketCodec {
	public static ByteBuf encode(Packet packet) {
		ByteBuf buf = ByteBufAllocator.DEFAULT.ioBuffer();
		buf.writeInt(packet.getMagic());
		buf.writeByte(packet.getVersion());
		buf.writeByte(packet.getSerialize());
		buf.writeByte(packet.getCommand());
		byte[] bodyBytes = Serializer.DEFAULT.serialize(packet);
		int len = bodyBytes.length;
		buf.writeInt(len);
		buf.writeBytes(bodyBytes);
		return buf;
	}

	public static Packet decode(ByteBuf buf) {
		int magic = buf.readInt();
		byte version = buf.readByte();
		byte serialize = buf.readByte();
		byte command = buf.readByte();
		int len = buf.readInt();
		byte[] bodyBytes = new byte[len];
		buf.readBytes(bodyBytes);
		Class<? extends Packet> packetType = CommandMapping.packetTypeOf(command);
		Packet packet = Serializer.DEFAULT.deserialize(packetType, bodyBytes);
		packet.setMagic(magic);
		packet.setVersion(version);
		packet.setSerialize(serialize);
		return packet;
	}

	public static void main(String[] args) {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("王浩");
		loginRequest.setPassword("123456");

		ByteBuf buf = encode(loginRequest);
		Packet decoded = decode(buf);
		if (decoded instanceof LoginRequest) {
			System.out.println(((LoginRequest) decoded).getUsername());
		}
	}
}
