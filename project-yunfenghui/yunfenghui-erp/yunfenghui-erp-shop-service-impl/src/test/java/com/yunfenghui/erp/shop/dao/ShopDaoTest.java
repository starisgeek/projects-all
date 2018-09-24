package com.yunfenghui.erp.shop.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.erp.shop.model.Shop;
import com.yunfenghui.test.BaseDaoTest;

public class ShopDaoTest extends BaseDaoTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ShopDao shopDao;

	@Test
	public void testInsertShop() {
		Shop shop = new Shop();
		shop.setName("深圳宝安旗舰店");
		shop.setNumber("12485");
		shop.setLocation("深圳宝安后瑞");
		shop.setMemberId(1234L);
		shop.setMemberPhone("13123456789");
		shop.setCreateTime(new Date());
		shop.setRemark("深圳第一家店");

		shopDao.insertShop(shop);
		logger.info("create shop:{} success", shop.getId());
	}

	@Test
	public void testQueryShopById() {
		int id = 1;
		Shop shop = shopDao.queryShopById(id);
		logger.info("found shop '{}' by id:{}", shop != null ? shop.getName() : "null", id);
	}

	@Test
	public void testQueryShopByNumber() {
		String number = "12485";
		Shop shop = shopDao.queryShopByNumber(number);
		logger.info("found shop '{}' by number:{}", shop != null ? shop.getName() : "null", number);
	}

	@Test
	public void testQueryShopName() {
		String name = "shenzhen baoan qi jian dian";
		String foundName = shopDao.queryShopName(name);
		logger.info("found shop '{}' by name:'{}'", foundName != null ? foundName : "null", name);
	}

	@Test
	public void testQueryAllShops() {
		List<Shop> shops = shopDao.queryAllShops();
		if (shops != null) {
			for (Shop shop : shops) {
				logger.info("Found shop:{}", shop.getName());
			}
		} else {
			logger.info("Shops not found");
		}
	}
}
