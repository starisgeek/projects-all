package com.yunfenghui.erp.user.model;

import java.io.Serializable;
import java.util.Date;

/**
 * User
 * 
 * @author Administrator
 *
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String loginPhone;
	private String password;
	private String passwordSalt;
	private int shopId;
	// private Role role;
	private String category;
	// 是否为超级管理员(1-是,0-否)
	private int isSuper;
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginPhone() {
		return loginPhone;
	}

	public void setLoginPhone(String loginPhone) {
		this.loginPhone = loginPhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	// public Role getRole() {
	// return role;
	// }
	//
	// public void setRole(Role role) {
	// this.role = role;
	// }

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getIsSuper() {
		return isSuper;
	}

	public void setIsSuper(int isSuper) {
		this.isSuper = isSuper;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
