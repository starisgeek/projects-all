package com.yunfenghui.erp.stock.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.common.KeyValue;
import com.yunfenghui.erp.stock.model.Supplier;
import com.yunfenghui.test.BaseDaoTest;

public class SupplierDaoTest extends BaseDaoTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SupplierDao supplierDao;

	@Test
	public void testInsertSupplier() {
		Supplier supplier = new Supplier();
		supplier.setName("中国Selected代工厂");
		supplier.setLinkman("小明");
		supplier.setLinkmanPhone("13123456789");
		supplier.setLocation("中国杭州");
		supplier.setShopId(1);
		supplier.setCreateTime(new Date());
		supplier.setRemark("唯一授权");
		supplierDao.insertSupplier(supplier);
		logger.info("insertSupplier:{} success", supplier.getId());
	}

	@Test
	public void testQuerySupplierById() {
		int id = 1;
		Supplier supplier = supplierDao.querySupplierById(id);
		logger.info("Found supplier:{} by id:{}",
				supplier != null ? supplier.getName() : "not found", id);
	}

	@Test
	public void testQuerySupplierByShopIdAndName() {
		int shopId = 1;
		String name = "中国Selected代工厂";
		Supplier supplier = supplierDao.querySupplierByShopIdAndName(shopId, name);
		logger.info("Found supplier location:{} by shopId:{} and name:{}",
				supplier != null ? supplier.getLocation() : "not found", shopId, name);
	}

	@Test
	public void testQueryAllSupplierIdAndNames() {
		int shopId = 1;
		List<KeyValue<Integer, String>> idAndNames = supplierDao.queryAllSupplierIdAndNames(shopId);
		if (idAndNames != null && !idAndNames.isEmpty()) {
			for (KeyValue<Integer, String> idAndName : idAndNames) {
				logger.info("Found id:{} and name:{} by shopId:{}", idAndName.getKey(),
						idAndName.getValue(), shopId);
			}
		} else {
			logger.info("IdAndNames not found");
		}
	}

}
