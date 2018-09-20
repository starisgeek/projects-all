package com.yunfenghui.jf.score.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.common.DateTimes;
import com.yunfenghui.common.service.NumberGenerator;
import com.yunfenghui.common.service.impl.DefaultNumberGenerator;
import com.yunfenghui.jf.score.model.WhiteScoreTransformRecord;
import com.yunfenghui.test.BaseDaoTest;

public class WhiteScoreTransformRecordDaoTest extends BaseDaoTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private WhiteScoreTransformRecordDao recordDao;

	@Test
	public void testBatchInsertTransformRecords() {
		List<WhiteScoreTransformRecord> transformRecords = new ArrayList<>();
		NumberGenerator ng = new DefaultNumberGenerator();
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			WhiteScoreTransformRecord transformRecord = new WhiteScoreTransformRecord();
			transformRecord.setRecordNo(ng.generate());
			transformRecord.setMemberId(r.nextInt(10000) + 1);
			transformRecord.setTransformScores(r.nextInt(10000) + 1);
			transformRecord.setCreateTime(new Date(System.currentTimeMillis() + r.nextInt(100000)));
			transformRecords.add(transformRecord);
		}
		recordDao.batchInsertTransformRecords(transformRecords);
		logger.info("batchInsertTransformRecords success");
	}

	@Test
	public void testQueryMaxMemberIdBy() {
		Date now = new Date();
		Integer maxMemberId = recordDao.queryMaxMemberIdBy(DateTimes.earliestOf(now),
				DateTimes.latestOf(now));
		logger.info("max member id:{}", maxMemberId);
	}

}
