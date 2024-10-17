/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : admin-template

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 23/06/2022 08:55:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for carousel
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel`  (
                             `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '主键id',
                             `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片',
                             `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名称',
                             `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '描述',
                             `enabled` tinyint(0) NULL DEFAULT NULL COMMENT '是否正在使用',
                             `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                             `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of carousel
-- ----------------------------
INSERT INTO `carousel` VALUES ('2ArlCcDC33ze9tunF1EwZ2oadsq', '9b51ffa314b3457fb61996a3766895fd.jpg', 'dddddddd', 'dddddddddddddddddd', 1, '2022-06-21 01:37:38', '2022-06-21 02:07:38');
INSERT INTO `carousel` VALUES ('2Arm3ygKS0EF4rkw5nlZ6uQtOec', '43ee209bb8f3424ca0e90ddbd40527fb.jpg', 'ddddd', 'ssssssssssssssssssss', 1, '2022-06-21 01:44:42', '2022-06-21 01:44:42');
INSERT INTO `carousel` VALUES ('2Arm4e5gnmQjRDfoFIuf8DktPED', '28f034f36d284a78b0220806ebbb9eb0.jpg', 'ewwwwwww', 'wqewqewqewq', 1, '2022-06-21 01:44:49', '2022-06-21 01:44:49');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
                         `id` int(0) NOT NULL COMMENT '菜单ID',
                         `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单编码',
                         `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单名称',
                         `father_id` int(0) NULL DEFAULT NULL COMMENT '父菜单',
                         `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '路径',
                         `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT 'icon',
                         `is_has_son` int(0) NULL DEFAULT NULL COMMENT '是否有子菜单',
                         `is_menu` tinyint(0) NULL DEFAULT NULL COMMENT '是否是菜单,true:是，false：否',
                         `component` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '组件名称',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (100, 'admin:dashboard', '工作台', 0, '/admin/dashboard', 'grid', 0, 1, 'admin,Dashboard');
INSERT INTO `menu` VALUES (101, 'admin:index', '用户首页', 0, '/admin/index', 'home-filled', 0, 1, 'admin,Index');
INSERT INTO `menu` VALUES (160, 'admin:user', '账号管理', 0, '/admin/user', 'user', 1, 1, 'admin,User');
INSERT INTO `menu` VALUES (161, 'admin:user1', '管理员账号', 160, '/admin/user1', 'sunny', 0, 1, 'admin,User1');
INSERT INTO `menu` VALUES (162, 'admin:user2', '用户账号', 160, '/admin/user2', 'sunny', 0, 1, 'admin,User2');
INSERT INTO `menu` VALUES (170, 'admin:userInfo', '账号详情', 0, '/admin/userInfo', 'sunny', 0, 0, 'admin,UserInfo');
INSERT INTO `menu` VALUES (171, 'admin:api', '接口文档', 0, '/admin/api', 'document', 0, 1, 'admin,Api');
INSERT INTO `menu` VALUES (180, 'admin:log', '日志记录', 0, '/admin/log', 'DocumentCopy', 1, 1, 'admin,Log');
INSERT INTO `menu` VALUES (181, 'admin:systemLog', '系统日志', 180, '/admin/systemLog', 'sunny', 0, 1, 'admin,SystemLog');
INSERT INTO `menu` VALUES (182, 'admin:sqlLog', 'SQL日志', 180, '/admin/sqlLog', 'sunny', 0, 1, 'admin,SqlLog');
INSERT INTO `menu` VALUES (183, 'admin:runLog', '运行日志', 180, '/admin/runLog', 'sunny', 0, 1, 'admin,RunLog');
INSERT INTO `menu` VALUES (184, 'admin:systemLogInfo', '系统日志详情', 0, '/admin/systemLogInfo', 'sunny', 0, 0, 'admin,SystemLogInfo');
INSERT INTO `menu` VALUES (190, 'admin:setting', '系统设置', 0, '/admin/setting', 'Setting', 1, 1, 'admin,Setting');
INSERT INTO `menu` VALUES (191, 'admin:setName', '系统名称', 190, '/admin/setName', 'sunny', 0, 1, 'admin,SetName');
INSERT INTO `menu` VALUES (200, 'user:index', '首页', 0, '/user/index', 'home-filled', 0, 1, 'user,Index');
INSERT INTO `menu` VALUES (501, 'main:main', '个人中心', 0, '/main/main', 'sunny', 0, 0, 'main,Main');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
                         `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色ID',
                         `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
                         `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色标识',
                         `authority` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色权限',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '超级管理员', 'ROLE_ADMIN');
INSERT INTO `role` VALUES ('2', 'user', '用户', 'ROLE_USER');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
                              `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色ID',
                              `menu_id` int(0) NOT NULL COMMENT '菜单ID',
                              INDEX `c_role_id2`(`role_id`) USING BTREE,
                              INDEX `c_menu_id`(`menu_id`) USING BTREE,
                              CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                              CONSTRAINT `role_menu_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '菜单角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', 101);
INSERT INTO `role_menu` VALUES ('1', 100);
INSERT INTO `role_menu` VALUES ('1', 501);
INSERT INTO `role_menu` VALUES ('2', 200);
INSERT INTO `role_menu` VALUES ('2', 501);
INSERT INTO `role_menu` VALUES ('1', 180);
INSERT INTO `role_menu` VALUES ('1', 184);
INSERT INTO `role_menu` VALUES ('1', 170);
INSERT INTO `role_menu` VALUES ('1', 171);
INSERT INTO `role_menu` VALUES ('1', 160);
INSERT INTO `role_menu` VALUES ('1', 190);

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
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log`  (
                               `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '日志id',
                               `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
                               `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '请求路径',
                               `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '请求方法',
                               `args` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '请求参数',
                               `result` tinyint(0) NULL DEFAULT NULL COMMENT '结果',
                               `message` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '异常信息',
                               `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
                               `class_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '控制器方法',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户ID',
                         `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
                         `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '昵称',
                         `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
                         `tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '电话号码',
                         `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
                         `account_non_locked` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否未锁定，1-是，0-否',
                         `enabled` tinyint(0) NOT NULL DEFAULT 1 COMMENT '是否启用，1-是，0-否',
                         `created_time` datetime(0) NOT NULL COMMENT '创建时间',
                         `updated_time` datetime(0) NOT NULL COMMENT '更新时间',
                         PRIMARY KEY (`id`) USING BTREE,
                         UNIQUE INDEX `uk_user_username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2AEm8ofTyAU6AhqcqIVWXb6Crya', 'admin', '张三三', '$2a$10$ZOQ60xJNR0YM.PU82SO4lOqjgAwlYkx2j7g/rwJipPLduZnUpyqHK', '15889658965', '153153@qq.com', 1, 1, '2022-06-07 14:22:41', '2022-06-14 10:17:19');
INSERT INTO `user` VALUES ('2AEwCLtpK4TRf1fsy6zIBfhbQqK', 'user', '李思思', '$2a$10$ZOQ60xJNR0YM.PU82SO4lOqjgAwlYkx2j7g/rwJipPLduZnUpyqHK', '15889658965', '1531@qq.com', 1, 1, '2022-06-07 15:45:24', '2022-06-14 10:21:28');
INSERT INTO `user` VALUES ('2AYpMmHy0n0QUwk1T9nSY9pHQzA', '4531', '261236', '$2a$10$ZOQ60xJNR0YM.PU82SO4lOqjgAwlYkx2j7g/rwJipPLduZnUpyqHK', '15889658965', '15313@qq.com', 1, 1, '2022-06-14 08:45:27', '2022-06-14 10:14:33');
INSERT INTO `user` VALUES ('2AYpZyXORDUhZWSNlUW6AfXuGjD', '4545', '1513', '$2a$10$ZOQ60xJNR0YM.PU82SO4lOqjgAwlYkx2j7g/rwJipPLduZnUpyqHK', '15889658965', '15153@qq.com', 1, 1, '2022-06-14 08:47:13', '2022-06-14 08:47:13');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
                              `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户ID',
                              `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色ID',
                              INDEX `c_user_id`(`user_id`) USING BTREE,
                              INDEX `c_role_id`(`role_id`) USING BTREE,
                              CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                              CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('2AEm8ofTyAU6AhqcqIVWXb6Crya', '1');
INSERT INTO `user_role` VALUES ('2AEwCLtpK4TRf1fsy6zIBfhbQqK', '2');
INSERT INTO `user_role` VALUES ('2AYpMmHy0n0QUwk1T9nSY9pHQzA', '1');
INSERT INTO `user_role` VALUES ('2AYpZyXORDUhZWSNlUW6AfXuGjD', '1');

SET FOREIGN_KEY_CHECKS = 1;
