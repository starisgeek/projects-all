package com.yunfenghui.jf.partner.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunfenghui.common.Commons;
import com.yunfenghui.common.service.NumberGenerator;
import com.yunfenghui.jf.partner.dao.PartnerDao;
import com.yunfenghui.jf.partner.model.Partner;
import com.yunfenghui.jf.partner.util.PartnerMessageCode;

import ocm.yunfenghui.jf.common.JFException;

@Service(PartnerService.ID)
public class PartnerServiceImpl implements PartnerService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PartnerDao partnerDao;
	@Resource(name = "uuidNumberGenerator")
	private NumberGenerator numberGenerator;
	private static final int PARTNER_NUMBER_LEN = 5;

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = JFException.class)
	public void createPartner(Partner partner) throws JFException {
		Integer foundId = partnerDao.queryPartnerIdByName(partner.getName());
		if (foundId != null) {
			logger.error("Failed to createPartner, because name:{} exists", partner.getName());
			throw new JFException(PartnerMessageCode.PARTNER_NAME_EXISTS);
		}
		partner.setAppId(generatePartnerAppId());
		partner.setNumber(generatePartnerNumber());
		partnerDao.insertPartner(partner);
	}

	@Override
	public Partner getPartnerById(int id) {
		return partnerDao.queryPartnerById(id);
	}

	@Override
	public Partner getPartnerByAppId(String appId) {
		return partnerDao.queryPartnerByAppId(appId);
	}

	private String generatePartnerAppId() {
		String appId;
		while (partnerDao.queryPartnerIdByAppId(appId = numberGenerator.generate()) != null)
			;
		return appId;
	}

	private String generatePartnerNumber() {
		String number;
		while (partnerDao
				.queryPartnerIdByNumber(number = Commons.randomNumbers(PARTNER_NUMBER_LEN)) != null)
			;
		return number;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void modifyPartnerPublicKey(int partnerId, String publicKey) {
		partnerDao.updatePublicKey(partnerId, publicKey);
	}

}
