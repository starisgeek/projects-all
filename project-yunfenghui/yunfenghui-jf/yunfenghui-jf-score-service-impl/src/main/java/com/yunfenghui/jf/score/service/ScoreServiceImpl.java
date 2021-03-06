package com.yunfenghui.jf.score.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.rocketmq.client.producer.MQProducer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.yunfenghui.common.page.Page;
import com.yunfenghui.common.page.PageResult;
import com.yunfenghui.common.service.NumberGenerator;
import com.yunfenghui.jf.common.JFException;
import com.yunfenghui.jf.score.model.AccountChangeRecord;
import com.yunfenghui.jf.score.model.MemberAccount;
import com.yunfenghui.jf.score.model.PartnerAccount;
import com.yunfenghui.jf.score.model.PayRecord;
import com.yunfenghui.jf.score.model.PayRequest;
import com.yunfenghui.jf.score.model.RedScoreTransformRecord;
import com.yunfenghui.jf.score.model.RefundRecord;
import com.yunfenghui.jf.score.model.RefundRequest;
import com.yunfenghui.jf.score.model.WhiteScoreSendRecord;
import com.yunfenghui.jf.score.model.WhiteScoreSendRecordNotify;
import com.yunfenghui.jf.score.model.WhiteScoreSendRequest;
import com.yunfenghui.jf.score.model.WhiteScoreTransformJob;
import com.yunfenghui.jf.score.model.WhiteScoreTransformRecord;
import com.yunfenghui.jf.score.service.support.BalanceService;
import com.yunfenghui.jf.score.service.support.MemeberAccountService;
import com.yunfenghui.jf.score.service.support.PartnerAccountService;
import com.yunfenghui.jf.score.service.support.RedScoreService;
import com.yunfenghui.jf.score.service.support.WhiteScoreService;

@Service(ScoreService.ID)
public class ScoreServiceImpl implements ScoreService {
	@Resource(name = "serialNumberGenerator")
	private NumberGenerator numberGenerator;
	@Resource(name = "mqProducer")
	private MQProducer producer;
	@Resource(name = MemeberAccountService.ID)
	private MemeberAccountService memberAccountService;
	@Resource(name = WhiteScoreService.ID)
	private WhiteScoreService whiteScoreService;
	@Resource(name = RedScoreService.ID)
	private RedScoreService redScoreService;
	@Resource(name = BalanceService.ID)
	private BalanceService balanceService;
	@Resource(name = PartnerAccountService.ID)
	private PartnerAccountService partnerAccountService;
	@Resource(name = "stringRedisTemplate")
	private StringRedisTemplate redisTemplate;
	private static final int ABSENT_BALANCE = 0;
	private static final String TRANSFORM_RATIO_KEY_PREFIX = "transform:";

	@Override
	public WhiteScoreSendRecord sendWhiteScore(WhiteScoreSendRequest sendRequest) {
		WhiteScoreSendRecord sendRecord = whiteScoreService
				.getWhiteScoreSendRecord(sendRequest.getPartnerId(), sendRequest.getOutTradeNo());
		if (sendRecord != null) {
			return sendRecord;
		}

		sendRecord = WhiteScoreSendRecord.newBuilder().recordNo(numberGenerator.generate())
				.memberId(sendRequest.getMemberId()).partnerId(sendRequest.getPartnerId())
				.outTradeNo(sendRequest.getOutTradeNo()).sendScores(sendRequest.getSendScores())
				.dealType(sendRequest.getDealType()).notifyUrl(sendRequest.getNotifyUrl())
				.createTime(new Date()).status(WhiteScoreSendRecord.STATUS_PENDING).build();
		whiteScoreService.addWhiteScoreSendRecord(sendRecord);

		whiteScoreService.sendWhiteScoreSendRecordMessage(sendRecord);

		return sendRecord;
	}

	@Override
	public void handleWhiteScoreSendRecords(List<WhiteScoreSendRecord> sendRecords) {
		List<WhiteScoreSendRecordNotify> notifies = whiteScoreService
				.handleWhiteScoreSendRecords(sendRecords);

		whiteScoreService.sendWhiteScoreSendRecordNotifyMessages(notifies);
	}

	@Override
	public void handleWhiteScoreSendRecordNotifies(
			List<WhiteScoreSendRecordNotify> sendRecordNotifies) {
		whiteScoreService.handleWhiteScoreSendRecordNotifies(sendRecordNotifies);
	}

	@Override
	public WhiteScoreSendRecord getWhiteScoreSendRecordBy(String tradeNo, int partnerId,
			String outTradeNo) {
		return whiteScoreService.getSendRecordBy(tradeNo, partnerId, outTradeNo);
	}

	@Override
	public MemberAccount getMemberAccountBy(int memberId) {
		return memberAccountService.getAccount(memberId);
	}

	@Override
	public int getWhiteScoreBalance(int memberId) {
		Integer balance = whiteScoreService.getBalance(memberId);
		return balance != null ? balance : ABSENT_BALANCE;
	}

	@Override
	public PageResult<AccountChangeRecord> getWhiteScoreChangeRecords(ChangeRecordQuery query,
			Page page) {
		return whiteScoreService.getChangeRecords(query, page);
	}

	@Override
	public WhiteScoreTransformJob getOrCreateWhiteScoreTransformJob(int transformDate) {
		WhiteScoreTransformJob job = whiteScoreService.getTransformJob(transformDate);
		if (job == null) {
			job = new WhiteScoreTransformJob();
			job.setTransformDate(transformDate);
			job.setStatus(WhiteScoreTransformJob.STATUS_PENDING);
			whiteScoreService.addTransformJob(job);
		}
		return job;
	}

	@Override
	public void batchTransformWhiteScore(List<MemberAccount> memberAccounts, int transformDate) {
		int transformRatio = getWhiteScoreTransformRatio(transformDate);
		List<WhiteScoreTransformRecord> transformRecords = buildWhiteScoreTransformRecords(
				memberAccounts, transformDate, transformRatio);
		whiteScoreService.handleTransformRecords(transformRecords);
	}

	@Override
	public void batchTransformWhiteScoreAndCompleteWhiteScoreTransformJob(
			List<MemberAccount> memberAccounts, int transformDate) {
		int transformRatio = getWhiteScoreTransformRatio(transformDate);
		List<WhiteScoreTransformRecord> transformRecords = buildWhiteScoreTransformRecords(
				memberAccounts, transformDate, transformRatio);
		whiteScoreService.handleTransformRecordsAndCompleteTransformJob(transformRecords,
				transformDate);
	}

	@Override
	public void completeWhiteScoreTransformJob(int transformDate) {
		whiteScoreService.completeTransformJob(transformDate);
	}

	private List<WhiteScoreTransformRecord> buildWhiteScoreTransformRecords(
			List<MemberAccount> memberAccounts, int transformDate, int transformRatio) {
		List<WhiteScoreTransformRecord> transformRecords = new ArrayList<>(memberAccounts.size());
		for (MemberAccount memberAccount : memberAccounts) {
			transformRecords.add(buildWhiteScoreTransformRecord(memberAccount, transformRatio));
		}

		return transformRecords;
	}

	private WhiteScoreTransformRecord buildWhiteScoreTransformRecord(MemberAccount memberAccount,
			int transformRatio) {
		WhiteScoreTransformRecord transformRecord = new WhiteScoreTransformRecord();
		transformRecord.setRecordNo(numberGenerator.generate());
		transformRecord.setMemberId(memberAccount.getMemberId());
		transformRecord.setTransformScores(
				calculateTransformWhiteScore(memberAccount.getWhiteScores(), transformRatio));
		transformRecord.setCreateTime(new Date());
		return transformRecord;
	}

	private int calculateTransformWhiteScore(int whiteScores, int transformRatio) {
		return (whiteScores / 10000) * transformRatio / 100;
	}

	@Override
	public Integer getMaxMemberIdOfWhiteScoreTransformRecordsBy(Date startTime, Date endTime) {
		return whiteScoreService.getMaxMemberIdOfTransformRecordsBy(startTime, endTime);
	}

	@Override
	public int getWhiteScoreTransformRatio(int transformDate) {
		final String key = TRANSFORM_RATIO_KEY_PREFIX + transformDate;
		// query from cache first
		String ratioString = redisTemplate.opsForValue().get(key);
		if (ratioString != null) {
			return Integer.valueOf(ratioString);
		}
		// get from db
		Integer ratio = whiteScoreService.getTransformRatio(transformDate);
		if (ratio != null) {
			// add to cache
			redisTemplate.opsForValue().set(key, ratio.toString());
			return ratio;
		}
		// 这里考虑用分布式锁实现
		Random r = new Random();
		ratio = 500 + r.nextInt(100);
		whiteScoreService.addTransformRatio(transformDate, ratio);
		// add to cache
		redisTemplate.opsForValue().set(key, ratio.toString());
		return ratio;
	}

	@Override
	public PageResult<WhiteScoreTransformRecord> getWhiteScoreTransformRecords(
			ScoreTransformRecordQuery query, Page page) {
		return whiteScoreService.getTransformRecords(query, page);
	}

	@Override
	public int getRedScoreBalance(int memberId) {
		Integer balance = redScoreService.getRedScoreBalance(memberId);
		return balance != null ? balance : ABSENT_BALANCE;
	}

	@Override
	public PageResult<AccountChangeRecord> getRedScoreChangeRecords(ChangeRecordQuery query,
			Page page) {
		return redScoreService.getRedScoreChangeRecords(query, page);
	}

	@Override
	public void transformAccountRedScore(int memberId, int transformScores) throws JFException {
		redScoreService.transformAccountRedScore(memberId, transformScores);
	}

	@Override
	public PageResult<RedScoreTransformRecord> getRedScoreTransformRecords(
			ScoreTransformRecordQuery query, Page page) {
		return redScoreService.getRedScoreTransformRecords(query, page);
	}

	@Override
	public int getBalance(int memberId) {
		Integer balance = balanceService.getBalance(memberId);
		return balance != null ? balance : ABSENT_BALANCE;
	}

	@Override
	public void applyBalanceCashOut(int memberId, int cashOutAmount) throws JFException {
		balanceService.applyBalanceCashOut(memberId, cashOutAmount);
	}

	@Override
	public void approveBalanceCashOut(String cashOutRecordNo, int approveUserId)
			throws JFException {
		balanceService.approveBalanceCashOut(cashOutRecordNo, approveUserId);
	}

	@Override
	public void rejectBalanceCashOut(String cashOutRecordNo, int rejectUserId, String reason)
			throws JFException {
		balanceService.rejectBalanceCashOut(cashOutRecordNo, rejectUserId, reason);
	}

	@Override
	public PayRecord pay(PayRequest payRequest) throws JFException {
		return balanceService.pay(payRequest);
	}

	@Override
	public PayRecord getPayRecord(String recordNo, int partnerId, String outTradeNo) {
		return balanceService.getPayRecord(recordNo, partnerId, outTradeNo);
	}

	@Override
	public PageResult<PayRecord> getPayRecords(PayRecordQuery query, Page page) {
		return balanceService.getPayRecords(query, page);
	}

	@Override
	public RefundRecord refund(RefundRequest refundRequest) throws JFException {
		return balanceService.refund(refundRequest);
	}

	@Override
	public RefundRecord getRefundRecord(String recordNo, int partnerId, String outRefundNo) {
		return balanceService.getRefundRecord(recordNo, partnerId, outRefundNo);
	}

	@Override
	public PageResult<RefundRecord> getRefundRecords(RefundRecordQuery query, Page page) {
		return balanceService.getRefundRecords(query, page);
	}

	@Override
	public List<RefundRecord> getRefundRecordsByPayRecordNo(String payRecordNo) {
		return balanceService.getRefundRecordsByPayRecordNo(payRecordNo);
	}

	@Override
	public List<MemberAccount> getTopNMemberAccounts(int topN, int memberId, int whiteScores,
			Date createTime) {
		return memberAccountService.getTopNAccounts(topN, memberId, whiteScores, createTime);
	}

	@Override
	public PageResult<AccountChangeRecord> getBalanceChangeRecords(ChangeRecordQuery query,
			Page page) {
		return balanceService.getBalanceChangeRecords(query, page);
	}

	@Override
	public PageResult<AccountChangeRecord> getPartnerStockScoreChangeRecords(
			ChangeRecordQuery query, Page page) {
		return partnerAccountService.getStockScoreChangeRecords(query, page);
	}

	@Override
	public PartnerAccount getPartnerAccount(int partnerId) {
		return partnerAccountService.getAccount(partnerId);
	}

	@Override
	public PageResult<AccountChangeRecord> getPartnerWhiteScoreChangeRecords(
			ChangeRecordQuery query, Page page) {
		return partnerAccountService.getWhiteScoreChangeRecords(query, page);
	}

	@Override
	public PageResult<WhiteScoreTransformRecord> getPartnerWhiteScoreTransformRecords(
			ScoreTransformRecordQuery query, Page page) {
		return partnerAccountService.getWhiteScoreTransformRecords(query, page);
	}

	@Override
	public PageResult<AccountChangeRecord> getPartnerRedScoreChangeRecords(ChangeRecordQuery query,
			Page page) {
		return partnerAccountService.getRedScoreChangeRecords(query, page);
	}

	@Override
	public void transformPartnerAccountRedScore(int partnerId, int transformScores)
			throws JFException {
		partnerAccountService.transformAccountRedScore(partnerId, transformScores);
	}

	@Override
	public PageResult<AccountChangeRecord> getPartnerBalanceChangeRecords(ChangeRecordQuery query,
			Page page) {
		return partnerAccountService.getBalanceChangeRecords(query, page);
	}

	@Override
	public void applyPartnerBalanceCashOut(int partnerId, int cashOutAmount) throws JFException {
		partnerAccountService.applyBalanceCashOut(partnerId, cashOutAmount);
	}

	@Override
	public void approvePartnerBalanceCashOut(String cashOutRecordNo, int approveUserId)
			throws JFException {
		partnerAccountService.approveBalanceCashOut(cashOutRecordNo, approveUserId);
	}

	@Override
	public void rejectPartnerBalanceCashOut(String cashOutRecordNo, int rejectUserId, String reason)
			throws JFException {
		partnerAccountService.rejectBalanceCashOut(cashOutRecordNo, rejectUserId, reason);
	}

	@Override
	public void rechargePartnerStockScore(int partnerId, int rechargeMoney, int createUserId) {
		partnerAccountService.rechargeStockScore(partnerId, rechargeMoney, createUserId);
	}

}
