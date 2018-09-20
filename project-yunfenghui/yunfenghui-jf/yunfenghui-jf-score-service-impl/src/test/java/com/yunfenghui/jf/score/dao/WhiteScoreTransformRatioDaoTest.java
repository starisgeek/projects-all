package com.yunfenghui.jf.score.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.test.BaseDaoTest;

public class WhiteScoreTransformRatioDaoTest extends BaseDaoTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private WhiteScoreTransformRatioDao ratioDao;

	@Test
	public void testInsertTransformRatio() {
		int transformDate = 20180920, ratio = 512;
		ratioDao.insertTransformRatio(transformDate, ratio);
		logger.info("insertTransformRatio success");
	}

	@Test
	public void testQueryTransformRatio() {
		int transformDate = 20180920;
		Integer ratio = ratioDao.queryTransformRatio(transformDate);
		logger.info("Found ratio:{} by transformDate:{}", ratio, transformDate);
	}

}
