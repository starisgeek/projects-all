package com.yunfenghui.erp.goods.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageRowBounds;
import com.yunfenghui.erp.goods.model.Goods;
import com.yunfenghui.erp.goods.service.GoodsService.GoodsQuery;
import com.yunfenghui.test.BaseDaoTest;

public class GoodsDaoTest extends BaseDaoTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private GoodsDao goodsDao;

	@Test
	public void testInsertGoods() {
		Goods goods = Goods.newBuilder().barcode("41736A505").name("SELECTED思莱德挺括涤纶面料男士合体商务正装西裤T")
				.shopId(1).salePrice(49900).scoreRatio(80).categoryId(1).brandId(1).unitId(1)
				.status(Goods.STATUS_NEW).createTime(new Date()).remark("挺括透气 耐磨").build();
		goodsDao.insertGoods(goods);
		logger.info("insertGoods:{} success", goods.getId());
	}

	@Test
	public void testUpdateGoodsStatus() {
		int goodsId = 1;
		int[] expectedStatuses = { Goods.STATUS_NEW, Goods.STATUS_PUT_AWAY };
		int updatedStatus = Goods.STATUS_PUT_AWAY;
		int updated = goodsDao.updateGoodsStatus(goodsId, expectedStatuses, updatedStatus);
		logger.info("updateGoodsStatus updated:{}", updated);
	}

	@Test
	public void testUpdateGoodsSalePrice() {
		int updated = goodsDao.updateGoodsSalePrice(1, 49800);
		logger.info("updateGoodsSalePrice, updated:{}", updated);
	}

	@Test
	public void testQueryGoodsIdByShopIdAndName() {
		int shopId = 1;
		String name = "SELECTED思莱德挺括涤纶面料男士合体商务正装西裤T";
		Integer goodsId = goodsDao.queryGoodsIdByShopIdAndName(shopId, name);
		logger.info("Found goodsId:{} by shopId:{} and name:{}", goodsId, shopId, name);
	}

	@Test
	public void testQueryGoodsIdByShopIdAndBarcode() {
		int shopId = 1;
		String barcode = "41736A505";
		Integer goodsId = goodsDao.queryGoodsIdByShopIdAndBarcode(shopId, barcode);
		logger.info("Found goodsId:{} by shopId:{} and barcode:{}", goodsId, shopId, barcode);
	}

	@Test
	public void testQueryGoodsByShopIdAndBarcode() {
		int shopId = 1;
		String barcode = "41736A505";
		Goods goods = goodsDao.queryGoodsByShopIdAndBarcode(shopId, barcode);
		logger.info("Found goods name:{} by shopId:{} and barcode:{}",
				goods != null ? goods.getName() : "not found", shopId, barcode);
	}

	@Test
	public void testQueryGoodsById() {
		int id = 1;
		Goods goods = goodsDao.queryGoodsById(id);
		logger.info("Found goods name:{} by id:{}", goods != null ? goods.getName() : "not found",
				id);
	}

	@Test
	public void testQueryGoodsBy() {
		GoodsQuery query = new GoodsQuery();
		PageRowBounds rowBounds = new PageRowBounds(0, 5);
		List<Goods> goodsList = goodsDao.queryGoodsBy(query, rowBounds);
		if (goodsList != null && !goodsList.isEmpty()) {
			for (Goods goods : goodsList) {
				logger.info("Found goods name:{}", goods.getName());
			}
		}
	}

}
