package com.yunfenghui.erp.stock.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageRowBounds;
import com.google.common.collect.Sets;
import com.yunfenghui.common.page.Page;
import com.yunfenghui.common.page.PageResult;
import com.yunfenghui.common.service.NumberGenerator;
import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.stock.dao.StockChangeRecordDao;
import com.yunfenghui.erp.stock.dao.StockDao;
import com.yunfenghui.erp.stock.dao.StockFrozenRecordDao;
import com.yunfenghui.erp.stock.dao.StockFrozenRecordItemDao;
import com.yunfenghui.erp.stock.dao.StockRecordDao;
import com.yunfenghui.erp.stock.dao.StockRecordItemDao;
import com.yunfenghui.erp.stock.model.Stock;
import com.yunfenghui.erp.stock.model.StockChangeRecord;
import com.yunfenghui.erp.stock.model.StockFrozenRecord;
import com.yunfenghui.erp.stock.model.StockFrozenRecordItem;
import com.yunfenghui.erp.stock.model.StockRecord;
import com.yunfenghui.erp.stock.model.StockRecordItem;
import com.yunfenghui.erp.stock.util.StockMessageCode;

@Service(StockService.ID)
public class StockServiceImpl implements StockService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StockDao stockDao;
	@Autowired
	private StockRecordDao stockRecordDao;
	@Autowired
	private StockRecordItemDao stockRecordItemDao;
	@Autowired
	private StockChangeRecordDao stockChangeRecordDao;
	@Autowired
	private StockFrozenRecordDao stockFrozenRecordDao;
	@Autowired
	private StockFrozenRecordItemDao stockFrozenRecordItemDao;
	@Resource(name = "numberGenerator")
	private NumberGenerator numberGenerator;

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public String createStockRecord(StockRecord record) {
		if (record.getRecordNo() == null) {
			record.setRecordNo(generateRecordNo(record.getShopId()));
		}
		stockRecordDao.insertStockRecord(record);
		List<StockChangeRecord> changeRecords = new ArrayList<>(record.getItems().size());
		Date now = new Date();
		for (StockRecordItem item : record.getItems()) {
			item.setRecordNo(record.getRecordNo());
			changeRecords
					.add(StockChangeRecord.newBuilder().serialNumber(numberGenerator.generate())
							.goodsId(item.getGoodsId()).shopId(record.getShopId())
							.changeAmount(item.getBuyQuantity() + item.getPresentQuantity())
							.dealType(StockChangeRecord.DEAL_TYPE_CREATE_STOCK)
							.originalRecordNo(record.getRecordNo()).createTime(now).build());
		}
		stockRecordItemDao.batchInsertStockRecordItems(record.getItems());
		createOrIncreaseGoodsStock(record);
		stockChangeRecordDao.batchInsertChangeRecords(changeRecords);
		return record.getRecordNo();
	}

	@SuppressWarnings("unchecked")
	private void createOrIncreaseGoodsStock(StockRecord record) {
		List<StockRecordItem> items = record.getItems();
		Set<Integer> goodsIds = Sets.newHashSetWithExpectedSize(items.size());
		for (StockRecordItem item : items) {
			goodsIds.add(item.getGoodsId());
		}
		Set<Integer> existsGoodsIds = stockDao.queryStockGoodsIds(goodsIds);
		existsGoodsIds = (existsGoodsIds != null ? existsGoodsIds : Collections.EMPTY_SET);
		List<Stock> stocks = new ArrayList<>();
		for (StockRecordItem item : items) {
			if (!existsGoodsIds.contains(item.getGoodsId())) {
				Stock stock = new Stock();
				stock.setGoodsId(item.getGoodsId());
				stock.setQuantity(item.getBuyQuantity() + item.getPresentQuantity());
				stock.setFrozenQuantity(0);
				stock.setLatestBuyPrice(item.getBuyPrice());
				stock.setShopId(record.getShopId());
				stock.setLatestSupplier(record.getSupplier());
				stocks.add(stock);
			} else {
				stockDao.increaseQuantityAndUpdateLatestBuyPriceAndLatestSupplierId(
						item.getGoodsId(), item.getBuyQuantity(), item.getBuyPrice(),
						record.getSupplier().getId());
			}
		}
		if (!stocks.isEmpty()) {
			stockDao.batchInsertStocks(stocks);
		}
	}

	@Override
	public StockRecord getStockRecordByNo(String recordNo) {
		return stockRecordDao.queryStockRecordByNo(recordNo);
	}

	@Override
	public List<StockRecordItem> getStockRecordItems(String recordNo) {
		return stockRecordItemDao.queryStockRecordItemsBy(recordNo);
	}

	@Override
	public String generateRecordNo(int shopId) {
		return numberGenerator.generate();
	}

	@Override
	public PageResult<StockRecord> getStockRecordsBy(StockRecordQuery query, Page page) {
		PageRowBounds rowBounds = new PageRowBounds(page.getCurrentPage(), page.getPageSize());
		List<StockRecord> records = stockRecordDao.queryStockRecordsBy(query, rowBounds);
		return new PageResult<>(page.getCurrentPage(), page.getPageSize(), rowBounds.getTotal(),
				records);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = ERPException.class)
	public void freezeStock(StockFrozenRecord frozenRecord) throws ERPException {
		for (StockFrozenRecordItem item : frozenRecord.getItems()) {
			int updated = stockDao.increaseFrozenQuantity(item.getGoodsId(),
					item.getFrozenQuantity());
			if (updated != 1) {
				logger.error("Failed to increaseFrozenQuantity, goodsId:{} not exists",
						item.getGoodsId());
				throw new ERPException(StockMessageCode.STOCK_GOODS_NOT_EXISTS);
			}
		}
		stockFrozenRecordDao.insertFrozenRecord(frozenRecord);
		stockFrozenRecordItemDao.batchInsertFrozenRecordItems(frozenRecord.getItems());
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = ERPException.class)
	public void unfreezeStock(StockFrozenRecord frozenRecord) throws ERPException {
		for (StockFrozenRecordItem item : frozenRecord.getItems()) {
			int updated = stockDao.decreaseFrozenQuantity(item.getGoodsId(),
					item.getFrozenQuantity());
			if (updated != 1) {
				logger.error("Failed to decreaseFrozenQuantity, goodsId:{} not exists",
						item.getGoodsId());
				throw new ERPException(StockMessageCode.STOCK_GOODS_NOT_EXISTS);
			}
		}
		removeStockFrozenRecordAndItems(frozenRecord.getOrderNo());
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = ERPException.class)
	public void decreaseStock(String orderNo) {
		StockFrozenRecord frozenRecord = stockFrozenRecordDao.queryFrozenRecordByOrderNo(orderNo);
		if (frozenRecord != null) {
			List<StockFrozenRecordItem> items = frozenRecord.getItems();
			if (items != null && !items.isEmpty()) {
				List<StockChangeRecord> changeRecords = new ArrayList<>(items.size());
				Date now = new Date();
				for (StockFrozenRecordItem item : items) {
					stockDao.decreaseQuantityAndFrozenQuantity(item.getGoodsId(),
							item.getFrozenQuantity());
					changeRecords.add(
							StockChangeRecord.newBuilder().serialNumber(numberGenerator.generate())
									.goodsId(item.getGoodsId()).shopId(frozenRecord.getShopId())
									.changeAmount(-item.getFrozenQuantity())
									.dealType(StockChangeRecord.DEAL_TYPE_CONSUME)
									.originalRecordNo(orderNo).createTime(now).build());
				}
				removeStockFrozenRecordAndItems(orderNo);
				stockChangeRecordDao.batchInsertChangeRecords(changeRecords);
			} else {
				logger.warn("Items of StockFrozenRecord:{} is null, this should not happen",
						orderNo);
			}
		} else {
			logger.info("queryFrozenRecordByOrderNo:{} but not found", orderNo);
		}

	}

	@Override
	public List<String> getTopNStockFrozenRecordNosBefore(int topN, Date createTime) {
		return stockFrozenRecordDao.queryTopNFrozenRecordNosBefore(topN, createTime);
	}

	private void removeStockFrozenRecordAndItems(String orderNo) {
		stockFrozenRecordDao.deleteFrozenRecord(orderNo);
		stockFrozenRecordItemDao.deleteFrozenRecordItems(orderNo);
	}

}
