package com.yunfenghui.erp.order.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 订单创建请求
 * 
 * @author Administrator
 *
 */
public class OrderCreateRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 门店id
	 */
	private int shopId;
	/**
	 * 会员id
	 */
	private int memberId;
	/**
	 * 会员手机号
	 */
	private String memberPhone;
	/**
	 * 会员名称
	 */
	private String memberName;
	/**
	 * 订单创建人id
	 */
	private int createUserId;

	private List<OrderItemCreateRequest> itemCreateRequests;

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public List<OrderItemCreateRequest> getItemCreateRequests() {
		return itemCreateRequests;
	}

	public void setItemCreateRequests(List<OrderItemCreateRequest> itemCreateRequests) {
		this.itemCreateRequests = itemCreateRequests;
	}

}
