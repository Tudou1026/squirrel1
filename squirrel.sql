/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : squirrel

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 19/08/2018 00:10:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for catelog
-- ----------------------------
DROP TABLE IF EXISTS `catelog`;
CREATE TABLE `catelog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) DEFAULT NULL COMMENT '分类名',
  `number` int(11) DEFAULT '0' COMMENT '该分类下的商品数量',
  `status` tinyint(10) DEFAULT '0' COMMENT '分类状态，0正常，1暂用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of catelog
-- ----------------------------
BEGIN;
INSERT INTO `catelog` VALUES (1, '闲置数码', 3, 1);
INSERT INTO `catelog` VALUES (2, '校园代步', 2, 1);
INSERT INTO `catelog` VALUES (3, '电器日用', 4, 1);
INSERT INTO `catelog` VALUES (4, '图书教材', 1, 1);
INSERT INTO `catelog` VALUES (5, '美妆衣物', 4, 1);
INSERT INTO `catelog` VALUES (6, '运动棋牌', 3, 1);
INSERT INTO `catelog` VALUES (7, '票券小物', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL COMMENT '评论主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户，外键',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品，外键',
  `content` text COMMENT '评论内容',
  `create_at` varchar(25) DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品主键',
  `catelog_id` int(11) DEFAULT NULL COMMENT '商品分类，外键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户外键',
  `name` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `price` int(5) NOT NULL COMMENT '出售价格',
  `start_time` varchar(25) DEFAULT NULL COMMENT '发布时间',
  `polish_time` varchar(30) DEFAULT NULL COMMENT '擦亮时间，按该时间进行查询，精确到时分秒',
  `end_time` varchar(25) DEFAULT NULL COMMENT '下架时间',
  `describle` text COMMENT '详细信息',
  `commet_num` int(11) DEFAULT NULL COMMENT '评论回复数量',
  `percent_new` int(1) DEFAULT NULL COMMENT '成色',
  `check_status` int(1) DEFAULT '0' COMMENT '商品状态,0为未通过,1通过审核,待售,已售出',
  PRIMARY KEY (`id`),
  KEY `catelog_id` (`catelog_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
BEGIN;
INSERT INTO `goods` VALUES (49, 3, 2, '小台灯', 6, '2018-08-10', '2018-08-10', '2018-08-20', '创意小台灯，新入手两个月', 0, 8, 1);
INSERT INTO `goods` VALUES (51, 5, 2, '双肩背包', 4, '2018-08-10', '2018-08-10', '2018-08-20', '男生潮流双肩背包，八成新，有意者联系', 0, 8, 1);
INSERT INTO `goods` VALUES (56, 6, 5, '精美吉他', 20, '2018-08-18', '2018-08-18', '2018-08-28', '自用二手吉他，9成新，低价出售，有意者联系。', 0, 9, 2);
INSERT INTO `goods` VALUES (57, 2, 5, '山地车', 52, '2018-08-18', '2018-08-18', '2018-08-28', '八成新山地车，无损坏，需要Call我。', 0, 8, 1);
INSERT INTO `goods` VALUES (58, 3, 5, '无线鼠标', 4, '2018-08-18', '2018-08-18', '2018-08-28', '罗技无线鼠标,自用一个月，9成新,手感好,反应灵敏。', 0, 9, 1);
INSERT INTO `goods` VALUES (59, 1, 5, '数码相机', 58, '2018-08-18', '2018-08-18', '2018-08-28', '自用的数码相机，一年前购买，无磕碰。', 0, 7, 1);
INSERT INTO `goods` VALUES (60, 1, 5, '笔记本电脑', 120, '2018-08-18', '2018-08-18', '2018-08-28', '八成新14寸笔记本电脑,商务本，适合办公。', 0, 8, 1);
INSERT INTO `goods` VALUES (61, 3, 2, '收纳盒', 2, '2018-08-18', '2018-08-18', '2018-08-28', '3层塑料物品收纳盒,毕业了，低价出售。', 0, 7, 1);
INSERT INTO `goods` VALUES (62, 3, 2, '台灯', 6, '2018-08-18', '2018-08-18', '2018-08-28', '考研时购买的台灯,可接usb接口。', 0, 7, 1);
INSERT INTO `goods` VALUES (63, 5, 2, '女鞋', 3, '2018-08-18', '2018-08-18', '2018-08-28', '韩式潮流学生女鞋,低价出售', 0, 8, 1);
INSERT INTO `goods` VALUES (64, 1, 7, '无线传呼机', 23, '2018-08-18', '2018-08-18', '2018-08-28', '一对无线传呼机,带有充电器，可以无线传呼两公里。', 0, 8, 1);
INSERT INTO `goods` VALUES (65, 5, 7, '手提女包', 4, '2018-08-18', '2018-08-18', '2018-08-28', '手提女包，自用一个月。', 0, 9, 1);
INSERT INTO `goods` VALUES (66, 5, 8, '手提包', 3, '2018-08-18', '2018-08-18', '2018-08-28', '自用手提包,便宜出售。', 0, 7, 1);
INSERT INTO `goods` VALUES (67, 4, 8, '小说', 30, '2018-08-18', '2018-08-18', '2018-08-28', '毕业季，一书架小说,便宜出售。', 0, 6, 1);
INSERT INTO `goods` VALUES (68, 2, 8, '概念代步车', 250, '2018-08-18', '2018-08-18', '2018-08-28', '9成新概念代步车，上市三个月。', 0, 9, 1);
INSERT INTO `goods` VALUES (69, 7, 8, '水晶挂件', 2, '2018-08-18', '2018-08-18', '2018-08-28', '水晶挂件一套，精美漂亮。', 0, 8, 1);
INSERT INTO `goods` VALUES (70, 6, 8, '卡坦岛桌游', 2, '2018-08-18', '2018-08-18', '2018-08-28', '卡坦岛桌游，适合宿舍多人玩，十分刺激。', 0, 7, 0);
COMMIT;

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片主键',
  `goods_id` int(11) NOT NULL COMMENT '商品外键',
  `img_url` text NOT NULL COMMENT '图片链接',
  PRIMARY KEY (`id`),
  KEY `goods_id` (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------
BEGIN;
INSERT INTO `image` VALUES (1, 49, 'c9d6fa11-df88-467d-bf58-acb9f4943376.jpg');
INSERT INTO `image` VALUES (3, 51, '8f172943-c969-4134-bd9d-966584618fb8.jpg');
INSERT INTO `image` VALUES (5, 55, '67ae6e0a-6d59-4c24-b40d-9a5424427e63.jpg');
INSERT INTO `image` VALUES (6, 56, '9399e8b4-a2d4-4cd5-bc55-eb2970b3e1d5.jpg');
INSERT INTO `image` VALUES (7, 57, '9967a2e0-4a96-4d0c-b626-5cd4a7a9df39.jpg');
INSERT INTO `image` VALUES (8, 58, '70d0677c-3110-4885-811d-e3ef7dc3592e.jpg');
INSERT INTO `image` VALUES (9, 59, '77cbdea3-fb13-4ea1-9780-1a250ca76382.jpg');
INSERT INTO `image` VALUES (10, 60, 'bf56c58c-cc53-4360-af86-19f32d16899a.jpg');
INSERT INTO `image` VALUES (11, 61, '17b44402-f7e6-46c2-839e-5a1878508203.jpg');
INSERT INTO `image` VALUES (12, 62, '130f2553-acc6-4038-b914-e1f9f23fc623.jpg');
INSERT INTO `image` VALUES (13, 63, '75e1da04-aa64-4f81-b498-b613282e1920.jpg');
INSERT INTO `image` VALUES (14, 64, '4c394d43-76a5-4f16-acd8-c516ae937a82.jpg');
INSERT INTO `image` VALUES (15, 65, 'c964cdfb-cb95-4f83-a8de-8091a51eaef7.jpg');
INSERT INTO `image` VALUES (16, 66, 'f955b9f0-de7d-4693-9a06-c282c0cc13cf.jpg');
INSERT INTO `image` VALUES (17, 67, 'a698bab7-3a73-403d-a3a5-09c98f3ac4cd.jpg');
INSERT INTO `image` VALUES (18, 68, '15a351a6-9d95-4d0f-8a5a-1b1bef5eb5e1.jpeg');
INSERT INTO `image` VALUES (19, 69, '7d3a8e2a-ba02-4563-9699-a868abf6d886.jpg');
INSERT INTO `image` VALUES (20, 70, 'bdc414c1-6dc4-42fb-95ee-c95cdc3fe736.jpg');
COMMIT;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统信息主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户，外键',
  `context` text COMMENT '信息内容',
  `create_at` varchar(25) DEFAULT NULL COMMENT '推送信息时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，0未读，1已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `goods_id` int(11) NOT NULL,
  `comment_status` bit(1) DEFAULT b'0' COMMENT '0为未评价，1为已评价',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`user_id`) USING BTREE,
  KEY `goodsId` (`goods_id`),
  CONSTRAINT `goodsId` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
BEGIN;
INSERT INTO `record` VALUES (19, 5, 51, b'1', '2018-08-18 19:05:53', '2018-08-18 21:08:02');
INSERT INTO `record` VALUES (20, 5, 49, b'1', '2018-08-18 19:06:01', '2018-08-18 21:08:04');
INSERT INTO `record` VALUES (23, 2, 56, b'0', '2018-08-19 00:04:15', '2018-08-19 00:04:15');
COMMIT;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论回复主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户，外键',
  `atuser_id` int(11) DEFAULT NULL,
  `commet_id` int(11) DEFAULT NULL COMMENT '评论，外键',
  `content` text COMMENT '回复内容',
  `create_at` varchar(25) DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录使用的手机号',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `QQ` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '即时通讯',
  `create_at` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建时间',
  `goods_num` int(11) DEFAULT '0' COMMENT '发布过的物品数量',
  `credit` double(2,1) DEFAULT NULL COMMENT '用户信誉分',
  `last_login` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最近一次登陆时间',
  `status` bit(1) DEFAULT b'0' COMMENT '账号是否冻结，默认0未冻结',
  `power` bit(1) DEFAULT b'0' COMMENT '用户权限，0为普通用户，1和其他值为管理员',
  `mark_num` int(5) DEFAULT '0' COMMENT '卖家被评价次数用来计算信誉分',
  `savings` int(6) DEFAULT '10' COMMENT '用户所拥有虚拟币积蓄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (2, '18021018582', '土豆1026', '70487B7D3A5408B8FAD706E642F5E6A2', '2750942591', '2018-08-09 15:39', 5, 4.7, NULL, b'1', b'0', 0, 22);
INSERT INTO `user` VALUES (5, '18800208446', '喵@个咪', 'E10ADC3949BA59ABBE56E057F20F883E', '2750942592', '2018-08-18 16:46', 6, NULL, NULL, b'1', b'0', 0, 47);
INSERT INTO `user` VALUES (6, '15051018582', '管理员', 'E10ADC3949BA59ABBE56E057F20F883E', '8698723', NULL, 0, NULL, NULL, b'1', b'1', 0, 100);
INSERT INTO `user` VALUES (7, '18800208582', '孤独的根号三', 'E10ADC3949BA59ABBE56E057F20F883E', NULL, '2018-08-18 22:51', 2, NULL, NULL, b'1', b'0', 0, 10);
INSERT INTO `user` VALUES (8, '18021018446', '今天$有点儿帅', 'E10ADC3949BA59ABBE56E057F20F883E', NULL, '2018-08-18 23:08', 5, NULL, NULL, b'1', b'0', 0, 10);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
