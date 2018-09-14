package com.yunfenghui.jf.score.dao;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.jf.score.model.MemberAccount;
import com.yunfenghui.test.BaseDaoTest;

public class MemberAccountDaoTest extends BaseDaoTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private MemberAccountDao accountDao;

	@Test
	public void testInsertAccount() {
		MemberAccount account = new MemberAccount();
		account.setMemberId(123);
		account.setWhiteScores(1234);
		account.setRedScores(20);
		account.setBalance(10);
		account.setCreateTime(new Date());
		accountDao.insertAccount(account);
	}

	@Test
	public void testQueryAccountById() {
		int memberId = 123;
		MemberAccount account = accountDao.queryAccountById(memberId);
		logger.info("Found account whiteScores:{} by memberId:{}",
				account != null ? account.getWhiteScores() : "null", memberId);
	}

	@Test
	public void testQueryWhiteScores() {
		int memberId = 7961;
		Integer balance = accountDao.queryWhiteScores(memberId);
		logger.info("Found white scores:{} by memberId:{}", balance, memberId);
	}

	@Test
	public void testQueryRedScores() {
		int memberId = 7961;
		Integer balance = accountDao.queryRedScores(memberId);
		logger.info("Found red scores:{} by memberId:{}", balance, memberId);
	}

	@Test
	public void testQueryBalance() {
		int memberId = 7961;
		Integer balance = accountDao.queryBalance(memberId);
		logger.info("Found balance:{} by memberId:{}", balance, memberId);
	}

	@Test
	public void testIncreaseWhiteScore() {
		int memberId = 123, increment = 100;
		int updated = accountDao.increaseWhiteScore(memberId, increment);
		logger.info("Increase member:{} whiteScore:{} success:{}", memberId, increment,
				updated == 1);
	}

	@Test
	public void testDecreaseWhiteScore() {
		int memberId = 123, decrement = 1235;
		int updated = accountDao.decreaseWhiteScore(memberId, decrement);
		logger.info("Decrease member:{} whiteScore:{} success:{}", memberId, decrement,
				updated == 1);
	}

	@Test
	public void testIncreaseRedScore() {
		int memberId = 123, increment = 100;
		int updated = accountDao.increaseRedScore(memberId, increment);
		logger.info("Increase member:{} redScore:{} success:{}", memberId, increment, updated == 1);
	}

	@Test
	public void testDecreaseRedScore() {
		int memberId = 123, decrement = 120;
		int updated = accountDao.decreaseRedScore(memberId, decrement);
		logger.info("Decrease member:{} redScore:{} success:{}", memberId, decrement, updated == 1);
	}

	@Test
	public void testIncreaseBalance() {
		int memberId = 123, increment = 100;
		int updated = accountDao.increaseBalance(memberId, increment);
		logger.info("Increase member:{} balance:{} success:{}", memberId, increment, updated == 1);
	}

	@Test
	public void testDecreaseBalance() {
		int memberId = 123, decrement = 110;
		int updated = accountDao.decreaseBalance(memberId, decrement);
		logger.info("Decrease member:{} balance:{} success:{}", memberId, decrement, updated == 1);
	}

}
