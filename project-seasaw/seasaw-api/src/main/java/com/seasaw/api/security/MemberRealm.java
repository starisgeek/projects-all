package com.seasaw.api.security;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;

import com.seasaw.member.model.Member;
import com.seasaw.member.service.MemberService;

public class MemberRealm extends AuthenticatingRealm {
	@Resource(name = "memberService")
	private MemberService memberService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String memberName = upToken.getUsername();
		Member member = memberService.getMemberByName(memberName);

		if (member != null) {
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(memberName,
					member.getPassword(), "memberRealm");
			return info;
		}
		throw new AuthenticationException("用户" + memberName + "不存在");
	}
}
