package com.yunfenghui.partner.service;

import com.yunfenghui.partner.model.Partner;

import ocm.yunfenghui.jf.common.JFException;

public interface PartnerService {
	String ID = "partnerService";

	/**
	 * 创建商户
	 * 
	 * @param partner
	 * @throws JFException
	 *             名称已存在
	 */
	void createPartner(Partner partner) throws JFException;

	/**
	 * 根据id查询商户
	 * 
	 * @param id
	 * @return
	 */
	Partner getPartnerById(int id);

	/**
	 * 根据appId查询商户
	 * 
	 * @param appId
	 * @return
	 */
	Partner getPartnerByAppId(String appId);

	/**
	 * @param partnerId
	 * @param publicKey
	 */
	void modifyPartnerPublicKey(int partnerId, String publicKey);
}
