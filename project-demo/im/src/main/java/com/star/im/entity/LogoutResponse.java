package com.star.im.entity;

import com.star.im.util.Commands;

public class LogoutResponse extends Packet {
	private boolean isSuccess;
	private String reason;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public byte getCommand() {
		return Commands.LOGOUT_RESPONSE;
	}

}
