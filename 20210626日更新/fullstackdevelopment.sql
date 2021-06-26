/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : fullstackdevelopment

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2021-06-26 15:00:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for db_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `db_admin_user`;
CREATE TABLE `db_admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '所属角色（外键）',
  `organ_id` int(11) DEFAULT NULL COMMENT '机构(外键)',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员名称',
  `admin_fullname` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `admin_phone` varchar(20) NOT NULL COMMENT '管理员手机号',
  `admin_password` varchar(200) NOT NULL COMMENT '管理员密码',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `delete_status` int(2) DEFAULT '0' COMMENT '是否删除0: 未删除, 1表示删除',
  `level` int(2) DEFAULT NULL COMMENT '等级',
  PRIMARY KEY (`id`),
  UNIQUE KEY `db_admin_user_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='管理用户信息表';

-- ----------------------------
-- Records of db_admin_user
-- ----------------------------
INSERT INTO `db_admin_user` VALUES ('1', '1', '1', 'administrator', '超级管理员', '17673132955', '58873bed47cee9c9941de53a7cc42bf7', '2019-10-04 11:18:13', '2019-10-05 15:39:08', '2019-10-04 11:18:32', '0', null);
INSERT INTO `db_admin_user` VALUES ('2', '2', '1', 'admin', '杨永生', '18616959965', 'def0e4568a393af9aca7bfa3d54c5147', '2019-10-04 11:18:17', '2019-11-12 10:33:25', '2020-01-11 13:42:12', '0', null);

-- ----------------------------
-- Table structure for db_log
-- ----------------------------
DROP TABLE IF EXISTS `db_log`;
CREATE TABLE `db_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(50) NOT NULL COMMENT '管理端用户名',
  `operation` varchar(50) NOT NULL COMMENT '操作',
  `method` varchar(100) DEFAULT NULL COMMENT '执行方法',
  `params` varchar(500) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'ip',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `db_log_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_log
-- ----------------------------

-- ----------------------------
-- Table structure for db_menu
-- ----------------------------
DROP TABLE IF EXISTS `db_menu`;
CREATE TABLE `db_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL COMMENT '父节点',
  `title` varchar(20) NOT NULL COMMENT '菜单名',
  `icon` varchar(20) DEFAULT NULL COMMENT '图标',
  `href` varchar(100) DEFAULT NULL COMMENT '资源地址',
  `perms` varchar(500) DEFAULT NULL COMMENT '权限',
  `spread` varchar(10) DEFAULT NULL COMMENT 'true：展开，false：不展开',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `delete_status` int(2) DEFAULT '0' COMMENT '是否删除',
  `sort_no` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `db_menus_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_menu
-- ----------------------------
INSERT INTO `db_menu` VALUES ('1', '0', '系统管理', 'ios-cog-outline', null, null, 'true', '1', '2019-06-26 12:27:52', '2020-02-04 23:02:05', '0', '0');
INSERT INTO `db_menu` VALUES ('2', '1', '系统人员', 'ios-contacts-outline', 'sys/adminUser', null, 'true', '98', '2019-06-26 12:34:35', '2019-10-03 14:59:48', '0', '0');
INSERT INTO `db_menu` VALUES ('3', '1', '菜单管理', 'ios-options', 'sys/menu', 'sys:menu', 'true', '97', '2019-06-26 12:39:28', '2019-08-04 10:38:36', '0', '0');
INSERT INTO `db_menu` VALUES ('4', '1', '角色管理', 'logo-instagram', 'sys/role', 'sys:role', 'true', '96', '2019-06-26 12:41:50', '2019-08-04 10:41:23', '0', '0');
INSERT INTO `db_menu` VALUES ('5', '1', '机构管理', 'md-keypad', 'sys/organ', null, 'true', '95', '2019-06-26 12:43:18', '2019-10-03 14:31:09', '0', '0');
INSERT INTO `db_menu` VALUES ('6', '0', '首页', 'md-home', 'sys/home', null, 'true', '9999', '2019-06-26 15:12:20', '2020-02-04 23:01:35', '0', '0');
INSERT INTO `db_menu` VALUES ('7', '1', '图标查看', 'md-paper', 'sys/icon', null, 'true', '94', '2019-06-30 04:26:36', '2019-06-30 04:26:40', '0', '0');
INSERT INTO `db_menu` VALUES ('8', '3', '查看', 'ios-apps', 'sys/menu/findMenuByMyRoleId', 'sys:menu:list', null, '100', '2019-06-30 17:28:29', '2019-08-04 11:31:02', '0', '0');
INSERT INTO `db_menu` VALUES ('9', '3', '编辑', 'ios-apps', 'sys/menu/updateMenu', 'sys:menu:updateMenu', null, '80', '2019-06-30 18:36:32', '2019-08-04 11:15:35', '0', '0');
INSERT INTO `db_menu` VALUES ('10', '2', '查看', 'ios-contacts-outline', 'sys/admin/findAdminUserList', 'sys:adminUser:list', null, '100', '2019-07-04 14:38:51', '2019-08-04 11:49:47', '0', '0');
INSERT INTO `db_menu` VALUES ('11', '3', '添加', 'md-add-circle', 'sys/menu/addMenu', 'sys:menu:addMenu', null, '90', '2019-08-04 10:37:34', '2019-08-04 10:46:33', '0', '0');
INSERT INTO `db_menu` VALUES ('12', '4', '查看', 'ios-apps', 'sys/role/findRoleList', 'sys:role:list', null, '100', '2019-08-04 10:41:13', '2019-08-04 11:45:31', '0', '0');
INSERT INTO `db_menu` VALUES ('13', '4', '添加', 'md-add-circle', 'sys/role/addRole', 'sys:role:addRole', null, '90', '2019-08-04 10:46:02', '2019-08-04 10:46:17', '0', '0');
INSERT INTO `db_menu` VALUES ('14', '2', '添加', '', 'sys/admin/addAdminUser', 'sys:admin:addAdminUser', null, '90', '2019-08-04 11:09:14', '2019-08-04 11:09:14', '0', '0');
INSERT INTO `db_menu` VALUES ('15', '5', '查看', null, 'sys/organ/findOrganTree', 'sys:organ:list', null, '100', '2019-08-04 11:10:46', '2019-08-04 11:36:32', '0', '0');
INSERT INTO `db_menu` VALUES ('16', '5', '添加', '', 'sys/organ/addOrgan', 'sys:organ:addOrgan', null, '90', '2019-08-04 11:11:12', '2019-08-04 11:11:12', '0', '0');
INSERT INTO `db_menu` VALUES ('17', '5', '编辑', '', 'sys/organ/updateOrgan', 'sys:organ:updateOrgan', null, '80', '2019-08-04 11:12:11', '2019-08-04 11:12:11', '0', '0');
INSERT INTO `db_menu` VALUES ('18', '4', '编辑', '', 'sys/role/updateRole', 'sys:role:updateRole', null, '80', '2019-08-04 11:18:37', '2019-08-04 11:18:37', '0', '0');
INSERT INTO `db_menu` VALUES ('19', '4', '权限菜单', null, 'sys/menu/findMenuByRoleId', 'sys:menu:roleMenuList', null, '50', '2019-08-04 11:41:23', '2019-08-04 15:36:33', '0', '0');
INSERT INTO `db_menu` VALUES ('20', '5', '删除', null, 'sys/organ/deleteOrgan', 'sys:organ:deleteOrgan', null, '70', '2019-08-04 11:43:16', '2019-08-04 15:46:46', '0', '0');
INSERT INTO `db_menu` VALUES ('21', '2', '角色列表', '', 'sys/role/findRoleAllList', 'sys:role:allList', null, '50', '2019-08-04 11:48:14', '2019-08-04 11:48:14', '0', '0');
INSERT INTO `db_menu` VALUES ('22', '4', '添加编辑菜单', '', 'sys/menu/addOrEditRoleMenuByIdArray', 'sys:menu:addOrEdit', null, '40', '2019-08-04 11:52:44', '2019-08-04 11:52:44', '0', '0');
INSERT INTO `db_menu` VALUES ('23', '3', '删除', '', 'sys/menu/deleteMenu', 'sys:menu:deleteMenu', null, '70', '2019-08-04 11:53:41', '2019-08-04 11:53:41', '0', '0');
INSERT INTO `db_menu` VALUES ('24', '2', '删除', '', 'sys/admin/deleteAdminUser', 'sys:admin:deleteAdminUser', null, '70', '2019-08-04 11:55:05', '2019-08-04 11:55:05', '0', '0');
INSERT INTO `db_menu` VALUES ('25', '4', '删除', '', 'sys/role/deleteRole', 'sys:role:deleteRole', null, '70', '2019-08-04 11:56:04', '2019-08-04 11:56:04', '0', '0');
INSERT INTO `db_menu` VALUES ('26', '2', '编辑', '', 'sys/admin/updateAdminUser', 'sys:admin:updateAdminUser', null, '80', '2019-08-04 11:56:55', '2019-08-04 11:56:55', '0', '0');
INSERT INTO `db_menu` VALUES ('27', '2', '密码初始化', '', 'sys/admin/editPasswordAdminUser', 'sys:admin:editPasswordAdminUser', null, '40', '2019-09-26 20:10:55', '2019-09-26 20:10:55', '0', '0');
INSERT INTO `db_menu` VALUES ('28', '2', '获取所有用户', '', 'sys/admin/findAllAdminUserList', 'sys:admin:findAllAdminUserList', null, '20', '2019-10-03 12:03:34', '2019-10-03 12:03:34', '0', '0');
INSERT INTO `db_menu` VALUES ('29', '2', '修改密码', '', 'sys/admin/updatePasswordAdminUser', 'sys:admin:updatePasswordAdminUser', null, '10', '2020-01-09 17:20:56', '2020-01-09 17:20:56', '0', '0');
INSERT INTO `db_menu` VALUES ('30', '2', '获取用户机构树', '', 'sys/organ/findOrganUserTree', 'sys:organ:findOrganUserTree', null, '10', '2020-01-09 17:20:56', '2020-01-09 17:20:56', '0', '0');
INSERT INTO `db_menu` VALUES ('31', '2', '获取机构树上的用户', '', 'sys/admin/findAdminUserListByOrganId', 'sys:admin:findAdminUserListByOrganId', null, '10', '2020-01-09 17:20:56', '2020-01-09 17:20:56', '0', '0');
INSERT INTO `db_menu` VALUES ('223', '0', '文章管理', 'ios-albums', null, null, null, '9000', '2021-05-29 13:14:41', '2021-05-29 13:15:35', '0', '0');
INSERT INTO `db_menu` VALUES ('224', '0', '基金管理', 'ios-archive', null, null, null, '8000', '2021-05-29 13:15:55', '2021-05-29 13:16:18', '0', '0');
INSERT INTO `db_menu` VALUES ('225', '223', '文章管理', 'md-beaker', 'sys/article', 'sys:article', null, '100', '2021-05-29 13:17:15', '2021-05-29 13:18:06', '0', '0');
INSERT INTO `db_menu` VALUES ('226', '223', '文章树管理', 'md-bookmarks', 'sys/articleMenu', 'sys:articleMenu', null, '80', '2021-05-29 13:17:43', '2021-05-29 13:18:20', '0', '0');
INSERT INTO `db_menu` VALUES ('227', '224', '我的基金', 'md-calendar', 'sys/fundInfo', 'sys:fundInfo', null, '100', '2021-05-29 13:18:48', '2021-05-29 13:19:56', '0', '0');
INSERT INTO `db_menu` VALUES ('228', '224', '基金类型', 'md-briefcase', 'sys/fundType', 'sys:fundType', null, '80', '2021-05-29 13:19:36', '2021-05-29 13:20:14', '0', '0');
INSERT INTO `db_menu` VALUES ('229', '225', '文档', null, 'sys/article/markdown', 'sys:article:markdown', null, '100', '2021-05-29 13:26:26', '2021-05-29 13:26:26', '0', '0');
INSERT INTO `db_menu` VALUES ('230', '225', '添加', null, 'sys/article/addArticle', 'sys:article:addArticle', null, '95', '2021-05-29 13:27:23', '2021-05-29 13:29:46', '0', '0');
INSERT INTO `db_menu` VALUES ('231', '225', '查询', null, 'sys/article/findArticleList', 'sys:article:findArticleList', null, '90', '2021-05-29 13:28:02', '2021-05-29 13:28:02', '0', '0');
INSERT INTO `db_menu` VALUES ('232', '225', '编辑', null, 'sys/article/updateArticle', 'sys:article:updateArticle', null, '80', '2021-05-29 13:28:47', '2021-05-29 13:28:47', '0', '0');
INSERT INTO `db_menu` VALUES ('233', '225', '更新文章内容', null, 'sys/article/updateArticleContent', 'sys:article:updateArticleContent', null, '70', '2021-05-29 13:29:30', '2021-05-29 13:29:30', '0', '0');
INSERT INTO `db_menu` VALUES ('234', '225', '删除', null, 'sys/article/deleteArticle', 'sys:article:deleteArticle', null, '60', '2021-05-29 13:30:18', '2021-05-29 13:30:18', '0', '0');
INSERT INTO `db_menu` VALUES ('235', '226', '添加', null, 'sys/articleMenu/addArticleMenu', 'sys:articleMenu:addArticleMenu', null, '100', '2021-05-29 13:31:32', '2021-05-29 13:31:32', '0', '0');
INSERT INTO `db_menu` VALUES ('236', '226', '查询文章', null, 'sys/articleMenu/findArticleMenuById', 'sys:articleMenu:findArticleMenuById', null, '90', '2021-05-29 13:32:05', '2021-05-29 13:32:05', '0', '0');
INSERT INTO `db_menu` VALUES ('237', '226', '编辑', null, 'sys/articleMenu/updateArticleMenu', 'sys:articleMenu:updateArticleMenu', null, '70', '2021-05-29 13:32:36', '2021-05-29 13:32:36', '0', '0');
INSERT INTO `db_menu` VALUES ('238', '226', '删除', null, 'sys/articleMenu/deleteArticleMenu', 'sys:articleMenu:deleteArticleMenu', null, '60', '2021-05-29 13:33:12', '2021-05-29 13:33:12', '0', '0');
INSERT INTO `db_menu` VALUES ('239', '226', '查询', null, 'sys/articleMenu/findArticleMenuToArticleList', 'sys:articleMenu:findArticleMenuToArticleList', null, '50', '2021-05-29 13:33:49', '2021-05-29 13:33:49', '0', '0');
INSERT INTO `db_menu` VALUES ('240', '226', '查询列表', null, 'sys/articleMenu/findArticleMenuToArticleListForHome', 'sys:articleMenu:findArticleMenuToArticleListForHome', null, '40', '2021-05-29 13:34:19', '2021-05-29 13:34:19', '0', '0');
INSERT INTO `db_menu` VALUES ('241', '226', '查询菜单', null, 'sys/articleMenu/findArticleMenuList', 'sys:articleMenu:findArticleMenuList', null, '30', '2021-05-29 13:34:56', '2021-05-29 13:34:56', '0', '0');
INSERT INTO `db_menu` VALUES ('242', '227', '添加', null, 'sys/fundInfo/addFundInfo', 'sys:fundInfo:addFundInfo', null, '100', '2021-05-29 13:35:30', '2021-05-29 13:35:30', '0', '0');
INSERT INTO `db_menu` VALUES ('243', '227', '编辑', null, 'sys/fundInfo/updateFundInfo', 'sys:fundInfo:updateFundInfo', null, '80', '2021-05-29 13:35:54', '2021-05-29 13:35:54', '0', '0');
INSERT INTO `db_menu` VALUES ('244', '227', '删除', null, 'sys/fundInfo/deleteFundInfo', 'sys:fundInfo:deleteFundInfo', null, '70', '2021-05-29 13:36:27', '2021-05-29 13:36:27', '0', '0');
INSERT INTO `db_menu` VALUES ('245', '227', '查询', null, 'sys/fundInfo/findFundInfoList', 'sys:fundInfo:findFundInfoList', null, '60', '2021-05-29 13:36:48', '2021-05-29 13:36:48', '0', '0');
INSERT INTO `db_menu` VALUES ('246', '228', '添加', null, 'sys/fundType/addFundType', 'sys:fundType:addFundType', null, '100', '2021-05-29 13:37:11', '2021-05-29 13:37:11', '0', '0');
INSERT INTO `db_menu` VALUES ('247', '228', '删除', null, 'sys/fundType/deleteFundType', 'sys:fundType:deleteFundType', null, '90', '2021-05-29 13:37:36', '2021-05-29 13:37:36', '0', '0');
INSERT INTO `db_menu` VALUES ('248', '228', '编辑', null, 'sys/fundType/updateFundType', 'sys:fundType:updateFundType', null, '70', '2021-05-29 13:38:04', '2021-05-29 13:38:04', '0', '0');
INSERT INTO `db_menu` VALUES ('249', '228', '查询', null, 'sys/fundType/findFundTypeList', 'sys:fundType:findFundTypeList', null, '50', '2021-05-29 13:38:35', '2021-05-29 13:38:35', '0', '0');
INSERT INTO `db_menu` VALUES ('250', '228', '查询类型', null, 'sys/fundType/findFundTypeListByType', 'sys:fundType:findFundTypeListByType', null, '40', '2021-05-29 13:46:54', '2021-05-29 13:46:54', '0', '0');

-- ----------------------------
-- Table structure for db_organ
-- ----------------------------
DROP TABLE IF EXISTS `db_organ`;
CREATE TABLE `db_organ` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `organ_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `remake` varchar(255) DEFAULT NULL COMMENT '备注',
  `sorting` int(11) DEFAULT NULL COMMENT '排序',
  `level` int(2) DEFAULT NULL COMMENT '代表层次关系: 0 表示总节点',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `delete_status` int(2) DEFAULT '0' COMMENT '是否删除 0 未删除 1 表示已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_organ_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of db_organ
-- ----------------------------
INSERT INTO `db_organ` VALUES ('1', null, '测评总部', null, '1', '0', '2019-06-26 12:45:54', '2020-02-03 20:37:34', '0');
INSERT INTO `db_organ` VALUES ('2', '1', '厦门分部', null, '2', '1', '2019-07-04 19:14:43', '2019-12-15 13:44:18', '0');
INSERT INTO `db_organ` VALUES ('3', '1', '深圳分部', null, '3', '1', '2019-07-04 19:22:05', '2019-12-15 13:44:28', '0');
INSERT INTO `db_organ` VALUES ('7', '2', '运营部', '', '1', '2', '2019-07-25 23:21:56', '2019-07-25 23:21:56', '0');
INSERT INTO `db_organ` VALUES ('8', '3', '运营部', null, '1', '2', '2019-07-25 23:44:18', '2019-12-15 13:57:43', '0');
INSERT INTO `db_organ` VALUES ('9', '1', '河南分部', null, '5', '1', '2019-11-18 21:11:01', '2019-12-15 13:44:46', '0');
INSERT INTO `db_organ` VALUES ('10', '9', '运营部', null, '1', '2', '2019-11-18 21:11:15', '2019-11-18 21:11:15', '0');
INSERT INTO `db_organ` VALUES ('11', '1', '济南分部', null, '4', '1', '2019-11-18 21:11:38', '2019-12-15 13:44:39', '0');
INSERT INTO `db_organ` VALUES ('12', '11', '运营部', null, '1', '2', '2019-11-18 21:11:48', '2019-12-15 13:50:19', '0');
INSERT INTO `db_organ` VALUES ('13', '2', '商家', '', '2', '3', '2019-12-07 20:47:03', '2019-12-15 13:45:30', '0');
INSERT INTO `db_organ` VALUES ('14', '1', '上海总部', '', '1', '1', '2019-12-15 13:41:00', '2019-12-15 13:41:00', '0');
INSERT INTO `db_organ` VALUES ('15', '14', '商家', null, '2', '3', '2019-12-15 13:44:08', '2019-12-15 13:44:08', '0');
INSERT INTO `db_organ` VALUES ('16', '14', '运营部', null, '1', '2', '2019-12-15 13:49:23', '2019-12-15 13:49:23', '0');
INSERT INTO `db_organ` VALUES ('17', '3', '商家', null, '2', '3', '2019-12-15 13:49:44', '2019-12-15 13:49:44', '0');
INSERT INTO `db_organ` VALUES ('18', '11', '商家', null, '2', '3', '2019-12-15 13:50:10', '2019-12-15 13:50:10', '0');
INSERT INTO `db_organ` VALUES ('19', '9', '商家', null, '2', '3', '2019-12-15 13:50:31', '2019-12-15 13:50:31', '0');
INSERT INTO `db_organ` VALUES ('20', '1', '驴妈妈分部', null, '99', '1', '2020-01-06 14:47:56', '2020-01-06 14:47:56', '0');
INSERT INTO `db_organ` VALUES ('21', '20', '运营部', null, '1', '2', '2020-01-06 14:48:29', '2020-01-06 14:48:29', '0');
INSERT INTO `db_organ` VALUES ('22', '20', '商家', null, '2', '3', '2020-01-06 14:48:39', '2020-01-06 14:48:39', '0');
INSERT INTO `db_organ` VALUES ('23', '16', '2332', '232', '22', null, '2020-10-10 22:13:32', '2020-10-10 22:13:32', '0');

-- ----------------------------
-- Table structure for db_role
-- ----------------------------
DROP TABLE IF EXISTS `db_role`;
CREATE TABLE `db_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_name` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '角色名称',
  `remark` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '角色备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `delete_status` int(2) DEFAULT '0' COMMENT '是否删除 0 未删除 1表示已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `db_role_ID_index` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='管理端角色表';

-- ----------------------------
-- Records of db_role
-- ----------------------------
INSERT INTO `db_role` VALUES ('1', '超级管理员', '管理员测试', '2019-10-03 02:10:27', '2019-10-03 02:10:24', '0');
INSERT INTO `db_role` VALUES ('2', '普通管理员', null, '2019-10-03 02:10:29', '2020-07-12 12:22:06', '0');

-- ----------------------------
-- Table structure for db_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `db_role_menu`;
CREATE TABLE `db_role_menu` (
  `menu_id` int(11) NOT NULL,
  `role_id` int(20) NOT NULL,
  PRIMARY KEY (`menu_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_role_menu
-- ----------------------------
INSERT INTO `db_role_menu` VALUES ('1', '1');
INSERT INTO `db_role_menu` VALUES ('1', '2');
INSERT INTO `db_role_menu` VALUES ('2', '1');
INSERT INTO `db_role_menu` VALUES ('2', '2');
INSERT INTO `db_role_menu` VALUES ('3', '1');
INSERT INTO `db_role_menu` VALUES ('3', '2');
INSERT INTO `db_role_menu` VALUES ('4', '1');
INSERT INTO `db_role_menu` VALUES ('4', '2');
INSERT INTO `db_role_menu` VALUES ('5', '1');
INSERT INTO `db_role_menu` VALUES ('5', '2');
INSERT INTO `db_role_menu` VALUES ('6', '1');
INSERT INTO `db_role_menu` VALUES ('6', '2');
INSERT INTO `db_role_menu` VALUES ('7', '1');
INSERT INTO `db_role_menu` VALUES ('7', '2');
INSERT INTO `db_role_menu` VALUES ('8', '1');
INSERT INTO `db_role_menu` VALUES ('8', '2');
INSERT INTO `db_role_menu` VALUES ('9', '1');
INSERT INTO `db_role_menu` VALUES ('9', '2');
INSERT INTO `db_role_menu` VALUES ('10', '1');
INSERT INTO `db_role_menu` VALUES ('10', '2');
INSERT INTO `db_role_menu` VALUES ('11', '1');
INSERT INTO `db_role_menu` VALUES ('11', '2');
INSERT INTO `db_role_menu` VALUES ('12', '1');
INSERT INTO `db_role_menu` VALUES ('12', '2');
INSERT INTO `db_role_menu` VALUES ('13', '1');
INSERT INTO `db_role_menu` VALUES ('13', '2');
INSERT INTO `db_role_menu` VALUES ('14', '1');
INSERT INTO `db_role_menu` VALUES ('14', '2');
INSERT INTO `db_role_menu` VALUES ('15', '1');
INSERT INTO `db_role_menu` VALUES ('15', '2');
INSERT INTO `db_role_menu` VALUES ('16', '1');
INSERT INTO `db_role_menu` VALUES ('16', '2');
INSERT INTO `db_role_menu` VALUES ('17', '1');
INSERT INTO `db_role_menu` VALUES ('17', '2');
INSERT INTO `db_role_menu` VALUES ('18', '1');
INSERT INTO `db_role_menu` VALUES ('18', '2');
INSERT INTO `db_role_menu` VALUES ('19', '1');
INSERT INTO `db_role_menu` VALUES ('19', '2');
INSERT INTO `db_role_menu` VALUES ('20', '1');
INSERT INTO `db_role_menu` VALUES ('20', '2');
INSERT INTO `db_role_menu` VALUES ('21', '1');
INSERT INTO `db_role_menu` VALUES ('21', '2');
INSERT INTO `db_role_menu` VALUES ('22', '1');
INSERT INTO `db_role_menu` VALUES ('22', '2');
INSERT INTO `db_role_menu` VALUES ('23', '1');
INSERT INTO `db_role_menu` VALUES ('23', '2');
INSERT INTO `db_role_menu` VALUES ('24', '1');
INSERT INTO `db_role_menu` VALUES ('24', '2');
INSERT INTO `db_role_menu` VALUES ('25', '1');
INSERT INTO `db_role_menu` VALUES ('25', '2');
INSERT INTO `db_role_menu` VALUES ('26', '1');
INSERT INTO `db_role_menu` VALUES ('26', '2');
INSERT INTO `db_role_menu` VALUES ('27', '1');
INSERT INTO `db_role_menu` VALUES ('27', '2');
INSERT INTO `db_role_menu` VALUES ('28', '1');
INSERT INTO `db_role_menu` VALUES ('28', '2');
INSERT INTO `db_role_menu` VALUES ('29', '1');
INSERT INTO `db_role_menu` VALUES ('29', '2');
INSERT INTO `db_role_menu` VALUES ('30', '1');
INSERT INTO `db_role_menu` VALUES ('30', '2');
INSERT INTO `db_role_menu` VALUES ('31', '1');
INSERT INTO `db_role_menu` VALUES ('31', '2');
INSERT INTO `db_role_menu` VALUES ('32', '1');
INSERT INTO `db_role_menu` VALUES ('32', '2');
INSERT INTO `db_role_menu` VALUES ('33', '1');
INSERT INTO `db_role_menu` VALUES ('33', '2');
INSERT INTO `db_role_menu` VALUES ('216', '2');
INSERT INTO `db_role_menu` VALUES ('217', '2');
INSERT INTO `db_role_menu` VALUES ('218', '2');
INSERT INTO `db_role_menu` VALUES ('219', '2');
INSERT INTO `db_role_menu` VALUES ('220', '2');
INSERT INTO `db_role_menu` VALUES ('221', '2');
INSERT INTO `db_role_menu` VALUES ('222', '2');
INSERT INTO `db_role_menu` VALUES ('223', '2');
INSERT INTO `db_role_menu` VALUES ('224', '2');
INSERT INTO `db_role_menu` VALUES ('225', '2');
INSERT INTO `db_role_menu` VALUES ('226', '2');
INSERT INTO `db_role_menu` VALUES ('227', '2');
INSERT INTO `db_role_menu` VALUES ('228', '2');
INSERT INTO `db_role_menu` VALUES ('229', '2');
INSERT INTO `db_role_menu` VALUES ('230', '2');
INSERT INTO `db_role_menu` VALUES ('231', '2');
INSERT INTO `db_role_menu` VALUES ('232', '2');
INSERT INTO `db_role_menu` VALUES ('233', '2');
INSERT INTO `db_role_menu` VALUES ('234', '2');
INSERT INTO `db_role_menu` VALUES ('235', '2');
INSERT INTO `db_role_menu` VALUES ('236', '2');
INSERT INTO `db_role_menu` VALUES ('237', '2');
INSERT INTO `db_role_menu` VALUES ('238', '2');
INSERT INTO `db_role_menu` VALUES ('239', '2');
INSERT INTO `db_role_menu` VALUES ('240', '2');
INSERT INTO `db_role_menu` VALUES ('241', '2');
INSERT INTO `db_role_menu` VALUES ('242', '2');
INSERT INTO `db_role_menu` VALUES ('243', '2');
INSERT INTO `db_role_menu` VALUES ('244', '2');
INSERT INTO `db_role_menu` VALUES ('245', '2');
INSERT INTO `db_role_menu` VALUES ('246', '2');
INSERT INTO `db_role_menu` VALUES ('247', '2');
INSERT INTO `db_role_menu` VALUES ('248', '2');
INSERT INTO `db_role_menu` VALUES ('249', '2');
INSERT INTO `db_role_menu` VALUES ('250', '2');

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `url` varchar(500) DEFAULT NULL COMMENT 'url链接',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sorting` int(2) DEFAULT NULL COMMENT '排序',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `delete_status` int(11) DEFAULT '0' COMMENT '是否删除: 0 未删除 1 已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_article_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('1', '1', '1.java概论', '2021-05-29-1622267868305.md', '1.Java基础-1.java概论', '1', '2', '2', '2021-05-29 13:57:48', '2021-06-05 15:46:18', '0');
INSERT INTO `t_article` VALUES ('2', '1', '2.基本语法', '2021-05-29-1622275960503.md', '1.Java基础-2.基本语法', '2', '2', '2', '2021-05-29 16:12:40', '2021-06-05 15:46:21', '0');
INSERT INTO `t_article` VALUES ('3', '1', '3.面向对象-基础', '2021-06-02-1622599441833.md', '1.Java基础-3.面向对象-基础', '3', '2', '2', '2021-06-02 10:04:01', '2021-06-05 15:46:24', '0');
INSERT INTO `t_article` VALUES ('4', '1', '4.面向对象-封装性', '2021-06-02-1622610279147.md', '1.Java基础-4.面向对象-封装性', '4', '2', '2', '2021-06-02 13:04:39', '2021-06-05 15:46:27', '0');
INSERT INTO `t_article` VALUES ('5', '1', '5.面向对象-继承性', '2021-06-03-1622682347966.md', '1.Java基础-5.面向对象-继承性', '5', '2', '2', '2021-06-03 09:05:47', '2021-06-05 15:46:31', '0');
INSERT INTO `t_article` VALUES ('6', '1', '6.面向对象-多态性', '2021-06-03-1622683209350.md', '1.Java基础-6.面向对象-多态性', '6', '2', '2', '2021-06-03 09:20:09', '2021-06-05 15:46:33', '0');
INSERT INTO `t_article` VALUES ('7', '1', '7.面向对象-抽象类', '2021-06-03-1622691448395.md', '1.Java基础-7.面向对象-抽象类', '7', '2', '2', '2021-06-03 11:37:28', '2021-06-05 15:46:36', '0');
INSERT INTO `t_article` VALUES ('8', '1', '8.异常类', '2021-06-03-1622698371926.md', '1.Java基础-8.异常类', '8', '2', '2', '2021-06-03 13:32:51', '2021-06-05 15:46:39', '0');
INSERT INTO `t_article` VALUES ('9', '1', '9.多线程', '2021-06-03-1622711133334.md', '1.Java基础-9.多线程', '9', '2', '2', '2021-06-03 17:05:33', '2021-06-05 15:46:43', '0');
INSERT INTO `t_article` VALUES ('10', '1', '10.常用类', '2021-06-04-1622767703564.md', '1.Java基础-10.常用类', '10', '2', '2', '2021-06-04 08:48:23', '2021-06-05 15:46:46', '0');
INSERT INTO `t_article` VALUES ('11', '6', '设计模式(1)--Prototype--原型模式--创建型', '2021-06-06-1622936138990.md', '设计模式(1)--Prototype--原型模式--创建型', '1', '2', '2', '2021-06-06 07:35:39', '2021-06-06 07:35:39', '0');
INSERT INTO `t_article` VALUES ('12', '6', '设计模式(2)--Singleton--单例模式--创建型', '2021-06-06-1622936984424.md', '设计模式(2)--Singleton--单例模式--创建型', '2', '2', '2', '2021-06-06 07:49:44', '2021-06-06 07:49:44', '0');
INSERT INTO `t_article` VALUES ('13', '6', '设计模式(3)--FactoryMethod--(2)工厂方法模式--创建型', '2021-06-06-1622937580615.md', '设计模式(3)--FactoryMethod--(2)工厂方法模式--创建型', '4', '2', '2', '2021-06-06 07:59:40', '2021-06-06 08:09:06', '0');
INSERT INTO `t_article` VALUES ('14', '6', '设计模式(3)--SimpleFactory--(1)简单工厂模式--创建型', '2021-06-06-1622938155203.md', '设计模式(3)--SimpleFactory--(1)简单工厂模式--创建型', '3', '2', '2', '2021-06-06 08:09:15', '2021-06-06 08:09:15', '0');
INSERT INTO `t_article` VALUES ('15', '6', '设计模式(4)--AbstractFactory--抽象工厂模式--创建型', '2021-06-06-1622939249194.md', '设计模式(4)--AbstractFactory--抽象工厂模式--创建型', '5', '2', '2', '2021-06-06 08:27:29', '2021-06-06 08:27:29', '0');
INSERT INTO `t_article` VALUES ('16', '6', '设计模式(5)--Builder--建造模式--创建型', '2021-06-06-1622939495348.md', '设计模式(5)--Builder--建造模式--创建型', '6', '2', '2', '2021-06-06 08:31:35', '2021-06-06 08:31:35', '0');
INSERT INTO `t_article` VALUES ('17', '6', '设计模式(6)--Adapter--适配器模式--结构型', '2021-06-06-1622939843719.md', '设计模式(6)--Adapter--适配器模式--结构型', '7', '2', '2', '2021-06-06 08:37:23', '2021-06-06 08:37:23', '0');
INSERT INTO `t_article` VALUES ('18', '6', '设计模式(7)--Bridge--桥接模式--结构型', '2021-06-06-1622941006286.md', '设计模式(7)--Bridge--桥接模式--结构型', '8', '2', '2', '2021-06-06 08:56:46', '2021-06-06 08:56:46', '0');
INSERT INTO `t_article` VALUES ('19', '6', '设计模式(8)--Decorator--装饰器模式--结构型', '2021-06-06-1622941037636.md', '设计模式(8)--Decorator--装饰器模式--结构型', '9', '2', '2', '2021-06-06 08:57:17', '2021-06-06 08:57:17', '0');
INSERT INTO `t_article` VALUES ('20', '6', '设计模式(9)--Composite--组合模式--结构型', '2021-06-06-1622941051769.md', '设计模式(9)--Composite--组合模式--结构型', '10', '2', '2', '2021-06-06 08:57:31', '2021-06-06 08:57:31', '0');
INSERT INTO `t_article` VALUES ('21', '6', '设计模式(10)--Facade--外观模式--结构型', '2021-06-06-1622941064835.md', '设计模式(10)--Facade--外观模式--结构型', '11', '2', '2', '2021-06-06 08:57:44', '2021-06-06 08:57:44', '0');
INSERT INTO `t_article` VALUES ('22', '6', '设计模式(11)--Flyweight--享元模式--结构型', '2021-06-06-1622941076215.md', '设计模式(11)--Flyweight--享元模式--结构型', '12', '2', '2', '2021-06-06 08:57:56', '2021-06-06 08:57:56', '0');
INSERT INTO `t_article` VALUES ('23', '6', '设计模式(12)--Proxy--代理模式--结构型', '2021-06-06-1622941086568.md', '设计模式(12)--Proxy--代理模式--结构型', '13', '2', '2', '2021-06-06 08:58:06', '2021-06-06 08:58:06', '0');
INSERT INTO `t_article` VALUES ('24', '6', '设计模式(13)--Chain of Responsibility--责任链模式--行为型', '2021-06-06-1622941122591.md', '设计模式(13)--Chain of Responsibility--责任链模式--行为型', '14', '2', '2', '2021-06-06 08:58:42', '2021-06-06 08:58:42', '0');
INSERT INTO `t_article` VALUES ('25', '6', '设计模式(14)--Command--命令模式--行为型', '2021-06-06-1622941137097.md', '设计模式(14)--Command--命令模式--行为型', '15', '2', '2', '2021-06-06 08:58:57', '2021-06-06 08:58:57', '0');
INSERT INTO `t_article` VALUES ('26', '6', '设计模式(15)--Interpreter--解释器模式--行为型', '2021-06-06-1622941149764.md', '设计模式(15)--Interpreter--解释器模式--行为型', '16', '2', '2', '2021-06-06 08:59:09', '2021-06-06 08:59:09', '0');
INSERT INTO `t_article` VALUES ('27', '6', '设计模式(16)--Iterator--迭代器模式--行为型', '2021-06-06-1622941160725.md', '设计模式(16)--Iterator--迭代器模式--行为型', '17', '2', '2', '2021-06-06 08:59:20', '2021-06-06 08:59:20', '0');
INSERT INTO `t_article` VALUES ('28', '6', '设计模式(18)--Memento--备忘录模式--行为型', '2021-06-06-1622941171211.md', '设计模式(18)--Memento--备忘录模式--行为型', '19', '2', '2', '2021-06-06 08:59:31', '2021-06-06 08:59:52', '0');
INSERT INTO `t_article` VALUES ('29', '6', '设计模式(17)--Mediator--中介者模式--行为型', '2021-06-06-1622941187000.md', '设计模式(17)--Mediator--中介者模式--行为型', '18', '2', '2', '2021-06-06 08:59:47', '2021-06-06 08:59:47', '0');
INSERT INTO `t_article` VALUES ('30', '6', '设计模式(19)--Observer--观察者模式--行为型', '2021-06-06-1622941208005.md', '设计模式(19)--Observer--观察者模式--行为型', '20', '2', '2', '2021-06-06 09:00:08', '2021-06-06 09:00:08', '0');
INSERT INTO `t_article` VALUES ('31', '6', '设计模式(20)--State--状态模式--行为型', '2021-06-06-1622941222856.md', '设计模式(20)--State--状态模式--行为型', '21', '2', '2', '2021-06-06 09:00:22', '2021-06-06 09:00:22', '0');
INSERT INTO `t_article` VALUES ('32', '6', '设计模式(21)--Strategy--策略模式--行为型', '2021-06-06-1622941239475.md', '设计模式(21)--Strategy--策略模式--行为型', '22', '2', '2', '2021-06-06 09:00:39', '2021-06-06 09:00:39', '0');
INSERT INTO `t_article` VALUES ('33', '6', '设计模式(22)--Template Method--模板方法模式--行为型', '2021-06-06-1622941250805.md', '设计模式(22)--Template Method--模板方法模式--行为型', '23', '2', '2', '2021-06-06 09:00:50', '2021-06-21 13:08:58', '0');
INSERT INTO `t_article` VALUES ('34', '6', '设计模式(23)--Visitor--访问者模式--行为型', '2021-06-06-1622941267050.md', '设计模式(23)--Visitor--访问者模式--行为型', '24', '2', '2', '2021-06-06 09:01:07', '2021-06-06 09:01:07', '0');
INSERT INTO `t_article` VALUES ('35', '7', '设计模式原则(1)--Single Responsibility Principle(SRP)--单一职责原则', '2021-06-06-1622941382118.md', '设计模式原则(1)--Single Responsibility Principle(SRP)--单一职责原则', '1', '2', '2', '2021-06-06 09:03:02', '2021-06-06 09:03:02', '0');
INSERT INTO `t_article` VALUES ('36', '7', '设计模式原则(2)--Liskov Substitution Principle(LSP)--里氏替换原则', '2021-06-06-1622941400447.md', '设计模式原则(2)--Liskov Substitution Principle(LSP)--里氏替换原则', '2', '2', '2', '2021-06-06 09:03:20', '2021-06-06 09:03:20', '0');
INSERT INTO `t_article` VALUES ('37', '7', '设计模式原则(3)--Dependency Inversion  Principle(DIP)--依赖倒转原则', '2021-06-06-1622941412455.md', '设计模式原则(3)--Dependency Inversion  Principle(DIP)--依赖倒转原则', '3', '2', '2', '2021-06-06 09:03:32', '2021-06-06 09:03:32', '0');
INSERT INTO `t_article` VALUES ('38', '7', '设计模式原则(4)--Interface  Segregation Principle(ISP)--接口隔离原则', '2021-06-06-1622941424910.md', '设计模式原则(4)--Interface  Segregation Principle(ISP)--接口隔离原则', '4', '2', '2', '2021-06-06 09:03:44', '2021-06-06 09:03:44', '0');
INSERT INTO `t_article` VALUES ('39', '7', '设计模式原则(5)--Law of  Demeter(LoD)--迪米特法则', '2021-06-06-1622941435482.md', '设计模式原则(5)--Law of  Demeter(LoD)--迪米特法则', '5', '2', '2', '2021-06-06 09:03:55', '2021-06-06 09:03:55', '0');
INSERT INTO `t_article` VALUES ('40', '7', '设计模式原则(6)--Open-Closed Principle(OCP)--开闭原则', '2021-06-06-1622941455493.md', '设计模式原则(6)--Open-Closed Principle(OCP)--开闭原则', '6', '2', '2', '2021-06-06 09:04:15', '2021-06-06 09:04:15', '0');
INSERT INTO `t_article` VALUES ('41', '7', '设计模式原则(7)--Composition&AggregationPrinciple CARP--合成&聚合复用原则', '2021-06-06-1622941466930.md', '设计模式原则(7)--Composition&AggregationPrinciple CARP--合成&聚合复用原则', '7', '2', '2', '2021-06-06 09:04:26', '2021-06-06 09:04:26', '0');
INSERT INTO `t_article` VALUES ('42', '7', '面向对象原则--高内聚、低耦合、多聚合、少继承', '2021-06-06-1622941483582.md', '面向对象原则--高内聚、低耦合、多聚合、少继承', '8', '2', '2', '2021-06-06 09:04:43', '2021-06-06 09:04:43', '0');
INSERT INTO `t_article` VALUES ('43', '1', '11.集合类', '2021-06-07-1623023855036.md', '1.Java基础-11.集合类', '11', '2', '2', '2021-06-07 07:57:35', '2021-06-07 07:57:35', '0');
INSERT INTO `t_article` VALUES ('44', '1', '12.泛型', '2021-06-08-1623113529956.md', '1.Java基础-12.泛型', '12', '2', '2', '2021-06-08 08:52:09', '2021-06-08 08:52:09', '0');
INSERT INTO `t_article` VALUES ('45', '1', '13.IO流', '2021-06-08-1623128607577.md', '1.Java基础-13.IO流', '13', '2', '2', '2021-06-08 13:03:27', '2021-06-08 13:03:27', '0');
INSERT INTO `t_article` VALUES ('46', '1', '14.网络编程', '2021-06-09-1623208136841.md', '1.Java基础-14.网络编程', '14', '2', '2', '2021-06-09 11:08:56', '2021-06-09 11:08:56', '0');
INSERT INTO `t_article` VALUES ('47', '1', '15.Java 反射机制', '2021-06-09-1623216727198.md', '1.Java基础-15.Java 反射机制', '15', '2', '2', '2021-06-09 13:32:07', '2021-06-09 13:32:07', '0');
INSERT INTO `t_article` VALUES ('48', '1', '16.Java 8 新特性', '2021-06-09-1623222500474.md', '1.Java基础-16.Java 8 新特性', '16', '2', '2', '2021-06-09 15:08:20', '2021-06-09 15:08:20', '0');
INSERT INTO `t_article` VALUES ('49', '1', '17.Java 9 新特性', '2021-06-09-1623226236823.md', '1.Java基础-17.Java 9 新特性', '17', '2', '2', '2021-06-09 16:10:36', '2021-06-09 16:10:36', '0');
INSERT INTO `t_article` VALUES ('50', '1', '18.Java 10 新特性', '2021-06-10-1623288964884.md', '1.Java基础-18.Java 10 新特性', '18', '2', '2', '2021-06-10 09:36:04', '2021-06-10 09:36:04', '0');
INSERT INTO `t_article` VALUES ('51', '1', '19.Java 11 新特性', '2021-06-10-1623289593330.md', '1.Java基础-19.Java 11 新特性', '19', '2', '2', '2021-06-10 09:46:33', '2021-06-10 09:46:33', '0');
INSERT INTO `t_article` VALUES ('52', '1', '20.Java 5-11 新特性总结', '2021-06-15-1623718986368.md', '1.Java基础-20.Java 5-11 新特性总结', '20', '2', '2', '2021-06-15 09:03:06', '2021-06-15 09:03:06', '0');
INSERT INTO `t_article` VALUES ('53', '1', '21.Java JUC 多线程', '2021-06-17-1623908833179.md', '1.Java基础-21.Java JUC 多线程', '21', '2', '2', '2021-06-17 13:47:13', '2021-06-17 13:47:13', '0');
INSERT INTO `t_article` VALUES ('54', '1', '22.Java NIO', '2021-06-17-1623909413788.md', '1.Java基础-22.Java NIO', '22', '2', '2', '2021-06-17 13:56:53', '2021-06-17 13:56:53', '0');
INSERT INTO `t_article` VALUES ('55', '1', '23.Java 12 新特性', '2021-06-17-1623910735660.md', '1.Java基础-23.Java 12 新特性', '23', '2', '2', '2021-06-17 14:18:55', '2021-06-17 14:18:55', '0');
INSERT INTO `t_article` VALUES ('56', '1', '24.Java 13新特性', '2021-06-17-1623913665871.md', '1.Java基础-24.Java 13新特性', '24', '2', '2', '2021-06-17 15:07:45', '2021-06-17 15:07:45', '0');
INSERT INTO `t_article` VALUES ('57', '3', '1.数组常用方法', '2021-06-17-1623915531408.md', '集合(1)--数组常用方法', '1', '2', '2', '2021-06-17 15:38:51', '2021-06-17 15:38:51', '0');
INSERT INTO `t_article` VALUES ('58', '3', '2.Collection--1.List--ArrayList', '2021-06-17-1623916014768.md', '集合(2)-Collection--1List--ArrayList', '2', '2', '2', '2021-06-17 15:46:54', '2021-06-17 15:49:03', '0');
INSERT INTO `t_article` VALUES ('59', '3', '3.Collection--2.List--Vector', '2021-06-17-1623937241526.md', '3.Collection--2.List--Vector', '3', '2', '2', '2021-06-17 21:40:41', '2021-06-17 21:40:41', '0');
INSERT INTO `t_article` VALUES ('60', '3', '4.Collection--3.List--LinkedList', '2021-06-17-1623938625481.md', '4.Collection--3.List--LinkedList', '4', '2', '2', '2021-06-17 22:03:45', '2021-06-17 22:03:45', '0');
INSERT INTO `t_article` VALUES ('61', '3', '5.Collection--4.List--Stack', '2021-06-17-1623938952997.md', '5.Collection--4.List--Stack', '5', '2', '2', '2021-06-17 22:09:13', '2021-06-17 22:09:13', '0');
INSERT INTO `t_article` VALUES ('62', '3', '6.Collection--1.Set--HashSet', '2021-06-17-1623940093633.md', '6.Collection--1.Set--HashSet', '6', '2', '2', '2021-06-17 22:28:13', '2021-06-17 22:28:13', '0');
INSERT INTO `t_article` VALUES ('63', '3', '7.Collection--2.Set--TreeSet', '2021-06-17-1623942402897.md', '7.Collection--2.Set--TreeSet', '7', '2', '2', '2021-06-17 23:06:42', '2021-06-17 23:06:42', '0');
INSERT INTO `t_article` VALUES ('64', '3', '8.Collection--3.Set--LinkedHashSet', '2021-06-17-1623943551642.md', '8.Collection--3.Set--LinkedHashSet', '8', '2', '2', '2021-06-17 23:25:51', '2021-06-17 23:25:51', '0');
INSERT INTO `t_article` VALUES ('65', '3', '9.Collection--Queue', '2021-06-17-1623943651258.md', '9.Collection--Queue', '9', '2', '2', '2021-06-17 23:27:31', '2021-06-17 23:27:31', '0');
INSERT INTO `t_article` VALUES ('66', '3', '10.Map--HashMap', '2021-06-17-1623943798411.md', '10.Map--HashMap', '10', '2', '2', '2021-06-17 23:29:58', '2021-06-17 23:29:58', '0');
INSERT INTO `t_article` VALUES ('67', '3', '11.Map--Hashtable', '2021-06-17-1623943943227.md', '11.Map--Hashtable', '11', '2', '2', '2021-06-17 23:32:23', '2021-06-17 23:32:23', '0');
INSERT INTO `t_article` VALUES ('68', '3', '12.Map--TreeMap', '2021-06-17-1623944154919.md', '12.Map--TreeMap', '12', '2', '2', '2021-06-17 23:35:54', '2021-06-17 23:35:54', '0');
INSERT INTO `t_article` VALUES ('69', '3', '13.Map--LinkedHashMap', '2021-06-17-1623944533718.md', '13.Map--LinkedHashMap', '13', '2', '2', '2021-06-17 23:42:13', '2021-06-17 23:42:13', '0');
INSERT INTO `t_article` VALUES ('70', '3', '14.工具类--Arrays', '2021-06-17-1623944705162.md', '14.工具类--Arrays', '14', '2', '2', '2021-06-17 23:45:05', '2021-06-17 23:45:05', '0');
INSERT INTO `t_article` VALUES ('71', '3', '15.工具类--Arrays--DualPivotQuicksort', '2021-06-17-1623944798526.md', '15.工具类--Arrays--DualPivotQuicksort', '15', '2', '2', '2021-06-17 23:46:38', '2021-06-17 23:46:38', '0');
INSERT INTO `t_article` VALUES ('72', '3', '16.工具类--Collections', '2021-06-17-1623944855290.md', '16.工具类--Collections', '16', '2', '2', '2021-06-17 23:47:35', '2021-06-17 23:47:35', '0');
INSERT INTO `t_article` VALUES ('73', '4', '1.JVM-概述', '2021-06-18-1623946117640.md', '1.JVM-概述', '1', '2', '2', '2021-06-18 00:08:37', '2021-06-18 00:08:37', '0');
INSERT INTO `t_article` VALUES ('74', '4', '2.JVM-运行机制', '2021-06-20-1624187167269.md', '2.JVM-运行机制', '2', '2', '2', '2021-06-20 19:06:07', '2021-06-20 19:06:07', '0');
INSERT INTO `t_article` VALUES ('75', '4', '3.JVM-参数配置', '2021-06-21-1624255128888.md', '3.JVM-参数配置', '3', '2', '2', '2021-06-21 13:58:48', '2021-06-21 13:58:48', '0');
INSERT INTO `t_article` VALUES ('76', '4', '4.JVM-GC算法', '2021-06-21-1624255681311.md', '4.JVM-GC算法', '4', '2', '2', '2021-06-21 14:08:01', '2021-06-21 14:08:01', '0');
INSERT INTO `t_article` VALUES ('77', '4', '5.JVM-GC参数', '2021-06-21-1624256268442.md', '5.JVM-GC参数', '5', '2', '2', '2021-06-21 14:17:48', '2021-06-21 14:17:48', '0');
INSERT INTO `t_article` VALUES ('78', '4', '6.JVM-类装载器', '2021-06-21-1624260591616.md', '6.JVM-类装载器', '6', '2', '2', '2021-06-21 15:29:51', '2021-06-21 15:29:51', '0');
INSERT INTO `t_article` VALUES ('79', '4', '7.JVM-Java堆分析', '2021-06-21-1624261552792.md', '7.JVM-Java堆分析', '7', '2', '2', '2021-06-21 15:45:52', '2021-06-21 15:45:52', '0');
INSERT INTO `t_article` VALUES ('80', '5', '1.遍历算法-二叉树遍历', '2021-06-21-1624261646656.md', '1.遍历算法-二叉树遍历', '1', '2', '2', '2021-06-21 15:47:26', '2021-06-21 15:47:26', '0');
INSERT INTO `t_article` VALUES ('81', '5', '2.遍历算法-Java遍历方式', '2021-06-21-1624262662720.md', '2.遍历算法-Java遍历方式', '2', '2', '2', '2021-06-21 16:04:22', '2021-06-21 16:05:02', '0');
INSERT INTO `t_article` VALUES ('82', '5', '3.查找算法-1.Sequential search--顺序查找', '2021-06-22-1624324364927.md', '3.查找算法-1.Sequential search--顺序查找', '3', '2', '2', '2021-06-22 09:12:44', '2021-06-22 09:12:44', '0');
INSERT INTO `t_article` VALUES ('83', '5', '4.查找算法-2.Binary 二分查找', '2021-06-22-1624324764269.md', '4.查找算法-2.Binary 二分查找', '4', '2', '2', '2021-06-22 09:19:24', '2021-06-22 09:19:24', '0');
INSERT INTO `t_article` VALUES ('84', '5', '5.查找算法-3.Interpolation search--插值查找', '2021-06-22-1624324938185.md', '5.查找算法-3.Interpolation search--插值查找', '5', '2', '2', '2021-06-22 09:22:18', '2021-06-22 09:22:18', '0');
INSERT INTO `t_article` VALUES ('85', '5', '6.查找算法-4.Fibonacci search--斐波那契查找', '2021-06-22-1624325499560.md', '6.查找算法-4.Fibonacci search--斐波那契查找', '6', '2', '2', '2021-06-22 09:31:39', '2021-06-22 09:31:39', '0');
INSERT INTO `t_article` VALUES ('86', '5', '7.查找算法-5.Tree table lookup--树表查找', '2021-06-22-1624326124193.md', '7.查找算法-5.Tree table lookup--树表查找', '7', '2', '2', '2021-06-22 09:42:04', '2021-06-22 09:42:04', '0');
INSERT INTO `t_article` VALUES ('87', '5', '8.查找算法-6.Block search--分块查找', '2021-06-22-1624343235102.md', '8.查找算法-6.Block search--分块查找', '8', '2', '2', '2021-06-22 14:27:15', '2021-06-22 14:27:15', '0');
INSERT INTO `t_article` VALUES ('88', '5', '9.查找算法-7.Hash search--哈希查找', '2021-06-22-1624343380103.md', '9.查找算法-7.Hash search--哈希查找', '9', '2', '2', '2021-06-22 14:29:40', '2021-06-22 14:29:40', '0');
INSERT INTO `t_article` VALUES ('89', '5', '10.查找算法总结', '2021-06-23-1624417119866.md', '10.查找算法总结', '10', '2', '2', '2021-06-23 10:58:39', '2021-06-23 10:58:39', '0');
INSERT INTO `t_article` VALUES ('90', '5', '11.排序算法-1.插入排序-1.直接插入排序', '2021-06-23-1624417414308.md', '11.排序算法-1.插入排序-1.直接插入排序', '11', '2', '2', '2021-06-23 11:03:34', '2021-06-23 11:03:34', '0');
INSERT INTO `t_article` VALUES ('91', '5', '12.排序算法-2.插入排序-2.折半(二分)插入排序', '2021-06-23-1624417819156.md', '12.排序算法-2.插入排序-2.折半(二分)插入排序', '12', '2', '2', '2021-06-23 11:10:19', '2021-06-23 11:10:19', '0');
INSERT INTO `t_article` VALUES ('92', '5', '13.排序算法-3.插入排序-3.Shell Sort--希尔排序', '2021-06-23-1624418057212.md', '13.排序算法-3.插入排序-3.Shell Sort--希尔排序', '13', '2', '2', '2021-06-23 11:14:17', '2021-06-23 11:14:17', '0');
INSERT INTO `t_article` VALUES ('93', '5', '14.排序算法-4.选择排序-1.简单(直接)选择排序', '2021-06-23-1624427812732.md', '14.排序算法-4.选择排序-1.简单(直接)选择排序', '14', '2', '2', '2021-06-23 13:56:52', '2021-06-23 13:56:52', '0');

-- ----------------------------
-- Table structure for t_article_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_article_menu`;
CREATE TABLE `t_article_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sorting` int(2) DEFAULT NULL COMMENT '排序',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `delete_status` int(11) DEFAULT '0' COMMENT '是否删除: 0 未删除 1 已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_article_menu_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article_menu
-- ----------------------------
INSERT INTO `t_article_menu` VALUES ('1', '1.1-基础阶段-JavaSE-NIO-JUC', '0', 'Java基础', '1000', '2', '2', '2021-05-29 13:56:01', '2021-06-23 13:54:50', '0');
INSERT INTO `t_article_menu` VALUES ('2', 'JavaWeb 简介', '1', null, '1000', '2', null, '2021-05-29 13:56:59', '2021-05-29 13:57:28', '1');
INSERT INTO `t_article_menu` VALUES ('3', '1.2-基础阶段-DataStructure', '0', '数据结构', '1010', '2', '2', '2021-06-02 13:59:11', '2021-06-05 12:47:42', '0');
INSERT INTO `t_article_menu` VALUES ('4', '1.3-基础阶段-JVM', '0', 'JVM', '1020', '2', '2', '2021-06-02 14:01:02', '2021-06-02 14:01:53', '0');
INSERT INTO `t_article_menu` VALUES ('5', '1.4-基础阶段-Algorithm', '0', '算法', '1030', '2', '2', '2021-06-02 14:04:02', '2021-06-05 12:47:48', '0');
INSERT INTO `t_article_menu` VALUES ('6', '1.5-基础阶段-DesignPatterns', '0', '设计模式', '1040', '2', '2', '2021-06-02 14:05:22', '2021-06-06 09:02:03', '0');
INSERT INTO `t_article_menu` VALUES ('7', '1.6-基础阶段-DesignPatternsPrinciples', '0', '设计模式原则', '1050', '2', '2', '2021-06-06 09:01:56', '2021-06-06 09:02:33', '0');

-- ----------------------------
-- Table structure for t_fund_info
-- ----------------------------
DROP TABLE IF EXISTS `t_fund_info`;
CREATE TABLE `t_fund_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fund_type_company_id` int(11) DEFAULT NULL COMMENT '基金公司',
  `fund_type_section_id` int(11) DEFAULT NULL COMMENT '基金版块',
  `fund_type_type_id` int(11) DEFAULT NULL COMMENT '基金类型',
  `fund_type_purchase_id` int(11) DEFAULT NULL COMMENT '购买类型',
  `fund_name` varchar(255) DEFAULT NULL COMMENT '基金名称',
  `fund_code` varchar(255) DEFAULT NULL COMMENT '基金代码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `fund_scale` decimal(10,2) DEFAULT NULL COMMENT '基金规模(亿)',
  `holding_yield` decimal(10,4) DEFAULT NULL COMMENT '持有收益率',
  `principal` decimal(10,2) DEFAULT NULL COMMENT '本金',
  `profit_loss` decimal(10,2) DEFAULT NULL COMMENT '盈亏',
  `recommendation_level` int(4) DEFAULT NULL COMMENT '购买等级几颗星',
  `create_user_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `delete_status` int(11) DEFAULT '0' COMMENT '是否删除: 0 未删除 1 已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_fund_info_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_fund_info
-- ----------------------------
INSERT INTO `t_fund_info` VALUES ('1', '3', '2', '1', '4', '嘉实恒生港股通新经济指数', '501311', '港基,长期看好', '12.02', '-0.0062', '4550.00', '-27.01', '10', '2', '2', '2021-05-29 13:55:22', '2021-05-29 13:55:22', '0');

-- ----------------------------
-- Table structure for t_fund_type
-- ----------------------------
DROP TABLE IF EXISTS `t_fund_type`;
CREATE TABLE `t_fund_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sorting` int(2) DEFAULT NULL COMMENT '排序',
  `type` int(2) DEFAULT NULL COMMENT '1基金类型,2基金版块 3 基金公司',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `delete_status` int(11) DEFAULT '0' COMMENT '是否删除: 0 未删除 1 已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_fund_type_id_uindex` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_fund_type
-- ----------------------------
INSERT INTO `t_fund_type` VALUES ('1', '指数型', '指数型', '1000', '1', '2', '2', '2021-05-29 13:43:06', '2021-05-29 13:43:06', '0');
INSERT INTO `t_fund_type` VALUES ('2', '港基', '港基', '1000', '2', '2', '2', '2021-05-29 13:43:26', '2021-05-29 13:43:26', '0');
INSERT INTO `t_fund_type` VALUES ('3', '嘉实基金', '嘉实基金管理有限公司', '1000', '3', '2', '2', '2021-05-29 13:44:00', '2021-05-29 13:44:00', '0');
INSERT INTO `t_fund_type` VALUES ('4', '已购买', '最高等级', '1000', '4', '2', '2', '2021-05-29 13:44:40', '2021-05-29 13:44:40', '0');
