package com.yunfenghui.erp.shop.model;

/**
 * 门店余额账户
 * 
 * @author Administrator
 *
 */
public class BalanceAccount {
	/**
	 * 门店id
	 */
	private int shopId;

	/**
	 * 余额(分)
	 */
	private int balance;

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
