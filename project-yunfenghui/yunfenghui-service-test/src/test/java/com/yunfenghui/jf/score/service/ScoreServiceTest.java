package com.yunfenghui.jf.score.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("classpath:score-service-consumer.xml")
public class ScoreServiceTest extends AbstractJUnit4SpringContextTests {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource(name = ScoreService.ID)
	private ScoreService scoreService;

	@Test
	public void testRechargePartnerStockScore() {
		int partnerId = 1, rechargeMoney = 100_00_00, createUserId = 7961;
		scoreService.rechargePartnerStockScore(partnerId, rechargeMoney, createUserId);
		logger.info("Recharge partner:{} stockScore success.", partnerId);
	}
}
