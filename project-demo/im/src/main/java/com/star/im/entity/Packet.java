package com.star.im.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 通信协议类
 * 
 * @author Administrator
 *
 * @param <Body>
 */
public abstract class Packet {
	@JSONField(serialize = false, deserialize = false)
	private int magic = 0x12345678;
	@JSONField(serialize = false, deserialize = false)
	private byte version = 1;
	@JSONField(serialize = false, deserialize = false)
	private byte serialize = 0;

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

	@JSONField(serialize = false, deserialize = false)
	public abstract byte getCommand();
}
