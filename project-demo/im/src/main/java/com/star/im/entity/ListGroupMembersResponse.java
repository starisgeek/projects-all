package com.star.im.entity;

import java.util.List;

import com.star.im.util.Commands;

public class ListGroupMembersResponse extends Packet {
	private String groupId;
	private List<String> usernameList;

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
		return Commands.LIST_GROUP_MEMBERS_RESPONSE;
	}

}
