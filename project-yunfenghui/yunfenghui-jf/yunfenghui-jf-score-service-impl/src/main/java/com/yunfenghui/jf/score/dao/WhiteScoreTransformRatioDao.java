package com.yunfenghui.jf.score.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WhiteScoreTransformRatioDao {
	/**
	 * 新增ratio
	 * 
	 * @param transformDate
	 * @param ratio
	 */
	void insertTransformRatio(@Param("transformDate") int transformDate, @Param("ratio") int ratio);

	/**
	 * 根据transformDate查询ratio
	 * 
	 * @param transformDate
	 * @return
	 */
	Integer queryTransformRatio(@Param("transformDate") int transformDate);
}
