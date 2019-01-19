package com.seasaw.api;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.seasaw.api.model.APIResult;
import com.seasaw.api.util.JWTUtils;
import com.seasaw.api.util.MessageCode;
import com.seasaw.member.model.Member;
import com.seasaw.member.service.MemberService;
import com.seasaw.member.util.MemberBusinessException;

@RestController
public class APIController {
	@Value("${jwt.access.token.keepalive.seconds}")
	private int accessTokenKeepAlivedSeconds;
	@Resource(name = "memberService")
	private MemberService memberService;

	@PostMapping("/register")
	public APIResult register(Member member) throws MemberBusinessException {
		memberService.register(member);
		return APIResult.newBuilder().code(MessageCode.SUCCESS).msg("注册成功").build();
	}

	@GetMapping("/accessToken")
	public APIResult accessToken(HttpServletRequest request) {
		String refreshToken = JWTUtils.getToken(request);
		if (refreshToken == null) {
			return APIResult.newBuilder().code(MessageCode.ACCESS_TOKEN_EXPIRED)
					.msg("Refresh Token已过期").build();
		}
		String memberName = JWTUtils.getMemberName(refreshToken);
		String secret = memberService.getSecret(memberName);
		Date expireAt = new Date(System.currentTimeMillis()
				+ TimeUnit.SECONDS.toMillis(accessTokenKeepAlivedSeconds));
		try {
			JWTUtils.verifyToken(refreshToken, secret);
			return APIResult.newBuilder().code(MessageCode.SUCCESS)
					.data(JWTUtils.generateToken(memberName, secret, expireAt)).build();
		} catch (JWTVerificationException e) {
			if (e instanceof TokenExpiredException) {
				return APIResult.newBuilder().code(MessageCode.REFRESH_TOKEN_EXPIRED)
						.msg("Refresh Token已过期,请重新登录!").build();
			}
			return APIResult.newBuilder().code(MessageCode.REFRESH_TOKEN_INVALID)
					.msg("无效的Refresh Token").build();
		}
	}

	@GetMapping("/member/{name}")
	public Member getMember(@PathVariable("name") String name) {
		return memberService.getMemberByName(name);
	}
}
