package com.yunfenghui.jf.score.service.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.rocketmq.client.producer.MQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunfenghui.common.page.Page;
import com.yunfenghui.common.page.PageResult;
import com.yunfenghui.common.service.NumberGenerator;
import com.yunfenghui.jf.score.dao.MemberAccountDao;
import com.yunfenghui.jf.score.dao.PartnerAccountDao;
import com.yunfenghui.jf.score.dao.PartnerStockScoreChangeRecordDao;
import com.yunfenghui.jf.score.dao.WhiteScoreChangeRecordDao;
import com.yunfenghui.jf.score.dao.WhiteScoreSendRecordDao;
import com.yunfenghui.jf.score.dao.WhiteScoreSendRecordNotifyDao;
import com.yunfenghui.jf.score.model.AccountChangeRecord;
import com.yunfenghui.jf.score.model.ChangeRecordDealTypes.PartnerStockScoreChangeRecordDealType;
import com.yunfenghui.jf.score.model.ChangeRecordDealTypes.WhiteScoreChangeRecordDealType;
import com.yunfenghui.jf.score.model.MemberAccount;
import com.yunfenghui.jf.score.model.WhiteScoreSendRecord;
import com.yunfenghui.jf.score.model.WhiteScoreSendRecordNotify;
import com.yunfenghui.jf.score.model.WhiteScoreTransformRecord;
import com.yunfenghui.jf.score.service.ScoreService.ChangeRecordQuery;
import com.yunfenghui.jf.score.service.ScoreService.ScoreTransformRecordQuery;
import com.yunfenghui.jf.score.util.ScoreMessageCode;

@Service(WhiteScoreService.ID)
public class WhiteScoreService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	public static final String ID = "whiteScoreService";
	@Resource(name = "mqProducer")
	private MQProducer producer;
	@Autowired
	private MemberAccountDao memberAccountDao;
	@Autowired
	private WhiteScoreSendRecordDao whiteScoreSendRecordDao;
	@Autowired
	private WhiteScoreChangeRecordDao whiteScoreChangeRecordDao;
	@Autowired
	private WhiteScoreSendRecordNotifyDao whiteScoreSendRecordNotifyDao;
	@Autowired
	private PartnerAccountDao partnerAccountDao;
	@Autowired
	private PartnerStockScoreChangeRecordDao partnerStockScoreChangeRecordDao;
	@Resource(name = "serialNumberGenerator")
	private NumberGenerator numberGenerator;
	@Resource(name = "whiteScoreSendRecordMessageSender")
	private RetriedMessageSender whiteScoreSendRecordMessageSender;
	@Resource(name = "whiteScoreSendRecordNotifyMessageSender")
	private RetriedMessageSender whiteScoreSendRecordNotifyMessageSender;

	public WhiteScoreSendRecord getWhiteScoreSendRecord(int partnerId, String outTradeNo) {
		return whiteScoreSendRecordDao.querySendRecordByPartnerIdAndOutTradeNo(partnerId,
				outTradeNo);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void addWhiteScoreSendRecord(WhiteScoreSendRecord sendRecord) {
		whiteScoreSendRecordDao.insertSendRecord(sendRecord);
	}

	public void sendWhiteScoreSendRecordMessage(WhiteScoreSendRecord sendRecord) {
		whiteScoreSendRecordMessageSender.send(sendRecord);
	}

	/**
	 * 处理白积分发放记录。
	 * 
	 * @param sendRecord
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public List<WhiteScoreSendRecordNotify> handleWhiteScoreSendRecords(
			List<WhiteScoreSendRecord> sendRecords) {
		if (sendRecords != null && !sendRecords.isEmpty()) {
			List<WhiteScoreSendRecord> successSendRecords = new LinkedList<>();
			List<WhiteScoreSendRecordNotify> notifies = new LinkedList<>();
			for (WhiteScoreSendRecord sendRecord : sendRecords) {
				int updated = partnerAccountDao.decreaseStockScore(sendRecord.getPartnerId(),
						sendRecord.getSendScores());
				// partner stock score not enough
				if (updated == 0) {
					updated = whiteScoreSendRecordDao.updateSendRecordStatusAndErrorCode(
							sendRecord.getRecordNo(), WhiteScoreSendRecord.STATUS_PENDING,
							WhiteScoreSendRecord.STATUS_SEND_FAILED,
							ScoreMessageCode.PARTNER_STOCK_SCORE_NOT_ENOUGH, new Date());
					// 幂等判断
					if (updated == 1) {
						if (sendRecord.getNotifyUrl() != null) {
							notifies.add(buildWhiteScoreSendRecordNotify(sendRecord,
									WhiteScoreSendRecord.STATUS_SEND_FAILED,
									ScoreMessageCode.PARTNER_STOCK_SCORE_NOT_ENOUGH));
						}
					} else {
						logger.info(
								"Failed to updateSendRecordStatusAndErrorCode, from:{} to {}, maybe already updated",
								WhiteScoreSendRecord.STATUS_PENDING,
								WhiteScoreSendRecord.STATUS_SEND_FAILED);
					}
				} else {
					updated = whiteScoreSendRecordDao.updateSendRecordStatusAndErrorCode(
							sendRecord.getRecordNo(), WhiteScoreSendRecord.STATUS_PENDING,
							WhiteScoreSendRecord.STATUS_SEND_SUCCESS, null, new Date());
					// 幂等判断
					if (updated == 1) {
						successSendRecords.add(sendRecord);
						if (sendRecord.getNotifyUrl() != null) {
							notifies.add(buildWhiteScoreSendRecordNotify(sendRecord,
									WhiteScoreSendRecord.STATUS_SEND_SUCCESS, null));
						}
					} else {
						partnerAccountDao.increaseStockScore(sendRecord.getPartnerId(),
								sendRecord.getSendScores());
					}
				}
			}
			if (!successSendRecords.isEmpty()) {
				handleSuccessWhiteScoreSendRecords(sendRecords);
			}

			if (!notifies.isEmpty()) {
				whiteScoreSendRecordNotifyDao.batchInsertNotifies(notifies);
			}

			return notifies;
		}
		return null;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void handleWhiteScoreSendRecordNotifies(List<WhiteScoreSendRecordNotify> notifies) {
		if (notifies != null && !notifies.isEmpty()) {
			List<String> recordNos = new ArrayList<>(notifies.size());
			for (WhiteScoreSendRecordNotify notify : notifies) {
				recordNos.add(notify.getRecordNo());
				// Here should send a notify to partner notify url
				logger.info("Handled white score send record notify:{}", notify.getRecordNo());
			}
			whiteScoreSendRecordNotifyDao.batchDeleteNotifies(recordNos);
		}
	}

	public void sendWhiteScoreSendRecordNotifyMessages(List<WhiteScoreSendRecordNotify> notifies) {
		if (notifies != null && !notifies.isEmpty()) {
			for (WhiteScoreSendRecordNotify notify : notifies) {
				sendWhiteScoreSendRecordNotifyMessage(notify);
			}
		}
	}

	protected void sendWhiteScoreSendRecordNotifyMessage(WhiteScoreSendRecordNotify notify) {
		whiteScoreSendRecordNotifyMessageSender.send(notify);
	}

	private void handleSuccessWhiteScoreSendRecords(List<WhiteScoreSendRecord> sendRecords) {
		List<AccountChangeRecord> partnerStockScoreChangeRecords = new ArrayList<>(
				sendRecords.size());
		List<AccountChangeRecord> whiteScoreChangeRecords = new ArrayList<>(sendRecords.size());

		for (WhiteScoreSendRecord sendRecord : sendRecords) {
			partnerStockScoreChangeRecords.add(buildPartnerStockScoreChangeRecord(sendRecord));
			increaseWhiteScore(sendRecord);
			whiteScoreChangeRecords.add(buildWhiteScoreChangeRecord(sendRecord));
		}
		partnerStockScoreChangeRecordDao.batchInsertChangeRecords(partnerStockScoreChangeRecords);
		whiteScoreChangeRecordDao.batchInsertChangeRecords(whiteScoreChangeRecords);
	}

	private AccountChangeRecord buildPartnerStockScoreChangeRecord(
			WhiteScoreSendRecord sendRecord) {
		return AccountChangeRecord.newBuilder().serialNumber(numberGenerator.generate())
				.accountId(sendRecord.getPartnerId()).changeAmount(-sendRecord.getSendScores())
				.dealType(partnerStockScoreChangeRecordDealTypeOf(sendRecord.getDealType()))
				.originalRecordNo(sendRecord.getRecordNo()).createTime(new Date()).build();
	}

	private AccountChangeRecord buildWhiteScoreChangeRecord(WhiteScoreSendRecord sendRecord) {
		return AccountChangeRecord.newBuilder().serialNumber(numberGenerator.generate())
				.accountId(sendRecord.getMemberId()).changeAmount(sendRecord.getSendScores())
				.dealType(PartnerStockScoreChangeRecordDealType.CONSUME)
				.originalRecordNo(sendRecord.getRecordNo()).createTime(new Date()).build();
	}

	private void increaseWhiteScore(WhiteScoreSendRecord sendRecord) {
		int updated = memberAccountDao.increaseWhiteScore(sendRecord.getMemberId(),
				sendRecord.getSendScores());
		if (updated != 1) {
			// create member account, maybe concurrent
			MemberAccount account = new MemberAccount();
			account.setMemberId(sendRecord.getMemberId());
			account.setWhiteScores(sendRecord.getSendScores());
			account.setRedScores(0);
			account.setBalance(0);
			account.setCreateTime(new Date());
			try {
				memberAccountDao.insertAccount(account);
			} catch (RuntimeException e) {
				logger.error("Failed to insertAccount, because memberAccount:{} maybe not exists",
						sendRecord.getMemberId(), e);
				// if exception happen, maybe member account already created by
				// other thread, retry to increase
				updated = memberAccountDao.increaseWhiteScore(sendRecord.getMemberId(),
						sendRecord.getSendScores());
				if (updated != 1) {
					logger.error(
							"Failed to increaseWhiteScore, because failed to insertAccount:{}, this must be not happen",
							sendRecord.getMemberId(), e);
				}
			}
		}
	}

	private WhiteScoreSendRecordNotify buildWhiteScoreSendRecordNotify(
			WhiteScoreSendRecord sendRecord, int status, String errorCode) {
		return WhiteScoreSendRecordNotify.newBuilder().recordNo(sendRecord.getRecordNo())
				.memberId(sendRecord.getMemberId()).partnerId(sendRecord.getPartnerId())
				.outTradeNo(sendRecord.getOutTradeNo()).sendScores(sendRecord.getSendScores())
				.createTime(new Date()).status(status).errorCode(errorCode).build();
	}

	private static int partnerStockScoreChangeRecordDealTypeOf(int whiteScoreChangeRecordDealType) {
		switch (whiteScoreChangeRecordDealType) {
		case WhiteScoreChangeRecordDealType.CONSUME:
			return PartnerStockScoreChangeRecordDealType.CONSUME;
		case WhiteScoreChangeRecordDealType.RECOMMEND:
			return PartnerStockScoreChangeRecordDealType.RECOMMEND;
		case WhiteScoreChangeRecordDealType.SELL:
			return PartnerStockScoreChangeRecordDealType.SELL;
		default:
			return PartnerStockScoreChangeRecordDealType.CONSUME;
		}
	}

	/**
	 * 查询会员白积分余额
	 * 
	 * @param memberId
	 * @return
	 */
	public Integer getWhiteScoreBalance(int memberId) {
		return null;
	}

	/**
	 * 根据白积分发放记录号或者商户id和外部交易号查询发放记录。如果tradeNo不为null,则优先根据tradeNo查询，否则根据partnerId和outTradeNo查询
	 * 
	 * @param tradeNo
	 * @param partnerId
	 * @param outTradeNo
	 * @return
	 */
	public WhiteScoreSendRecord getWhiteScoreSendRecordBy(String tradeNo, int partnerId,
			String outTradeNo) {
		return null;
	}

	/**
	 * 根据条件分页查询白积分变动流水
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
	 * 根据条件分页查询白积分转换记录。按照时间降序排序。
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	public PageResult<WhiteScoreTransformRecord> getWhiteScoreTransformRecords(
			ScoreTransformRecordQuery query, Page page) {
		return null;
	}
}
