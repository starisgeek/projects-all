package com.yunfenghui.jf.score.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.jf.score.model.MemberAccount;

@Repository
public interface MemberAccountDao {
	/**
	 * 新增账户
	 * 
	 * @param account
	 */
	void insertAccount(MemberAccount account);

	/**
	 * 根据账户id查询
	 * 
	 * @param memberId
	 * @return
	 */
	MemberAccount queryAccountById(@Param("memberId") int memberId);

	/**
	 * 根据会员id查询白积分余额
	 * 
	 * @param memberId
	 * @return
	 */
	Integer queryWhiteScores(@Param("memberId") int memberId);

	/**
	 * 根据会员id查询白积分余额
	 * 
	 * @param memberId
	 * @return
	 */
	Integer queryRedScores(@Param("memberId") int memberId);

	/**
	 * 根据会员id查询消费余额
	 * 
	 * @param memberId
	 * @return
	 */
	Integer queryBalance(@Param("memberId") int memberId);

	/**
	 * 增加白积分
	 * 
	 * @param memberId
	 * @param increment
	 * @return
	 */
	int increaseWhiteScore(@Param("memberId") int memberId, @Param("increment") int increment);

	/**
	 * 扣减白积分
	 * 
	 * @param memberId
	 * @param decrement
	 * @return
	 */
	int decreaseWhiteScore(@Param("memberId") int memberId, @Param("decrement") int decrement);

	/**
	 * 增加红积分
	 * 
	 * @param memberId
	 * @param increment
	 * @return
	 */
	int increaseRedScore(@Param("memberId") int memberId, @Param("increment") int increment);

	/**
	 * 扣减红积分
	 * 
	 * @param memberId
	 * @param decrement
	 * @return
	 */
	int decreaseRedScore(@Param("memberId") int memberId, @Param("decrement") int decrement);

	/**
	 * 增加余额
	 * 
	 * @param memberId
	 * @param increment
	 * @return
	 */
	int increaseBalance(@Param("memberId") int memberId, @Param("increment") int increment);

	/**
	 * 扣减余额
	 * 
	 * @param memberId
	 * @param decrement
	 * @return
	 */
	int decreaseBalance(@Param("memberId") int memberId, @Param("decrement") int decrement);
}
