package com.yunfenghui.jf.api.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Sets;
import com.yunfenghui.jf.api.filter.AbstractApiFilter.OrderedFilters;
import com.yunfenghui.jf.api.filter.support.Authenticator;
import com.yunfenghui.jf.api.filter.support.SignVerificator;

@Configuration
public class FilterConfiguration {
	@Bean
	public FilterRegistrationBean<AuthentionFilter> authentionFilterBean() {
		FilterRegistrationBean<AuthentionFilter> filterBean = new FilterRegistrationBean<>();
		filterBean.setFilter(authentionFilter());
		filterBean.setUrlPatterns(Sets.newHashSet("/*"));
		filterBean.setOrder(OrderedFilters.AUTH.ordinal());
		return filterBean;
	}

	@Bean
	public AuthentionFilter authentionFilter() {
		return new AuthentionFilter();
	}

	@Bean
	public FilterRegistrationBean<SignVerificationFilter> signVerificationFilterBean() {
		FilterRegistrationBean<SignVerificationFilter> filterBean = new FilterRegistrationBean<>();
		filterBean.setFilter(signVerificationFilter());
		filterBean.setUrlPatterns(Sets.newHashSet("/*"));
		filterBean.setOrder(OrderedFilters.SIGN_VER.ordinal());
		return filterBean;
	}

	@Bean
	public SignVerificationFilter signVerificationFilter() {
		return new SignVerificationFilter();
	}

	@Bean(name = Authenticator.ID)
	public Authenticator authenticator() {
		return new Authenticator();
	}

	@Bean(name = SignVerificator.ID)
	public SignVerificator signVerificator() {
		return new SignVerificator();
	}
}
