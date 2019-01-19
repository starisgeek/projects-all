package com.seasaw.member.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.seasaw.member.model.Member;

@Repository
public class MemberDao {
	private Map<String, Member> nameAndMemberMap = new HashMap<>();
	private Map<Integer, Member> idAndMemberMap = new HashMap<>();
	private final AtomicInteger idGenerator = new AtomicInteger(0);

	public int insertMember(Member member) {
		int id = idGenerator.incrementAndGet();
		member.setId(id);
		idAndMemberMap.put(id, member);
		nameAndMemberMap.put(member.getName(), member);
		return member.getId();
	}

	public Member queryMemberById(int id) {
		return idAndMemberMap.get(id);
	}

	public Member queryMemberByName(String name) {
		return nameAndMemberMap.get(name);
	}

	public int updateSecret(String name, String secret) {
		Member member = nameAndMemberMap.get(name);
		if (member != null) {
			member.setSecret(secret);
			return 1;
		}
		return 0;
	}
}
