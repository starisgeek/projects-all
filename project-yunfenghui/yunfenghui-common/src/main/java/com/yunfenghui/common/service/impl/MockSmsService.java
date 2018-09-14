package com.yunfenghui.common.service.impl;

import java.util.List;

import com.yfh.erp.common.ERPException;
import com.yfh.erp.common.KeyValue;
import com.yfh.erp.common.service.SmsService;

public class MockSmsService implements SmsService {

	@Override
	public void send(String phoneNumbers, String templateCode,
			List<KeyValue<String, String>> params) throws ERPException {
	}
}
