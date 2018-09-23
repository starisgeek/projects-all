package com.yunfenghui.jf.api.controller;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.yunfenghui.common.HttpTemplate;
import com.yunfenghui.jf.score.model.ChangeRecordDealTypes.WhiteScoreChangeRecordDealType;

public class ApiControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(ApiControllerTest.class);
	private static final String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCfADBa2EeTQup+vfVHGOSqw0cNTI6ifuGVm9/+g+R9ANdcWqBmngpR+upCIGX4Tzq//lbu+TbZWvo963aBykkqVFWT3ud6GolhuJlgZTdPj3ep/3aAkQFQ0L2U9nzFUix8SzCbv7U/hYpc4F/bgoGmGzs+wRnHeFC0x5PUbpASHIbzksAeC54i0g6mGanc/LEwJTghOm/gpjiVpVnBFKTuTvJgEObjkolDx0AxU9lLqMROo+a/++K+oYopLgjoAfNtwipQ5Mi8hXBQ2jP3UbRthucCPzhW2oRHET7bRCBPD35kzY0r82z8ZGeehzj+k3dljuy6y6dQJpyCxtd9SVTrAgMBAAECggEAf4WVQqcH8NHQZMpReC4858Aqsj3ef/EnV+hmZCjADomoMEf3MZY7TLjjPRni7p4MzoNckdmK5hSTcynwJ/Rnbe2pnSk/mazC1n9JdAh5rfOw08Ux87dYyMpB4sE2HMPlLeLYH3JIGaQu7oJ+Hv2yodhhUdlppZOXlwYiSFRMdFjpedHchhnnLQFWvf5uBp/Rm7VCU/IV37CUStl8JJ8nWLn7vGwFDxInv3BIBe7PRi0tZIvSgdf/0Ipbz4Gk7iS+kP8r75y5bCWq0nQrOVs1dhHJgCwhK+jaVe8nEUjojVaPiQxeqNkmVGGL81FrJRftoyRItua/s2t9NdXv+gF6cQKBgQDq7HG868v8QvZVQEM0vQHD1yIIeWEmR7nSK/KL5TdyIMXaFSj08Lh7l0zZFqTIi/vz8mwLH6DvU8A5mvc8zI8NGwqP0KgdbLqFTsWvKWjLaagKBlixFheYRSVTtBv6SLCl5rGCwxvuDLUKjkietOktvmhpemY7yUreNd1dFXZG5QKBgQCtRAC/wClMzY18mTcTwnztgYBEwh4vgyt7aEIE+OKzw0+VHrZ+QrsVSwe+2VkEtEap1h6ORBZT+eeTi1BdbrIpyik+7FGMcEhsqxlaEE0wRe1XGwY/cWq/DK2P9OxZnwEu0XP0GNTFvDl80INXgHZOjCEvZXP9TFbQ952FCvofjwKBgQCCpr0atIjd4t/cycCWtAcnQ55J2DePcpSFrqtV4kTT1CY1A0+j6byv/5Vdppo+bpvRMCGmjhCTCaD+aJDX32wvfbqCASl+M9oICacno4AEoN4ztRPeaMr2hH4d7y0gJQ0ZNxPCBYCjA64sELWSjkqghiTLhKcW2PwqWQqjmiwNgQKBgQCGtW1Dp9o0TUfojJKmuTo5ycp5KtcfJ4NB8iAKD5rqGjqgfH10EtuzRV9jQUaVgrfByah+AW++oHgsXU8BJVQ9a2C//Cm234Ffg3M1c6Efm+5YH7muz7YnjWrPlqO/VgEsbkoRiNC8X4fug4rqGAVjMk2GtQEF+TkyRKHiU1N9/wKBgC/02O9YutD9xm99sNnbDtTiW1K7RbaBcYlm2UMhSBjlr990rWnPpd3JhFrx2pGaAobVI2Cd54nxttdU2CrZ+1p6QrAbxcOuD6FUiPcy5SxuAyQEUWzUPYRBRRaxwtbD2zN/8v2lPPVaX4d3Kku3/d19FhEe24oaAI8Srt8K06O4";
	private static final String APP_ID = "711cc0fa42a04cb7800dd653cb560424";

	@Test
	public void testSendWhiteScore() {
		final String url = "http://127.0.0.1:8080/api/sendWhiteScore";
		Map<String, String> paramsMap = new HashMap<>();
		paramsMap.put("appId", APP_ID);
		paramsMap.put("memberId", "1234");
		paramsMap.put("outTradeNo", UUID.randomUUID().toString().replace("-", ""));
		paramsMap.put("sendScores", String.valueOf(new Random().nextInt(10000) + 1));
		paramsMap.put("dealType", String.valueOf(WhiteScoreChangeRecordDealType.CONSUME));
		try {
			String signContent = AlipaySignature.getSignContent(paramsMap);
			String sign = AlipaySignature.rsaSign(signContent, PRIVATE_KEY,
					AlipayConstants.CHARSET_UTF8, AlipayConstants.SIGN_TYPE_RSA2);
			paramsMap.put("sign", sign);
			long start = System.currentTimeMillis();
			String response = HttpTemplate.post(url, paramsMap);
			logger.info("use:{}, response:{}", (System.currentTimeMillis() - start), response);
		} catch (Exception e) {
			logger.error("request api error.", e);
		}
	}

	@Test
	public void testQuerySendRecord() {
		fail("Not yet implemented");
	}

}
