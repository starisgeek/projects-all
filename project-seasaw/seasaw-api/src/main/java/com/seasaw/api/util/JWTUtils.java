package com.seasaw.api.util;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JWTUtils {
	public static final String HTTP_HEADER_TOKEN = "Authorization";
	public static final String KEY_ACCESS_TOKEN = "access-token";
	public static final String KEY_REFRESH_TOKEN = "refresh-token";
	public static final String CLAIM_MEMBER_NAME = "memberName";

	public static String generateToken(String memberName, String secret, Date expireAt) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.create().withClaim(CLAIM_MEMBER_NAME, memberName).withExpiresAt(expireAt)
				.sign(algorithm);
	}

	public static void verifyToken(String token, String secret) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		JWTVerifier verifier = JWT.require(algorithm).build();
		verifier.verify(token);
	}

	public static String getToken(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		return httpRequest.getHeader(HTTP_HEADER_TOKEN);
	}

	public static String getMemberName(String token) {
		DecodedJWT jwt = JWT.decode(token);
		return jwt.getClaim(CLAIM_MEMBER_NAME).asString();
	}

	// public static String getAccessToken(ServletRequest request) {
	// HttpServletRequest httpRequest = (HttpServletRequest) request;
	// return httpRequest.getHeader(KEY_ACCESS_TOKEN);
	// }
	//
	// public static String getRefreshToken(ServletRequest request) {
	// HttpServletRequest httpRequest = (HttpServletRequest) request;
	// return httpRequest.getHeader(KEY_REFRESH_TOKEN);
	// }
}
