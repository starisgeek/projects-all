package com.yunfenghui.erp.shop.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.erp.shop.model.BalanceAccount;
import com.yunfenghui.test.BaseDaoTest;

public class BalanceAccountDaoTest extends BaseDaoTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BalanceAccountDao accountDao;

	@Test
	public void testInsertBalanceAccount() {
		BalanceAccount account = new BalanceAccount();
		account.setShopId(1);
		account.setBalance(0);
		accountDao.insertBalanceAccount(account);
		logger.info("create balance account success");
	}

}
