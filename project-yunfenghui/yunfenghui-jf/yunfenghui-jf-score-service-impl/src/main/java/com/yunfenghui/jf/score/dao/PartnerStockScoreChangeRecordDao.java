package com.yunfenghui.jf.score.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.jf.score.model.AccountChangeRecord;

@Repository
public interface PartnerStockScoreChangeRecordDao {
	/**
	 * 新增商户充值记录变动流水
	 * 
	 * @param changeRecord
	 */
	void insertChangeRecord(AccountChangeRecord changeRecord);

	/**
	 * 批量新增商户充值记录变动流水
	 * 
	 * @param changeRecords
	 */
	void batchInsertChangeRecords(@Param("changeRecords") List<AccountChangeRecord> changeRecords);
}
