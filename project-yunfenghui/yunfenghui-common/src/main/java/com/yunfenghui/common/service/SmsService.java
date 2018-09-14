package com.yunfenghui.common.service;

import java.util.List;

import com.yunfenghui.common.KeyValue;

public interface SmsService {
	String ID = "smsService";

	void send(String phoneNumbers, String templateCode, List<KeyValue<String, String>> params)
			throws Exception;
}
