package com.star.im.entity;

import com.star.im.util.Commands;

/**
 * 消息响应包
 * 
 * @author Administrator
 *
 */
public class MessageResponse extends Packet {
	private String fromUserId;
	private String fromUsername;
	private String message;

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUsername() {
		return fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}

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
