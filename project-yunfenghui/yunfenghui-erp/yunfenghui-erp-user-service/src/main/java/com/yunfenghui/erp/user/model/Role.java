package com.yunfenghui.erp.user.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	// 主键
	private int id;
	// 名称
	private String name;
	// 门店id
	private int shopId;
	// 创建时间
	private Date createTime;
	// 权限列表
	private List<Permission> permissions;
	// 备注
	private String remark;

	public static final String SUPER = "超级管理员";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
