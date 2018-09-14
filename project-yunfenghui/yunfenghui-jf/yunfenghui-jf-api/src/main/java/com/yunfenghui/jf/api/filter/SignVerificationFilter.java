package com.yunfenghui.jf.api.filter;

import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.yunfenghui.jf.api.filter.support.SignVerificator;

public class SignVerificationFilter extends AbstractApiFilter {
	@Resource(name = SignVerificator.ID)
	private SignVerificator verificator;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws Exception {
		verificator.verificate(buildParams(request));
		chain.doFilter(request, response);
	}

	private static Map<String, String> buildParams(HttpServletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();
		if (parameterNames != null) {
			Map<String, String> params = Maps.newHashMapWithExpectedSize(64);
			String parameterName, parameterValue;
			while (parameterNames.hasMoreElements()) {
				parameterName = parameterNames.nextElement();
				parameterValue = request.getParameter(parameterName);
				if (parameterValue != null && parameterValue.length() > 0) {
					params.put(parameterName, request.getParameter(parameterName));
				}
			}
			return params;
		}
		return null;
	}
}
