package com.yunfenghui.jf.partner.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户实体类
 * 
 * @author Administrator
 *
 */
public class Partner implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private int id;
	/**
	 * appId
	 */
	private String appId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 商户编号
	 */
	private String number;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 商户公钥
	 */
	private String publicKey;
	/**
	 * 平台公钥
	 */
	private String platformPublicKey;
	/**
	 * 平台私钥
	 */
	private String platformPrivateKey;
	/**
	 * 备注
	 */
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPlatformPublicKey() {
		return platformPublicKey;
	}

	public void setPlatformPublicKey(String platformPublicKey) {
		this.platformPublicKey = platformPublicKey;
	}

	public String getPlatformPrivateKey() {
		return platformPrivateKey;
	}

	public void setPlatformPrivateKey(String platformPrivateKey) {
		this.platformPrivateKey = platformPrivateKey;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
