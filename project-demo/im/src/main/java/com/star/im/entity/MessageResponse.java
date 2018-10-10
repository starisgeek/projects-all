package com.star.im.entity;

import com.star.im.util.Commands;

/**
 * 消息响应包
 * 
 * @author Administrator
 *
 */
public class MessageResponse extends Packet {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public byte getCommand() {
		return Commands.MESSAGE_RESPONSE;
	}

}
