/*
Navicat MySQL Data Transfer

Source Server         : DB
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : lovepets

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2018-05-23 14:28:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `adressid` smallint(6) NOT NULL AUTO_INCREMENT,
  `addressid_content` varchar(255) DEFAULT NULL,
  `user_id` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`adressid`),
  KEY `address_user` (`user_id`),
  CONSTRAINT `address_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for `dianzan`
-- ----------------------------
DROP TABLE IF EXISTS `dianzan`;
CREATE TABLE `dianzan` (
  `dianzan_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `user_id` smallint(6) DEFAULT NULL,
  `found_id` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`dianzan_id`),
  KEY `dianzan_user` (`user_id`),
  KEY `dianzan_found` (`found_id`),
  CONSTRAINT `dianzan_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `dianzan_found` FOREIGN KEY (`found_id`) REFERENCES `found` (`found_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dianzan
-- ----------------------------

-- ----------------------------
-- Table structure for `found`
-- ----------------------------
DROP TABLE IF EXISTS `found`;
CREATE TABLE `found` (
  `found_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `user_id` smallint(6) DEFAULT NULL,
  `found_date` date DEFAULT NULL,
  `found_time` time DEFAULT NULL,
  `found_content` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`found_id`),
  KEY `found_user` (`user_id`),
  CONSTRAINT `found_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of found
-- ----------------------------

-- ----------------------------
-- Table structure for `friend`
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `friend_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `user_id` smallint(6) DEFAULT NULL,
  `friend_user_id` smallint(6) DEFAULT NULL,
  `friend_type` int(2) DEFAULT NULL,
  PRIMARY KEY (`friend_id`),
  KEY `friend_user` (`user_id`),
  CONSTRAINT `friend_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend
-- ----------------------------

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `goods_detail` varchar(255) DEFAULT NULL,
  `goods_price` double DEFAULT NULL,
  `goods_img` varchar(255) DEFAULT NULL,
  `goods_type` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `goods_type` (`goods_type`),
  CONSTRAINT `goods_type` FOREIGN KEY (`goods_type`) REFERENCES `type` (`type_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for `image`
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `image_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `found_id` smallint(6) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `user_id` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `image_found` (`found_id`),
  KEY `image_user` (`user_id`),
  CONSTRAINT `image_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `image_found` FOREIGN KEY (`found_id`) REFERENCES `found` (`found_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `message_content` varchar(255) DEFAULT NULL,
  `send_id` smallint(6) NOT NULL,
  `receive_id` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `user_id` smallint(6) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `order_user` (`user_id`),
  CONSTRAINT `order_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for `order_detail`
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `detail_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `order_id` smallint(6) DEFAULT NULL,
  `type_id` smallint(1) DEFAULT NULL,
  `count` smallint(6) DEFAULT NULL,
  `goods_id` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `detail_goods` (`goods_id`),
  KEY `detail_order` (`order_id`),
  KEY `detail_type` (`type_id`),
  CONSTRAINT `detail_goods` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `detail_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `detail_type` FOREIGN KEY (`type_id`) REFERENCES `type` (`type_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `pinglun`
-- ----------------------------
DROP TABLE IF EXISTS `pinglun`;
CREATE TABLE `pinglun` (
  `pinglun_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `found_id` smallint(6) DEFAULT NULL,
  `user_id` smallint(6) DEFAULT NULL,
  `pinglun_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pinglun_id`),
  KEY `pinglun_user` (`user_id`),
  KEY `pinglun_found` (`found_id`),
  CONSTRAINT `pinglun_found` FOREIGN KEY (`found_id`) REFERENCES `found` (`found_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `pinglun_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pinglun
-- ----------------------------

-- ----------------------------
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `type_id` smallint(1) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '上门');
INSERT INTO `type` VALUES ('2', '商店');
INSERT INTO `type` VALUES ('3', '商城');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_tel` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_sex` int(2) DEFAULT NULL,
  `user_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
