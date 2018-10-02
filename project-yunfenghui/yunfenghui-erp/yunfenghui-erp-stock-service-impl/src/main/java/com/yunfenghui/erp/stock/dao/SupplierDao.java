package com.yunfenghui.erp.stock.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.common.KeyValue;
import com.yunfenghui.erp.stock.model.Supplier;

@Repository
public interface SupplierDao {
	/**
	 * 新增Supplier
	 * 
	 * @param supplier
	 */
	void insertSupplier(Supplier supplier);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	Supplier querySupplierById(@Param("id") int id);

	/**
	 * 根据shopId和名称查询供应商。用于创建供应商判断名称唯一性。
	 * 
	 * @param shopId
	 * @param name
	 * @param excludeId
	 * @return
	 */
	Supplier querySupplierByShopIdAndName(@Param("shopId") int shopId, @Param("name") String name);

	/**
	 * 根据shopId查询所有供应商id和name列表
	 * 
	 * @param shopId
	 * @return
	 */
	List<KeyValue<Integer, String>> queryAllSupplierIdAndNames(@Param("shopId") int shopId);

	/**
	 * 根据条件分页查询供应商
	 * 
	 * @param query
	 * @param rowBounds
	 * @return
	 */
	// List<Supplier> querySupplierBy(@Param("query") SupplierQuery query,
	// PageRowBounds rowBounds);
}
