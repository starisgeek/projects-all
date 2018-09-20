CREATE DATABASE /*!32312 IF NOT EXISTS*/`yunfenghui_jf_score` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yunfenghui_jf_score`;


CREATE TABLE `member_account` (
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `white_scores` int(11) NOT NULL COMMENT '白积分余额',
  `red_scores` int(11) NOT NULL COMMENT '红积分余额',
  `balance` int(11) NOT NULL COMMENT '消费余额',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `white_score_send_record` (
  `record_no` varchar(32) NOT NULL COMMENT '记录号',
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `partner_id` int(11) NOT NULL COMMENT '商户id',
  `out_trade_no` varchar(64) NOT NULL COMMENT '外部交易号',
  `send_scores` int(11) NOT NULL COMMENT '发放积分',
  `deal_type` int(11) NOT NULL COMMENT '交易类型',
  `notify_url` varchar(128) NULL COMMENT '通知url',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NULL COMMENT '修改时间',
  `status` int(11) NOT NULL COMMENT '状态',
  `error_code` varchar(128) NULL COMMENT '错误码',
  PRIMARY KEY (`record_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `white_score_send_record_notify` (
  `record_no` varchar(32) NOT NULL COMMENT '记录号',
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `partner_id` int(11) NOT NULL COMMENT '商户id',
  `out_trade_no` varchar(64) NOT NULL COMMENT '外部交易号',
  `send_scores` int(11) NOT NULL COMMENT '发放积分',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` int(11) NOT NULL COMMENT '状态',
  `error_code` varchar(128) NULL COMMENT '错误码',
  PRIMARY KEY (`record_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `white_score_transform_record` (
  `record_no` varchar(32) NOT NULL COMMENT '记录号',
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `transform_scores` int(11) NOT NULL COMMENT '转换积分',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`record_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `white_score_transform_job` (
  `transform_date` int(11) NOT NULL COMMENT '转换日期yyyyMMdd',
  `status` int(11) NOT NULL COMMENT '状态',
  PRIMARY KEY (`transform_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `white_score_transform_ratio` (
  `transform_date` int(11) NOT NULL COMMENT '转换日期yyyyMMdd',
  `ratio` int(11) NOT NULL COMMENT '转换比率(万分之)',
  PRIMARY KEY (`transform_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `red_score_transform_record` (
  `record_no` varchar(32) NOT NULL COMMENT '记录号',
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `transform_scores` int(11) NOT NULL COMMENT '转换积分',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`record_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `white_score_change_record` (
  `serial_number` varchar(32) NOT NULL COMMENT '流水号',
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `change_amount` int(11) NOT NULL COMMENT '变动数量',
  `deal_type` int(11) NOT NULL COMMENT '交易类型',
  `original_record_no` varchar(32) NOT NULL COMMENT '源记录号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `red_score_change_record` (
  `serial_number` varchar(32) NOT NULL COMMENT '流水号',
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `change_amount` int(11) NOT NULL COMMENT '变动数量',
  `deal_type` int(11) NOT NULL COMMENT '交易类型',
  `original_record_no` varchar(32) NOT NULL COMMENT '源记录号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `balance_change_record` (
  `serial_number` varchar(32) NOT NULL COMMENT '流水号',
  `member_id` int(11) NOT NULL COMMENT '会员id',
  `change_amount` int(11) NOT NULL COMMENT '变动数量',
  `deal_type` int(11) NOT NULL COMMENT '交易类型',
  `original_record_no` varchar(32) NOT NULL COMMENT '源记录号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `partner_account` (
  `partner_id` int(11) NOT NULL COMMENT '商户id',
  `stock_scores` int(11) NOT NULL COMMENT '库存积分',
  `white_scores` int(11) NOT NULL COMMENT '白积分余额',
  `red_scores` int(11) NOT NULL COMMENT '红积分余额',
  `balance` int(11) NOT NULL COMMENT '消费余额',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`partner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `partner_stock_score_recharge_record` (
  `record_no` varchar(32) NOT NULL COMMENT '记录号',
  `partner_id` int(11) NOT NULL COMMENT '商户id',
  `recharge_money` int(11) NOT NULL COMMENT '充值金额',
  `increase_stock_scores` varchar(64) NOT NULL COMMENT '增加的库存积分',
  `increase_white_scores` varchar(64) NOT NULL COMMENT '增加的白积分',
  `create_user_id` int(11) NOT NULL COMMENT '创建用户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`record_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `partner_white_score_transform_record` (
  `record_no` varchar(32) NOT NULL COMMENT '记录号',
  `partner_id` int(11) NOT NULL COMMENT '商户id',
  `transform_scores` int(11) NOT NULL COMMENT '转换积分',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`record_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `partner_red_score_transform_record` (
  `record_no` varchar(32) NOT NULL COMMENT '记录号',
  `partner_id` int(11) NOT NULL COMMENT '商户id',
  `transform_scores` int(11) NOT NULL COMMENT '转换积分',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`record_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `partner_stock_score_change_record` (
  `serial_number` varchar(32) NOT NULL COMMENT '流水号',
  `partner_id` int(11) NOT NULL COMMENT '商户id',
  `change_amount` int(11) NOT NULL COMMENT '变动数量',
  `deal_type` int(11) NOT NULL COMMENT '交易类型',
  `original_record_no` varchar(32) NOT NULL COMMENT '源记录号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `partner_white_score_change_record` (
  `serial_number` varchar(32) NOT NULL COMMENT '流水号',
  `partner_id` int(11) NOT NULL COMMENT '商户id',
  `change_amount` int(11) NOT NULL COMMENT '变动数量',
  `deal_type` int(11) NOT NULL COMMENT '交易类型',
  `original_record_no` varchar(32) NOT NULL COMMENT '源记录号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `partner_red_score_change_record` (
  `serial_number` varchar(32) NOT NULL COMMENT '流水号',
  `partner_id` int(11) NOT NULL COMMENT '商户id',
  `change_amount` int(11) NOT NULL COMMENT '变动数量',
  `deal_type` int(11) NOT NULL COMMENT '交易类型',
  `original_record_no` varchar(32) NOT NULL COMMENT '源记录号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `partner_balance_change_record` (
  `serial_number` varchar(32) NOT NULL COMMENT '流水号',
  `partner_id` int(11) NOT NULL COMMENT '商户id',
  `change_amount` int(11) NOT NULL COMMENT '变动数量',
  `deal_type` int(11) NOT NULL COMMENT '交易类型',
  `original_record_no` varchar(32) NOT NULL COMMENT '源记录号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
