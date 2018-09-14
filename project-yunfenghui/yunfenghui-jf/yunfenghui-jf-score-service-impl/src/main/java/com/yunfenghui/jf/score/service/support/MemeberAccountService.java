package com.yunfenghui.jf.score.service.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunfenghui.jf.score.dao.MemberAccountDao;
import com.yunfenghui.jf.score.model.MemberAccount;

@Service(MemeberAccountService.ID)
public class MemeberAccountService {
	public static final String ID = "memeberAccountService";

	@Autowired
	private MemberAccountDao memberAccountDao;

	/**
	 * 根据会员id获取账户信息
	 * 
	 * @param memberId
	 * @return
	 */
	public MemberAccount getAccount(int memberId) {
		return memberAccountDao.queryAccountById(memberId);
	}

	/**
	 * 查询topN会员账户，条件必须大于memberId.结果需要按照memberId升序排序。
	 * 用于白积分转换红积分Job调用.
	 * 
	 * @param topN
	 * @param memberId
	 * @return
	 */
	public List<MemberAccount> getTopNAccounts(int topN, int memberId) {
		return null;
	}
}
