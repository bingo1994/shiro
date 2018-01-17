/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50558
Source Host           : localhost:3306
Source Database       : db_shiro

Target Server Type    : MYSQL
Target Server Version : 50558
File Encoding         : 65001

Date: 2018-01-09 16:31:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource` (
  `res_id` int(11) NOT NULL AUTO_INCREMENT,
  `res_name` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`res_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_resource
-- ----------------------------
INSERT INTO `tb_resource` VALUES ('1', '用户管理', '0', null, 'user:*');
INSERT INTO `tb_resource` VALUES ('2', '用户添加', null, null, 'user:add');
INSERT INTO `tb_resource` VALUES ('3', '用户删除', null, null, 'user:delete');
INSERT INTO `tb_resource` VALUES ('4', '用户修改', null, null, 'user:update');
INSERT INTO `tb_resource` VALUES ('5', '用户查询', null, null, 'user:list');

-- ----------------------------
-- Table structure for `tb_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `role_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '系统管理员', '所有权限');
INSERT INTO `tb_role` VALUES ('2', '查询管理员', '仅有查询');
INSERT INTO `tb_role` VALUES ('3', '修改管理员', '查询和修改');
INSERT INTO `tb_role` VALUES ('4', '添加管理员', '查询和添加');

-- ----------------------------
-- Table structure for `tb_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_resource`;
CREATE TABLE `tb_role_resource` (
  `rr_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `res_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`rr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_resource
-- ----------------------------
INSERT INTO `tb_role_resource` VALUES ('1', '1', '1');
INSERT INTO `tb_role_resource` VALUES ('2', '2', '5');
INSERT INTO `tb_role_resource` VALUES ('3', '3', '4');
INSERT INTO `tb_role_resource` VALUES ('4', '3', '5');
INSERT INTO `tb_role_resource` VALUES ('5', '4', '2');
INSERT INTO `tb_role_resource` VALUES ('6', '4', '5');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_sex` varchar(255) DEFAULT NULL,
  `user_age` int(11) DEFAULT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '038bdaf98f2037b31f1e75b5b4c9b26e', '管理员', '男', '21', '南京');
INSERT INTO `tb_user` VALUES ('2', 'a', '69bd3eb0e4d34817ed10d0e20a8eee55', '用户1', '女', '22', '巢湖');
INSERT INTO `tb_user` VALUES ('3', 'b', '64c8d4b749dea5871f9bca5f9835139e', '用户2', '女', '25', '六安');
INSERT INTO `tb_user` VALUES ('4', 'c', '83c745f0348192892d1405c97c9d82ba', '用户3', '男', '21', '宣城');
INSERT INTO `tb_user` VALUES ('5', 'd', 'cbb8d82273ec3238c5443a3c13fd9021', '用户4', '女', '20', '合肥');
INSERT INTO `tb_user` VALUES ('6', 'aa', 'f67609a1b44045932c6102d46ea7baa9', 'aa', '女', '25', '北京');

-- ----------------------------
-- Table structure for `tb_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `ur_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', '1', '1');
INSERT INTO `tb_user_role` VALUES ('2', '2', '2');
INSERT INTO `tb_user_role` VALUES ('3', '3', '3');
INSERT INTO `tb_user_role` VALUES ('4', '4', '4');
INSERT INTO `tb_user_role` VALUES ('5', '5', '4');
