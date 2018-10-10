package com.star.im.handler;

import com.star.im.entity.Packet;
import com.star.im.util.Serializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
	@Override
	protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
		out.writeInt(packet.getMagic());
		out.writeByte(packet.getVersion());
		out.writeByte(packet.getSerialize());
		out.writeByte(packet.getCommand());
		byte[] bodyBytes = Serializer.DEFAULT.serialize(packet);
		int len = bodyBytes.length;
		out.writeInt(len);
		out.writeBytes(bodyBytes);
	}
}
