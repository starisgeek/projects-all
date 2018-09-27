package com.yunfenghui.erp.stock.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 供应商
 * 
 * @author wanghao
 *         2018年4月18日
 */
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private int id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 联系人名称
	 */
	private String linkman;
	/**
	 * 联系电话
	 */
	private String linkmanPhone;
	/**
	 * 地址
	 */
	private String location;
	/**
	 * 所属门店id
	 */
	private int shopId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 备注
	 */
	private String remark;

	public static final Supplier EMPTY = new Supplier(0);

	public Supplier() {
	}

	public Supplier(int id) {
		this.id = id;
	}

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

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkmanPhone() {
		return linkmanPhone;
	}

	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
