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