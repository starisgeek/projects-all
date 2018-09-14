package com.yunfenghui.common.service.impl;

import org.apache.commons.codec.digest.DigestUtils;

import com.yunfenghui.common.service.PasswordService;

public class Md5PasswordService implements PasswordService {
	@Override
	public String encrypt(String plaintextPassword) {
		return DigestUtils.md5Hex(plaintextPassword);
	}

	@Override
	public String encrypt(String plaintextPassword, String salt) {
		return DigestUtils.md5Hex(plaintextPassword + salt);
	}

	@Override
	public boolean passwordMatch(String plaintextPassword, String encryptedPassword) {
		return DigestUtils.md5Hex(plaintextPassword).equals(encryptedPassword);
	}

	@Override
	public boolean passwordMatch(String plaintextPassword, String salt, String encryptedPassword) {
		return DigestUtils.md5Hex(plaintextPassword + salt).equals(encryptedPassword);
	}

}
