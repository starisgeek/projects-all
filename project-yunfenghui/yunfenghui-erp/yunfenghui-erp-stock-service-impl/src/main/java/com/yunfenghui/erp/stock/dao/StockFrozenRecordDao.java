package com.yunfenghui.erp.stock.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.stock.model.StockFrozenRecord;

@Repository
public interface StockFrozenRecordDao {
	/**
	 * 新增StockFrozenRecord
	 * 
	 * @param frozenRecord
	 */
	void insertFrozenRecord(StockFrozenRecord frozenRecord);

	/**
	 * 根据订单号删除StockFrozenRecord
	 * 
	 * @param orderNo
	 * @return
	 */
	int deleteFrozenRecord(@Param("orderNo") String orderNo);

	/**
	 * 根据订单号查询(包括StockFrozenRecordItem)
	 * 
	 * @param orderNo
	 * @return
	 */
	StockFrozenRecord queryFrozenRecordByOrderNo(@Param("orderNo") String orderNo);

	/**
	 * 查询topN条StockFrozenRecord订单号，必须早于createTime
	 * 
	 * @param topN
	 * @param createTime
	 * @return
	 */
	List<String> queryTopNFrozenRecordNosBefore(@Param("topN") int topN,
			@Param("createTime") Date createTime);
}
