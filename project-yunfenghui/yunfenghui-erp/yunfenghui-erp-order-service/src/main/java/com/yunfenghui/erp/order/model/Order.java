package com.yunfenghui.erp.order.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.yunfenghui.common.KeyValue;

/**
 * 收银订单
 * 
 * @author Administrator
 *
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 订单明细
	 */
	private List<OrderItem> items;
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
	 * 订单总金额
	 */
	private int totalAmount;
	/**
	 * 总退款金额
	 */
	private int totalRefundAmount;
	/**
	 * 总发放积分
	 */
	private int totalSendScores;
	/**
	 * 外部交易号
	 */
	private String outTradeNo;
	/**
	 * 支付方式
	 */
	private int payWay = PAY_WAY_UNKNOWN;
	/**
	 * 状态
	 */
	private int status = STATUS_WAIT_PAY;
	/**
	 * 订单创建人id
	 */
	private int createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date modifyTime;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

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

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalRefundAmount() {
		return totalRefundAmount;
	}

	public void setTotalRefundAmount(int totalRefundAmount) {
		this.totalRefundAmount = totalRefundAmount;
	}

	public int getTotalSendScores() {
		return totalSendScores;
	}

	public void setTotalSendScores(int totalSendScores) {
		this.totalSendScores = totalSendScores;
	}

	public int getPayWay() {
		return payWay;
	}

	public void setPayWay(int payWay) {
		this.payWay = payWay;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {
		private final Order order;

		private Builder() {
			this.order = new Order();
		}

		public Builder orderNo(String orderNo) {
			order.setOrderNo(orderNo);
			return this;
		}

		public Builder items(List<OrderItem> items) {
			order.setItems(items);
			return this;
		}

		public Builder shopId(int shopId) {
			order.setShopId(shopId);
			return this;
		}

		public Builder memberId(int memberId) {
			order.setMemberId(memberId);
			return this;
		}

		public Builder memberPhone(String memberPhone) {
			order.setMemberPhone(memberPhone);
			return this;
		}

		public Builder memberName(String memberName) {
			order.setMemberName(memberName);
			return this;
		}

		public Builder totalAmount(int totalAmount) {
			order.setTotalAmount(totalAmount);
			return this;
		}

		public Builder totalRefundAmount(int totalRefundAmount) {
			order.setTotalRefundAmount(totalRefundAmount);
			return this;
		}

		public Builder totalSendScores(int totalSendScores) {
			order.setTotalSendScores(totalSendScores);
			return this;
		}

		public Builder payWay(int payWay) {
			order.setPayWay(payWay);
			return this;
		}

		public Builder status(int status) {
			order.setStatus(status);
			return this;
		}

		public Builder createUserId(int createUserId) {
			order.setCreateUserId(createUserId);
			return this;
		}

		public Builder createTime(Date createTime) {
			order.setCreateTime(createTime);
			return this;
		}

		public Builder modifyTime(Date modifyTime) {
			order.setModifyTime(modifyTime);
			return this;
		}

		public Order build() {
			return order;
		}
	}

	public static final int PAY_WAY_UNKNOWN = 0;
	public static final int PAY_WAY_CASH = 1;
	public static final int PAY_WAY_ALI = 2;
	public static final int PAY_WAY_WECHAT = 3;

	private static final Map<Integer, KeyValue<Integer, String>> PAY_WAY_MAP = new TreeMap<>();
	static {
		PAY_WAY_MAP.put(PAY_WAY_UNKNOWN, new KeyValue<>(PAY_WAY_UNKNOWN, "未支付"));
		PAY_WAY_MAP.put(PAY_WAY_CASH, new KeyValue<>(PAY_WAY_CASH, "现金"));
		PAY_WAY_MAP.put(PAY_WAY_ALI, new KeyValue<>(PAY_WAY_ALI, "支付宝"));
		PAY_WAY_MAP.put(PAY_WAY_WECHAT, new KeyValue<>(PAY_WAY_WECHAT, "微信支付"));
	}

	public static final int STATUS_WAIT_PAY = 1;
	public static final int STATUS_TRADE_SUCCESS = 2;
	public static final int STATUS_TRADE_CLOSED = 3;
	public static final int STATUS_TRADE_FINISHED = 9;

	private static final Map<Integer, KeyValue<Integer, String>> STATUS_MAP = new TreeMap<>();
	static {
		STATUS_MAP.put(STATUS_WAIT_PAY, new KeyValue<>(STATUS_WAIT_PAY, "待付款"));
		STATUS_MAP.put(STATUS_TRADE_SUCCESS, new KeyValue<>(STATUS_TRADE_SUCCESS, "已付款"));
		STATUS_MAP.put(STATUS_TRADE_CLOSED, new KeyValue<>(STATUS_TRADE_CLOSED, "已关闭"));
		STATUS_MAP.put(STATUS_TRADE_FINISHED, new KeyValue<>(STATUS_TRADE_FINISHED, "已完成"));
	}

	public static List<KeyValue<Integer, String>> getStatuses() {
		return new ArrayList<>(STATUS_MAP.values());
	}
}
