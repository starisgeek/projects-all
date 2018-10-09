package com.star.im.entity;

/**
 * 通信协议类
 * 
 * @author Administrator
 *
 * @param <Body>
 */
public class Packet<Body> {
	private int magic = 0x12345678;
	private byte version = 1;
	private byte serialize = 0;
	private byte command;
	private Body body;

	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}

	public byte getVersion() {
		return version;
	}

	public void setVersion(byte version) {
		this.version = version;
	}

	public byte getSerialize() {
		return serialize;
	}

	public void setSerialize(byte serialize) {
		this.serialize = serialize;
	}

	public byte getCommand() {
		return command;
	}

	public void setCommand(byte command) {
		this.command = command;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}
