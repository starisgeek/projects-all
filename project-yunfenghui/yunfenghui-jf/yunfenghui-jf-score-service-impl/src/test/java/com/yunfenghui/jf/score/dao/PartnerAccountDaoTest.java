package com.yunfenghui.jf.score.dao;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.jf.score.model.PartnerAccount;
import com.yunfenghui.test.BaseDaoTest;

public class PartnerAccountDaoTest extends BaseDaoTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PartnerAccountDao accountDao;

	@Test
	public void testIncreaseStockScoreAndWhiteScore() {
		int partnerId = 1, stockScoreIncrement = 1000, whiteScoreIncrement = 100;
		int updated = accountDao.increaseStockScoreAndWhiteScore(partnerId, stockScoreIncrement,
				whiteScoreIncrement);
		logger.info("Increase partner:{} stockScore:{} and whiteScore:{} success:{}", partnerId,
				stockScoreIncrement, whiteScoreIncrement, updated == 1);
	}

	@Test
	public void testDecreaseStockScore() {
		int partnerId = 1, decrement = 6000;
		int updated = accountDao.decreaseStockScore(partnerId, decrement);
		logger.info("Decrease partner:{} rechargeScore:{} success:{}", partnerId, decrement,
				updated == 1);
	}

	@Test
	public void testInsertAccount() {
		PartnerAccount account = new PartnerAccount();
		account.setPartnerId(1);
		account.setStockScores(0);
		account.setWhiteScores(0);
		account.setRedScores(0);
		account.setBalance(0);
		account.setCreateTime(new Date());
		accountDao.insertAccount(account);
	}

	@Test
	public void testQueryAccountById() {
		int partnerId = 1;
		PartnerAccount account = accountDao.queryAccountById(partnerId);
		logger.info("Found account whiteScores:{} by partnerId:{}",
				account != null ? account.getWhiteScores() : "null", partnerId);
	}

	@Test
	public void testIncreaseWhiteScore() {
		int partnerId = 1, increment = 1000;
		int updated = accountDao.increaseWhiteScore(partnerId, increment);
		logger.info("Increase partner:{} whiteScore:{} success:{}", partnerId, increment,
				updated == 1);
	}

	@Test
	public void testDecreaseWhiteScore() {
		int partnerId = 1, decrement = 6000;
		int updated = accountDao.decreaseWhiteScore(partnerId, decrement);
		logger.info("Decrease partner:{} whiteScore:{} success:{}", partnerId, decrement,
				updated == 1);
	}

	@Test
	public void testIncreaseRedScore() {
		int partnerId = 1, increment = 100;
		int updated = accountDao.increaseRedScore(partnerId, increment);
		logger.info("Increase partner:{} redScore:{} success:{}", partnerId, increment,
				updated == 1);
	}

	@Test
	public void testDecreaseRedScore() {
		int partnerId = 1, decrement = 1200;
		int updated = accountDao.decreaseRedScore(partnerId, decrement);
		logger.info("Decrease partner:{} redScore:{} success:{}", partnerId, decrement,
				updated == 1);
	}

	@Test
	public void testIncreaseBalance() {
		int partnerId = 1, increment = 100;
		int updated = accountDao.increaseBalance(partnerId, increment);
		logger.info("Increase partner:{} balance:{} success:{}", partnerId, increment,
				updated == 1);
	}

	@Test
	public void testDecreaseBalance() {
		int partnerId = 1, decrement = 200;
		int updated = accountDao.decreaseBalance(partnerId, decrement);
		logger.info("Decrease partner:{} balance:{} success:{}", partnerId, decrement,
				updated == 1);
	}

}
