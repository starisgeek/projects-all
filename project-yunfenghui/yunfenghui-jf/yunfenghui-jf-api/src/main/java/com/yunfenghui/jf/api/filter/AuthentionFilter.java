package com.yunfenghui.jf.api.filter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yunfenghui.jf.api.filter.support.Authenticator;
import com.yunfenghui.jf.api.filter.support.Subject;

public class AuthentionFilter extends AbstractApiFilter {
	@Resource(name = Authenticator.ID)
	private Authenticator authenticator;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws Exception {
		Subject subject = null;
		try {
			subject = authenticator.authenticate(request.getParameter("appId"));
			Subject.bind(subject);
			chain.doFilter(request, response);
		} finally {
			if (subject != null) {
				Subject.unbind();
			}
		}
	}

}
