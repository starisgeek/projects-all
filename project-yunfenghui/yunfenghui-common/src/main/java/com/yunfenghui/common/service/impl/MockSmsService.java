package com.yunfenghui.common.service.impl;

import java.util.List;

import com.yunfenghui.common.KeyValue;
import com.yunfenghui.common.service.SmsService;

public class MockSmsService implements SmsService {

	@Override
	public void send(String phoneNumbers, String templateCode,
			List<KeyValue<String, String>> params) throws Exception {
	}
}
