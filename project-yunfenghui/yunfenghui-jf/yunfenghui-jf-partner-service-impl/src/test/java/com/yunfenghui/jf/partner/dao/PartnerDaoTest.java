package com.yunfenghui.jf.partner.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunfenghui.common.Commons;
import com.yunfenghui.common.service.NumberGenerator;
import com.yunfenghui.jf.partner.model.Partner;
import com.yunfenghui.test.BaseDaoTest;

public class PartnerDaoTest extends BaseDaoTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PartnerDao partnerDao;
	@Resource(name = "uuidNumberGenerator")
	private NumberGenerator numberGenerator;

	@Test
	public void testInsertPartner() {
		Partner partner = new Partner();
		partner.setAppId(numberGenerator.generate());
		partner.setName("云风商城");
		partner.setNumber(Commons.randomNumbers(5));
		partner.setCreateTime(new Date());
		partner.setRemark("云风商城");
		partnerDao.insertPartner(partner);
		logger.info("insert partner, id:{}", partner.getId());
	}

	@Test
	public void testQueryPartnerIdByName() {
		String name = "云风商城";
		Integer id = partnerDao.queryPartnerIdByName(name);
		logger.info("Found {} by name:{}", id, name);
	}

	@Test
	public void testQueryPartnerIdByAppId() {
		String appId = "711cc0fa42a04cb7800dd653cb560424";
		Integer id = partnerDao.queryPartnerIdByAppId(appId);
		logger.info("Found {} by appId:{}", id, appId);
	}

	@Test
	public void testQueryPartnerIdByNumber() {
		String number = "21656";
		Integer id = partnerDao.queryPartnerIdByNumber(number);
		logger.info("Found {} by number:{}", id, number);
	}

	@Test
	public void testQueryPartnerById() {
		int id = 1;
		Partner partner = partnerDao.queryPartnerById(id);
		logger.info("Found partner by {}, name:{}", id,
				partner != null ? partner.getName() : "null");
	}

	@Test
	public void testQueryPartnerByAppId() {
		String appId = "187fc9198d394def8a8c16a465d2a121";
		Partner partner = partnerDao.queryPartnerByAppId(appId);
		logger.info("Found partner by {}, name:{}", appId,
				partner != null ? partner.getName() : "null");
	}

	@Test
	public void testUpdatePublicKey() {
		int id = 1;
		String publicKey = "aaa";
		logger.info("update partner:{} public key, updated==1?{}", id,
				partnerDao.updatePublicKey(id, publicKey) == 1);
	}

}
