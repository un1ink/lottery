/*
 Navicat Premium Data Transfer

 Source Server         : docker_mysql
 Source Server Type    : MySQL
 Source Server Version : 50741 (5.7.41)
 Source Host           : localhost:3307
 Source Schema         : lottery_02

 Target Server Type    : MySQL
 Target Server Version : 50741 (5.7.41)
 File Encoding         : 65001

 Date: 04/04/2023 17:07:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for invoice_mq_state
-- ----------------------------
DROP TABLE IF EXISTS `invoice_mq_state`;
CREATE TABLE `invoice_mq_state`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `uId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户ID',
  `orderId` bigint(32) NULL DEFAULT NULL COMMENT '订单ID',
  `mqState` tinyint(4) NULL DEFAULT 0 COMMENT '消息队列状态‘COMMENT',
  `createTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户发奖结果消息队列状态' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of invoice_mq_state
-- ----------------------------
INSERT INTO `invoice_mq_state` VALUES (9, 'un1ink_0403', 100001, 0, '2023-04-03 10:20:34');
INSERT INTO `invoice_mq_state` VALUES (10, 'un1ink_0403', 100001, 0, '2023-04-04 08:12:22');
INSERT INTO `invoice_mq_state` VALUES (11, 'un1ink_0404', 100001, 0, '2023-04-04 08:15:40');

-- ----------------------------
-- Table structure for user_strategy_export_001
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_001`;
CREATE TABLE `user_strategy_export_001`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uId` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `activityId` bigint(20) NULL DEFAULT NULL,
  `orderId` bigint(20) NULL DEFAULT NULL,
  `strategyId` bigint(20) NULL DEFAULT NULL,
  `strategyMode` int(11) NULL DEFAULT NULL,
  `grantType` int(11) NULL DEFAULT NULL,
  `grantDate` timestamp NULL DEFAULT NULL,
  `grantState` int(11) NULL DEFAULT NULL,
  `awardId` bigint(20) NULL DEFAULT NULL,
  `awardType` int(11) NULL DEFAULT NULL,
  `awardName` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `awardContent` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `uuid` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户策略计算结果表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_strategy_export_001
-- ----------------------------

-- ----------------------------
-- Table structure for user_strategy_export_002
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_002`;
CREATE TABLE `user_strategy_export_002`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uId` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `activityId` bigint(20) NULL DEFAULT NULL,
  `orderId` bigint(20) NULL DEFAULT NULL,
  `strategyId` bigint(20) NULL DEFAULT NULL,
  `strategyMode` int(11) NULL DEFAULT NULL,
  `grantType` int(11) NULL DEFAULT NULL,
  `grantDate` timestamp NULL DEFAULT NULL,
  `grantState` int(11) NULL DEFAULT NULL,
  `awardId` bigint(20) NULL DEFAULT NULL,
  `awardType` int(11) NULL DEFAULT NULL,
  `awardName` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `awardContent` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `uuid` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户策略计算结果表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_strategy_export_002
-- ----------------------------

-- ----------------------------
-- Table structure for user_strategy_export_003
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_003`;
CREATE TABLE `user_strategy_export_003`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uId` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `activityId` bigint(20) NULL DEFAULT NULL,
  `orderId` bigint(20) NULL DEFAULT NULL,
  `strategyId` bigint(20) NULL DEFAULT NULL,
  `strategyMode` int(11) NULL DEFAULT NULL,
  `grantType` int(11) NULL DEFAULT NULL,
  `grantDate` timestamp NULL DEFAULT NULL,
  `grantState` int(11) NULL DEFAULT NULL,
  `awardId` bigint(20) NULL DEFAULT NULL,
  `awardType` int(11) NULL DEFAULT NULL,
  `awardName` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `awardContent` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `uuid` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户策略计算结果表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_strategy_export_003
-- ----------------------------
INSERT INTO `user_strategy_export_003` VALUES (17, 'un1ink_0403', 100001, 1642834467506798592, 100001, 1, 1, '2023-04-03 10:20:34', 1, 3, 1, '?????3', NULL, '1642834467506798592', NULL, '2023-04-03 10:20:34');
INSERT INTO `user_strategy_export_003` VALUES (18, 'un1ink_0403', 100001, 1643164596439269376, 100001, 1, 1, '2023-03-25 16:39:43', 0, 4, 1, '?????4', NULL, '1643164596439269376', NULL, NULL);

-- ----------------------------
-- Table structure for user_strategy_export_004
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export_004`;
CREATE TABLE `user_strategy_export_004`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uId` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `activityId` bigint(20) NULL DEFAULT NULL,
  `orderId` bigint(20) NULL DEFAULT NULL,
  `strategyId` bigint(20) NULL DEFAULT NULL,
  `strategyMode` int(11) NULL DEFAULT NULL,
  `grantType` int(11) NULL DEFAULT NULL,
  `grantDate` timestamp NULL DEFAULT NULL,
  `grantState` int(11) NULL DEFAULT NULL,
  `awardId` bigint(20) NULL DEFAULT NULL,
  `awardType` int(11) NULL DEFAULT NULL,
  `awardName` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `awardContent` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `uuid` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户策略计算结果表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_strategy_export_004
-- ----------------------------
INSERT INTO `user_strategy_export_004` VALUES (1, 'un1ink_0404', 100001, 1643165424734617600, 100001, 1, 1, '2023-04-04 08:15:40', 1, 1, 1, '?????1', '?????', '1643165424734617600', NULL, '2023-04-04 08:15:40');

-- ----------------------------
-- Table structure for user_take_activity
-- ----------------------------
DROP TABLE IF EXISTS `user_take_activity`;
CREATE TABLE `user_take_activity`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uId` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `takeId` bigint(20) NULL DEFAULT NULL,
  `activityId` bigint(20) NULL DEFAULT NULL,
  `activityName` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `takeDate` timestamp NULL DEFAULT NULL,
  `takeCount` int(11) NULL DEFAULT NULL,
  `uuid` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `strategyId` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户参与活动记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_take_activity
-- ----------------------------
INSERT INTO `user_take_activity` VALUES (49, 'un1ink_0403', 1642834467154477056, 100001, '????', '2023-04-03 18:20:34', 1, 'un1ink_0403_100001_1', '2023-04-03 10:20:34', '2023-04-03 10:20:34', 1, NULL);
INSERT INTO `user_take_activity` VALUES (50, 'un1ink_0403', 1643164596082753536, 100001, '????', '2023-04-04 16:12:23', 1, 'un1ink_0403_100001_1', '2023-04-04 08:12:22', '2023-04-04 08:12:22', 1, NULL);
INSERT INTO `user_take_activity` VALUES (51, 'un1ink_0404', 1643165424390684672, 100001, '????', '2023-04-04 16:15:40', 1, 'un1ink_0404_100001_1', '2023-04-04 08:15:40', '2023-04-04 08:15:40', 1, NULL);

-- ----------------------------
-- Table structure for user_take_activity_count
-- ----------------------------
DROP TABLE IF EXISTS `user_take_activity_count`;
CREATE TABLE `user_take_activity_count`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uId` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `activityId` bigint(20) NULL DEFAULT NULL,
  `totalCount` int(11) NULL DEFAULT NULL,
  `leftCount` int(11) NULL DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户活动参与次数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_take_activity_count
-- ----------------------------
INSERT INTO `user_take_activity_count` VALUES (20, 'un1ink_0403', 100001, 10, 8, '2023-04-03 10:20:34', '2023-04-03 10:20:34');
INSERT INTO `user_take_activity_count` VALUES (21, 'un1ink_0404', 100001, 10, 9, '2023-04-04 08:15:40', '2023-04-04 08:15:40');

SET FOREIGN_KEY_CHECKS = 1;
