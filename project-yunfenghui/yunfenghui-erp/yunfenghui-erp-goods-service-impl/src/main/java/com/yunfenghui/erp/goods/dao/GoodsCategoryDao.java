package com.yunfenghui.erp.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.goods.model.GoodsCategory;

@Repository
public interface GoodsCategoryDao {
	/**
	 * 根据父id查询下面的类别
	 * 
	 * @param parentId
	 * @return
	 */
	List<GoodsCategory> queryGoodsCategoriesByParentId(@Param("parentId") int parentId);
}
