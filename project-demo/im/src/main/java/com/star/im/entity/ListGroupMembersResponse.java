package com.star.im.entity;

import java.util.List;

import com.star.im.util.Commands;

public class ListGroupMembersResponse extends Packet {
	private List<String> usernameList;

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
