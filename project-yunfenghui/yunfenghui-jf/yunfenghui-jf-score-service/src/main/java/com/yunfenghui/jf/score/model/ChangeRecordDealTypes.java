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
	}
}
