package com.yunfenghui.erp.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.user.model.Permission;

@Repository
public interface PermissionDao {
	/**
	 * 根据category和isMenu标记查询权限
	 * 
	 * @param category
	 * @return
	 */
	List<Permission> queryPermissionsByCategoryAndIsMenu(@Param("category") String category,
			@Param("isMenu") int isMenu);

	/**
	 * 根据category和isMenu标记查询权限
	 * 
	 * @param userId
	 * @return
	 */
	List<Permission> queryPermissionsByUserIdAndIsMenu(@Param("userId") int userId,
			@Param("isMenu") int isMenu);

	/**
	 * 根据用户id查询权限列表
	 * 
	 * @param userId
	 * @return
	 */
	List<Permission> queryPermissionsByUserId(@Param("userId") int userId);
}
