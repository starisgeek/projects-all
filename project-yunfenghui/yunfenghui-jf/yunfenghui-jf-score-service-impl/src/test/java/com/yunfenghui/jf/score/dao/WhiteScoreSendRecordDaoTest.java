package com.yunfenghui.jf.score.dao;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.common.service.NumberGenerator;
import com.yunfenghui.jf.score.model.WhiteScoreSendRecord;
import com.yunfenghui.test.BaseDaoTest;

public class WhiteScoreSendRecordDaoTest extends BaseDaoTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private WhiteScoreSendRecordDao recordDao;
	@Resource(name = "serialNumberGenerator")
	private NumberGenerator generator;

	@Test
	public void testInsertSendRecord() {
		WhiteScoreSendRecord record = WhiteScoreSendRecord.newBuilder()
				.recordNo(generator.generate()).memberId(123).partnerId(1).outTradeNo(uuid())
				.sendScores(1230).status(WhiteScoreSendRecord.STATUS_PENDING).createTime(new Date())
				.build();

		recordDao.insertSendRecord(record);
	}

	@Test
	public void testUpdateSendRecordStatusAndErrorCode() {
		String recordNo = "5b6daafcf6bc8a10a22c58e2";
		int expectStatus = WhiteScoreSendRecord.STATUS_PENDING;
		int updateStatus = WhiteScoreSendRecord.STATUS_SEND_FAILED;
		String errorCode = "recharge score not enough";
		int updated = recordDao.updateSendRecordStatusAndErrorCode(recordNo, expectStatus,
				updateStatus, errorCode, new Date());
		logger.info("updateSendRecordStatus('{}', {}, {}, {}), updated:{}", recordNo, expectStatus,
				updateStatus, errorCode, updated == 1);
	}

	@Test
	public void testQueryRecordNoByPartnerIdAndOutTradeNo() {
		int partnerId = 1;
		String outerTradeNo = "6091cdc76c3e4495903911687dee4f19";
		String recordNo = recordDao.queryRecordNoByPartnerIdAndOutTradeNo(partnerId, outerTradeNo);
		logger.info("Found '{}' by partnerId:{} and outTradeNo:{}", recordNo, partnerId,
				outerTradeNo);
	}

	private static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
