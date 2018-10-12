package com.star.im.entity;

import java.util.List;

import com.star.im.util.Commands;

public class CreateGroupRequest extends Packet {
	private List<String> userIdList;

	@Override
	public byte getCommand() {
		return Commands.CREATE_GROUP_REQUEST;
	}

	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}

}
