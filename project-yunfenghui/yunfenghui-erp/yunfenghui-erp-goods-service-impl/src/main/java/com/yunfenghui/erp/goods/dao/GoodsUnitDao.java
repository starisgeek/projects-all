package com.yunfenghui.erp.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.goods.model.GoodsUnit;

@Repository
public interface GoodsUnitDao {

	/**
	 * 根据id查询
	 *
	 * @param id
	 * @return
	 */
	GoodsUnit queryGoodsUnitById(@Param("id") int id);

	/**
	 * 查询所有单位
	 * 
	 * @return
	 */
	List<GoodsUnit> queryAllGoodsUnits();

}
