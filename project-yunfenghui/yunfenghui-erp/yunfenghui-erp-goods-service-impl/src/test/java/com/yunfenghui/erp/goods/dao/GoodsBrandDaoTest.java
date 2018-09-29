package com.yunfenghui.erp.goods.dao;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.erp.goods.model.GoodsBrand;
import com.yunfenghui.test.BaseDaoTest;

public class GoodsBrandDaoTest extends BaseDaoTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private GoodsBrandDao brandDao;

	@Test
	public void testInsertGoodsBrand() {
		GoodsBrand brand = new GoodsBrand();
		brand.setName("Selected/斯莱德");
		brand.setShopId(1);
		brandDao.insertGoodsBrand(brand);
		logger.info("insertGoodsBrand:{} success", brand.getId());
	}

	@Test
	public void testQueryGoodsBrandById() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryGoodsBrandByShopIdAndName() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryAllGoodsBrandBy() {
		fail("Not yet implemented");
	}

}
