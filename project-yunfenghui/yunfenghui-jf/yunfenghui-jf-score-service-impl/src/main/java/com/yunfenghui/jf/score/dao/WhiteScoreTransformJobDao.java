package com.yunfenghui.jf.score.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.jf.score.model.WhiteScoreTransformJob;

@Repository
public interface WhiteScoreTransformJobDao {
	/**
	 * 新增WhiteScoreTransformJob
	 * 
	 * @param job
	 */
	void insertTransformJob(WhiteScoreTransformJob job);

	/**
	 * 根据日期查询WhiteScoreTransformJob
	 * 
	 * @param transformDate
	 * @return
	 */
	WhiteScoreTransformJob queryTransformJob(@Param("transformDate") int transformDate);

	/**
	 * 更新状态
	 * 
	 * @param transformDate
	 * @param status
	 * @return
	 */
	int updateTransformJobStatus(@Param("transformDate") int transformDate,
			@Param("status") int status);
}
