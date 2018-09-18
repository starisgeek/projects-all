package com.yunfenghui.jf.score.service.support;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yunfenghui.common.page.Page;
import com.yunfenghui.common.page.PageResult;
import com.yunfenghui.jf.common.JFException;
import com.yunfenghui.jf.score.model.AccountChangeRecord;
import com.yunfenghui.jf.score.model.PayRecord;
import com.yunfenghui.jf.score.model.PayRequest;
import com.yunfenghui.jf.score.model.RefundRecord;
import com.yunfenghui.jf.score.model.RefundRequest;
import com.yunfenghui.jf.score.service.ScoreService.ChangeRecordQuery;
import com.yunfenghui.jf.score.service.ScoreService.PayRecordQuery;
import com.yunfenghui.jf.score.service.ScoreService.RefundRecordQuery;

@Service(BalanceService.ID)
public class BalanceService {
	public static final String ID = "balanceService";

	/**
	 * 查询会员消费余额。
	 * 
	 * @param memberId
	 * @return
	 */
	public Integer getBalance(int memberId) {
		return null;
	}

	/**
	 * 会员申请余额提现
	 * 
	 * @param memberId
	 * @param cashOutAmount
	 * @throws JFException
	 *             余额不足
	 */
	public void applyBalanceCashOut(int memberId, int cashOutAmount) throws JFException {

	}

	/**
	 * 审核会员余额提现
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
	 * 驳回会员余额提现
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

	/**
	 * 余额支付
	 * 
	 * @param payRecord
	 * @return 支付记录号
	 * @throws JFException
	 *             余额不足。重复的支付。
	 */
	public PayRecord pay(PayRequest payRequest) throws JFException {
		return null;
	}

	/**
	 * 根据支付记录或商户id和外部交易号查询支付记录
	 * 
	 * @param recordNo
	 * @param partnerId
	 * @param outTradeNo
	 * @return
	 */
	public PayRecord getPayRecord(String recordNo, int partnerId, String outTradeNo) {
		return null;
	}

	/**
	 * 根据条件分页查询支付记录。按照时间降序排序。
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	public PageResult<PayRecord> getPayRecords(PayRecordQuery query, Page page) {
		return null;
	}

	/**
	 * 支付退款(一笔支付记录支持多笔退款)
	 * 
	 * @param refundRequest
	 * @return
	 * @throws JFException
	 *             支付记录不存在。重复的退款。退款总额已超出支付总额。
	 */
	public RefundRecord refund(RefundRequest refundRequest) throws JFException {
		return null;
	}

	/**
	 * 根据退款记录号或者商户id和外部退款号查询退款记录
	 * 
	 * @param recordNo
	 * @param partnerId
	 * @param outRefundNo
	 * @return
	 */
	public RefundRecord getRefundRecord(String recordNo, int partnerId, String outRefundNo) {
		return null;
	}

	/**
	 * 根据条件分页查询退款记录。按照时间降序排序。
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	public PageResult<RefundRecord> getRefundRecords(RefundRecordQuery query, Page page) {
		return null;
	}

	/**
	 * 根据支付记录号查询退款记录
	 * 
	 * @param payRecordNo
	 * @return
	 */
	public List<RefundRecord> getRefundRecordsByPayRecordNo(String payRecordNo) {
		return null;
	}

	/**
	 * 根据条件分页查询余额变动流水
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	public PageResult<AccountChangeRecord> getBalanceChangeRecords(ChangeRecordQuery query,
			Page page) {
		return null;
	}
}
