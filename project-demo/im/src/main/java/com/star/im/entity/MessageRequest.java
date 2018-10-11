package com.star.im.entity;

import com.star.im.util.Commands;

/**
 * 消息请求包
 * 
 * @author Administrator
 *
 */
public class MessageRequest extends Packet {
	private String toUserId;
	private String message;

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public byte getCommand() {
		return Commands.MESSAGE_REQUEST;
	}

}
