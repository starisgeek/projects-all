package com.yunfenghui.jf.score.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yunfenghui.common.page.Page;
import com.yunfenghui.common.page.PageResult;
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

public interface ScoreService {
	String ID = "scoreService";

	/**
	 * 发放白积分，此方法按照约定不会检查memberId是否存在或者有效。
	 * 新增白积分发放记录，状态为待处理。
	 * 上一步在单独的事务中，如果成功则发送消息到MQ，后续处理在MQ messaage receiver中完成。
	 * 如果发送MQ这一步失败，则可以考虑使用DelayedQueue重试一定次数。
	 * 
	 * @param sendRequest
	 * @return 白积分发放记录
	 */
	WhiteScoreSendRecord sendWhiteScore(WhiteScoreSendRequest sendRequest);

	/**
	 * 处理白积分发放记录。提供给MQ message receiver/人工处理调用。
	 * 
	 * @param sendRecord
	 */
	void handleWhiteScoreSendRecords(List<WhiteScoreSendRecord> sendRecords);

	/**
	 * 处理白积分发放记录通知。提供给MQ message receiver/人工处理调用。
	 * 
	 * @param sendRecordNotifies
	 */
	void handleWhiteScoreSendRecordNotifies(List<WhiteScoreSendRecordNotify> sendRecordNotifies);

	/**
	 * 查询会员白积分余额。如果memberId对应的账户不存在，则返回0
	 * 
	 * @param memberId
	 * @return
	 */
	int getWhiteScoreBalance(int memberId);

	/**
	 * 根据白积分发放记录号或者商户id和外部交易号查询发放记录。如果tradeNo不为null,则优先根据tradeNo查询，否则根据partnerId和outTradeNo查询
	 * 
	 * @param tradeNo
	 * @param partnerId
	 * @param outTradeNo
	 * @return
	 */
	WhiteScoreSendRecord getWhiteScoreSendRecordBy(String tradeNo, int partnerId,
			String outTradeNo);

	/**
	 * 根据条件分页查询白积分变动流水
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	PageResult<AccountChangeRecord> getWhiteScoreChangeRecords(ChangeRecordQuery query, Page page);

	/**
	 * 查询或者创建白积分转换Job
	 * 
	 * @param transformDate
	 */
	WhiteScoreTransformJob getOrCreateWhiteScoreTransformJob(int transformDate);

	/**
	 * 批量添加白积分转换记录
	 * 
	 * @param transformRecords
	 */
	void addWhiteScoreTransformRecords(List<WhiteScoreTransformRecord> transformRecords);

	/**
	 * 批量添加白积分转换记录并设置转换job状态为完成
	 * 
	 * @param transformRecords
	 * @param transformDate
	 */
	void addWhiteScoreTransformRecordsAndCompleteWhiteScoreTransformJob(
			List<WhiteScoreTransformRecord> transformRecords, int transformDate);

	/**
	 * 根据条件分页查询白积分转换记录。按照时间降序排序。
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	PageResult<WhiteScoreTransformRecord> getWhiteScoreTransformRecords(
			ScoreTransformRecordQuery query, Page page);

	/**
	 * 根据时间范围查询最大的memberId
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Integer getMaxMemberIdOfWhiteScoreTransformRecordsBy(Date startTime, Date endTime);

	/**
	 * 根据日期获取白积分转换比率
	 * 
	 * @param transformDate
	 * @return
	 */
	Integer getWhiteScoreTransformRatio(int transformDate);

	/**
	 * 查询会员红积分余额。如果memberId对应的账户不存在，则返回0
	 * 
	 * @param memberId
	 * @return
	 */
	int getRedScoreBalance(int memberId);

	/**
	 * 根据条件分页查询红积分变动流水
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	PageResult<AccountChangeRecord> getRedScoreChangeRecords(ChangeRecordQuery query, Page page);

	/**
	 * 转换红积分到消费余额
	 * 
	 * @param memberId
	 * @param transformScores
	 * @throws JFException
	 *             会员不存在。红积分余额不足。
	 */
	void transformAccountRedScore(int memberId, int transformScores) throws JFException;

	/**
	 * 根据条件分页查询红积分转换记录。按照时间降序排序。
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	PageResult<RedScoreTransformRecord> getRedScoreTransformRecords(ScoreTransformRecordQuery query,
			Page page);

	/**
	 * 根据会员id获取账户信息
	 * 
	 * @param memberId
	 * @return
	 */
	MemberAccount getMemberAccountBy(int memberId);

	/**
	 * 查询会员消费余额。如果memberId对应的账户不存在，则返回0
	 * 
	 * @param memberId
	 * @return
	 */
	int getBalance(int memberId);

	/**
	 * 会员申请余额提现
	 * 
	 * @param memberId
	 * @param cashOutAmount
	 * @throws JFException
	 *             余额不足
	 */
	void applyBalanceCashOut(int memberId, int cashOutAmount) throws JFException;

	/**
	 * 审核会员余额提现
	 * 
	 * @param cashOutRecordNo
	 * @param approveUserId
	 * @throws JFException
	 *             记录不存在。记录状态错误
	 */
	void approveBalanceCashOut(String cashOutRecordNo, int approveUserId) throws JFException;

	/**
	 * 驳回会员余额提现
	 * 
	 * @param cashOutRecordNo
	 * @param approveUserId
	 * @param reason
	 * @throws JFException
	 *             记录不存在。记录状态错误
	 */
	void rejectBalanceCashOut(String cashOutRecordNo, int rejectUserId, String reason)
			throws JFException;

	/**
	 * 余额支付
	 * 
	 * @param payRecord
	 * @return 支付记录号
	 * @throws JFException
	 *             余额不足。重复的支付。
	 */
	PayRecord pay(PayRequest payRequest) throws JFException;

	/**
	 * 根据支付记录或商户id和外部交易号查询支付记录
	 * 
	 * @param recordNo
	 * @param partnerId
	 * @param outTradeNo
	 * @return
	 */
	PayRecord getPayRecord(String recordNo, int partnerId, String outTradeNo);

	/**
	 * 根据条件分页查询支付记录。按照时间降序排序。
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	PageResult<PayRecord> getPayRecords(PayRecordQuery query, Page page);

	/**
	 * 支付退款(一笔支付记录支持多笔退款)
	 * 
	 * @param refundRequest
	 * @return
	 * @throws JFException
	 *             支付记录不存在。重复的退款。退款总额已超出支付总额。
	 */
	RefundRecord refund(RefundRequest refundRequest) throws JFException;

	/**
	 * 根据退款记录号或者商户id和外部退款号查询退款记录
	 * 
	 * @param recordNo
	 * @param partnerId
	 * @param outRefundNo
	 * @return
	 */
	RefundRecord getRefundRecord(String recordNo, int partnerId, String outRefundNo);

	/**
	 * 根据条件分页查询退款记录。按照时间降序排序。
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	PageResult<RefundRecord> getRefundRecords(RefundRecordQuery query, Page page);

	/**
	 * 根据支付记录号查询退款记录
	 * 
	 * @param payRecordNo
	 * @return
	 */
	List<RefundRecord> getRefundRecordsByPayRecordNo(String payRecordNo);

	/**
	 * 查询topN会员账户，条件必须大于memberId，且白积分余额必须大于等于whiteScores,账户创建时间必须小于createTime.
	 * 结果需要按照memberId升序排序。
	 * 用于白积分转换红积分Job调用.
	 * 
	 * @param topN
	 * @param memberId
	 * @param whiteScores
	 * @return
	 */
	List<MemberAccount> getTopNMemberAccounts(int topN, int memberId, int whiteScores,
			Date createTime);

	/**
	 * 根据条件分页查询余额变动流水
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	PageResult<AccountChangeRecord> getBalanceChangeRecords(ChangeRecordQuery query, Page page);

	/**
	 * 充值商户库存积分(如果商户账户不存在，则创建)
	 * 
	 * @param partnerId
	 * @param rechargeMoney
	 * @param createUserId
	 */
	void rechargePartnerStockScore(int partnerId, int rechargeMoney, int createUserId);

	/**
	 * 根据条件分页查询商户库存积分变动流水
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	PageResult<AccountChangeRecord> getPartnerStockScoreChangeRecords(ChangeRecordQuery query,
			Page page);

	/**
	 * 根据商户id查询账户信息
	 * 
	 * @param partnerId
	 * @return
	 */
	PartnerAccount getPartnerAccount(int partnerId);

	/**
	 * 根据条件分页查询商户白积分变动流水
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	PageResult<AccountChangeRecord> getPartnerWhiteScoreChangeRecords(ChangeRecordQuery query,
			Page page);

	/**
	 * 根据条件分页查询商户白积分转换记录。按照时间降序排序。
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	PageResult<WhiteScoreTransformRecord> getPartnerWhiteScoreTransformRecords(
			ScoreTransformRecordQuery query, Page page);

	/**
	 * 根据条件分页查询商户红积分变动流水
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	PageResult<AccountChangeRecord> getPartnerRedScoreChangeRecords(ChangeRecordQuery query,
			Page page);

	/**
	 * 转换商户红积分到消费余额
	 * 
	 * @param partnerId
	 * @param transformScores
	 * @throws JFException
	 *             商户不存在。红积分余额不足。
	 */
	void transformPartnerAccountRedScore(int partnerId, int transformScores) throws JFException;

	/**
	 * 根据条件分页查询商户余额变动流水
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	PageResult<AccountChangeRecord> getPartnerBalanceChangeRecords(ChangeRecordQuery query,
			Page page);

	/**
	 * 商户申请余额提现
	 * 
	 * @param partnerId
	 * @param cashOutAmount
	 * @throws JFException
	 *             余额不足
	 */
	void applyPartnerBalanceCashOut(int partnerId, int cashOutAmount) throws JFException;

	/**
	 * 审核商户余额提现
	 * 
	 * @param cashOutRecordNo
	 * @param approveUserId
	 * @throws JFException
	 *             记录不存在。记录状态错误
	 */
	void approvePartnerBalanceCashOut(String cashOutRecordNo, int approveUserId) throws JFException;

	/**
	 * 驳回商户余额提现
	 * 
	 * @param cashOutRecordNo
	 * @param approveUserId
	 * @param reason
	 * @throws JFException
	 *             记录不存在。记录状态错误
	 */
	void rejectPartnerBalanceCashOut(String cashOutRecordNo, int rejectUserId, String reason)
			throws JFException;

	class TimeRangeBasedQuery implements Serializable {
		private static final long serialVersionUID = 1L;
		private Date startTime;
		private Date endTIme;

		public Date getStartTime() {
			return startTime;
		}

		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}

		public Date getEndTIme() {
			return endTIme;
		}

		public void setEndTIme(Date endTIme) {
			this.endTIme = endTIme;
		}

	}

	/**
	 * 变动流水查询条件
	 * 
	 * @author Administrator
	 *
	 */
	class ChangeRecordQuery extends TimeRangeBasedQuery {
		private static final long serialVersionUID = 1L;
		private Integer accountId;

		public Integer getAccountId() {
			return accountId;
		}

		public void setAccountId(Integer accountId) {
			this.accountId = accountId;
		}
	}

	/**
	 * 积分(白/红积分)转换记录查询条件
	 * 
	 * @author Administrator
	 *
	 */
	class ScoreTransformRecordQuery extends TimeRangeBasedQuery {
		private static final long serialVersionUID = 1L;
		private Integer accountId;

		public Integer getAccountId() {
			return accountId;
		}

		public void setAccountId(Integer accountId) {
			this.accountId = accountId;
		}
	}

	/**
	 * 支付记录查询条件
	 * 
	 * @author Administrator
	 *
	 */
	class PayRecordQuery extends TimeRangeBasedQuery {
		private static final long serialVersionUID = 1L;
		private Integer memberId;

		public Integer getMemberId() {
			return memberId;
		}

		public void setMemberId(Integer memberId) {
			this.memberId = memberId;
		}
	}

	/**
	 * 退款记录查询条件
	 * 
	 * @author Administrator
	 *
	 */
	class RefundRecordQuery extends TimeRangeBasedQuery {
		private static final long serialVersionUID = 1L;
		private Integer memberId;

		public Integer getMemberId() {
			return memberId;
		}

		public void setMemberId(Integer memberId) {
			this.memberId = memberId;
		}
	}
}
