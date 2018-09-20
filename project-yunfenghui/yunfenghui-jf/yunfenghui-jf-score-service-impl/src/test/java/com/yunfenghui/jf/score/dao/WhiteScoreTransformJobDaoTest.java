package com.yunfenghui.jf.score.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.jf.score.model.WhiteScoreTransformJob;
import com.yunfenghui.test.BaseDaoTest;

public class WhiteScoreTransformJobDaoTest extends BaseDaoTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private WhiteScoreTransformJobDao jobDao;

	@Test
	public void testInsertTransformJob() {
		WhiteScoreTransformJob job = new WhiteScoreTransformJob();
		job.setTransformDate(20180920);
		jobDao.insertTransformJob(job);
		logger.info("insertTransformJob success");
	}

	@Test
	public void testQueryTransformJob() {
		int transformDate = 20180920;
		WhiteScoreTransformJob job = jobDao.queryTransformJob(transformDate);
		logger.info("Found job status:{} by transform date:{}",
				job != null ? job.getStatus() : "not found", transformDate);
	}

	@Test
	public void testUpdateTransformJobStatus() {
		int transformDate = 20180920, status = WhiteScoreTransformJob.STATUS_COMPLETED;
		int updated = jobDao.updateTransformJobStatus(transformDate, status);
		logger.info("updated:{} by transformDate:{} and updateStatus:{}", updated, transformDate,
				status);
	}

}
