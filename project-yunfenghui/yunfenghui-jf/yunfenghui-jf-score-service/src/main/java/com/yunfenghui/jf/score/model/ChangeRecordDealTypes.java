package com.yunfenghui.jf.score.model;

/**
 * 变动流水交易类型
 * 
 * @author Administrator
 *
 */
public interface ChangeRecordDealTypes {
	interface PartnerStockScoreChangeRecordDealType {
		/**
		 * 充值
		 */
		int RECHARGE = 1;

		/**
		 * 会员消费
		 */
		int CONSUME = 2;
		/**
		 * 推荐
		 */
		int RECOMMEND = 3;
		/**
		 * 销售
		 */
		int SELL = 4;
	}

	interface PartnerWhiteScoreChangeRecordDealType {
		/**
		 * 充值
		 */
		int RECHARGE = 1;

	}

	interface WhiteScoreChangeRecordDealType {
		/**
		 * 消费
		 */
		int CONSUME = 1;

		/**
		 * 推荐
		 */
		int RECOMMEND = 2;

		/**
		 * 销售
		 */
		int SELL = 3;

		/**
		 * 转换红积分
		 */
		int TRANSFORM_TO_REDSCORE = 4;
	}

	interface RedScoreChangeRecordDealType {
		/**
		 * 白积分转入
		 */
		int TRANSFORM_FROM_WHITESCORE = 1;

		/**
		 * 转换余额
		 */
		int TRANSFORM_TO_BALANCE = 2;
	}
}
