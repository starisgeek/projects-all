package com.yunfenghui.jf.partner.service;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.yunfenghui.jf.partner.model.Partner;

@ContextConfiguration("classpath:partner-service-consumer.xml")
public class PartnerServiceTest extends AbstractJUnit4SpringContextTests {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource(name = PartnerService.ID)
	private PartnerService partnerService;

	@Test
	public void testCreatePartner() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPartnerById() {
		int id = 1;
		Partner partner = partnerService.getPartnerById(id);
		logger.info("Found partner is {} by {}", partner != null ? partner.getName() : "", id);
	}

	@Test
	public void testGetPartnerByAppId() {
		String appId = "711cc0fa42a04cb7800dd653cb560424";
		Partner partner = partnerService.getPartnerByAppId(appId);
		logger.info("Found partner is {} by {}", partner != null ? partner.getName() : "", appId);
	}

	@Test
	public void testModifyPartnerPublicKey() {
		fail("Not yet implemented");
	}

}
