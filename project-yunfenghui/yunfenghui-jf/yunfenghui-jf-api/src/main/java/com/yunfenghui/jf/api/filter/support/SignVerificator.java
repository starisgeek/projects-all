package com.yunfenghui.jf.api.filter.support;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.yunfenghui.common.Commons;
import com.yunfenghui.jf.api.support.ApiMessageCode;

import ocm.yunfenghui.jf.common.JFException;

public class SignVerificator {
	public static final String ID = "signVerificator";
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void verificate(Map<String, String> params) throws JFException {
		ensureSign(params);
		try {
			if (!AlipaySignature.rsaCheckV1(params, Subject.get().getPublicKey(), Commons.UTF8,
					AlipayConstants.SIGN_TYPE_RSA2)) {
				throw new JFException(ApiMessageCode.SIGN_ERROR);
			}
		} catch (AlipayApiException e) {
			logger.error("Failed to verificate sign", e);
			throw new JFException(ApiMessageCode.SIGN_ERROR);
		}
	}

	private String ensureSign(Map<String, String> params) throws JFException {
		if (params == null) {
			logger.error("Params is null");
			throw new JFException(ApiMessageCode.PARAM_MISSING);
		}
		String sign = params.get("sign");
		if (sign == null) {
			logger.error("Failed to verificate, sign is null");
			throw new JFException(ApiMessageCode.PARAM_MISSING);
		}
		return sign;
	}
}
