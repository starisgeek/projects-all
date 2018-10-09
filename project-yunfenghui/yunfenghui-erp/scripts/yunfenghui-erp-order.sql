CREATE DATABASE /*!32312 IF NOT EXISTS*/`yunfenghui_erp_order` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yunfenghui_erp_order`;

CREATE TABLE `order` (
  `order_no` varchar(32) NOT NULL COMMENT '订单号',
  `shop_id` int(11) NOT NULL COMMENT '门店ID',
  `member_id` int(11) NOT NULL COMMENT '云风商城会员ID',
  `member_phone` varchar(15) NOT NULL COMMENT '云风商城会员手机号',
  `member_name` varchar(32) NOT NULL COMMENT '云风商城会员账号',
  `total_amount` int(11) NOT NULL COMMENT '订单总金额',
  `total_send_scores` int(11) NOT NULL COMMENT '发放总积分',
  `total_refund_amount` int(11) NOT NULL DEFAULT 0  COMMENT '订单退款总金额',
  `out_trade_no` varchar(64) COMMENT '外部交易号',
  `pay_way` int(11) NOT NULL COMMENT '支付方式',
  `create_user_id` int(11) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NULL COMMENT '修改时间',
  `status` int(11) NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order_item` (
  `order_no` varchar(32) NOT NULL COMMENT '订单号',
  `goods_Id` int(11) NOT NULL COMMENT '商品id',
  `goods_barcode` varchar(32) NOT NULL COMMENT '条码',
  `goods_name` varchar(64) NOT NULL COMMENT '商品名称',
  `sale_price` int(11) NOT NULL COMMENT '商品售价',
  `send_score_ratio` int(11) NOT NULL COMMENT '奖励积分比率',
  `send_scores` int(11) NOT NULL COMMENT '发放积分',
  `quantity` int(11) NOT NULL COMMENT '数量',
  `total_amount` int(11) NOT NULL COMMENT '总金额',
  `refund_quantity` int(11) NOT NULL COMMENT '退款数量',
  `refund_amount` int(11) NOT NULL COMMENT '退款金额',
  `refund_scores` int(11) NOT NULL COMMENT '退款积分',
  PRIMARY KEY (`order_no`, `goods_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order_message` (
  `order_no` varchar(32) NOT NULL COMMENT '订单号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order_refund_record` (
  `refund_record_no` varchar(32) NOT NULL COMMENT '退款记录号',
  `order_no` varchar(32) NOT NULL COMMENT '订单号',
  `out_refund_trade_no` varchar(64) COMMENT '外部退款交易号',
  `shop_id` int(11) NOT NULL COMMENT '门店ID',
  `total_refund_amount` int(11) NOT NULL COMMENT '退款总金额',
  `refund_status` int(11) NOT NULL COMMENT '退款状态',
  `create_user_id` int(11) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NULL COMMENT '修改时间',
  `reason` varchar(100) NOT NULL COMMENT '退款原因',
  PRIMARY KEY (`refund_record_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `order_refund_record_item` (
  `refund_record_no` varchar(32) NOT NULL COMMENT '退款记录号',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `refund_price` int(11) NOT NULL COMMENT '退款价格',
  `refund_quantity` int(11) NOT NULL COMMENT '退款数量',
  `refund_amount` int(11) NOT NULL COMMENT '退款金额',
  PRIMARY KEY (`refund_record_no`, `goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
