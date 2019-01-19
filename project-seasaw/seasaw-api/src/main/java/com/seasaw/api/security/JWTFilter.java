package com.seasaw.api.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.seasaw.api.model.APIResult;
import com.seasaw.api.util.APIUtils;
import com.seasaw.api.util.JWTUtils;
import com.seasaw.api.util.MessageCode;
import com.seasaw.member.service.MemberService;

public class JWTFilter extends FormAuthenticationFilter {
	@Value("${jwt.access.token.keepalive.seconds}")
	private int accessTokenKeepAlivedSeconds;
	@Value("${jwt.refresh.token.keepalive.seconds}")
	private int refreshTokenKeepAliveSeconds;
	@Resource(name = "memberService")
	private MemberService memberService;

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				return executeLogin(request, response);
			}
		}
		String token = JWTUtils.getToken(request);
		if (token == null) {
			APIResult result = APIResult.newBuilder().code(MessageCode.PARAM_IS_MISS)
					.msg("缺少参数Authorization").build();
			APIUtils.writeResult(response, result);
		}
		String memberName = JWTUtils.getMemberName(token);
		try {
			JWTUtils.verifyToken(token, memberService.getSecret(memberName));
			return true;
		} catch (JWTVerificationException e) {
			if (e instanceof TokenExpiredException) {
				APIResult result = APIResult.newBuilder().code(MessageCode.ACCESS_TOKEN_EXPIRED)
						.msg("Access Token已过期").build();
				APIUtils.writeResult(response, result);
			} else {
				APIResult result = APIResult.newBuilder().code(MessageCode.ACCESS_TOKEN_INVALID)
						.msg("无效的Access Token").build();
				APIUtils.writeResult(response, result);
			}

			return false;
		}

	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
			ServletRequest request, ServletResponse response) throws Exception {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String secret = memberService.generateSecret(upToken.getUsername());
		Map<String, String> tokenMap = new HashMap<>();
		long current = System.currentTimeMillis();
		String accessToken = JWTUtils.generateToken(upToken.getUsername(), secret,
				new Date(current + TimeUnit.SECONDS.toMillis(accessTokenKeepAlivedSeconds)));
		String refreshToken = JWTUtils.generateToken(upToken.getUsername(), secret,
				new Date(current + TimeUnit.SECONDS.toMillis(refreshTokenKeepAliveSeconds)));
		tokenMap.put(JWTUtils.KEY_ACCESS_TOKEN, accessToken);
		tokenMap.put(JWTUtils.KEY_REFRESH_TOKEN, refreshToken);
		APIUtils.writeResult(response, APIResult.newBuilder().code(MessageCode.SUCCESS).msg("登录成功")
				.data(tokenMap).build());
		return false;
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
			ServletRequest request, ServletResponse response) {
		APIResult result = APIResult.newBuilder().code(MessageCode.USER_NAME_OR_PASSWORD_WRONG)
				.msg("用户名或者密码错误").build();
		APIUtils.writeResult(response, result);
		return false;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
			Object mappedValue) {
		return false;
	}

}
