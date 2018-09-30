CREATE DATABASE /*!32312 IF NOT EXISTS*/`yunfenghui_erp_stock` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yunfenghui_erp_stock`;

CREATE TABLE `stock` (
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `shop_id` int(11) NOT NULL COMMENT '门店id',
  `quantity` int(11) NOT NULL COMMENT '库存数量',
  `frozen_quantity` int(11) NOT NULL COMMENT '冻结数量',
  `latest_buy_price` int(11) NOT NULL COMMENT '最新进价',
  `latest_supplier_id` int(11) NOT NULL COMMENT '最新供应商id',
  PRIMARY KEY (`goods_id`, `shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `stock_record` (
  `record_no` varchar(32) NOT NULL COMMENT '入库单号',
  `shop_id` int(11) NOT NULL COMMENT '门店id',
  `supplier_id` int(11) NOT NULL COMMENT '供应商id',
  `total_amount` int(11) NOT NULL COMMENT '入库单总金额',
  `create_user_id` int(11) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`record_no`),
  KEY `shop_id` (`shop_id`,`create_time`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `erp_stock_record_item` */

CREATE TABLE `stock_record_item` (
  `record_no` varchar(32) NOT NULL COMMENT '入库单号',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `buy_price` int(11) NOT NULL COMMENT '进价',
  `buy_quantity` int(11) NOT NULL COMMENT '进货量',
  `present_quantity` int(11) NOT NULL COMMENT '赠送量'
  `total_amount` int(11) NOT NULL COMMENT `入库单Item总金额`
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `stock_frozen_record` (
  `order_no` varchar(32) NOT NULL COMMENT '订单号',
  `shop_id` int(11) NOT NULL COMMENT '门店id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `stock_frozen_record_item` (
  `order_no` varchar(32) NOT NULL COMMENT '订单号',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `frozen_quantity` int(11) NOT NULL COMMENT '冻结数量',
  PRIMARY KEY (`order_no`, `goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `stock_change_record` (
  `serial_number` varchar(32) NOT NULL COMMENT '流水号',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `shop_id` int(11) NOT NULL COMMENT '门店id',
  `change_amount` int(11) NOT NULL COMMENT '变动数量',
  `deal_type` int(11) NOT NULL COMMENT '交易类型',
  `original_record_no` varchar(32) NOT NULL COMMENT '源记录号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '供应商名称',
  `link_man` varchar(32) NOT NULL COMMENT '联系人',
  `link_man_phone` varchar(20) NOT NULL COMMENT '联系电话',
  `location` varchar(50) COMMENT '详细地址',
  `shop_id` int(11) NOT NULL COMMENT '门店ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;