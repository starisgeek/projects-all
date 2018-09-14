package com.yunfenghui.jf.api.filter.support;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunfenghui.jf.api.support.ApiMessageCode;
import com.yunfenghui.jf.partner.model.Partner;
import com.yunfenghui.jf.partner.service.PartnerService;

import ocm.yunfenghui.jf.common.JFException;

public class Authenticator {
	private Logger logger = LoggerFactory.getLogger(getClass());
	public static final String ID = "authenticator";
	@Resource(name = PartnerService.ID)
	private PartnerService partnerService;

	public Subject authenticate(String appId) throws JFException {
		return buildSubject(ensureAppId(appId));
	}

	private Partner ensureAppId(String appId) throws JFException {
		if (appId == null) {
			logger.error("Failed to authenticate, appId is null");
			throw new JFException(ApiMessageCode.PARAM_MISSING);
		}
		Partner partner = partnerService.getPartnerByAppId(appId);
		if (partner == null) {
			logger.error("Failed to authenticate, appId:{} is invalid", appId);
			throw new JFException(ApiMessageCode.APP_ID_INVALID);
		}
		return partner;
	}

	private static Subject buildSubject(Partner partner) {
		return Subject.newBuilder().appId(partner.getAppId()).partnerId(partner.getId())
				.publicKey(partner.getPublicKey()).build();
	}
}
