package com.yunfenghui.erp.user.dao;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.common.service.PasswordService;
import com.yunfenghui.common.service.PasswordService.SaltGenerator;
import com.yunfenghui.common.service.impl.Md5PasswordService;
import com.yunfenghui.common.service.impl.UUIDSaltGenerator;
import com.yunfenghui.erp.common.Commons;
import com.yunfenghui.erp.user.model.User;
import com.yunfenghui.test.BaseDaoTest;

public class UserDaoTest extends BaseDaoTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserDao userDao;

	@Test
	public void testInsertUser() {
		User u = new User();
		u.setUsername("王浩");
		u.setLoginPhone("13266688976");
		PasswordService ps = new Md5PasswordService();
		String password = "123456";
		SaltGenerator sg = new UUIDSaltGenerator();
		String salt = sg.generate();
		u.setPassword(ps.encrypt(password, salt));
		u.setPasswordSalt(salt);
		u.setShopId(Commons.PLATFORM_SHOPID);
		u.setCategory(Commons.CATEGORY_PLATFORM);
		u.setIsSuper(Commons.YES);
		u.setCreateTime(new Date());

		userDao.insertUser(u);
		logger.info("create user:{}", u.getId());
	}

	@Test
	public void testQueryUserById() {
		User user = userDao.queryUserById(2);
		logger.info("found user:{}", user == null ? "null" : user.getLoginPhone());
	}

	@Test
	public void testQueryUserByPhoneAndShopId() {
		User user = userDao.queryUserByLoginPhoneAndShopId("13266688976", Commons.PLATFORM_SHOPID);
		logger.info("found user:{}", user == null ? "null" : user.getId());
	}

	@Test
	public void testQueryCountByShopIdAndIsSuper() {
		int count = userDao.queryCountByShopIdAndIsSuper(Commons.PLATFORM_SHOPID, Commons.YES);
		logger.info("found:{} by shopId:{} and isSuper:{}", count, Commons.PLATFORM_SHOPID,
				Commons.YES);
	}
}
