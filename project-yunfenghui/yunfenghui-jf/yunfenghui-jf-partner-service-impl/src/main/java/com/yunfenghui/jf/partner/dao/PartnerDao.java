package com.yunfenghui.jf.partner.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yunfenghui.jf.partner.model.Partner;

@Repository
public interface PartnerDao {
	/**
	 * 新增Partner
	 * 
	 * @param partner
	 */
	void insertPartner(Partner partner);

	/**
	 * 根据名称查询id.用于新增商户时判断名称是否存在
	 * 
	 * @param name
	 * @return
	 */
	Integer queryPartnerIdByName(@Param("name") String name);

	/**
	 * 根据appId查询id.用于新增商户时判断appId是否存在
	 * 
	 * @param appId
	 * @return
	 */
	Integer queryPartnerIdByAppId(@Param("appId") String appId);

	/**
	 * 根据number查询id.用于新增商户时判断number是否存在
	 * 
	 * @param number
	 * @return
	 */
	Integer queryPartnerIdByNumber(@Param("number") String number);

	/**
	 * 根据id查询Partner
	 * 
	 * @param id
	 * @return
	 */
	Partner queryPartnerById(@Param("id") int id);

	/**
	 * 根据appId查询Partner
	 * 
	 * @param appId
	 * @return
	 */
	Partner queryPartnerByAppId(@Param("appId") String appId);

	/**
	 * 修改公钥
	 * 
	 * @param partnerId
	 * @param publicKey
	 */
	int updatePublicKey(@Param("partnerId") int partnerId, @Param("publicKey") String publicKey);
}
