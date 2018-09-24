package com.yunfenghui.erp.shop.service;

import java.util.List;

import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.shop.model.Shop;

public interface ShopService {
	String ID = "shopService";

	/**
	 * 门店编号长度
	 */
	int SHOP_NUMBER_LENGTH = 5;

	/**
	 * 创建门店(同时会创建余额账户)
	 * 
	 * @param shop
	 * @throws ERPException
	 *             门店名称已存在
	 */
	void createShop(Shop shop) throws ERPException;

	/**
	 * 根据门店id查询Shop
	 * 
	 * @param id
	 * @return
	 */
	Shop getShopById(int id);

	/**
	 * 根据门店number查询Shop
	 * 
	 * @param number
	 * @return
	 */
	Shop getShopByNumber(String number);

	/**
	 * 查询所有门店
	 * 
	 * @return
	 */
	List<Shop> getAllShops();
}
