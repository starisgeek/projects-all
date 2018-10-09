package com.yunfenghui.erp.order.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.order.model.RefundRecord;

@Repository
public interface RefundRecordDao {
	/**
	 * 新增RefundRecord
	 * 
	 * @param record
	 */
	void insertRefundRecord(RefundRecord record);

	/**
	 * 修改RefundRecord状态和outRefundTradeNo
	 * 
	 * @param refundRecordNo
	 * @param expectedStatus
	 * @param updatedStatus
	 * @param outRefundTradeNo
	 * @param modifyTime
	 * @return
	 */
	int updateRefundRecordStatusAndOutRefundTradeNo(@Param("refundRecordNo") String refundRecordNo,
			@Param("expectedStatus") int expectedStatus, @Param("updatedStatus") int updatedStatus,
			@Param("outRefundTradeNo") String outRefundTradeNo,
			@Param("modifyTime") Date modifyTime);
}
