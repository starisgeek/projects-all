package com.yunfenghui.jf.score.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.jf.score.model.WhiteScoreTransformRecord;

@Repository
public interface WhiteScoreTransformRecordDao {
	/**
	 * 批量新增WhiteScoreTransformRecord
	 * 
	 * @param transformRecords
	 */
	void batchInsertTransformRecords(
			@Param("transformRecords") List<WhiteScoreTransformRecord> transformRecords);

	/**
	 * 根据时间范围查询白积分转换记录里最大的memberId，可能为null。
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Integer queryMaxMemberIdBy(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
