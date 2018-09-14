package com.yunfenghui.jf.score.dao;

import org.springframework.stereotype.Repository;

import com.yunfenghui.jf.score.model.StockScoreRechargeRecord;

@Repository
public interface PartnerStockScoreRechargeRecordDao {
	/**
	 * 新增充值记录
	 * 
	 * @param rechargeRecord
	 */
	void insertRechargeRecord(StockScoreRechargeRecord rechargeRecord);
}
