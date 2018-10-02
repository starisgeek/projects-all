package com.yunfenghui.erp.stock.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.erp.stock.model.Stock;

@Repository
public interface StockDao {
	/**
	 * 新增Stock
	 * 
	 * @param stock
	 */
	void insertStock(Stock stock);

	/**
	 * 批量新增Stock
	 * 
	 * @param stocks
	 */
	void batchInsertStocks(@Param("stocks") List<Stock> stocks);

	/**
	 * 根据商品id查询库存
	 * 
	 * @param goodsId
	 * @return
	 */
	Stock queryStockByGoodsId(@Param("goodsId") int goodsId);

	/**
	 * 根据一组商品id查询商品库存id集合，用于判断商品库存id是否存在
	 * 
	 * @param goodsIds
	 * @return
	 */
	Set<Integer> queryStockGoodsIds(@Param("goodsIds") Set<Integer> goodsIds);

	/**
	 * 增加库存
	 * 
	 * @param goodsId
	 * @param increment
	 * @return
	 */
	int increaseQuantity(@Param("goodsId") int goodsId, @Param("increment") int increment);

	/**
	 * 扣减库存
	 * 
	 * @param goodsId
	 * @param decrement
	 * @return
	 */
	int decreaseStockQuantity(@Param("goodsId") int goodsId, @Param("decrement") int decrement);

	/**
	 * 增加冻结库存
	 * 
	 * @param goodsId
	 * @param increment
	 * @return
	 */
	int increaseFrozenQuantity(@Param("goodsId") int goodsId, @Param("increment") int increment);

	/**
	 * 扣减冻结库存
	 * 
	 * @param goodsId
	 * @param decrement
	 * @return
	 */
	int decreaseFrozenQuantity(@Param("goodsId") int goodsId, @Param("decrement") int decrement);

	/**
	 * 扣减库存和冻结库存数量
	 * 
	 * @param goodsId
	 * @param decrement
	 * @return
	 */
	int decreaseQuantityAndFrozenQuantity(@Param("goodsId") int goodsId,
			@Param("decrement") int decrement);

	/**
	 * 增加quantity,更新latestBuyPrice,latestSupplierId
	 * 
	 * @param goodsId
	 * @param increment
	 * @param latestBuyPrice
	 * @param latestSupplierId
	 * @return
	 */
	int increaseQuantityAndUpdateLatestBuyPriceAndLatestSupplierId(@Param("goodsId") int goodsId,
			@Param("increment") int increment, @Param("latestBuyPrice") int latestBuyPrice,
			@Param("latestSupplierId") int latestSupplierId);
}
