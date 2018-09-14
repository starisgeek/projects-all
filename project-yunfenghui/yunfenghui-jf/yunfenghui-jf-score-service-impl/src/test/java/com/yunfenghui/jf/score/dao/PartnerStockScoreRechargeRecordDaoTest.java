package com.yunfenghui.jf.score.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.common.service.NumberGenerator;
import com.yunfenghui.jf.score.model.StockScoreRechargeRecord;
import com.yunfenghui.test.BaseDaoTest;

public class PartnerStockScoreRechargeRecordDaoTest extends BaseDaoTest {
	@Autowired
	private PartnerStockScoreRechargeRecordDao recordDao;
	@Resource(name = "serialNumberGenerator")
	private NumberGenerator generator;

	@Test
	public void testInsertRechargeRecord() {
		StockScoreRechargeRecord record = new StockScoreRechargeRecord();
		record.setRecordNo(generator.generate());
		record.setPartnerId(1);
		record.setRechargeMoney(100);
		record.setIncreaseStockScores(record.getRechargeMoney() * 10);
		record.setIncreaseWhiteScores(record.getRechargeMoney());
		record.setCreateUserId(1);
		record.setCreateTime(new Date());

		recordDao.insertRechargeRecord(record);
		logger.info("testInsertRechargeRecord success");
	}

}
