package com.yunfenghui.erp.shop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunfenghui.erp.common.Commons;
import com.yunfenghui.erp.common.ERPException;
import com.yunfenghui.erp.shop.dao.BalanceAccountDao;
import com.yunfenghui.erp.shop.dao.ShopDao;
import com.yunfenghui.erp.shop.model.BalanceAccount;
import com.yunfenghui.erp.shop.model.Shop;
import com.yunfenghui.erp.shop.util.ShopMessageCode;

@Service(ShopService.ID)
public class ShopServiceImpl implements ShopService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private BalanceAccountDao balanceAccountDao;

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = ERPException.class)
	public void createShop(Shop shop) throws ERPException {
		String foundName = shopDao.queryShopName(shop.getName());
		if (foundName != null) {
			logger.error("Failed to create shop, because shop '{}' exists", shop.getName());
			throw new ERPException(ShopMessageCode.SHOP_NAME_EXISTS);
		}
		shop.setNumber(generateShopNumber());
		shopDao.insertShop(shop);

		BalanceAccount account = new BalanceAccount();
		account.setShopId(shop.getId());
		account.setBalance(0);
		balanceAccountDao.insertBalanceAccount(account);
	}

	@Override
	public Shop getShopById(int id) {
		return shopDao.queryShopById(id);
	}

	@Override
	public Shop getShopByNumber(String number) {
		return shopDao.queryShopByNumber(number);
	}

	@Override
	public List<Shop> getAllShops() {
		return shopDao.queryAllShops();
	}

	private String generateShopNumber() {
		String number;
		do {
			number = Commons.randomNumbers(SHOP_NUMBER_LENGTH);
		} while (shopDao.queryShopByNumber(number) != null);
		return number;
	}

}
