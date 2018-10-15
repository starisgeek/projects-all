package com.star.im.entity;

import com.star.im.util.Commands;

public class LogoutRequest extends Packet {
	@Override
	public byte getCommand() {
		return Commands.LOGOUT_REQUEST;
	}
}
