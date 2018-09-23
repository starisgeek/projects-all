package com.yunfenghui.jf.job;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yunfenghui.common.DateTimes;
import com.yunfenghui.jf.score.model.MemberAccount;
import com.yunfenghui.jf.score.model.WhiteScoreTransformJob;
import com.yunfenghui.jf.score.service.ScoreService;

@Service("whiteScoreTransformDayJob")
public class WhiteScoreTransformDayJob {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource(name = ScoreService.ID)
	private ScoreService scoreService;
	private int maxBatchSize = 1000;

	public void execute() {
		logger.info("{} start execute", getClass().getSimpleName());
		int transformDate = DateTimes.dayIntOf(new Date());
		WhiteScoreTransformJob job = scoreService.getOrCreateWhiteScoreTransformJob(transformDate);
		if (job.getStatus() != WhiteScoreTransformJob.STATUS_COMPLETED) {
			Date now = new Date(), startTime = DateTimes.earliestOf(now),
					endTime = DateTimes.latestOf(now);
			Integer maxMemberId = scoreService
					.getMaxMemberIdOfWhiteScoreTransformRecordsBy(startTime, endTime);
			logger.info("Found max memberId:{}", maxMemberId);
			int memberId = maxMemberId == null ? 0 : maxMemberId;
			for (;;) {
				List<MemberAccount> accounts = scoreService.getTopNMemberAccounts(maxBatchSize,
						memberId, 10000, startTime);
				if (accounts != null && !accounts.isEmpty()) {
					logger.info("Found memberAccount size:{}", accounts.size());
					if (accounts.size() == maxBatchSize) {
						scoreService.batchTransformWhiteScore(accounts, transformDate);
						memberId = accounts.get(accounts.size() - 1).getMemberId();
					} else {
						scoreService.batchTransformWhiteScoreAndCompleteWhiteScoreTransformJob(
								accounts, transformDate);
						break;
					}
				} else {
					scoreService.completeWhiteScoreTransformJob(transformDate);
					break;
				}
			}

		}
		logger.info("{} completed, transform date:{}", getClass().getSimpleName(), transformDate);
	}

	public void setScoreService(ScoreService scoreService) {
		this.scoreService = scoreService;
	}

}
