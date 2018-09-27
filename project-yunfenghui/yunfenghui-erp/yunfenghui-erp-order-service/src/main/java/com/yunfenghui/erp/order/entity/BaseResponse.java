package com.yunfenghui.erp.order.entity;

import java.io.Serializable;

/**
 * 响应基类
 * 
 * @author Administrator
 *
 */
public abstract class BaseResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 网关返回码，非业务返回码
	 */
	private String code;
	/**
	 * 返回码描述
	 */
	private String msg;
	/**
	 * 业务返回码
	 */
	private String subCode;
	/**
	 * 业务返回码描述
	 */
	private String subMsg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getSubMsg() {
		return subMsg;
	}

	public void setSubMsg(String subMsg) {
		this.subMsg = subMsg;
	}

}
