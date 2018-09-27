package com.yunfenghui.erp.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageRowBounds;
import com.yunfenghui.erp.goods.model.Goods;
import com.yunfenghui.erp.goods.service.GoodsService.GoodsQuery;

@Repository
public interface GoodsDao {
	/**
	 * 新增商品
	 * 
	 * @param goods
	 */
	void insertGoods(Goods goods);

	/**
	 * 根据shopId和name查询商品id。主要用于新增商品时验证名称唯一性
	 * 
	 * @param shopId
	 * @param name
	 * @return
	 */
	Integer queryGoodsIdByShopIdAndName(@Param("shopId") int shopId, @Param("name") String name);

	/**
	 * 根据shopId和barcode查询商品id。主要用于新增商品时验证条码唯一性
	 * 
	 * @param shopId
	 * @param barcode
	 * @return
	 */
	Integer queryGoodsIdByShopIdAndBarcode(@Param("shopId") int shopId,
			@Param("barcode") String barcode);

	/**
	 * 根据条件查询Goods
	 * 
	 * @param query
	 * @param rowBounds
	 * @return
	 */
	List<Goods> queryGoodsBy(@Param("query") GoodsQuery query, PageRowBounds rowBounds);
}
