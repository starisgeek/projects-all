package com.yunfenghui.erp.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.shop.model.Shop;

@Repository
public interface ShopDao {
	/**
	 * 新增Shop
	 * 
	 * @param shop
	 */
	void insertShop(Shop shop);

	/**
	 * 根据id查询Shop
	 * 
	 * @param id
	 * @return
	 */
	Shop queryShopById(@Param("id") int id);

	/**
	 * 根据number查询Shop
	 * 
	 * @param number
	 * @return
	 */
	Shop queryShopByNumber(@Param("number") String number);

	/**
	 * 根据名称查询.用于新增Shop时判断名称是否存在
	 * 
	 * @param name
	 * @return
	 */
	String queryShopName(@Param("name") String name);

	/**
	 * 查询所有门店
	 * 
	 * @return
	 */
	List<Shop> queryAllShops();
}
