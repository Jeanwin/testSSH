/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : testspring

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-10-27 17:35:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '三国演义', '三国演义');
INSERT INTO `book` VALUES ('2', '红楼梦', '红楼梦-曹雪芹');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `loginname` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'lisi', 'zhangsan', '123456', '1984719960@qq.com');
INSERT INTO `user` VALUES ('2', 'shan', 'shan', '123', '1298921@qq.com');
