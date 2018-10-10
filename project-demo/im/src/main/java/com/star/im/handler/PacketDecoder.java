package com.star.im.handler;

import java.util.List;

import com.star.im.entity.Packet;
import com.star.im.util.CommandMapping;
import com.star.im.util.Serializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class PacketDecoder extends ByteToMessageDecoder {
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out)
			throws Exception {
		int magic = in.readInt();
		byte version = in.readByte();
		byte serialize = in.readByte();
		byte command = in.readByte();
		int len = in.readInt();
		byte[] bodyBytes = new byte[len];
		in.readBytes(bodyBytes);
		Class<? extends Packet> packetType = CommandMapping.packetTypeOf(command);
		Packet packet = Serializer.DEFAULT.deserialize(packetType, bodyBytes);
		packet.setMagic(magic);
		packet.setVersion(version);
		packet.setSerialize(serialize);

		out.add(packet);
	}
}
