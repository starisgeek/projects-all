package com.yunfenghui.common.service;

public interface PasswordService {
	String encrypt(String plaintextPassword);

	String encrypt(String plaintextPassword, String salt);

	boolean passwordMatch(String plaintextPassword, String encryptedPassword);

	boolean passwordMatch(String plaintextPassword, String salt, String encryptedPassword);

	interface SaltGenerator {
		String generate();
	}
}
