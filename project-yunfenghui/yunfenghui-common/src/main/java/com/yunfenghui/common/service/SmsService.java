package com.yunfenghui.common.service;

import java.util.List;

import com.yfh.erp.common.ERPException;
import com.yfh.erp.common.KeyValue;

public interface SmsService {
	String ID = "smsService";

	void send(String phoneNumbers, String templateCode, List<KeyValue<String, String>> params)
			throws ERPException;
}
