/*
Navicat MySQL Data Transfer

Source Server         : 临时ks
Source Server Version : 50562
Source Host           : 118.25.156.93:3306
Source Database       : ks

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2021-05-22 12:42:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(30) DEFAULT NULL,
  `psex` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', '修改人', '女');
INSERT INTO `person` VALUES ('2', '老李', '男');
INSERT INTO `person` VALUES ('3', '小美', '女');
INSERT INTO `person` VALUES ('4', '小兰', '女');
INSERT INTO `person` VALUES ('15', '土', '男');
INSERT INTO `person` VALUES ('16', '老土', '男');
INSERT INTO `person` VALUES ('17', '达美', '女');
