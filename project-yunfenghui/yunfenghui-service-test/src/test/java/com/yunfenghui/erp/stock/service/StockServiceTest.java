package com.yunfenghui.erp.stock.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.yunfenghui.common.KeyValue;
import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.stock.model.StockRecord;
import com.yunfenghui.erp.stock.model.StockRecordItem;
import com.yunfenghui.erp.stock.model.Supplier;

@ContextConfiguration("classpath:stock-service-consumer.xml")
public class StockServiceTest extends AbstractJUnit4SpringContextTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource(name = StockService.ID)
	private StockService stockService;

	@Test
	public void testCreateStockRecord() {
		StockRecord record = new StockRecord();
		record.setCreateTime(new Date());
		record.setCreateUserId(1);
		record.setShopId(1);
		record.setSupplier(new Supplier(1));
		record.setRemark("上新货");
		List<StockRecordItem> items = new ArrayList<>();
		record.setItems(items);
		StockRecordItem item = new StockRecordItem();
		item.setGoodsId(1);
		item.setBuyPrice(26000);
		item.setBuyQuantity(10);
		item.setPresentQuantity(0);
		items.add(item);
		item = new StockRecordItem();
		item.setGoodsId(2);
		item.setBuyPrice(12000);
		item.setBuyQuantity(15);
		item.setPresentQuantity(1);
		items.add(item);
		try {
			String recordNo = stockService.createStockRecord(record);
			logger.info("createStockRecord:{} success", recordNo);
		} catch (ERPException e) {
			logger.error("Failed to createStockRecord", e);
		}
	}

	@Test
	public void testGetStockRecordByNo() {
		String recordNo = "5bb373d5f6bc368ee9b8464e";
		StockRecord record = stockService.getStockRecordByNo(recordNo);
		if (record != null) {
			logger.info("Found stock record totalAmount:{}", record.getTotalAmount());
			Supplier supplier = record.getSupplier();
			logger.info("Found supplier:{}", supplier.getName());
			for (StockRecordItem item : record.getItems()) {
				logger.info(
						"Found item buyQuantity:{}, buyPrice:{}, presentQuantity:{}, totalAmount:{}",
						item.getBuyQuantity(), item.getBuyPrice(), item.getPresentQuantity(),
						item.getTotalAmount());
			}
		} else {
			logger.info("StockRecord:{} not found", recordNo);
		}
	}

	@Test
	public void testGetStockRecordItems() {
		String recordNo = "5bb373d5f6bc368ee9b8464e";
		List<StockRecordItem> items = stockService.getStockRecordItems(recordNo);
		if (items != null && !items.isEmpty()) {
			for (StockRecordItem item : items) {
				logger.info(
						"Found item buyQuantity:{}, buyPrice:{}, presentQuantity:{}, totalAmount:{}",
						item.getBuyQuantity(), item.getBuyPrice(), item.getPresentQuantity(),
						item.getTotalAmount());
			}
		} else {
			logger.info("StockRecordItems not found by:{}", recordNo);
		}
	}

	@Test
	public void testGenerateRecordNo() {
		int shopId = 1;
		String recordNo = stockService.generateRecordNo(shopId);
		logger.info("generate recordNo:{} by shopId:{}", recordNo, shopId);
	}

	@Test
	public void testGetStockRecordsBy() {
		fail("Not yet implemented");
	}

	@Test
	public void testFreezeStock() {
		String orderNo = String.valueOf(System.currentTimeMillis());
		List<KeyValue<Integer, Integer>> goodsIdAndFreezeQuantityList = new ArrayList<>();
		goodsIdAndFreezeQuantityList.add(new KeyValue<Integer, Integer>(1, 2));
		goodsIdAndFreezeQuantityList.add(new KeyValue<Integer, Integer>(2, 5));
		int shopId = 1;
		try {
			stockService.freezeStock(orderNo, goodsIdAndFreezeQuantityList, shopId);
			logger.info("freezeStock:{} success", orderNo);
		} catch (ERPException e) {
			logger.error("Failed to freezeStock:{}", orderNo);
		}
	}

	@Test
	public void testUnfreezeStock() {
		String orderNo = "1538489417367";
		List<KeyValue<Integer, Integer>> goodsIdAndFreezeQuantityList = new ArrayList<>();
		goodsIdAndFreezeQuantityList.add(new KeyValue<Integer, Integer>(1, 2));
		goodsIdAndFreezeQuantityList.add(new KeyValue<Integer, Integer>(2, 5));
		try {
			stockService.unfreezeStock(orderNo, goodsIdAndFreezeQuantityList);
			logger.info("unfreezeStock:{} success", orderNo);
		} catch (ERPException e) {
			logger.error("Failed to unfreezeStock:{}", orderNo);
		}
	}

	@Test
	public void testDecreaseStock() {
		String orderNo = "1538489923610";
		List<KeyValue<Integer, Integer>> goodsIdAndFreezeQuantityList = new ArrayList<>();
		goodsIdAndFreezeQuantityList.add(new KeyValue<Integer, Integer>(1, 2));
		goodsIdAndFreezeQuantityList.add(new KeyValue<Integer, Integer>(2, 5));
		int shopId = 1;
		stockService.decreaseStock(orderNo, goodsIdAndFreezeQuantityList, shopId);
		logger.info("decreaseStock:{} success", orderNo);
	}

}
