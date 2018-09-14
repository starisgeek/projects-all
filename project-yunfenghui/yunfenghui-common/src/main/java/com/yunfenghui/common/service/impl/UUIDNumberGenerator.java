package com.yunfenghui.common.service.impl;

import java.util.UUID;

import com.yunfenghui.common.service.NumberGenerator;

public class UUIDNumberGenerator implements NumberGenerator {
	@Override
	public String generate() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
