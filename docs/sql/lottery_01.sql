/*
 Navicat Premium Data Transfer

 Source Server         : docker_mysql
 Source Server Type    : MySQL
 Source Server Version : 50741 (5.7.41)
 Source Host           : localhost:3307
 Source Schema         : lottery_01

 Target Server Type    : MySQL
 Target Server Version : 50741 (5.7.41)
 File Encoding         : 65001

 Date: 04/04/2023 17:07:34
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户发奖结果消息队列状态' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of invoice_mq_state
-- ----------------------------

-- ----------------------------
-- Table structure for user_strategy_export
-- ----------------------------
DROP TABLE IF EXISTS `user_strategy_export`;
CREATE TABLE `user_strategy_export`  (
  `id` bigint(20) NULL DEFAULT NULL,
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
  `updateTime` timestamp NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户策略计算结果表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_strategy_export
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户策略计算结果表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_strategy_export_001
-- ----------------------------
INSERT INTO `user_strategy_export_001` VALUES (1, 'un1ink_8', 100001, 1642439358437048320, 100001, 1, 1, '2023-03-25 16:39:43', 0, 2, 1, '?????2', '?????', '1642439358437048320', NULL, NULL);
INSERT INTO `user_strategy_export_001` VALUES (2, 'un1ink_8', 100001, 1642439358860673024, 100001, 1, 1, '2023-03-25 16:39:43', 0, 4, 1, '?????4', NULL, '1642439358860673024', NULL, NULL);
INSERT INTO `user_strategy_export_001` VALUES (3, 'un1ink_hast_3', 100001, 1642440620620562432, 100001, 1, 1, '2023-03-25 16:39:43', 0, 2, 1, '?????2', '?????', '1642440620620562432', NULL, NULL);
INSERT INTO `user_strategy_export_001` VALUES (4, 'un1ink_hast_3', 100001, 1642440621119684608, 100001, 1, 1, '2023-03-25 16:39:43', 0, 4, 1, '?????4', NULL, '1642440621119684608', NULL, NULL);
INSERT INTO `user_strategy_export_001` VALUES (5, 'un1ink_hast_16', 100001, 1642440629751562240, 100001, 1, 1, '2023-03-25 16:39:43', 0, 3, 1, '?????3', NULL, '1642440629751562240', NULL, NULL);
INSERT INTO `user_strategy_export_001` VALUES (6, 'un1ink_hast_16', 100001, 1642440630108078080, 100001, 1, 1, '2023-03-25 16:39:43', 0, 3, 1, '?????3', NULL, '1642440630108078080', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户策略计算结果表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_strategy_export_002
-- ----------------------------
INSERT INTO `user_strategy_export_002` VALUES (1, 'xiaofuge', 100001, 1642422084716118016, 100001, 1, 1, '2023-03-25 16:39:43', 0, 5, 1, '?????5', NULL, '1642422084716118016', NULL, NULL);
INSERT INTO `user_strategy_export_002` VALUES (2, 'xiaofuge', 100001, 1642422085169102848, 100001, 1, 1, '2023-03-25 16:39:43', 0, 3, 1, '?????3', NULL, '1642422085169102848', NULL, NULL);
INSERT INTO `user_strategy_export_002` VALUES (3, 'xiaofuge', 100001, 1642422489919438848, 100001, 1, 1, '2023-03-25 16:39:43', 0, 5, 1, '?????5', NULL, '1642422489919438848', NULL, NULL);
INSERT INTO `user_strategy_export_002` VALUES (4, 'xiaofuge', 100001, 1642422490330480640, 100001, 1, 1, '2023-03-25 16:39:43', 0, 5, 1, '?????5', NULL, '1642422490330480640', NULL, NULL);
INSERT INTO `user_strategy_export_002` VALUES (5, 'un1ink_7', 100001, 1642439199405817856, 100001, 1, 1, '2023-03-25 16:39:43', 0, 1, 1, '?????1', '?????', '1642439199405817856', NULL, NULL);
INSERT INTO `user_strategy_export_002` VALUES (6, 'un1ink_7', 100001, 1642439199833636864, 100001, 1, 1, '2023-03-25 16:39:43', 0, 2, 1, '?????2', '?????', '1642439199833636864', NULL, NULL);
INSERT INTO `user_strategy_export_002` VALUES (17, 'un1ink_hast_2', 100001, 1642440619853004800, 100001, 1, 1, '2023-03-25 16:39:43', 0, 5, 1, '?????5', NULL, '1642440619853004800', NULL, NULL);
INSERT INTO `user_strategy_export_002` VALUES (18, 'un1ink_hast_2', 100001, 1642440620301795328, 100001, 1, 1, '2023-03-25 16:39:43', 0, 5, 1, '?????5', NULL, '1642440620301795328', NULL, NULL);
INSERT INTO `user_strategy_export_002` VALUES (19, 'un1ink_hast_15', 100001, 1642440629055307776, 100001, 1, 1, '2023-03-25 16:39:43', 0, 2, 1, '?????2', '?????', '1642440629055307776', NULL, NULL);
INSERT INTO `user_strategy_export_002` VALUES (20, 'un1ink_hast_15', 100001, 1642440629441183744, 100001, 1, 1, '2023-03-25 16:39:43', 0, 1, 1, '?????1', '?????', '1642440629441183744', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户策略计算结果表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_strategy_export_003
-- ----------------------------
INSERT INTO `user_strategy_export_003` VALUES (1, 'un1ink', 100001, 1642422605283770368, 100001, 1, 1, '2023-03-25 16:39:43', 0, 5, 1, '?????5', NULL, '1642422605283770368', NULL, NULL);
INSERT INTO `user_strategy_export_003` VALUES (2, 'un1ink', 100001, 1642422605719977984, 100001, 1, 1, '2023-03-25 16:39:43', 0, 4, 1, '?????4', NULL, '1642422605719977984', NULL, NULL);
INSERT INTO `user_strategy_export_003` VALUES (3, 'un1ink', 100001, 1642430009190825984, 100001, 1, 1, '2023-03-25 16:39:43', 0, 5, 1, '?????5', NULL, '1642430009190825984', NULL, NULL);
INSERT INTO `user_strategy_export_003` VALUES (4, 'un1ink_6', 100001, 1642439142690439168, 100001, 1, 1, '2023-03-25 16:39:43', 0, 3, 1, '?????3', NULL, '1642439142690439168', NULL, NULL);
INSERT INTO `user_strategy_export_003` VALUES (5, 'un1ink_6', 100001, 1642439143189561344, 100001, 1, 1, '2023-03-25 16:39:43', 0, 4, 1, '?????4', NULL, '1642439143189561344', NULL, NULL);
INSERT INTO `user_strategy_export_003` VALUES (6, 'un1ink_hast_1', 100001, 1642440619093835776, 100001, 1, 1, '2023-03-25 16:39:43', 0, 4, 1, '?????4', NULL, '1642440619093835776', NULL, NULL);
INSERT INTO `user_strategy_export_003` VALUES (7, 'un1ink_hast_1', 100001, 1642440619538432000, 100001, 1, 1, '2023-03-25 16:39:43', 0, 5, 1, '?????5', NULL, '1642440619538432000', NULL, NULL);
INSERT INTO `user_strategy_export_003` VALUES (8, 'un1ink_hast_9', 100001, 1642440624877780992, 100001, 1, 1, '2023-03-25 16:39:43', 0, 2, 1, '?????2', '?????', '1642440624877780992', NULL, NULL);
INSERT INTO `user_strategy_export_003` VALUES (9, 'un1ink_hast_9', 100001, 1642440625301405696, 100001, 1, 1, '2023-03-25 16:39:43', 0, 3, 1, '?????3', NULL, '1642440625301405696', NULL, NULL);
INSERT INTO `user_strategy_export_003` VALUES (10, 'un1ink_hast_10', 100001, 1642440625603395584, 100001, 1, 1, '2023-03-25 16:39:43', 0, 4, 1, '?????4', NULL, '1642440625603395584', NULL, NULL);
INSERT INTO `user_strategy_export_003` VALUES (11, 'un1ink_hast_10', 100001, 1642440625997660160, 100001, 1, 1, '2023-03-25 16:39:43', 0, 5, 1, '?????5', NULL, '1642440625997660160', NULL, NULL);
INSERT INTO `user_strategy_export_003` VALUES (12, 'un1ink_hast_18', 100001, 1642440631068573696, 100001, 1, 1, '2023-03-25 16:39:43', 0, 3, 1, '?????3', NULL, '1642440631068573696', NULL, NULL);
INSERT INTO `user_strategy_export_003` VALUES (13, 'un1ink_hast_18', 100001, 1642440631458643968, 100001, 1, 1, '2023-03-25 16:39:43', 0, 1, 1, '?????1', '?????', '1642440631458643968', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户策略计算结果表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_strategy_export_004
-- ----------------------------
INSERT INTO `user_strategy_export_004` VALUES (1, 'un1ink_5', 100001, 1642438984846196736, 100001, 1, 1, '2023-03-25 16:39:43', 0, 4, 1, '?????4', NULL, '1642438984846196736', NULL, NULL);
INSERT INTO `user_strategy_export_004` VALUES (2, 'un1ink_5', 100001, 1642438985261432832, 100001, 1, 1, '2023-03-25 16:39:43', 0, 5, 1, '?????5', NULL, '1642438985261432832', NULL, NULL);
INSERT INTO `user_strategy_export_004` VALUES (3, 'un1ink_5', 100001, 1642439071290802176, 100001, 1, 1, '2023-03-25 16:39:43', 0, 2, 1, '?????2', '?????', '1642439071290802176', NULL, NULL);
INSERT INTO `user_strategy_export_004` VALUES (4, 'un1ink_5', 100001, 1642439071731204096, 100001, 1, 1, '2023-03-25 16:39:43', 0, 2, 1, '?????2', '?????', '1642439071731204096', NULL, NULL);
INSERT INTO `user_strategy_export_004` VALUES (5, 'un1ink_hast_0', 100001, 1642440618112368640, 100001, 1, 1, '2023-03-25 16:39:43', 0, 5, 1, '?????5', NULL, '1642440618112368640', NULL, NULL);
INSERT INTO `user_strategy_export_004` VALUES (6, 'un1ink_hast_0', 100001, 1642440618720542720, 100001, 1, 1, '2023-03-25 16:39:43', 0, 4, 1, '?????4', NULL, '1642440618720542720', NULL, NULL);
INSERT INTO `user_strategy_export_004` VALUES (7, 'un1ink_hast_8', 100001, 1642440624202498048, 100001, 1, 1, '2023-03-25 16:39:43', 0, 3, 1, '?????3', NULL, '1642440624202498048', NULL, NULL);
INSERT INTO `user_strategy_export_004` VALUES (8, 'un1ink_hast_8', 100001, 1642440624605151232, 100001, 1, 1, '2023-03-25 16:39:43', 0, 2, 1, '?????2', '?????', '1642440624605151232', NULL, NULL);
INSERT INTO `user_strategy_export_004` VALUES (9, 'un1ink_hast_17', 100001, 1642440630393290752, 100001, 1, 1, '2023-03-25 16:39:43', 0, 3, 1, '?????3', NULL, '1642440630393290752', NULL, NULL);
INSERT INTO `user_strategy_export_004` VALUES (10, 'un1ink_hast_17', 100001, 1642440630779166720, 100001, 1, 1, '2023-03-25 16:39:43', 0, 4, 1, '?????4', NULL, '1642440630779166720', NULL, NULL);

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
INSERT INTO `user_take_activity` VALUES (1, 'Uhdgkw766120d', 1641080343161389056, 100001, '????', '2023-03-29 22:10:18', 1, 'Uhdgkw766120d_100001_1', NULL, NULL, NULL, NULL);
INSERT INTO `user_take_activity` VALUES (2, 'Uhdgkw766120d', 1641081433311330304, 100001, '????', '2023-03-29 22:14:38', 1, 'Uhdgkw766120d_100001_1', '2023-03-29 14:14:38', '2023-03-29 14:14:38', NULL, NULL);
INSERT INTO `user_take_activity` VALUES (3, 'xiaofuge', 1642422084397350912, 100001, '????', '2023-04-02 15:01:54', 1, 'xiaofuge_100001_1', '2023-04-02 07:01:54', '2023-04-02 07:01:54', 1, NULL);
INSERT INTO `user_take_activity` VALUES (4, 'xiaofuge', 1642422085001330688, 100001, '????', '2023-04-02 15:01:54', 1, 'xiaofuge_100001_1', '2023-04-02 07:01:54', '2023-04-02 07:01:54', 1, NULL);
INSERT INTO `user_take_activity` VALUES (5, 'xiaofuge', 1642422489634226176, 100001, '????', '2023-04-02 15:03:31', 2, 'xiaofuge_100001_2', '2023-04-02 07:03:30', '2023-04-02 07:03:30', 1, NULL);
INSERT INTO `user_take_activity` VALUES (6, 'xiaofuge', 1642422490183680000, 100001, '????', '2023-04-02 15:03:31', 3, 'xiaofuge_100001_3', '2023-04-02 07:03:31', '2023-04-02 07:03:31', 1, NULL);
INSERT INTO `user_take_activity` VALUES (7, 'un1ink', 1642422604985974784, 100001, '????', '2023-04-02 15:03:58', 1, 'un1ink_100001_1', '2023-04-02 07:03:58', '2023-04-02 07:03:58', 1, NULL);
INSERT INTO `user_take_activity` VALUES (8, 'un1ink', 1642422605564788736, 100001, '????', '2023-04-02 15:03:59', 1, 'un1ink_100001_1', '2023-04-02 07:03:58', '2023-04-02 07:03:58', 1, NULL);
INSERT INTO `user_take_activity` VALUES (9, 'un1ink', 1642430009044025344, 100001, '????', '2023-04-02 15:33:24', 2, 'un1ink_100001_2', '2023-04-02 07:33:23', '2023-04-02 07:33:23', 1, NULL);
INSERT INTO `user_take_activity` VALUES (10, 'un1ink_5', 1642438984548401152, 100001, '????', '2023-04-02 16:09:04', 1, 'un1ink_5_100001_1', '2023-04-02 08:09:03', '2023-04-02 08:09:03', 1, NULL);
INSERT INTO `user_take_activity` VALUES (11, 'un1ink_5', 1642438985110437888, 100001, '????', '2023-04-02 16:09:04', 1, 'un1ink_5_100001_1', '2023-04-02 08:09:03', '2023-04-02 08:09:03', 1, NULL);
INSERT INTO `user_take_activity` VALUES (12, 'un1ink_5', 1642439070976229376, 100001, '????', '2023-04-02 16:09:24', 2, 'un1ink_5_100001_2', '2023-04-02 08:09:24', '2023-04-02 08:09:24', 1, NULL);
INSERT INTO `user_take_activity` VALUES (13, 'un1ink_5', 1642439071576014848, 100001, '????', '2023-04-02 16:09:24', 3, 'un1ink_5_100001_3', '2023-04-02 08:09:24', '2023-04-02 08:09:24', 1, NULL);
INSERT INTO `user_take_activity` VALUES (14, 'un1ink_6', 1642439142363283456, 100001, '????', '2023-04-02 16:09:41', 1, 'un1ink_6_100001_1', '2023-04-02 08:09:41', '2023-04-02 08:09:41', 1, NULL);
INSERT INTO `user_take_activity` VALUES (15, 'un1ink_6', 1642439143017594880, 100001, '????', '2023-04-02 16:09:41', 1, 'un1ink_6_100001_1', '2023-04-02 08:09:41', '2023-04-02 08:09:41', 1, NULL);
INSERT INTO `user_take_activity` VALUES (16, 'un1ink_7', 1642439199095439360, 100001, '????', '2023-04-02 16:09:55', 1, 'un1ink_7_100001_1', '2023-04-02 08:09:54', '2023-04-02 08:09:54', 1, NULL);
INSERT INTO `user_take_activity` VALUES (17, 'un1ink_7', 1642439199678447616, 100001, '????', '2023-04-02 16:09:55', 1, 'un1ink_7_100001_1', '2023-04-02 08:09:54', '2023-04-02 08:09:54', 1, NULL);
INSERT INTO `user_take_activity` VALUES (18, 'un1ink_8', 1642439358118281216, 100001, '????', '2023-04-02 16:10:33', 1, 'un1ink_8_100001_1', '2023-04-02 08:10:32', '2023-04-02 08:10:32', 1, NULL);
INSERT INTO `user_take_activity` VALUES (19, 'un1ink_8', 1642439358709678080, 100001, '????', '2023-04-02 16:10:33', 1, 'un1ink_8_100001_1', '2023-04-02 08:10:32', '2023-04-02 08:10:32', 1, NULL);
INSERT INTO `user_take_activity` VALUES (30, 'un1ink_hast_0', 1642440617797795840, 100001, '????', '2023-04-02 16:15:33', 1, 'un1ink_hast_0_100001_1', '2023-04-02 08:15:33', '2023-04-02 08:15:33', 1, NULL);
INSERT INTO `user_take_activity` VALUES (31, 'un1ink_hast_0', 1642440618544381952, 100001, '????', '2023-04-02 16:15:33', 1, 'un1ink_hast_0_100001_1', '2023-04-02 08:15:33', '2023-04-02 08:15:33', 1, NULL);
INSERT INTO `user_take_activity` VALUES (32, 'un1ink_hast_1', 1642440618934452224, 100001, '????', '2023-04-02 16:15:33', 1, 'un1ink_hast_1_100001_1', '2023-04-02 08:15:33', '2023-04-02 08:15:33', 1, NULL);
INSERT INTO `user_take_activity` VALUES (33, 'un1ink_hast_1', 1642440619379048448, 100001, '????', '2023-04-02 16:15:33', 1, 'un1ink_hast_1_100001_1', '2023-04-02 08:15:33', '2023-04-02 08:15:33', 1, NULL);
INSERT INTO `user_take_activity` VALUES (34, 'un1ink_hast_2', 1642440619693621248, 100001, '????', '2023-04-02 16:15:33', 1, 'un1ink_hast_2_100001_1', '2023-04-02 08:15:33', '2023-04-02 08:15:33', 1, NULL);
INSERT INTO `user_take_activity` VALUES (35, 'un1ink_hast_2', 1642440620138217472, 100001, '????', '2023-04-02 16:15:34', 1, 'un1ink_hast_2_100001_1', '2023-04-02 08:15:33', '2023-04-02 08:15:33', 1, NULL);
INSERT INTO `user_take_activity` VALUES (36, 'un1ink_hast_3', 1642440620465373184, 100001, '????', '2023-04-02 16:15:34', 1, 'un1ink_hast_3_100001_1', '2023-04-02 08:15:33', '2023-04-02 08:15:33', 1, NULL);
INSERT INTO `user_take_activity` VALUES (37, 'un1ink_hast_3', 1642440620918358016, 100001, '????', '2023-04-02 16:15:34', 1, 'un1ink_hast_3_100001_1', '2023-04-02 08:15:33', '2023-04-02 08:15:33', 1, NULL);
INSERT INTO `user_take_activity` VALUES (38, 'un1ink_hast_8', 1642440624064086016, 100001, '????', '2023-04-02 16:15:34', 1, 'un1ink_hast_8_100001_1', '2023-04-02 08:15:34', '2023-04-02 08:15:34', 1, NULL);
INSERT INTO `user_take_activity` VALUES (39, 'un1ink_hast_8', 1642440624466739200, 100001, '????', '2023-04-02 16:15:35', 1, 'un1ink_hast_8_100001_1', '2023-04-02 08:15:34', '2023-04-02 08:15:34', 1, NULL);
INSERT INTO `user_take_activity` VALUES (40, 'un1ink_hast_9', 1642440624743563264, 100001, '????', '2023-04-02 16:15:35', 1, 'un1ink_hast_9_100001_1', '2023-04-02 08:15:34', '2023-04-02 08:15:34', 1, NULL);
INSERT INTO `user_take_activity` VALUES (41, 'un1ink_hast_9', 1642440625158799360, 100001, '????', '2023-04-02 16:15:35', 1, 'un1ink_hast_9_100001_1', '2023-04-02 08:15:34', '2023-04-02 08:15:34', 1, NULL);
INSERT INTO `user_take_activity` VALUES (42, 'un1ink_hast_10', 1642440625456594944, 100001, '????', '2023-04-02 16:15:35', 1, 'un1ink_hast_10_100001_1', '2023-04-02 08:15:34', '2023-04-02 08:15:34', 1, NULL);
INSERT INTO `user_take_activity` VALUES (43, 'un1ink_hast_10', 1642440625850859520, 100001, '????', '2023-04-02 16:15:35', 1, 'un1ink_hast_10_100001_1', '2023-04-02 08:15:34', '2023-04-02 08:15:34', 1, NULL);
INSERT INTO `user_take_activity` VALUES (44, 'un1ink_hast_15', 1642440628879147008, 100001, '????', '2023-04-02 16:15:36', 1, 'un1ink_hast_15_100001_1', '2023-04-02 08:15:35', '2023-04-02 08:15:35', 1, NULL);
INSERT INTO `user_take_activity` VALUES (45, 'un1ink_hast_15', 1642440629306966016, 100001, '????', '2023-04-02 16:15:36', 1, 'un1ink_hast_15_100001_1', '2023-04-02 08:15:35', '2023-04-02 08:15:35', 1, NULL);
INSERT INTO `user_take_activity` VALUES (46, 'un1ink_hast_16', 1642440629600567296, 100001, '????', '2023-04-02 16:15:36', 1, 'un1ink_hast_16_100001_1', '2023-04-02 08:15:35', '2023-04-02 08:15:35', 1, NULL);
INSERT INTO `user_take_activity` VALUES (47, 'un1ink_hast_16', 1642440629982248960, 100001, '????', '2023-04-02 16:15:36', 1, 'un1ink_hast_16_100001_1', '2023-04-02 08:15:35', '2023-04-02 08:15:35', 1, NULL);
INSERT INTO `user_take_activity` VALUES (48, 'un1ink_hast_17', 1642440630250684416, 100001, '????', '2023-04-02 16:15:36', 1, 'un1ink_hast_17_100001_1', '2023-04-02 08:15:35', '2023-04-02 08:15:35', 1, NULL);
INSERT INTO `user_take_activity` VALUES (49, 'un1ink_hast_17', 1642440630653337600, 100001, '????', '2023-04-02 16:15:36', 1, 'un1ink_hast_17_100001_1', '2023-04-02 08:15:36', '2023-04-02 08:15:36', 1, NULL);
INSERT INTO `user_take_activity` VALUES (50, 'un1ink_hast_18', 1642440630917578752, 100001, '????', '2023-04-02 16:15:36', 1, 'un1ink_hast_18_100001_1', '2023-04-02 08:15:36', '2023-04-02 08:15:36', 1, NULL);
INSERT INTO `user_take_activity` VALUES (51, 'un1ink_hast_18', 1642440631324426240, 100001, '????', '2023-04-02 16:15:36', 1, 'un1ink_hast_18_100001_1', '2023-04-02 08:15:36', '2023-04-02 08:15:36', 1, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户活动参与次数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_take_activity_count
-- ----------------------------
INSERT INTO `user_take_activity_count` VALUES (1, 'Uhdgkw766120d', 100001, 10, 8, '2023-03-29 14:10:18', '2023-03-29 14:10:18');
INSERT INTO `user_take_activity_count` VALUES (2, 'xiaofuge', 100001, 10, 6, '2023-04-02 07:01:54', '2023-04-02 07:01:54');
INSERT INTO `user_take_activity_count` VALUES (3, 'un1ink', 100001, 10, 7, '2023-04-02 07:03:58', '2023-04-02 07:03:58');
INSERT INTO `user_take_activity_count` VALUES (4, 'un1ink_5', 100001, 10, 6, '2023-04-02 08:09:03', '2023-04-02 08:09:03');
INSERT INTO `user_take_activity_count` VALUES (5, 'un1ink_6', 100001, 10, 8, '2023-04-02 08:09:41', '2023-04-02 08:09:41');
INSERT INTO `user_take_activity_count` VALUES (6, 'un1ink_7', 100001, 10, 8, '2023-04-02 08:09:54', '2023-04-02 08:09:54');
INSERT INTO `user_take_activity_count` VALUES (7, 'un1ink_8', 100001, 10, 8, '2023-04-02 08:10:32', '2023-04-02 08:10:32');
INSERT INTO `user_take_activity_count` VALUES (8, 'uId', 100001, 10, 0, '2023-04-02 08:12:35', '2023-04-02 08:12:35');
INSERT INTO `user_take_activity_count` VALUES (9, 'un1ink_hast_0', 100001, 10, 8, '2023-04-02 08:15:33', '2023-04-02 08:15:33');
INSERT INTO `user_take_activity_count` VALUES (10, 'un1ink_hast_1', 100001, 10, 8, '2023-04-02 08:15:33', '2023-04-02 08:15:33');
INSERT INTO `user_take_activity_count` VALUES (11, 'un1ink_hast_2', 100001, 10, 8, '2023-04-02 08:15:33', '2023-04-02 08:15:33');
INSERT INTO `user_take_activity_count` VALUES (12, 'un1ink_hast_3', 100001, 10, 8, '2023-04-02 08:15:33', '2023-04-02 08:15:33');
INSERT INTO `user_take_activity_count` VALUES (13, 'un1ink_hast_8', 100001, 10, 8, '2023-04-02 08:15:34', '2023-04-02 08:15:34');
INSERT INTO `user_take_activity_count` VALUES (14, 'un1ink_hast_9', 100001, 10, 8, '2023-04-02 08:15:34', '2023-04-02 08:15:34');
INSERT INTO `user_take_activity_count` VALUES (15, 'un1ink_hast_10', 100001, 10, 8, '2023-04-02 08:15:34', '2023-04-02 08:15:34');
INSERT INTO `user_take_activity_count` VALUES (16, 'un1ink_hast_15', 100001, 10, 8, '2023-04-02 08:15:35', '2023-04-02 08:15:35');
INSERT INTO `user_take_activity_count` VALUES (17, 'un1ink_hast_16', 100001, 10, 8, '2023-04-02 08:15:35', '2023-04-02 08:15:35');
INSERT INTO `user_take_activity_count` VALUES (18, 'un1ink_hast_17', 100001, 10, 8, '2023-04-02 08:15:35', '2023-04-02 08:15:35');
INSERT INTO `user_take_activity_count` VALUES (19, 'un1ink_hast_18', 100001, 10, 8, '2023-04-02 08:15:36', '2023-04-02 08:15:36');

SET FOREIGN_KEY_CHECKS = 1;
