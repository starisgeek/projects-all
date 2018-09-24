CREATE DATABASE /*!32312 IF NOT EXISTS*/`yunfenghui_erp_shop` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yunfenghui_erp_shop`;


CREATE TABLE `shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(10) NOT NULL COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `member_id` bigint(20) NOT NULL COMMENT '云风商城会员ID',
  `member_phone` varchar(32) NOT NULL COMMENT '云风商城会员账号',
  `location` varchar(50) DEFAULT NULL COMMENT '详细地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `balance_account` (
  `shop_id` int(11) NOT NULL COMMENT '门店ID',
  `balance` decimal(18,2) NOT NULL COMMENT '余额',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `balance_account_change_record` (
  `serial_number` varchar(32) NOT NULL COMMENT '流水号',
  `original_serial_number` varchar(32) NOT NULL COMMENT '源流水号',
  `change_amount` decimal(18,2) NOT NULL COMMENT '变动金额',
  `business_type` int(11) NOT NULL COMMENT '业务类型',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;