package com.yunfenghui.erp.goods.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageRowBounds;
import com.yunfenghui.common.page.Page;
import com.yunfenghui.common.page.PageResult;
import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.goods.dao.GoodsDao;
import com.yunfenghui.erp.goods.model.Goods;
import com.yunfenghui.erp.goods.model.StockRecord;
import com.yunfenghui.erp.goods.model.StockRecordItem;
import com.yunfenghui.erp.goods.util.GoodsMessageCode;

@Service(GoodsService.ID)
public class GoodsServiceImpl implements GoodsService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private GoodsDao goodsDao;

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = ERPException.class)
	public void createGoods(Goods goods) throws ERPException {
		goods.setStatus(Goods.STATUS_NEW);
		doCreateGoods(goods);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = ERPException.class)
	public void createAndPutAwayGoods(Goods goods) throws ERPException {
		goods.setStatus(Goods.STATUS_PUT_AWAY);
		doCreateGoods(goods);
	}

	protected void doCreateGoods(Goods goods) throws ERPException {
		Integer foundId = goodsDao.queryGoodsIdByShopIdAndName(goods.getShopId(), goods.getName());
		if (foundId != null) {
			logger.error("Failed to create goods, because name:{} already exists in shop:{}",
					goods.getName(), goods.getShopId());
			throw new ERPException(GoodsMessageCode.GOODS_NAME_EXISTS);
		}
		foundId = goodsDao.queryGoodsIdByShopIdAndBarcode(goods.getShopId(), goods.getBarcode());
		if (foundId != null) {
			logger.error("Failed to create goods, because barcode:{} already exists in shop:{}",
					goods.getBarcode(), goods.getShopId());
			throw new ERPException(GoodsMessageCode.GOODS_BARCODE_EXISTS);
		}
		goods.setSalePrice(goods.getOriginalPrice());
		goods.setMemberPrice(goods.getOriginalPrice());
		goods.setCreateTime(new Date());
		goodsDao.insertGoods(goods);
	}

	@Override
	public void putAwayGoods(int goodsId) throws ERPException {
		// TODO Auto-generated method stub

	}

	@Override
	public void soldOutGoods(int goodsId) throws ERPException {
		// TODO Auto-generated method stub

	}

	@Override
	public Goods getGoodsById(int goodsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Goods getGoodsByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<Goods> getGoodsBy(GoodsQuery query, Page page) {
		PageRowBounds rowBounds = new PageRowBounds(page.getCurrentPage(), page.getPageSize());
		List<Goods> goodsList = goodsDao.queryGoodsBy(query, rowBounds);
		return new PageResult<>(page.getCurrentPage(), page.getPageSize(), rowBounds.getTotal(),
				goodsList);
	}

	@Override
	public void createStockRecord(StockRecord record) throws ERPException {
		// TODO Auto-generated method stub

	}

	@Override
	public StockRecord getStockRecordByNo(String recordNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StockRecordItem> getStockRecordItems(String recordNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateRecordNo(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<StockRecord> getStockRecordsBy(StockRecordQuery query, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

}
