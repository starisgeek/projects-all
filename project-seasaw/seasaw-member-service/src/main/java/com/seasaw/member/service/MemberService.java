package com.seasaw.member.service;

import com.seasaw.member.model.Member;
import com.seasaw.member.util.MemberBusinessException;

public interface MemberService {
	String ID = "memberService";

	/**
	 * 注册会员(手机端)
	 * 
	 * @param member
	 * @throws SeasawException
	 */
	void register(Member member) throws MemberBusinessException;

	Member getMemberById(int memberId);

	Member getMemberByName(String name);

	String getSecret(String name);

	String generateSecret(String name) throws MemberBusinessException;
}
