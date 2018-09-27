package com.yunfenghui.erp.goods.util;

public interface GoodsMessageCode {
	/**
	 * 商品名称已存在
	 */
	String GOODS_NAME_EXISTS = "erp.goods.name.exists";

	/**
	 * 商品条码已存在
	 */
	String GOODS_BARCODE_EXISTS = "erp.goods.barcode.exists";

	/**
	 * 商品不存在
	 */
	String GOODS_NOT_EXISTS = "erp.goods.not.exists";

	/**
	 * 商品不存在或状态错误
	 */
	String GOODS_NOT_EXISTS_OR_STATUS_ERROR = "erp.goods.not.exist.or.status.error";
}
