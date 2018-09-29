package com.yunfenghui.erp.goods.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.yunfenghui.erp.common.ERPException;

@ContextConfiguration("classpath:goods-service-consumer.xml")
public class GoodsServiceTest extends AbstractJUnit4SpringContextTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource(name = GoodsService.ID)
	private GoodsService goodsService;

	@Test
	public void testCreateGoods() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateAndPutAwayGoods() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutAwayGoods() {
		fail("Not yet implemented");
	}

	@Test
	public void testSoldOutGoods() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdjustSalePrice() {
		int goodsId = 1;
		int newSalePrice = 50000;
		int createUserId = 1;
		String reason = "材料价格上涨";
		try {
			goodsService.adjustSalePrice(goodsId, newSalePrice, createUserId, reason);
		} catch (ERPException e) {
			logger.error("Failed to adjustSalePrice", e);
		}
	}

	@Test
	public void testGetGoodsById() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGoodsByShopIdAndBarcode() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetGoodsBy() {
		fail("Not yet implemented");
	}

}
