package com.yunfenghui.erp.stock.service;

import java.util.List;

import com.yunfenghui.common.KeyValue;
import com.yunfenghui.erp.common.ERPException;

/**
 * 库存服务接口
 * 
 * @author Administrator
 *
 */
public interface StockService {

	/**
	 * 根据订单冻结库存数量。用于创建订单时调用。
	 * 增加冻结库存数量。
	 * 
	 * @param orderNo
	 * @param goodsIdAndFreezeQuantityList
	 * @throws ERPException
	 *             订单号已存在。商品不存在。
	 */
	void freezeStock(String orderNo, List<KeyValue<Integer, Integer>> goodsIdAndFreezeQuantityList)
			throws ERPException;

	/**
	 * 解冻库存数量。用于创建订单失败时调用。
	 * 扣减库存冻结数量，删除冻结记录。
	 * 
	 * @param freezeRecordNo
	 */
	void unfreezeStock(String orderNo);

	/**
	 * 根据订单号扣减库存数量。用于订单支付完成时调用。
	 * 扣减库存冻结数量，删除冻结记录及明细，扣减库存数量，添加库存变动明细。
	 * 
	 * @param orderNo
	 */
	void decreaseStock(String orderNo);
}
