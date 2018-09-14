package com.yunfenghui.common.service.impl;

import java.util.UUID;

import com.yfh.erp.common.service.PasswordService.SaltGenerator;

public class UUIDSaltGenerator implements SaltGenerator {
	@Override
	public String generate() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
