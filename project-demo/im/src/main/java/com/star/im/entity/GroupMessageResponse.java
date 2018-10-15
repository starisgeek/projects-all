package com.star.im.entity;

import com.star.im.util.Commands;

public class GroupMessageResponse extends Packet {
	private String fromUsername;
	private String groupId;
	private String message;

	public String getFromUsername() {
		return fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public byte getCommand() {
		return Commands.GROUP_MESSAGE_RESPONSE;
	}

}
