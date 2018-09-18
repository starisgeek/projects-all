package com.yunfenghui.jf.score.service.support;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunfenghui.common.page.Page;
import com.yunfenghui.common.page.PageResult;
import com.yunfenghui.common.service.NumberGenerator;
import com.yunfenghui.jf.common.JFException;
import com.yunfenghui.jf.score.dao.PartnerAccountDao;
import com.yunfenghui.jf.score.dao.PartnerStockScoreChangeRecordDao;
import com.yunfenghui.jf.score.dao.PartnerStockScoreRechargeRecordDao;
import com.yunfenghui.jf.score.dao.PartnerWhiteScoreChangeRecordDao;
import com.yunfenghui.jf.score.model.AccountChangeRecord;
import com.yunfenghui.jf.score.model.ChangeRecordDealTypes.PartnerStockScoreChangeRecordDealType;
import com.yunfenghui.jf.score.model.ChangeRecordDealTypes.PartnerWhiteScoreChangeRecordDealType;
import com.yunfenghui.jf.score.model.PartnerAccount;
import com.yunfenghui.jf.score.model.StockScoreRechargeRecord;
import com.yunfenghui.jf.score.model.WhiteScoreTransformRecord;
import com.yunfenghui.jf.score.service.ScoreService.ChangeRecordQuery;
import com.yunfenghui.jf.score.service.ScoreService.ScoreTransformRecordQuery;

@Service(PartnerAccountService.ID)
public class PartnerAccountService {
	public static final String ID = "partnerAccountService";
	@Autowired
	private PartnerAccountDao partnerAccountDao;
	@Autowired
	private PartnerStockScoreChangeRecordDao stockScoreChangeRecordDao;
	@Autowired
	private PartnerStockScoreRechargeRecordDao rechargeRecordDao;
	@Autowired
	private PartnerWhiteScoreChangeRecordDao whiteScoreChangeRecordDao;
	@Resource(name = "serialNumberGenerator")
	private NumberGenerator numberGenerator;

	/**
	 * 充值商户库存积分(如果商户账户不存在，则创建)
	 * 
	 * @param partnerId
	 * @param rechargeMoney
	 * @param createUserId
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void rechargeStockScore(int partnerId, int rechargeMoney, int createUserId) {
		// create stock score recharge record
		StockScoreRechargeRecord rechargeRecord = createStockScoreRechargeRecord(partnerId,
				rechargeMoney, createUserId);

		// create or increase partner account stock score and white score
		PartnerAccount account = partnerAccountDao.queryAccountById(partnerId);
		if (account == null) {
			account = new PartnerAccount();
			account.setPartnerId(partnerId);
			account.setStockScores(rechargeRecord.getIncreaseStockScores());
			account.setWhiteScores(rechargeRecord.getIncreaseWhiteScores());
			account.setCreateTime(new Date());
			partnerAccountDao.insertAccount(account);
		} else {
			partnerAccountDao.increaseStockScoreAndWhiteScore(partnerId,
					rechargeRecord.getIncreaseStockScores(),
					rechargeRecord.getIncreaseWhiteScores());
		}

		// create partner stock score and white score change record
		AccountChangeRecord changeRecord = AccountChangeRecord.newBuilder()
				.serialNumber(numberGenerator.generate()).accountId(partnerId)
				.changeAmount(rechargeRecord.getIncreaseStockScores())
				.dealType(PartnerStockScoreChangeRecordDealType.RECHARGE)
				.originalRecordNo(rechargeRecord.getRecordNo()).createTime(new Date()).build();
		stockScoreChangeRecordDao.insertChangeRecord(changeRecord);
		changeRecord = AccountChangeRecord.newBuilder().serialNumber(numberGenerator.generate())
				.accountId(partnerId).changeAmount(rechargeRecord.getIncreaseWhiteScores())
				.dealType(PartnerWhiteScoreChangeRecordDealType.RECHARGE)
				.originalRecordNo(rechargeRecord.getRecordNo()).createTime(new Date()).build();
		whiteScoreChangeRecordDao.insertChangeRecord(changeRecord);
	}

	private StockScoreRechargeRecord createStockScoreRechargeRecord(int partnerId,
			int rechargeMoney, int createUserId) {
		StockScoreRechargeRecord rechargeRecord = new StockScoreRechargeRecord();
		rechargeRecord.setRecordNo(numberGenerator.generate());
		rechargeRecord.setPartnerId(partnerId);
		rechargeRecord.setRechargeMoney(rechargeMoney);
		rechargeRecord.setIncreaseStockScores(rechargeMoney * 10);
		rechargeRecord.setIncreaseWhiteScores(rechargeMoney);
		rechargeRecord.setCreateUserId(createUserId);
		rechargeRecord.setCreateTime(new Date());
		rechargeRecordDao.insertRechargeRecord(rechargeRecord);
		return rechargeRecord;
	}

	/**
	 * 根据条件分页查询商户库存积分变动流水
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	public PageResult<AccountChangeRecord> getStockScoreChangeRecords(ChangeRecordQuery query,
			Page page) {
		return null;
	}

	/**
	 * 根据商户id查询账户信息
	 * 
	 * @param partnerId
	 * @return
	 */
	public PartnerAccount getAccount(int partnerId) {
		return null;
	}

	/**
	 * 根据条件分页查询商户白积分变动流水
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	public PageResult<AccountChangeRecord> getWhiteScoreChangeRecords(ChangeRecordQuery query,
			Page page) {
		return null;
	}

	/**
	 * 根据条件分页查询商户白积分转换记录。按照时间降序排序。
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	public PageResult<WhiteScoreTransformRecord> getWhiteScoreTransformRecords(
			ScoreTransformRecordQuery query, Page page) {
		return null;
	}

	/**
	 * 根据条件分页查询商户红积分变动流水
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	public PageResult<AccountChangeRecord> getRedScoreChangeRecords(ChangeRecordQuery query,
			Page page) {
		return null;
	}

	/**
	 * 转换商户红积分到消费余额
	 * 
	 * @param partnerId
	 * @param transformScores
	 * @throws JFException
	 *             商户不存在。红积分余额不足。
	 */
	public void transformAccountRedScore(int partnerId, int transformScores) throws JFException {

	}

	/**
	 * 根据条件分页查询商户余额变动流水
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	public PageResult<AccountChangeRecord> getBalanceChangeRecords(ChangeRecordQuery query,
			Page page) {
		return null;
	}

	/**
	 * 商户申请余额提现
	 * 
	 * @param partnerId
	 * @param cashOutAmount
	 * @throws JFException
	 *             余额不足
	 */
	public void applyBalanceCashOut(int partnerId, int cashOutAmount) throws JFException {

	}

	/**
	 * 审核商户余额提现
	 * 
	 * @param cashOutRecordNo
	 * @param approveUserId
	 * @throws JFException
	 *             记录不存在。记录状态错误
	 */
	public void approveBalanceCashOut(String cashOutRecordNo, int approveUserId)
			throws JFException {

	}

	/**
	 * 驳回商户余额提现
	 * 
	 * @param cashOutRecordNo
	 * @param approveUserId
	 * @param reason
	 * @throws JFException
	 *             记录不存在。记录状态错误
	 */
	public void rejectBalanceCashOut(String cashOutRecordNo, int rejectUserId, String reason)
			throws JFException {

	}
}
