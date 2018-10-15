package com.star.im.handler;

import com.star.im.entity.Packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class ImLengthFieldDecoder extends LengthFieldBasedFrameDecoder {
	private static final int LENGTH_FIELD_OFFSET = 7;
	private static final int LENGTH_FIELD_LENGTH = 4;

	public ImLengthFieldDecoder() {
		super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		if (validateMagicNumber(in)) {
			return super.decode(ctx, in);
		} else {
			ctx.channel().close();
			return null;
		}
	}

	private boolean validateMagicNumber(ByteBuf in) {
		return in.getInt(in.readerIndex()) == Packet.MAGIC_NUMBER;
	}
}
