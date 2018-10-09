package com.star.im.entity;

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
		// TODO Auto-generated method stub
		return 0;
	}

}
