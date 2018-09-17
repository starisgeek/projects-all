package com.yunfenghui.jf.score.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.yunfenghui.common.service.NumberGenerator;
import com.yunfenghui.common.service.impl.UUIDNumberGenerator;
import com.yunfenghui.jf.score.model.ChangeRecordDealTypes.WhiteScoreChangeRecordDealType;
import com.yunfenghui.jf.score.model.MemberAccount;
import com.yunfenghui.jf.score.model.WhiteScoreSendRecord;
import com.yunfenghui.jf.score.model.WhiteScoreSendRequest;

@ContextConfiguration("classpath:score-service-consumer.xml")
public class ScoreServiceTest extends AbstractJUnit4SpringContextTests {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource(name = ScoreService.ID)
	private ScoreService scoreService;
	private NumberGenerator generator = new UUIDNumberGenerator();

	@Test
	public void testRechargePartnerStockScore() {
		int partnerId = 1, rechargeMoney = 100_00_00, createUserId = 7961;
		scoreService.rechargePartnerStockScore(partnerId, rechargeMoney, createUserId);
		logger.info("Recharge partner:{} stockScore success.", partnerId);
	}

	@Test
	public void testSendWhiteScore() {
		WhiteScoreSendRequest sendRequest = new WhiteScoreSendRequest();
		sendRequest.setMemberId(1);
		sendRequest.setPartnerId(1);
		sendRequest.setOutTradeNo(generator.generate());
		sendRequest.setSendScores(5000);
		sendRequest.setDealType(WhiteScoreChangeRecordDealType.CONSUME);
		sendRequest.setNotifyUrl("http://localhost/partner/whitescore/sendrecord/notify/");
		scoreService.sendWhiteScore(sendRequest);
		WhiteScoreSendRecord sendRecord = scoreService.sendWhiteScore(sendRequest);
		logger.info("Send white score request success, sendRecord:{}", sendRecord.getRecordNo());
	}

	@Test
	public void testGetMemberAccountBy() {
		int memberId = 1;
		MemberAccount account = scoreService.getMemberAccountBy(memberId);
		logger.info("Found memberAccount whiteScores:{}, redScores:{}, by id:{}",
				account != null ? account.getWhiteScores() : "not found",
				account != null ? account.getRedScores() : "not found", memberId);
	}
}
