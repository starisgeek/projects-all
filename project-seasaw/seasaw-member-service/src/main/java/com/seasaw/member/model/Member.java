package com.seasaw.member.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员
 * 
 * @author Administrator
 *
 */
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	/**
	 * 登录名
	 */
	private String name;
	/**
	 * 登录密码
	 */
	private String password;

	private String secret;
	/**
	 * 出生日期
	 */
	private Date bornDate;
	/**
	 * 住址
	 */
	private String address;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
