package com.yunfenghui.erp.goods.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

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
import com.yunfenghui.common.service.NumberGenerator;
import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.goods.dao.GoodsAdjustPriceRecordDao;
import com.yunfenghui.erp.goods.dao.GoodsDao;
import com.yunfenghui.erp.goods.model.Goods;
import com.yunfenghui.erp.goods.model.GoodsAdjustPriceRecord;
import com.yunfenghui.erp.goods.util.GoodsMessageCode;

@Service(GoodsService.ID)
public class GoodsServiceImpl implements GoodsService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private GoodsAdjustPriceRecordDao adjustPriceRecordDao;
	@Resource(name = "numberGenerator")
	private NumberGenerator numberGenerator;

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
		goods.setCreateTime(new Date());
		goodsDao.insertGoods(goods);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = ERPException.class)
	public void putAwayGoods(int goodsId) throws ERPException {
		modifyGoodsStatus(goodsId, new int[] { Goods.STATUS_NEW, Goods.STATUS_SOLD_OUT },
				Goods.STATUS_PUT_AWAY);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = ERPException.class)
	public void soldOutGoods(int goodsId) throws ERPException {
		modifyGoodsStatus(goodsId, new int[] { Goods.STATUS_NEW, Goods.STATUS_PUT_AWAY },
				Goods.STATUS_SOLD_OUT);
	}

	private void modifyGoodsStatus(int goodsId, int[] expectedStatuses, int updatedStatus)
			throws ERPException {
		int updated = goodsDao.updateGoodsStatus(goodsId, expectedStatuses, updatedStatus);
		if (updated != 1) {
			logger.error(
					"Failed to updateGoodsStatus, goodsId:{}, expected statuses:{}, updated status:{}",
					goodsId, Arrays.asList(expectedStatuses), updatedStatus);
			throw new ERPException(GoodsMessageCode.GOODS_NOT_EXISTS_OR_STATUS_ERROR);
		}
	}

	@Override
	public Goods getGoodsById(int goodsId) {
		return goodsDao.queryGoodsById(goodsId);
	}

	@Override
	public Goods getGoodsByShopIdAndBarcode(int shopId, String barcode) {
		return goodsDao.queryGoodsByShopIdAndBarcode(shopId, barcode);
	}

	@Override
	public PageResult<Goods> getGoodsBy(GoodsQuery query, Page page) {
		PageRowBounds rowBounds = new PageRowBounds(page.getCurrentPage(), page.getPageSize());
		List<Goods> goodsList = goodsDao.queryGoodsBy(query, rowBounds);
		return new PageResult<>(page.getCurrentPage(), page.getPageSize(), rowBounds.getTotal(),
				goodsList);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = ERPException.class)
	public void adjustSalePrice(int goodsId, int newSalePrice, int createUserId, String reason)
			throws ERPException {
		Goods goods = goodsDao.queryGoodsById(goodsId);
		if (goods == null) {
			logger.error("queryGoodsById:{}, but not exists", goodsId);
			throw new ERPException(GoodsMessageCode.GOODS_NOT_EXISTS);
		}
		adjustPriceRecordDao.insertAdjustPriceRecord(GoodsAdjustPriceRecord.newBuilder()
				.recordNo(numberGenerator.generate()).shopId(goods.getShopId()).goodsId(goodsId)
				.oldPrice(goods.getSalePrice()).newPrice(newSalePrice).createUserId(createUserId)
				.createTime(new Date()).reason(reason).build());
		int updated = goodsDao.updateGoodsSalePrice(goodsId, newSalePrice);
		if (updated != 1) {
			logger.error("Failed to updateGoodsSalePrice, goodsId:{} not exists", goodsId);
			throw new ERPException(GoodsMessageCode.GOODS_NOT_EXISTS);
		}
	}

}
