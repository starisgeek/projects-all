package com.yunfenghui.erp.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.user.model.User;

@Repository
public interface UserDao {
	/**
	 * 新增User
	 * 
	 * @param user
	 */
	void insertUser(User user);

	/**
	 * 根据id查询User
	 * 
	 * @param id
	 * @return
	 */
	User queryUserById(@Param("id") int id);

	/**
	 * 根据登录手机号和门店id查询
	 * 
	 * @param loginPhone
	 * @param shopId
	 * @return
	 */
	User queryUserByLoginPhoneAndShopId(@Param("loginPhone") String loginPhone,
			@Param("shopId") int shopId);

	/**
	 * 根据shopId和isSuper查询总数
	 * 
	 * @param shopId
	 * @param isSuper
	 * @return
	 */
	int queryCountByShopIdAndIsSuper(@Param("shopId") int shopId, @Param("isSuper") int isSuper);
}
