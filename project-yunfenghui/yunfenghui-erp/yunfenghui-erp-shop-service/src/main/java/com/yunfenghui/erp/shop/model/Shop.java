package com.yunfenghui.erp.shop.model;

import java.io.Serializable;
import java.util.Date;

import com.yunfenghui.erp.common.Commons;

/**
 * 门店
 * 
 * @author Administrator
 *
 */
public class Shop implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 门店id
	 */
	private int id;
	/**
	 * 编号(唯一)
	 */
	private String number;
	/**
	 * 名称(唯一)
	 */
	private String name;
	/**
	 * 云风商城会员id
	 */
	private long memberId;
	/**
	 * 云风商城会员手机号
	 */
	private String memberPhone;
	/**
	 * 详细地址
	 */
	private String location;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 账户余额
	 */
	private BalanceAccount balanceAccount;
	/**
	 * 备注
	 */
	private String remark;

	public static final Shop PLATFORM_SHOP = new Shop(Commons.PLATFORM_SHOPID);

	public Shop() {

	}

	public Shop(int id) {
		this.id = id;
	}

	public Shop(int id, String number) {
		this.id = id;
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BalanceAccount getBalanceAccount() {
		return balanceAccount;
	}

	public void setBalanceAccount(BalanceAccount balanceAccount) {
		this.balanceAccount = balanceAccount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
