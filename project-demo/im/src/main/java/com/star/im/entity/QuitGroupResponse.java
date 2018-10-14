package com.star.im.entity;

import com.star.im.util.Commands;

public class QuitGroupResponse extends Packet {
	private boolean isSuccess;
	private String groupId;
	private String reason;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public byte getCommand() {
		return Commands.QUIT_GROUP_RESPONSE;
	}

}
