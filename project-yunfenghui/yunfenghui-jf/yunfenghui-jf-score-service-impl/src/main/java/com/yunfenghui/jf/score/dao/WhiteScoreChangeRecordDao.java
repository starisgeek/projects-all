package com.yunfenghui.jf.score.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.jf.score.model.AccountChangeRecord;

@Repository
public interface WhiteScoreChangeRecordDao {

	/**
	 * 新增白积分变动流水
	 * 
	 * @param changeRecord
	 */
	void insertChangeRecord(AccountChangeRecord changeRecord);

	/**
	 * 批量新增白积分变动流水
	 * 
	 * @param changeRecords
	 */
	void batchInsertChangeRecords(@Param("changeRecords") List<AccountChangeRecord> changeRecords);
}
