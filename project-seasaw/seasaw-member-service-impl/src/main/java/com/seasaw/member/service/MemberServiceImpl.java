package com.seasaw.member.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seasaw.member.dao.MemberDao;
import com.seasaw.member.model.Member;
import com.seasaw.member.util.MemberBusinessException;
import com.seasaw.member.util.MessageCode;

@Service(MemberService.ID)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;

	@Override
	public void register(Member member) throws MemberBusinessException {
		memberDao.insertMember(member);
	}

	@Override
	public Member getMemberById(int memberId) {
		return memberDao.queryMemberById(memberId);
	}

	@Override
	public Member getMemberByName(String name) {
		return memberDao.queryMemberByName(name);
	}

	@Override
	public String getSecret(String name) {
		Member member = memberDao.queryMemberByName(name);
		return member != null ? member.getSecret() : null;
	}

	@Override
	public String generateSecret(String name) throws MemberBusinessException {
		String secret = UUID.randomUUID().toString();
		if (memberDao.updateSecret(name, secret) == 1) {
			return secret;
		}
		throw new MemberBusinessException(MessageCode.MEMBER_NAME_NOT_FOUND, "会员名称不存在");
	}

}
