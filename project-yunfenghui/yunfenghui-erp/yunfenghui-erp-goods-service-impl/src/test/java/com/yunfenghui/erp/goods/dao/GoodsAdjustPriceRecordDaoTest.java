package com.yunfenghui.erp.goods.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.common.service.NumberGenerator;
import com.yunfenghui.erp.goods.model.GoodsAdjustPriceRecord;
import com.yunfenghui.test.BaseDaoTest;

public class GoodsAdjustPriceRecordDaoTest extends BaseDaoTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private GoodsAdjustPriceRecordDao recordDao;
	@Resource(name = "numberGenerator")
	private NumberGenerator ng;

	@Test
	public void testInsertAdjustPriceRecord() {
		GoodsAdjustPriceRecord record = GoodsAdjustPriceRecord.newBuilder().recordNo(ng.generate())
				.shopId(1).goodsId(1).oldPrice(49800).newPrice(50000).createUserId(1)
				.createTime(new Date()).reason("材料上涨").build();
		recordDao.insertAdjustPriceRecord(record);
		logger.info("insertAdjustPriceRecord success");
	}

}
