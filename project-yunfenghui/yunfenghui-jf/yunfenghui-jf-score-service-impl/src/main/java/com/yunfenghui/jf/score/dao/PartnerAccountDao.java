package com.yunfenghui.jf.score.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.jf.score.model.PartnerAccount;

@Repository
public interface PartnerAccountDao {
	/**
	 * 新增账户
	 * 
	 * @param account
	 */
	void insertAccount(PartnerAccount account);

	/**
	 * 根据账户id查询
	 * 
	 * @param partnerId
	 * @return
	 */
	PartnerAccount queryAccountById(@Param("partnerId") int partnerId);

	/**
	 * 同时增加库存积分和白积分。提供商户充值时调用。
	 * 
	 * @param partnerId
	 * @param stockScoreIncrement
	 * @param whiteScoreIncrement
	 * @return
	 */
	int increaseStockScoreAndWhiteScore(@Param("partnerId") int partnerId,
			@Param("stockScoreIncrement") int stockScoreIncrement,
			@Param("whiteScoreIncrement") int whiteScoreIncrement);

	/**
	 * 增加库存积分
	 * 
	 * @param partnerId
	 * @param increment
	 * @return
	 */
	int increaseStockScore(@Param("partnerId") int partnerId, @Param("increment") int increment);

	/**
	 * 扣减库存积分
	 * 
	 * @param partnerId
	 * @param decrement
	 * @return
	 */
	int decreaseStockScore(@Param("partnerId") int partnerId, @Param("decrement") int decrement);

	/**
	 * 增加白积分
	 * 
	 * @param partnerId
	 * @param increment
	 * @return
	 */
	int increaseWhiteScore(@Param("partnerId") int partnerId, @Param("increment") int increment);

	/**
	 * 扣减白积分
	 * 
	 * @param partnerId
	 * @param decrement
	 * @return
	 */
	int decreaseWhiteScore(@Param("partnerId") int partnerId, @Param("decrement") int decrement);

	/**
	 * 增加红积分
	 * 
	 * @param partnerId
	 * @param increment
	 * @return
	 */
	int increaseRedScore(@Param("partnerId") int partnerId, @Param("increment") int increment);

	/**
	 * 扣减红积分
	 * 
	 * @param partnerId
	 * @param decrement
	 * @return
	 */
	int decreaseRedScore(@Param("partnerId") int partnerId, @Param("decrement") int decrement);

	/**
	 * 增加余额
	 * 
	 * @param partnerId
	 * @param increment
	 * @return
	 */
	int increaseBalance(@Param("partnerId") int partnerId, @Param("increment") int increment);

	/**
	 * 扣减余额
	 * 
	 * @param partnerId
	 * @param decrement
	 * @return
	 */
	int decreaseBalance(@Param("partnerId") int partnerId, @Param("decrement") int decrement);
}
