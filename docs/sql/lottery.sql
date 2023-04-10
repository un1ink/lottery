/*
 Navicat Premium Data Transfer

 Source Server         : docker_mysql
 Source Server Type    : MySQL
 Source Server Version : 50741 (5.7.41)
 Source Host           : localhost:3307
 Source Schema         : lottery

 Target Server Type    : MySQL
 Target Server Version : 50741 (5.7.41)
 File Encoding         : 65001

 Date: 04/04/2023 17:07:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `activityId` bigint(20) NULL DEFAULT NULL COMMENT '活动ID',
  `activityName` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '活动名称',
  `activityDesc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '活动描述',
  `strategyId` bigint(20) NULL DEFAULT NULL COMMENT '策略id',
  `beginDateTime` datetime NOT NULL COMMENT '开始时间',
  `endDateTime` datetime NOT NULL COMMENT '结束时间',
  `stockCount` int(11) NOT NULL COMMENT '初始库存(当前无实际意义)',
  `stockSurplusCount` int(11) NULL DEFAULT NULL COMMENT '实际剩余库存',
  `takeCount` int(11) NULL DEFAULT NULL COMMENT '每人可参与次数',
  `state` int(11) NULL DEFAULT NULL COMMENT '活动状态：编辑、提审、撤审、通过、运行、拒绝、关闭、开启',
  `creator` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '创建人',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `activity_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '活动配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (1, 200005, '????', '?????????', 100001, '2023-03-25 15:22:38', '2023-03-25 15:22:38', 100, 100, 10, 0, 'xiaofuge', '2023-03-25 07:22:38', '2023-03-25 07:22:38');
INSERT INTO `activity` VALUES (2, 100001, '????', '?????????', 100001, '2022-03-25 16:04:01', '2023-11-25 16:04:01', 100, 89, 10, 5, 'xiaofuge', '2023-03-25 08:04:01', '2023-03-25 08:04:01');
INSERT INTO `activity` VALUES (5, 100002, '????', '?????????', 100001, '2023-03-25 16:05:51', '2023-03-25 16:05:51', 100, 100, 10, 0, 'xiaofuge', '2023-03-25 08:05:50', '2023-03-25 08:05:50');
INSERT INTO `activity` VALUES (6, 300001, '????', '?????????', 100001, '2023-03-25 23:36:29', '2023-03-25 23:36:29', 100, 100, 10, 0, 'xiaofuge', '2023-03-25 15:36:29', '2023-03-25 15:36:29');
INSERT INTO `activity` VALUES (7, 300001, '????', '?????????', 100001, '2023-03-25 23:37:25', '2023-03-25 23:37:25', 100, 100, 10, 0, 'xiaofuge', '2023-03-25 15:37:25', '2023-03-25 15:37:25');
INSERT INTO `activity` VALUES (8, 300001, '????', '?????????', 100001, '2023-03-25 23:44:02', '2023-03-25 23:44:02', 100, 100, 10, 0, 'xiaofuge', '2023-03-25 15:44:02', '2023-03-25 15:44:02');
INSERT INTO `activity` VALUES (9, 300002, 'needDoing', '?????????', 100001, '2023-03-25 23:46:08', '2023-03-25 23:46:08', 100, 100, 10, 7, 'xiaofuge', '2023-03-25 15:46:08', '2023-03-25 15:46:08');
INSERT INTO `activity` VALUES (17, 120981321, 'needClose', '??????', 100001, '2023-03-01 22:13:35', '2023-03-15 22:13:35', 100, 100, 10, 7, 'xiaofuge', '2023-03-26 14:13:35', '2023-03-26 14:13:35');

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `awardId` bigint(20) NULL DEFAULT NULL COMMENT '奖品ID',
  `awardType` int(4) NULL DEFAULT NULL COMMENT '奖品类型（文字描述、兑换码、优惠券、实物奖品暂无）',
  `awardCount` int(11) NULL DEFAULT NULL COMMENT '奖品数量',
  `awardName` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '奖品名称',
  `awardContent` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
  `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'updateTime',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '奖品配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of award
-- ----------------------------
INSERT INTO `award` VALUES (1, 1, 1, 10, '超值优惠卷1', '购物代金卷', '2023-03-25 08:53:16', '2023-03-25 08:53:16');
INSERT INTO `award` VALUES (2, 2, 1, 20, '超值优惠卷2', '购物代金卷', '2023-03-25 08:53:26', '2023-03-25 08:53:26');
INSERT INTO `award` VALUES (3, 3, 1, 30, '超值优惠卷3', NULL, '2023-03-26 09:15:02', '2023-03-26 09:15:02');
INSERT INTO `award` VALUES (4, 4, 1, 40, '超值优惠卷4', NULL, '2023-03-26 09:18:05', '2023-03-26 09:18:05');
INSERT INTO `award` VALUES (5, 5, 1, 50, '超值优惠卷5', NULL, '2023-03-26 09:18:14', '2023-03-26 09:18:14');
INSERT INTO `award` VALUES (21, 101, 3, NULL, '??', '???????? fustack', '2023-03-26 14:13:35', '2023-03-26 14:13:35');
INSERT INTO `award` VALUES (22, 102, 3, NULL, '??', '???????? fustack', '2023-03-26 14:13:35', '2023-03-26 14:13:35');
INSERT INTO `award` VALUES (23, 103, 3, NULL, '??', '???????? fustack', '2023-03-26 14:13:35', '2023-03-26 14:13:35');
INSERT INTO `award` VALUES (24, 104, 3, NULL, '??', '???????? fustack', '2023-03-26 14:13:35', '2023-03-26 14:13:35');
INSERT INTO `award` VALUES (25, 105, 3, NULL, '???', '???????? fustack', '2023-03-26 14:13:35', '2023-03-26 14:13:35');

-- ----------------------------
-- Table structure for rule_tree
-- ----------------------------
DROP TABLE IF EXISTS `rule_tree`;
CREATE TABLE `rule_tree`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `treeName` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则树NAME',
  `treeDesc` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则树描述',
  `treeRootNodeId` bigint(20) NULL DEFAULT NULL COMMENT '规则树根ID',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2110081903 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rule_tree
-- ----------------------------
INSERT INTO `rule_tree` VALUES (2110081902, '抽奖活动规则树', '用于决策不同用户可参与的活动', 1, '2021-10-08 15:38:05', '2021-10-08 15:38:05');

-- ----------------------------
-- Table structure for rule_tree_node
-- ----------------------------
DROP TABLE IF EXISTS `rule_tree_node`;
CREATE TABLE `rule_tree_node`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `treeId` int(2) NULL DEFAULT NULL COMMENT '规则树ID',
  `nodeType` int(2) NULL DEFAULT NULL COMMENT '节点类型；1子叶、2果实',
  `nodeValue` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点值[nodeType=2]；果实值',
  `ruleKey` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则Key',
  `ruleDesc` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rule_tree_node
-- ----------------------------
INSERT INTO `rule_tree_node` VALUES (1, 2110081902, 1, NULL, 'userGender', '用户性别[男/女]');
INSERT INTO `rule_tree_node` VALUES (11, 2110081902, 1, NULL, 'userAge', '用户年龄');
INSERT INTO `rule_tree_node` VALUES (12, 2110081902, 1, NULL, 'userAge', '用户年龄');
INSERT INTO `rule_tree_node` VALUES (111, 2110081902, 2, '100001', NULL, NULL);
INSERT INTO `rule_tree_node` VALUES (112, 2110081902, 2, '100002', NULL, NULL);
INSERT INTO `rule_tree_node` VALUES (121, 2110081902, 2, '100003', NULL, NULL);
INSERT INTO `rule_tree_node` VALUES (122, 2110081902, 2, '100004', NULL, NULL);

-- ----------------------------
-- Table structure for rule_tree_node_line
-- ----------------------------
DROP TABLE IF EXISTS `rule_tree_node_line`;
CREATE TABLE `rule_tree_node_line`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `treeId` bigint(20) NULL DEFAULT NULL COMMENT '规则树ID',
  `nodeIdFrom` bigint(20) NULL DEFAULT NULL COMMENT '节点From',
  `nodeIdTo` bigint(20) NULL DEFAULT NULL COMMENT '节点To',
  `ruleLimitType` int(2) NULL DEFAULT NULL COMMENT '限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围];7:果实',
  `ruleLimitValue` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '限定值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rule_tree_node_line
-- ----------------------------
INSERT INTO `rule_tree_node_line` VALUES (1, 2110081902, 1, 11, 1, 'man');
INSERT INTO `rule_tree_node_line` VALUES (2, 2110081902, 1, 12, 1, 'woman');
INSERT INTO `rule_tree_node_line` VALUES (3, 2110081902, 11, 111, 3, '25');
INSERT INTO `rule_tree_node_line` VALUES (4, 2110081902, 11, 112, 4, '25');
INSERT INTO `rule_tree_node_line` VALUES (5, 2110081902, 12, 121, 3, '25');
INSERT INTO `rule_tree_node_line` VALUES (6, 2110081902, 12, 122, 4, '25');

-- ----------------------------
-- Table structure for strategy
-- ----------------------------
DROP TABLE IF EXISTS `strategy`;
CREATE TABLE `strategy`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategyId` bigint(11) NOT NULL COMMENT '策略ID',
  `strategyDesc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '策略描述',
  `strategyMode` int(4) NULL DEFAULT NULL COMMENT '策略方式「1:单项概率、2:总体概率」',
  `grantType` int(4) NULL DEFAULT NULL COMMENT '发放奖品方式「1:即时、2:定时[含活动结束]、3:人工」',
  `grantDate` datetime NULL DEFAULT NULL COMMENT '发放奖品时间',
  `extInfo` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展信息',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `strategy_strategyId_uindex`(`strategyId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '策略配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of strategy
-- ----------------------------
INSERT INTO `strategy` VALUES (1, 100001, 'this is mode1', 1, 1, '2023-03-25 16:39:43', '无扩展信息', '2023-03-25 16:39:58', '2023-03-25 16:40:01');
INSERT INTO `strategy` VALUES (5, 100002, '????', 1, 1, '2023-03-26 22:13:35', '', '2023-03-26 14:13:35', '2023-03-26 14:13:35');

-- ----------------------------
-- Table structure for strategy_detail
-- ----------------------------
DROP TABLE IF EXISTS `strategy_detail`;
CREATE TABLE `strategy_detail`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategyId` bigint(11) NOT NULL COMMENT '策略ID',
  `awardId` bigint(11) NULL DEFAULT NULL COMMENT '奖品ID',
  `awardName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `awardCount` int(11) NULL DEFAULT NULL COMMENT '奖品数量',
  `awardRate` decimal(5, 2) NULL DEFAULT NULL COMMENT '中奖概率',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `awardSurplusCount` int(11) NULL DEFAULT 0 COMMENT '奖品剩余库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '策略明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of strategy_detail
-- ----------------------------
INSERT INTO `strategy_detail` VALUES (1, 100001, 1, '奖品1', 10, 0.05, '2023-03-25 16:54:45', '2023-03-25 16:54:40', 99);
INSERT INTO `strategy_detail` VALUES (2, 100001, 2, '奖品2', 20, 0.10, '2023-03-25 16:54:37', '2023-03-25 16:54:42', 97);
INSERT INTO `strategy_detail` VALUES (3, 100001, 3, '奖品3', 50, 0.15, NULL, NULL, 98);
INSERT INTO `strategy_detail` VALUES (4, 100001, 4, '奖品4', 100, 0.25, NULL, NULL, 97);
INSERT INTO `strategy_detail` VALUES (5, 100001, 5, '奖品5', 200, 0.35, NULL, NULL, 97);
INSERT INTO `strategy_detail` VALUES (6, 10002, 101, '10', 10, 0.05, '2023-03-26 14:13:35', '2023-03-26 14:13:35', 100);
INSERT INTO `strategy_detail` VALUES (7, 10002, 102, '20', 20, 0.15, '2023-03-26 14:13:35', '2023-03-26 14:13:35', 100);
INSERT INTO `strategy_detail` VALUES (8, 10002, 103, '50', 50, 0.20, '2023-03-26 14:13:35', '2023-03-26 14:13:35', 100);
INSERT INTO `strategy_detail` VALUES (9, 10002, 104, '100', 100, 0.25, '2023-03-26 14:13:35', '2023-03-26 14:13:35', 100);
INSERT INTO `strategy_detail` VALUES (10, 10002, 104, '500', 500, 0.35, '2023-03-26 14:13:35', '2023-03-26 14:13:35', 100);

SET FOREIGN_KEY_CHECKS = 1;
