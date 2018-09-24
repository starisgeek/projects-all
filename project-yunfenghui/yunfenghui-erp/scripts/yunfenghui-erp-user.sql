CREATE DATABASE /*!32312 IF NOT EXISTS*/`yunfenghui_erp_user` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yunfenghui_erp_user`;


CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL COMMENT '用户真实名字',
  `login_phone` varchar(20) NOT NULL COMMENT '登陆手机号',
  `password` varchar(32) DEFAULT NULL COMMENT '登陆密码',
  `password_salt` varchar(64) DEFAULT NULL COMMENT '加密登录密码盐',
  `category` varchar(20) DEFAULT NULL COMMENT '用户类别',
  `shop_id` int(11) NOT NULL COMMENT '门店ID',
  `is_super` int(11) NOT NULL COMMENT '是否为超级管理员',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `shop_id` int(11) NOT NULL COMMENT '门店ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `permission` (
  `id` varchar(32) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT '名称',
  `category` varchar(20) DEFAULT NULL COMMENT '用户类别',
  `url` varchar(128) NOT NULL COMMENT 'url',
  `parent_id` varchar(32) NOT NULL COMMENT '父节点id',
  `is_menu` int(11) NOT NULL COMMENT '是否为菜单',
  `is_configurable` int(11) NOT NULL COMMENT '是否可配置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user_role` (
  `user_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `role_permission` (
  `role_id` int(10) unsigned NOT NULL,
  `permission_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `permission_dependencies` (
  `permission_id` varchar(32) NOT NULL,
  `dependent_id` varchar(32) NOT NULL,
  PRIMARY KEY (`permission_id`,`dependent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;