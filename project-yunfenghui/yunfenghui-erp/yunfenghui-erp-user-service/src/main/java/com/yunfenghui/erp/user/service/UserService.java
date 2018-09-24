package com.yunfenghui.erp.user.service;

import java.util.List;
import java.util.Set;

import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.user.model.Permission;
import com.yunfenghui.erp.user.model.Role;
import com.yunfenghui.erp.user.model.User;

public interface UserService {
	String ID = "userService";

	/**
	 * 创建新用户(相同的门店登陆手机号必须唯一，一个门店只能有一个超级管理员)
	 * 
	 * @param user
	 * @throws ERPException
	 *             手机号已存在，超级管理员已存在
	 */
	void createUser(User user) throws ERPException;

	/**
	 * 根据手机号和shopId查询User。主要用于用户登录。
	 * 
	 * @param loginPhone
	 * @return
	 */
	User getUserByLoginPhoneAndShopId(String loginPhone, int shopId);

	/**
	 * 根据id查询User
	 * 
	 * @param id
	 * @return
	 */
	User getUserById(int id);

	/**
	 * 创建角色
	 * 
	 * @param role
	 * @throws ERPException
	 *             角色名称已存在，角色不包含任何权限
	 */
	void createRole(Role role) throws ERPException;

	/**
	 * 根据角色id查询Role
	 * 
	 * @param id
	 * @return
	 */
	Role getRoleById(int id);

	/**
	 * 根据门店id查询所有角色
	 * 
	 * @param shopId
	 * @return
	 */
	List<Role> getAllRolesByShopId(int shopId);

	/**
	 * 根据userId查询权限树。主要用于门店、平台用户登录后台系统显示菜单树。
	 * 
	 * @param userId
	 * @return
	 */
	Permission getPermissionsAsMenuTree(int userId);

	/**
	 * 根据角色id查询所有权限
	 * 
	 * @param roleId
	 * @return
	 */
	Permission getPermissionsAsMenuByRoleId(int roleId);

	/**
	 * 根据userId查询所有权限.用于鉴权
	 * 
	 * @param userId
	 * @return
	 */
	Set<String> getAllPermissionStrings(int userId);
}
