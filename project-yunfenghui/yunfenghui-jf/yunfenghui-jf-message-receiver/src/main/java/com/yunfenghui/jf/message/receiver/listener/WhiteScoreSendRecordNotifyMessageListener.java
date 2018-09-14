package com.yunfenghui.jf.message.receiver.listener;

import java.util.List;

import com.yunfenghui.jf.score.model.WhiteScoreSendRecordNotify;

public class WhiteScoreSendRecordNotifyMessageListener
		extends AbstractMessageListener<WhiteScoreSendRecordNotify> {

	public WhiteScoreSendRecordNotifyMessageListener() {
		super(WhiteScoreSendRecordNotify.class);
	}

	@Override
	protected void handle(List<WhiteScoreSendRecordNotify> objs) throws Exception {
		scoreService.handleWhiteScoreSendRecordNotifies(objs);
	}

}
