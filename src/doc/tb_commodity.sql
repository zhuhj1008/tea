/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : tea

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-05-02 08:33:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_commodity
-- ----------------------------
DROP TABLE IF EXISTS `tb_commodity`;
CREATE TABLE `tb_commodity` (
  `commodity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `commodity_name` varchar(50) DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL COMMENT '价格',
  `item_id` int(11) DEFAULT NULL COMMENT '所属类目',
  `picture` varchar(500) DEFAULT NULL COMMENT '图片',
  `brand` int(11) DEFAULT NULL COMMENT '品牌',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `update_by` int(11) DEFAULT NULL COMMENT '更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否可用（0否 1是）',
  PRIMARY KEY (`commodity_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_commodity
-- ----------------------------
INSERT INTO `tb_commodity` VALUES ('1', '祁门红茶', '100.20', '4', 'www.picture.com', null, '1', '1', '2018-04-30 11:23:15', '2018-04-30 12:46:31', '1');
INSERT INTO `tb_commodity` VALUES ('2', '大吉岭红茶', '51.50', '4', 'www.picture.com', null, '1', '1', '2018-04-30 11:23:15', '2018-04-30 11:23:15', '1');
INSERT INTO `tb_commodity` VALUES ('3', '阿萨姆红茶', '52.50', '4', 'www.picture.com', null, '1', '1', '2018-04-30 11:23:15', '2018-04-30 11:23:15', '1');
INSERT INTO `tb_commodity` VALUES ('4', '吴裕泰', '43.00', '5', 'www.tea.green.com', null, '1', '1', '2018-04-30 11:23:15', '2018-04-30 11:23:15', '1');
INSERT INTO `tb_commodity` VALUES ('5', '天福茗茶', '30.50', '5', 'www.tea.green.com', null, '1', '1', '2018-04-30 11:23:15', '2018-04-30 11:23:15', '1');
INSERT INTO `tb_commodity` VALUES ('6', '张一元', '61.00', '5', 'www.tea.green.com', null, '1', '1', '2018-04-30 11:23:15', '2018-04-30 11:23:15', '1');

-- ----------------------------
-- Table structure for tb_commodity_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_commodity_detail`;
CREATE TABLE `tb_commodity_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_id` int(11) DEFAULT NULL,
  `unit` varchar(50) DEFAULT NULL COMMENT '单位',
  `property` varchar(500) DEFAULT NULL COMMENT '属性',
  `freight` decimal(11,2) DEFAULT NULL COMMENT '运费',
  `integral` int(11) DEFAULT NULL COMMENT '积分',
  `origin` varchar(11) DEFAULT NULL COMMENT '产地',
  `enable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_commodity_detail
-- ----------------------------
INSERT INTO `tb_commodity_detail` VALUES ('1', '1', '50g', '', '6.00', '12', '祁门', '1');
INSERT INTO `tb_commodity_detail` VALUES ('2', '2', '50g', '', '6.00', '12', '大吉岭', '1');
INSERT INTO `tb_commodity_detail` VALUES ('3', '3', '50g', '', '9.00', '16', '阿尔卑斯山', '1');
INSERT INTO `tb_commodity_detail` VALUES ('4', '4', '50g', '', '9.00', '16', '北京', '1');
INSERT INTO `tb_commodity_detail` VALUES ('5', '5', '50g', '', '9.00', '16', '北京', '1');
INSERT INTO `tb_commodity_detail` VALUES ('6', '6', '50g', '', '9.00', '16', '北京', '1');

-- ----------------------------
-- Table structure for tb_commodity_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `tb_commodity_evaluate`;
CREATE TABLE `tb_commodity_evaluate` (
  `evaluate_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `commodity_id` int(11) DEFAULT NULL COMMENT '商品id',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户编号',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `level` int(1) DEFAULT NULL COMMENT '星级',
  `evaluate` varchar(500) DEFAULT NULL COMMENT '评论',
  `evaluate_picture` varchar(500) DEFAULT NULL COMMENT '评论配图',
  `evaluate_time` datetime DEFAULT NULL COMMENT '评论时间',
  `append_evaluate` varchar(500) DEFAULT NULL COMMENT '追加评论',
  `append_evaluate_time` datetime DEFAULT NULL COMMENT '追加评论时间',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新人',
  `enable` tinyint(1) DEFAULT '1' COMMENT '可用',
  PRIMARY KEY (`evaluate_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_commodity_evaluate
-- ----------------------------
INSERT INTO `tb_commodity_evaluate` VALUES ('1', '4', '1', 'joe', '4', '物有所值', null, null, '真的很好', '2018-04-30 15:55:28', '1', '2018-04-30 15:51:58', null, '2018-04-30 15:55:28', '1');
INSERT INTO `tb_commodity_evaluate` VALUES ('2', '4', '2', 'dom', '1', '差评', null, null, null, null, '1', '2018-04-30 15:52:35', null, '2018-04-30 15:52:35', '1');

-- ----------------------------
-- Table structure for tb_commodity_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_commodity_item`;
CREATE TABLE `tb_commodity_item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类目ID',
  `name` varchar(50) DEFAULT NULL COMMENT '类目名称',
  `parent_id` int(11) DEFAULT NULL,
  `parent_ids` varchar(50) DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '可用',
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_commodity_item
-- ----------------------------
INSERT INTO `tb_commodity_item` VALUES ('1', '茶叶', '0', '0', '1', '1', '2018-04-29 22:39:10', '2018-04-29 22:39:10', '1');
INSERT INTO `tb_commodity_item` VALUES ('2', '茶具', '0', '0', '1', '1', '2018-04-29 22:39:50', '2018-04-29 22:39:50', '1');
INSERT INTO `tb_commodity_item` VALUES ('3', '其他', '0', '0', '1', '1', '2018-04-29 22:40:03', '2018-04-29 22:40:03', '1');
INSERT INTO `tb_commodity_item` VALUES ('4', '红茶', '1', '0,1', '1', '1', '2018-04-29 22:40:33', '2018-04-29 22:40:33', '1');
INSERT INTO `tb_commodity_item` VALUES ('5', '绿茶', '1', '0,1', '1', '1', '2018-04-29 22:40:56', '2018-04-29 22:40:56', '1');
INSERT INTO `tb_commodity_item` VALUES ('6', '乌龙茶', '1', '0,1', '1', '1', '2018-04-29 22:42:04', '2018-04-29 22:42:04', '1');
INSERT INTO `tb_commodity_item` VALUES ('7', '花茶', '1', '0,1', '1', '1', '2018-04-29 22:42:19', '2018-04-29 22:42:19', '1');
INSERT INTO `tb_commodity_item` VALUES ('8', '茶壶', '2', '0,2', '1', '1', '2018-04-29 22:43:23', '2018-04-29 22:43:23', '1');
INSERT INTO `tb_commodity_item` VALUES ('9', '茶杯', '2', '0,2', '1', '1', '2018-04-29 22:44:05', '2018-04-29 22:44:05', '1');
INSERT INTO `tb_commodity_item` VALUES ('10', '茶勺', '2', '0,2', '1', '1', '2018-04-29 22:44:20', '2018-04-29 22:44:20', '1');

-- ----------------------------
-- Table structure for tb_coupon
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon`;
CREATE TABLE `tb_coupon` (
  `coupon_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `discount` decimal(5,2) DEFAULT NULL COMMENT '折扣（0~1）',
  `money` decimal(5,2) DEFAULT '0.00' COMMENT '优惠金额',
  `amount` int(5) DEFAULT '1' COMMENT '几件享受优惠',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新人',
  `enable` tinyint(1) DEFAULT '1' COMMENT '可用',
  PRIMARY KEY (`coupon_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupon
-- ----------------------------
INSERT INTO `tb_coupon` VALUES ('1', '五一八折优惠', '0.80', '0.00', '1', '1', '2018-04-30 14:20:02', '1', '2018-04-30 14:32:50', '1');
INSERT INTO `tb_coupon` VALUES ('2', '五一满两件减10', '0.00', '10.00', '2', '1', '2018-04-30 14:22:25', '1', '2018-04-30 14:22:25', '1');

-- ----------------------------
-- Table structure for tb_coupon_customer
-- ----------------------------
DROP TABLE IF EXISTS `tb_coupon_customer`;
CREATE TABLE `tb_coupon_customer` (
  `customer_coupon_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL COMMENT '顾客编号',
  `coupon_id` int(11) DEFAULT NULL COMMENT '优惠券编号',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新人',
  `enable` tinyint(1) DEFAULT '1' COMMENT '可用',
  PRIMARY KEY (`customer_coupon_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coupon_customer
-- ----------------------------
INSERT INTO `tb_coupon_customer` VALUES ('1', '1', '1', '1', '2018-04-30 14:29:48', '1', '2018-04-30 14:29:48', '1');
INSERT INTO `tb_coupon_customer` VALUES ('2', '1', '2', '1', '2018-04-30 14:30:34', '1', '2018-04-30 14:30:34', '1');

-- ----------------------------
-- Table structure for tb_express
-- ----------------------------
DROP TABLE IF EXISTS `tb_express`;
CREATE TABLE `tb_express` (
  `express_id` int(11) NOT NULL,
  `express_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`express_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_express
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `order_id` int(11) NOT NULL COMMENT '订单编号',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户编号',
  `order_money` decimal(11,2) DEFAULT NULL COMMENT '订单总额',
  `order_status` int(2) DEFAULT NULL COMMENT '订单状态',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `post_code` varchar(10) DEFAULT NULL COMMENT '邮编',
  `express_id` int(2) DEFAULT NULL COMMENT '快递商家',
  `express_code` varchar(64) DEFAULT NULL COMMENT '快递单号',
  `express_money` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '快递收费',
  `receive_address` varchar(100) DEFAULT NULL COMMENT '收货地址',
  `remake` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新人',
  `enable` tinyint(1) DEFAULT '1' COMMENT '可用',
  PRIMARY KEY (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_detail`;
CREATE TABLE `tb_order_detail` (
  `detail_id` int(11) NOT NULL COMMENT '明细编号',
  `order_id` int(11) DEFAULT NULL COMMENT '订单编号',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户编号',
  `commodity_id` int(11) DEFAULT NULL COMMENT '商品编号',
  `price` decimal(11,2) DEFAULT NULL COMMENT '价格',
  `amount` int(5) DEFAULT NULL COMMENT '数量',
  `status` int(5) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for tb_receiving_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_receiving_address`;
CREATE TABLE `tb_receiving_address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(20) DEFAULT NULL COMMENT '省份',
  `city` varchar(20) DEFAULT NULL COMMENT '市',
  `area` varchar(20) DEFAULT NULL COMMENT '地区',
  `post_code` char(6) DEFAULT NULL COMMENT '邮编',
  `address_detail` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新人',
  `enable` tinyint(1) DEFAULT '1' COMMENT '可用',
  PRIMARY KEY (`address_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_receiving_address
-- ----------------------------

-- ----------------------------
-- Table structure for tb_shop_cart
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_cart`;
CREATE TABLE `tb_shop_cart` (
  `shop_cart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车编号',
  `customer_id` int(11) DEFAULT NULL COMMENT '顾客编号',
  `commodity_id` int(11) DEFAULT NULL COMMENT '商品编号',
  `commodity_amont` int(5) DEFAULT NULL COMMENT '商品数量',
  `status` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`shop_cart_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop_cart
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_customer
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_customer`;
CREATE TABLE `tb_user_customer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '顾客编号',
  `head_portrait` varchar(500) DEFAULT NULL COMMENT '头像',
  `customer_name` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `user_type` int(2) DEFAULT NULL COMMENT '用户类型',
  `integral` int(11) DEFAULT NULL COMMENT '积分',
  `receiving_address_default` int(11) DEFAULT NULL COMMENT '默认收货地址',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新人',
  `enable` tinyint(1) DEFAULT '1' COMMENT '可用',
  PRIMARY KEY (`customer_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_customer
-- ----------------------------
INSERT INTO `tb_user_customer` VALUES ('1', 'a8a5f6g4h2j3.jpg', '朱鸿钧', '15175225612', '1916931393@qq.com', '2', '52', '1', '1', '2018-04-30 17:01:54', '1', '2018-04-30 17:01:54', '1');

-- ----------------------------
-- Table structure for tb_user_manager
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_manager`;
CREATE TABLE `tb_user_manager` (
  `manager_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员Id',
  `head_portrait` varchar(500) DEFAULT NULL COMMENT '头像',
  `manager_name` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `salt` varchar(64) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新人',
  `enable` tinyint(1) DEFAULT '1' COMMENT '可用',
  PRIMARY KEY (`manager_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_manager
-- ----------------------------
INSERT INTO `tb_user_manager` VALUES ('1', 'aaa546r4f5n6c6a.png', 'root', '男', '15175225612', '1916931393@qq.com', null, 'root', '0', '1', '2018-04-30 17:29:56', '1', '2018-04-30 17:29:51', '1');
