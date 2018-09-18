package com.yunfenghui.jf.score.service.support;

import org.springframework.stereotype.Service;

import com.yunfenghui.common.page.Page;
import com.yunfenghui.common.page.PageResult;
import com.yunfenghui.jf.common.JFException;
import com.yunfenghui.jf.score.model.AccountChangeRecord;
import com.yunfenghui.jf.score.model.RedScoreTransformRecord;
import com.yunfenghui.jf.score.service.ScoreService.ChangeRecordQuery;
import com.yunfenghui.jf.score.service.ScoreService.ScoreTransformRecordQuery;

@Service(RedScoreService.ID)
public class RedScoreService {
	public static final String ID = "redScoreService";

	/**
	 * 查询会员红积分余额。
	 * 
	 * @param memberId
	 * @return
	 */
	public Integer getRedScoreBalance(int memberId) {
		return null;
	}

	/**
	 * 根据条件分页查询红积分变动流水
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	public PageResult<AccountChangeRecord> getRedScoreChangeRecords(ChangeRecordQuery query,
			Page page) {
		return null;
	}

	/**
	 * 转换红积分到消费余额
	 * 
	 * @param memberId
	 * @param transformScores
	 * @throws JFException
	 *             会员不存在。红积分余额不足。
	 */
	public void transformAccountRedScore(int memberId, int transformScores) throws JFException {

	}

	/**
	 * 根据条件分页查询红积分转换记录。按照时间降序排序。
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	public PageResult<RedScoreTransformRecord> getRedScoreTransformRecords(
			ScoreTransformRecordQuery query, Page page) {
		return null;
	}
}
