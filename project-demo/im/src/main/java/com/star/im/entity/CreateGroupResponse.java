package com.star.im.entity;

import java.util.List;

import com.star.im.util.Commands;

public class CreateGroupResponse extends Packet {
	private boolean isSuccess;
	private String groupId;
	private List<String> usernameList;

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

	public List<String> getUsernameList() {
		return usernameList;
	}

	public void setUsernameList(List<String> usernameList) {
		this.usernameList = usernameList;
	}

	@Override
	public byte getCommand() {
		return Commands.CREATE_GROUP_RESPONSE;
	}

}
