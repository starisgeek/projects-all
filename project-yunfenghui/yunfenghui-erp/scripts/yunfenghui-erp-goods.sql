CREATE DATABASE /*!32312 IF NOT EXISTS*/`yunfenghui_erp_goods` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yunfenghui_erp_goods`;


CREATE TABLE `goods` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `barcode` varchar(32) NOT NULL COMMENT '条码',
  `name` varchar(64) NOT NULL COMMENT '商品名称',
  `pinyin` varchar(32) DEFAULT NULL COMMENT '拼音码',
  `shop_id` int(10) unsigned NOT NULL COMMENT '所属门店ID',
  `sale_price` int(10) unsigned NOT NULL COMMENT '销售价',
  `score_ratio` int(10) unsigned NOT NULL COMMENT '积分返还比例',
  `category_id` int(10) unsigned NOT NULL COMMENT '商品分类ID',
  `brand_id` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '商品品牌ID',
  `unit_id` int(10) unsigned NOT NULL DEFAULT 0 COMMENT '库存单位ID',
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

CREATE TABLE `goods_adjust_price_record` (
  `record_no` varchar(32) NOT NULL COMMENT '记录号',
  `shop_id` int(11) NOT NULL COMMENT '门店ID',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `old_price` int(11) NOT NULL COMMENT '原价',
  `new_price` int(11) NOT NULL COMMENT '调整后价格',
  `create_user_id` int(11) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `reason` varchar(64) NOT NULL COMMENT '调价原因',
  PRIMARY KEY (`record_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
