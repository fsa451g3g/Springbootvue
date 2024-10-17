/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80039
 Source Host           : localhost:3306
 Source Schema         : projectv1

 Target Server Type    : MySQL
 Target Server Version : 80039
 File Encoding         : 65001

 Date: 16/10/2024 19:45:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名称',
  `current_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '当前值',
  `initial_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '初始值',
  `placeholder` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '提示语',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of setting
-- ----------------------------
INSERT INTO `setting` VALUES ('1', '系统名称', 'setName', '管理系统', '管理系统', '请输入系统名称（长度在2到8个字符之间）');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '昵称',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色',
  `tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '电话号码',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '邮箱',
  `account_non_locked` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否未锁定，1-是，0-否',
  `enabled` tinyint(0) NOT NULL DEFAULT 1 COMMENT '是否启用，1-是，0-否',
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('192c4401fd55406b92b7a331d91ad8cf', '4545', '1513', '$2a$10$ZOQ60xJNR0YM.PU82SO4lOqjgAwlYkx2j7g/rwJipPLduZnUpyqHK', 'user', '15889658965', '15153@qq.com', 1, 1, '2022-06-14 08:47:13', '2022-06-14 08:47:13');
INSERT INTO `user` VALUES ('3b10f8d5bd064bedab7d85909b9af188', 'user', '李思思', '$2a$10$ZOQ60xJNR0YM.PU82SO4lOqjgAwlYkx2j7g/rwJipPLduZnUpyqHK', 'user', '15889658965', '1531@qq.com', 1, 1, '2022-06-07 15:45:24', '2022-06-14 10:21:28');
INSERT INTO `user` VALUES ('9e7bf6d125cc47adae226dbba8f1b8aa', 'admin', '张三三', '$2a$10$ZOQ60xJNR0YM.PU82SO4lOqjgAwlYkx2j7g/rwJipPLduZnUpyqHK', 'admin', '15889658965', '153153@qq.com', 1, 1, '2022-06-07 14:22:41', '2022-06-14 10:17:19');
INSERT INTO `user` VALUES ('ca3abc21465646cc974cb9e2a6d14d5f', '4531', '261236', '$2a$10$ZOQ60xJNR0YM.PU82SO4lOqjgAwlYkx2j7g/rwJipPLduZnUpyqHK', 'admin', '15889658965', '15313@qq.com', 1, 1, '2022-06-14 08:45:27', '2022-06-14 10:14:33');

SET FOREIGN_KEY_CHECKS = 1;
