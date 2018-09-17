package com.yunfenghui.jf.score.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.jf.score.model.WhiteScoreSendRecord;

@Repository
public interface WhiteScoreSendRecordDao {
	/**
	 * 新增白积分发放记录
	 * 
	 * @param sendRecord
	 */
	void insertSendRecord(WhiteScoreSendRecord sendRecord);

	/**
	 * 修改记录状态
	 * 
	 * @param recordNo
	 * @param expectStatus
	 * @param updateStatus
	 * @param errorCode
	 * @param modifyTime
	 * @return
	 */
	int updateSendRecordStatusAndErrorCode(@Param("recordNo") String recordNo,
			@Param("expectStatus") int expectStatus, @Param("updateStatus") int updateStatus,
			@Param("errorCode") String errorCode, @Param("modifyTime") Date modifyTime);

	/**
	 * 根据商户id和外部交易号查询白积分发放记录号。主要用于发放白积分时验证是否存在.
	 * 
	 * @param partnerId
	 * @param outTradeNo
	 * @return
	 */
	String queryRecordNoByPartnerIdAndOutTradeNo(@Param("partnerId") int partnerId,
			@Param("outTradeNo") String outTradeNo);

	/**
	 * 根据recordNo查询白积分发放记录。
	 * 
	 * @param recordNo
	 * @return
	 */
	WhiteScoreSendRecord querySendRecordByRecordNo(@Param("recordNo") String recordNo);

	/**
	 * 根据商户id和外部交易号查询白积分发放记录。
	 * 
	 * @param partnerId
	 * @param outTradeNo
	 * @return
	 */
	WhiteScoreSendRecord querySendRecordByPartnerIdAndOutTradeNo(@Param("partnerId") int partnerId,
			@Param("outTradeNo") String outTradeNo);
}
