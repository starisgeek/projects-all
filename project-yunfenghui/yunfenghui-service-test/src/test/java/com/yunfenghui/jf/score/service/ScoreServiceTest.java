package com.yunfenghui.jf.score.service;

import java.util.Random;

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
		int partnerId = 1, rechargeMoney = 10000_00_00, createUserId = 7961;
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
	public void testBatchSendWhiteScore() {
		Random r = new Random();
		for (int i = 1; i <= 1500; i++) {
			WhiteScoreSendRequest sendRequest = new WhiteScoreSendRequest();
			sendRequest.setMemberId(i);
			sendRequest.setPartnerId(1);
			sendRequest.setOutTradeNo(generator.generate());
			sendRequest.setSendScores(r.nextInt(50000) + 1);
			sendRequest.setDealType(WhiteScoreChangeRecordDealType.CONSUME);
			sendRequest.setNotifyUrl("http://localhost/partner/whitescore/sendrecord/notify/");
			scoreService.sendWhiteScore(sendRequest);
		}

		logger.info("Batch send white score request success");
	}

	@Test
	public void testGetMemberAccountBy() {
		int memberId = 1;
		MemberAccount account = scoreService.getMemberAccountBy(memberId);
		logger.info("Found memberAccount whiteScores:{}, redScores:{}, by id:{}",
				account != null ? account.getWhiteScores() : "not found",
				account != null ? account.getRedScores() : "not found", memberId);
	}

	@Test
	public void testGetWhiteScoreBalance() {
		int memberId = 1;
		Integer whiteScores = scoreService.getWhiteScoreBalance(memberId);
		logger.info("Found whiteScores:{} by memberId:{}", whiteScores != null ? whiteScores : 0,
				memberId);
	}

	@Test
	public void testGetWhiteScoreSendRecordBy() {
		String tradeNo = null;
		int partnerId = 1;
		String outTradeNo = "66fe047d75894e508864324b45104526";
		WhiteScoreSendRecord sendRecord = scoreService.getWhiteScoreSendRecordBy(tradeNo, partnerId,
				outTradeNo);
		logger.info("Found sendRecord, whiteScores:{} by tradeNo:{}, partnerId:{}, outTradeNo:{}",
				sendRecord != null ? sendRecord.getSendScores() : "not found", tradeNo, partnerId,
				outTradeNo);
	}
}
