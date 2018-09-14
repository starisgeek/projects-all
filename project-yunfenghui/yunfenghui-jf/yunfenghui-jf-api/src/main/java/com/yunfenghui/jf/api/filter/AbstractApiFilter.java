package com.yunfenghui.jf.api.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.yunfenghui.jf.api.response.ApiResponse;

import ocm.yunfenghui.jf.common.JFException;

public abstract class AbstractApiFilter implements Filter {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			doFilterInternal((HttpServletRequest) request, (HttpServletResponse) response, chain);
		} catch (Exception e) {
			writeException((HttpServletResponse) response, e);
		}
	}

	protected abstract void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain) throws Exception;

	protected static void writeException(HttpServletResponse response, Exception e)
			throws IOException {
		response.getWriter().write(JSON.toJSONString(ApiResponse
				.fail(e instanceof JFException ? ((JFException) e).getErrorCode() : "")));
	}

	public static enum OrderedFilters {
		AUTH, SIGN_VER
	}
}
