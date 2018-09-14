package com.yunfenghui.jf.score.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.common.service.NumberGenerator;
import com.yunfenghui.jf.score.model.AccountChangeRecord;
import com.yunfenghui.test.BaseDaoTest;

public class WhiteScoreChangeRecordDaoTest extends BaseDaoTest {
	@Autowired
	private WhiteScoreChangeRecordDao recordDao;
	@Resource(name = "serialNumberGenerator")
	private NumberGenerator generator;

	@Test
	public void testInsertChangeRecord() {
		AccountChangeRecord changeRecord = AccountChangeRecord.newBuilder()
				.serialNumber(generator.generate()).accountId(1).changeAmount(1000)
				.originalRecordNo(generator.generate()).dealType(1).build();

		recordDao.insertChangeRecord(changeRecord);
	}

}
