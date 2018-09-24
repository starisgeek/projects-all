package com.yunfenghui.erp.shop.dao;

import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.shop.model.BalanceAccount;

@Repository
public interface BalanceAccountDao {
	/**
	 * 新增余额账户
	 * 
	 * @param balanceAccount
	 */
	void insertBalanceAccount(BalanceAccount balanceAccount);
}
