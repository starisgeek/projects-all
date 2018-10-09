package com.yunfenghui.erp.order.util;

public interface OrderMessageCode {
	/**
	 * 订单不存在
	 */
	String ORDER_NOT_EXISTS = "erp.order.not.exists";
	/**
	 * 订单不存在或者状态错误
	 */
	String ORDER_NOT_EXISTS_OR_STATUS_ERROR = "erp.order.not.exists.or.status.error";
	/**
	 * 退款订单项数量超出购买数量
	 */
	String REFUND_ORDER_ITEM_QUANTITY_OVERFLOW = "erp.refund.order.item.quantity.overflow";

	/**
	 * 退款订单项在原订单中不存在
	 */
	String REFUND_ORDER_ITEM_NOT_FOUND_IN_ORDER = "erp.refund.order.item.not.found.in.order";
}
