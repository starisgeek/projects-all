package com.star.im.handler;

import java.util.List;

import com.star.im.entity.Packet;
import com.star.im.util.CommandMapping;
import com.star.im.util.Serializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.MessageToMessageCodec;

@Sharable
public class PacketCodec extends MessageToMessageCodec<ByteBuf, Packet> {
	public static final PacketCodec INSTANCE = new PacketCodec();

	private PacketCodec() {
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> out)
			throws Exception {
		ByteBuf buf = ctx.channel().alloc().ioBuffer();
		buf.writeInt(packet.getMagic());
		buf.writeByte(packet.getVersion());
		buf.writeByte(packet.getSerialize());
		buf.writeByte(packet.getCommand());
		byte[] bodyBytes = Serializer.DEFAULT.serialize(packet);
		int len = bodyBytes.length;
		buf.writeInt(len);
		buf.writeBytes(bodyBytes);

		out.add(buf);
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out)
			throws Exception {
		int magic = msg.readInt();
		byte version = msg.readByte();
		byte serialize = msg.readByte();
		byte command = msg.readByte();
		int len = msg.readInt();
		byte[] bodyBytes = new byte[len];
		msg.readBytes(bodyBytes);
		Class<? extends Packet> packetType = CommandMapping.packetTypeOf(command);
		Packet packet = Serializer.DEFAULT.deserialize(packetType, bodyBytes);
		packet.setMagic(magic);
		packet.setVersion(version);
		packet.setSerialize(serialize);

		out.add(packet);
	}

}
