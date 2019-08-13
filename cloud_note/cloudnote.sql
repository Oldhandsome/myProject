/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : cloudnote

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-08-06 07:46:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `activity`
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `activity_id` varchar(100) NOT NULL,
  `activity_title` varchar(500) DEFAULT NULL,
  `activity_introduce` text,
  `activity_end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------

-- ----------------------------
-- Table structure for `activity_status`
-- ----------------------------
DROP TABLE IF EXISTS `activity_status`;
CREATE TABLE `activity_status` (
  `activity_status_id` varchar(100) NOT NULL,
  `activity_id` varchar(100) NOT NULL,
  `activity_status_code` varchar(500) DEFAULT NULL,
  `activity_status_name` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`activity_status_id`),
  KEY `fk_a_s_id` (`activity_id`),
  CONSTRAINT `fk_a_s_id` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_status
-- ----------------------------

-- ----------------------------
-- Table structure for `contribute`
-- ----------------------------
DROP TABLE IF EXISTS `contribute`;
CREATE TABLE `contribute` (
  `contribute_id` varchar(100) NOT NULL,
  `activity_id` varchar(100) DEFAULT NULL,
  `note_id` varchar(100) DEFAULT NULL,
  `praise` int(11) DEFAULT NULL,
  `belittle` int(11) DEFAULT NULL,
  PRIMARY KEY (`contribute_id`),
  KEY `fk_a_c_id` (`activity_id`),
  KEY `fk_n_c_id` (`note_id`),
  CONSTRAINT `fk_a_c_id` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`),
  CONSTRAINT `fk_n_c_id` FOREIGN KEY (`note_id`) REFERENCES `note` (`note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contribute
-- ----------------------------

-- ----------------------------
-- Table structure for `note`
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `note_id` varchar(100) NOT NULL,
  `note_book_id` varchar(100) DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `note_status_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `note_type_id` varchar(100) DEFAULT NULL,
  `note_title` varchar(100) DEFAULT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  `created_at` bigint(20) DEFAULT NULL,
  `note_content` text,
  PRIMARY KEY (`note_id`),
  KEY `fk_s_n_id` (`note_status_id`),
  KEY `fk_t_n_id` (`note_type_id`),
  KEY `fk_u_n_id` (`user_id`),
  KEY `fk_nb_n` (`note_book_id`),
  CONSTRAINT `fk_nb_n` FOREIGN KEY (`note_book_id`) REFERENCES `note_book` (`note_book_id`),
  CONSTRAINT `fk_s_n_id` FOREIGN KEY (`note_status_id`) REFERENCES `note_status` (`note_status_id`),
  CONSTRAINT `fk_t_n_id` FOREIGN KEY (`note_type_id`) REFERENCES `note_type` (`note_type_id`),
  CONSTRAINT `fk_u_n_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES ('072383e4-54fd-4cad-bdad-c87632996be4', '4162484d-70cc-4eea-a2f5-63b1fbc67624', '79519', '1', null, '英语笔记一', null, '1564807062370', '这篇写作真难写1');
INSERT INTO `note` VALUES ('08837535-68c5-4998-af01-773d8bc4faa0', '4162484d-70cc-4eea-a2f5-63b1fbc67624', '79519', '1', null, '英语笔记二', null, '1564807062380', '这篇写作真难写2');
INSERT INTO `note` VALUES ('0a0d4503-7b77-48a5-b845-5c9f6fba82cf', 'c8454fe3-4cb1-4fb6-9e80-04cbd0a89abf', '86063', '1', null, '数学笔记一', null, '1564806661034', '这道题真的很难1');
INSERT INTO `note` VALUES ('0ed5351e-a2f9-4901-8c60-1277e3e58fc8', 'c8454fe3-4cb1-4fb6-9e80-04cbd0a89abf', '86063', '1', null, '数学笔记二', null, '1564806661038', '这道题真的很难2');
INSERT INTO `note` VALUES ('17717ff7-ab7d-4512-a840-aa3c6e8ff210', '5b5f8989-bde0-40d4-8ef5-9b04379f9d06', '86063', '1', null, '语文笔记一', null, '1564806661080', '这个句子真难理解1');
INSERT INTO `note` VALUES ('1f69fbe0-d617-4ec2-aa66-4e9cfa62aae6', 'c8454fe3-4cb1-4fb6-9e80-04cbd0a89abf', '86063', '1', null, '数学笔记三', null, '1564806661050', '这道题真的很难3');
INSERT INTO `note` VALUES ('2ee7b3fb-dc4a-4cbd-934a-f3b780c71e36', 'a586d56f-7c99-4016-9bfd-1c5966820d61', '86063', '1', null, '默认笔记一', null, '1564806661000', '哇哈哈哈1');
INSERT INTO `note` VALUES ('357eb29d-9edf-4d6e-b791-08e75ed67a48', '4162484d-70cc-4eea-a2f5-63b1fbc67624', '79519', '1', null, '英语笔记三', null, '1564807062390', '这篇写作真难写3');
INSERT INTO `note` VALUES ('4c5aa1b4-d52b-4fab-a177-e9dcb07029d0', 'a586d56f-7c99-4016-9bfd-1c5966820d61', '86063', '1', null, '默认笔记二', null, '1564806661005', '哇哈哈哈2');
INSERT INTO `note` VALUES ('734bb7a1-2808-4d2e-8e5b-0c66234b02da', '5b5f8989-bde0-40d4-8ef5-9b04379f9d06', '86063', '1', null, '语文笔记二', null, '1564806661100', '这个句子真难理解2');
INSERT INTO `note` VALUES ('a23ee999-fa0e-4b30-ac07-55389c55aa26', '6419a47a-c980-4531-b538-3f8ade974f7c', '79519', '1', null, '默认笔记一', null, '1564807062500', '哇哈哈1');
INSERT INTO `note` VALUES ('a6d83224-1960-4412-9e91-6c8d58eff8ec', '5b5f8989-bde0-40d4-8ef5-9b04379f9d06', '86063', '1', null, '语文笔记三', null, '1564806661150', '这个句子真难理解1');
INSERT INTO `note` VALUES ('c5012970-09ae-43ad-975f-3d6f02a7f32b', '6419a47a-c980-4531-b538-3f8ade974f7c', '79519', '1', null, '默认笔记二', null, '1564807062550', '哇哈哈2');
INSERT INTO `note` VALUES ('cb035738-3ebe-4b10-9d9a-0179ae83857a', '6419a47a-c980-4531-b538-3f8ade974f7c', '79519', '1', null, '默认笔记三', null, '1564807062600', '哇哈哈3');
INSERT INTO `note` VALUES ('ce9633f8-5f02-4cab-a28d-b92e4188df9f', '7c67fc47-ba75-4271-b56f-5f9999079795', '79519', '1', null, '数学笔记一', null, '1564807062400', '这道题真的好难啊1');
INSERT INTO `note` VALUES ('d26e4fb3-a814-409e-9eb7-2264fd9e8a23', 'a586d56f-7c99-4016-9bfd-1c5966820d61', '86063', '1', null, '默认笔记三', null, '1564806661040', '哇哈哈哈3');
INSERT INTO `note` VALUES ('e1f139eb-3378-481f-bd5c-9e57404bd272', '7c67fc47-ba75-4271-b56f-5f9999079795', '79519', '1', null, '数学笔记二', null, '1564807062450', '这道题真的好难啊2');
INSERT INTO `note` VALUES ('e786f144-6df8-4a5f-a03b-3487458c43c0', '7c67fc47-ba75-4271-b56f-5f9999079795', '79519', '1', null, '数学笔记三', null, '1564807062490', '这道题真的好难啊3');

-- ----------------------------
-- Table structure for `note_book`
-- ----------------------------
DROP TABLE IF EXISTS `note_book`;
CREATE TABLE `note_book` (
  `note_book_id` varchar(100) NOT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `note_book_type_id` varchar(100) DEFAULT NULL,
  `note_book_name` varchar(500) DEFAULT NULL,
  `explaination` text,
  `created_at` bigint(20) DEFAULT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`note_book_id`),
  KEY `fk_u_n` (`user_id`),
  KEY `fk_nbt_n` (`note_book_type_id`),
  CONSTRAINT `fk_nbt_n` FOREIGN KEY (`note_book_type_id`) REFERENCES `note_book_type` (`note_book_type_id`),
  CONSTRAINT `fk_u_n` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of note_book
-- ----------------------------
INSERT INTO `note_book` VALUES ('4162484d-70cc-4eea-a2f5-63b1fbc67624', '79519', '0', 'english', '英语笔记本', '1564795513600', null);
INSERT INTO `note_book` VALUES ('5b5f8989-bde0-40d4-8ef5-9b04379f9d06', '86063', '0', 'chinese', '语文笔记本', '1564795738767', null);
INSERT INTO `note_book` VALUES ('6419a47a-c980-4531-b538-3f8ade974f7c', '79519', '0', 'default', '默认笔记本', '1564795513600', null);
INSERT INTO `note_book` VALUES ('7c67fc47-ba75-4271-b56f-5f9999079795', '79519', '0', 'math', '数学笔记本', '1564795631903', null);
INSERT INTO `note_book` VALUES ('a586d56f-7c99-4016-9bfd-1c5966820d61', '86063', '0', 'default', '默认笔记本', '1564795513600', null);
INSERT INTO `note_book` VALUES ('c8454fe3-4cb1-4fb6-9e80-04cbd0a89abf', '86063', '0', 'math', '数学笔记本', '1564795661510', null);

-- ----------------------------
-- Table structure for `note_book_type`
-- ----------------------------
DROP TABLE IF EXISTS `note_book_type`;
CREATE TABLE `note_book_type` (
  `note_book_type_id` varchar(100) NOT NULL,
  `note_book__type_code` varchar(100) DEFAULT NULL,
  `note_book__type_name` varchar(500) DEFAULT NULL,
  `note_book_type_explaination` text,
  PRIMARY KEY (`note_book_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of note_book_type
-- ----------------------------
INSERT INTO `note_book_type` VALUES ('0', '', '默认笔记本', '每个人都会有的默认笔记本');

-- ----------------------------
-- Table structure for `note_status`
-- ----------------------------
DROP TABLE IF EXISTS `note_status`;
CREATE TABLE `note_status` (
  `note_status_id` varchar(100) NOT NULL,
  `note_status_code` varchar(100) DEFAULT NULL,
  `note_status_name` varchar(500) DEFAULT NULL,
  `note_satus_explaination` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`note_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of note_status
-- ----------------------------
INSERT INTO `note_status` VALUES ('1', 'normal', 'normal', '正常');
INSERT INTO `note_status` VALUES ('2', 'abandoned', 'abandoned', '在回收站');
INSERT INTO `note_status` VALUES ('3', 'deleted', 'deleted', '被删除的');
INSERT INTO `note_status` VALUES ('4', 'stared', 'stared', '被收藏的');

-- ----------------------------
-- Table structure for `note_type`
-- ----------------------------
DROP TABLE IF EXISTS `note_type`;
CREATE TABLE `note_type` (
  `note_type_id` varchar(100) NOT NULL,
  `note_type_code` varchar(100) DEFAULT NULL,
  `note_type_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`note_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of note_type
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(100) NOT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `authoritative_code` varchar(100) DEFAULT NULL,
  `explaination` text,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('79519', 'jack', '4QrcOUm6Wau+VuBX8g+IPg==', 'G72IZGCCcBXl1gXtRCUiUQ==', '这人很黑！');
INSERT INTO `user` VALUES ('86063', 'lucy', '4QrcOUm6Wau+VuBX8g+IPg==', 'G72IZGCCcBXl1gXtRCUiUQ==', '');
