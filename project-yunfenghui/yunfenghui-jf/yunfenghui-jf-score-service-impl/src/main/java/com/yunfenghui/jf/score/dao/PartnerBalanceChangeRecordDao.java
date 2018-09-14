package com.yunfenghui.jf.score.dao;

import org.springframework.stereotype.Repository;

import com.yunfenghui.jf.score.model.AccountChangeRecord;

@Repository
public interface PartnerBalanceChangeRecordDao {
	/**
	 * 新增商户余额变动流水
	 * 
	 * @param changeRecord
	 */
	void insertChangeRecord(AccountChangeRecord changeRecord);
}
