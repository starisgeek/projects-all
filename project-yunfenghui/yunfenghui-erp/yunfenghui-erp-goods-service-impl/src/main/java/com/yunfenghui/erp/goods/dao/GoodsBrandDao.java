package com.yunfenghui.erp.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.goods.model.GoodsBrand;

@Repository
public interface GoodsBrandDao {
	/**
	 * 新增品牌
	 *
	 * @param brand
	 */
	void insertGoodsBrand(GoodsBrand brand);

	/**
	 * 根据id查询
	 *
	 * @param id
	 * @return
	 */
	GoodsBrand queryGoodsBrandById(@Param("id") int id);

	/**
	 * 根据shopId和名称查询品牌。用于创建品牌判断名称唯一性。
	 *
	 * @param shopId
	 * @param name
	 * @param excludeId
	 * @return
	 */
	GoodsBrand queryGoodsBrandByShopIdAndName(@Param("shopId") int shopId,
			@Param("name") String name);

	/**
	 * 根据shopId查询所有品牌
	 * 
	 * @param shopId
	 * @return 品牌集合
	 */
	List<GoodsBrand> queryAllGoodsBrandBy(@Param("shopId") int shopId);
}
