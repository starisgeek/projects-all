package com.yunfenghui.jf.message.receiver.listener;

import java.util.List;

import com.yunfenghui.jf.score.model.WhiteScoreSendRecord;

public class WhiteScoreSendRecordMessageListener
		extends AbstractMessageListener<WhiteScoreSendRecord> {
	public WhiteScoreSendRecordMessageListener() {
		super(WhiteScoreSendRecord.class);
	}

	@Override
	protected void handle(List<WhiteScoreSendRecord> objs) throws Exception {
		scoreService.handleWhiteScoreSendRecords(objs);
	}

}
