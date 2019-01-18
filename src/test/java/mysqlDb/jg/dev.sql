/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : dev

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 25/12/2018 11:35:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for consumetype
-- ----------------------------
DROP TABLE IF EXISTS `consumetype`;
CREATE TABLE `consumetype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeId` int(11) DEFAULT NULL,
  `typeName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for income
-- ----------------------------
DROP TABLE IF EXISTS `income`;
CREATE TABLE `income`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(12) DEFAULT NULL COMMENT '年',
  `month` int(12) DEFAULT NULL COMMENT '月',
  `money` decimal(12, 4) DEFAULT NULL COMMENT '收入',
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '来源',
  `createDate` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '建立时间',
  `updateDate` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resourceName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '映射名字(功能名称)',
  `accessPath` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '访问路径(url)',
  `parentId` int(11) NOT NULL COMMENT '父类id',
  `resourceId` int(11) NOT NULL COMMENT '资源标识\n对应父id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立时间',
  `createUser` int(11) NOT NULL DEFAULT -1 COMMENT '建立者',
  `updateDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `updateUser` int(11) NOT NULL DEFAULT -1 COMMENT '更新者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for rolefunction
-- ----------------------------
DROP TABLE IF EXISTS `rolefunction`;
CREATE TABLE `rolefunction`  (
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `functionId` int(11) NOT NULL COMMENT '功能代码'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for summary
-- ----------------------------
DROP TABLE IF EXISTS `summary`;
CREATE TABLE `summary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `countDate` date DEFAULT NULL COMMENT '统计日期',
  `expense` decimal(12, 4) DEFAULT NULL COMMENT '支出',
  `createDate` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '建立时间',
  `updateDate` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32338 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tally
-- ----------------------------
DROP TABLE IF EXISTS `tally`;
CREATE TABLE `tally`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `used` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用途',
  `howMuch` decimal(12, 4) DEFAULT NULL COMMENT '钱数',
  `createDate` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '建立时间',
  `userId` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '消费类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 404 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立日期',
  `createUser` int(11) NOT NULL DEFAULT -1 COMMENT '建立者',
  `updateDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `updateUser` int(11) NOT NULL DEFAULT -1 COMMENT '更新者',
  `disabledFlag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '禁用标志',
  `disabledDate` datetime(0) DEFAULT NULL COMMENT '禁用时间',
  `faceImage` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `loginName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '登录账号',
  `passWord` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole`  (
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `userId` int(11) NOT NULL COMMENT '用户Id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
