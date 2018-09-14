CREATE DATABASE /*!32312 IF NOT EXISTS*/`yunfenghui_jf_partner` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yunfenghui_jf_partner`;


CREATE TABLE `partner` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商户id',
  `app_id` varchar(64) NOT NULL COMMENT 'app_id',
  `name` varchar(64) NOT NULL COMMENT '商户名称',
  `number` varchar(64) NOT NULL COMMENT '商户编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `public_key` varchar(1024) NULL COMMENT '商户公钥',
  `platform_public_key` varchar(1024) NULL COMMENT '平台公钥',
  `platform_private_key` varchar(1024) NULL COMMENT '平台私钥',
  `remark` varchar(256) NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
