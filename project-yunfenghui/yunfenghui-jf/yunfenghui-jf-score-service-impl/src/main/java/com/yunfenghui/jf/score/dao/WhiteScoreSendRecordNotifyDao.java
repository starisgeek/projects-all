package com.yunfenghui.jf.score.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.jf.score.model.WhiteScoreSendRecordNotify;

@Repository
public interface WhiteScoreSendRecordNotifyDao {
	/**
	 * 批量新增通知
	 * 
	 * @param notifies
	 */
	void batchInsertNotifies(@Param("notifies") List<WhiteScoreSendRecordNotify> notifies);

	/**
	 * 根据一组recordNo删除通知
	 * 
	 * @param recordNos
	 */
	int batchDeleteNotifies(@Param("recordNos") List<String> recordNos);
}
