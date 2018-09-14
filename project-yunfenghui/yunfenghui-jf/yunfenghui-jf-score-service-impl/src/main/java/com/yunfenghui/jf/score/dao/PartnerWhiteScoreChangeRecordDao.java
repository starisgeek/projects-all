package com.yunfenghui.jf.score.dao;

import org.springframework.stereotype.Repository;

import com.yunfenghui.jf.score.model.AccountChangeRecord;

@Repository
public interface PartnerWhiteScoreChangeRecordDao {
	/**
	 * 新增商户白积分变动流水
	 * 
	 * @param changeRecord
	 */
	void insertChangeRecord(AccountChangeRecord changeRecord);
}
