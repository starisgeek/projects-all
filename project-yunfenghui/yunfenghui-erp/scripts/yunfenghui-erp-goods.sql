CREATE DATABASE /*!32312 IF NOT EXISTS*/`yunfenghui_goods` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yunfenghui_goods`;


CREATE TABLE `goods` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `barcode` varchar(32) NOT NULL COMMENT '条码',
  `name` varchar(64) NOT NULL COMMENT '商品名称',
  `pinyin` varchar(32) DEFAULT NULL COMMENT '拼音码',
  `shop_id` int(10) unsigned NOT NULL COMMENT '所属门店ID',
  `latest_buy_price` int(10) unsigned DEFAULT NULL COMMENT '最新进货价',
  `original_price` int(10) unsigned NOT NULL COMMENT '原价',
  `sale_price` int(10) unsigned NOT NULL COMMENT '销售价',
  `member_price` int(10) unsigned NOT NULL COMMENT '会员价',
  `discount` int(10) unsigned NOT NULL COMMENT '折扣%',
  `score_ratio` int(10) unsigned NOT NULL COMMENT '积分返还比例',
  `category_id` int(10) unsigned NOT NULL COMMENT '商品分类ID',
  `brand_id` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '商品品牌ID',
  `unit_id` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '库存单位ID',
  `stock_quantity` int(11) NOT NULL COMMENT '库存数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` int(10) unsigned NOT NULL COMMENT '状态',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `goods_category` */

CREATE TABLE `goods_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) NOT NULL COMMENT '类别名称',
  `parent_id` int(10) unsigned DEFAULT NULL COMMENT '上级ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `goods_brand` (
  `id` int(10) unsigned NOT NULL  AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) NOT NULL COMMENT '类别名称',
  `shop_id` int(10) unsigned NOT NULL COMMENT '门店ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `goods_unit` (
  `id` int(10) unsigned NOT NULL  AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) NOT NULL COMMENT '类别名称',
  PRIMARY KEY (`id`)
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

/*Table structure for table `stock_record_item` */

CREATE TABLE `stock_record_item` (
  `record_no` varchar(32) NOT NULL COMMENT '入库单号',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `buy_price` int(11) NOT NULL COMMENT '进价',
  `buy_quantity` int(11) NOT NULL COMMENT '进货量',
  `present_quantity` int(11) NOT NULL COMMENT '赠送量'
  `total_amount` int(11) NOT NULL COMMENT `入库单Item总金额`
) ENGINE=InnoDB DEFAULT CHARSET=utf8;