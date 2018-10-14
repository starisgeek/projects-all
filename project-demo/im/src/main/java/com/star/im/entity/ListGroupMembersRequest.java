package com.star.im.entity;

import com.star.im.util.Commands;

public class ListGroupMembersRequest extends Packet {
	private String groupId;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	public byte getCommand() {
		return Commands.LIST_GROUP_MEMBERS_REQUEST;
	}

}
