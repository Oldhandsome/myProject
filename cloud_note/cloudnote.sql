/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : cloudnote

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-08-14 14:19:31
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
  KEY `fk_u_n_id` (`user_id`),
  KEY `fk_nb_n` (`note_book_id`),
  KEY `fk_t_n_id` (`note_type_id`),
  CONSTRAINT `fk_nb_n` FOREIGN KEY (`note_book_id`) REFERENCES `note_book` (`note_book_id`),
  CONSTRAINT `fk_s_n_id` FOREIGN KEY (`note_status_id`) REFERENCES `status` (`status_id`),
  CONSTRAINT `fk_t_n_id` FOREIGN KEY (`note_type_id`) REFERENCES `type` (`type_id`),
  CONSTRAINT `fk_u_n_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES ('072383e4-54fd-4cad-bdad-c87632996be4', '4162484d-70cc-4eea-a2f5-63b1fbc67624', '79519', '4', '1', '英语笔记一', '1565648604', '1564807062370', '<p>这篇写作真的难1啊啊啊啊啊</p>');
INSERT INTO `note` VALUES ('08837535-68c5-4998-af01-773d8bc4faa0', '4162484d-70cc-4eea-a2f5-63b1fbc67624', '79519', '4', '1', '英语笔记二', null, '1564807062380', '这篇写作真难写2');
INSERT INTO `note` VALUES ('0a0d4503-7b77-48a5-b845-5c9f6fba82cf', 'c8454fe3-4cb1-4fb6-9e80-04cbd0a89abf', '86063', '1', '1', '数学笔记一', null, '1564806661034', '这道题真的很难1');
INSERT INTO `note` VALUES ('0ed5351e-a2f9-4901-8c60-1277e3e58fc8', 'c8454fe3-4cb1-4fb6-9e80-04cbd0a89abf', '86063', '1', '1', '数学笔记二', null, '1564806661038', '这道题真的很难2');
INSERT INTO `note` VALUES ('17717ff7-ab7d-4512-a840-aa3c6e8ff210', '5b5f8989-bde0-40d4-8ef5-9b04379f9d06', '86063', '1', '1', '语文笔记一', null, '1564806661080', '这个句子真难理解1');
INSERT INTO `note` VALUES ('1f69fbe0-d617-4ec2-aa66-4e9cfa62aae6', '1026a6c4-b452-441d-958e-ae05d2bf357b', '86063', '4', '1', '数学笔记三', '1565625896600', '1564806661050', '这道题真的很难3');
INSERT INTO `note` VALUES ('22507308-f5e6-4c38-8239-45f3595954e6', '4162484d-70cc-4eea-a2f5-63b1fbc67624', '79519', '1', '1', '英语笔记四', null, '1565334450', '<p>这片真的难做</p>');
INSERT INTO `note` VALUES ('2ee7b3fb-dc4a-4cbd-934a-f3b780c71e36', 'a586d56f-7c99-4016-9bfd-1c5966820d61', '86063', '1', '1', '默认笔记一', null, '1564806661000', '哇哈哈哈1');
INSERT INTO `note` VALUES ('357eb29d-9edf-4d6e-b791-08e75ed67a48', '4162484d-70cc-4eea-a2f5-63b1fbc67624', '79519', '4', '1', '英语笔记三', null, '1564807062390', '这篇写作真难写3');
INSERT INTO `note` VALUES ('4c5aa1b4-d52b-4fab-a177-e9dcb07029d0', 'a586d56f-7c99-4016-9bfd-1c5966820d61', '86063', '1', '1', '默认笔记二', null, '1564806661005', '哇哈哈哈2');
INSERT INTO `note` VALUES ('5a405d26-49db-48b4-8288-6f4e9a5cffdf', '3f15d730-a536-41e3-8712-fa9dbeb45943', '79519', '1', '1', 'learning', null, '1565625185', '<p>learning is useful</p>');
INSERT INTO `note` VALUES ('734bb7a1-2808-4d2e-8e5b-0c66234b02da', '5b5f8989-bde0-40d4-8ef5-9b04379f9d06', '86063', '1', '1', '语文笔记二', '1565244907117', '1564806661100', '<p>这个句子真难理解2fasfdasdfasdf</p>');
INSERT INTO `note` VALUES ('895a3b55-e4ed-4c94-a78f-7b15ac3c37b3', '3f15d730-a536-41e3-8712-fa9dbeb45943', '79519', '4', '1', 'learning-1', null, '1565416171', '<p>learning is too hard!</p>');
INSERT INTO `note` VALUES ('a23ee999-fa0e-4b30-ac07-55389c55aa26', '6419a47a-c980-4531-b538-3f8ade974f7c', '79519', '4', '1', '默认笔记一', '1565653955691', '1564807062500', '哇哈哈1');
INSERT INTO `note` VALUES ('a6d83224-1960-4412-9e91-6c8d58eff8ec', '5b5f8989-bde0-40d4-8ef5-9b04379f9d06', '86063', '1', '1', '语文笔记三', null, '1564806661150', '这个句子真难理解1');
INSERT INTO `note` VALUES ('c5012970-09ae-43ad-975f-3d6f02a7f32b', '6419a47a-c980-4531-b538-3f8ade974f7c', '79519', '1', '1', '默认笔记二', '1565133717444', '1564807062550', '<p><span style=\"font-size: 24px;\">哇哈哈哈你妹啊</span></p>');
INSERT INTO `note` VALUES ('cb035738-3ebe-4b10-9d9a-0179ae83857a', '6419a47a-c980-4531-b538-3f8ade974f7c', '79519', '1', '1', '默认笔记三', '1565653057607', '1564807062600', '<p>默认笔记三</p><p>你知道我在想你么？</p><p>哇哈哈</p>');
INSERT INTO `note` VALUES ('ce9633f8-5f02-4cab-a28d-b92e4188df9f', '7c67fc47-ba75-4271-b56f-5f9999079795', '79519', '4', '1', '数学笔记五', '1565648895', '1564807062400', '<p>这道题真的好难啊1</p>');
INSERT INTO `note` VALUES ('d26e4fb3-a814-409e-9eb7-2264fd9e8a23', 'a586d56f-7c99-4016-9bfd-1c5966820d61', '86063', '1', '1', '默认笔记三', null, '1564806661040', '哇哈哈哈3');
INSERT INTO `note` VALUES ('d9d945a0-b0bf-4ad8-b95c-8abc0b08cff7', '70c8b3e5-4235-4ea6-aaad-d9efa6ca7779', '79519', '1', '1', 'teaching-1', '1565623502492', '1565399722', '<p>教书育人真快乐</p>');
INSERT INTO `note` VALUES ('e1f139eb-3378-481f-bd5c-9e57404bd272', '7c67fc47-ba75-4271-b56f-5f9999079795', '79519', '4', '1', '数学笔记二', null, '1564807062450', '这道题真的好难啊2');
INSERT INTO `note` VALUES ('e39cddd3-c9d1-41a7-9f77-eeb5ba0a8ad7', '6419a47a-c980-4531-b538-3f8ade974f7c', '79519', '2', '1', '默认笔记四', null, '1565334687', '<p>哇哈哈哈</p>');
INSERT INTO `note` VALUES ('e786f144-6df8-4a5f-a03b-3487458c43c0', '6419a47a-c980-4531-b538-3f8ade974f7c', '79519', '4', '1', '数学笔记三', '1565711761823', '1564807062490', '这道题真的好难啊3');

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
  CONSTRAINT `fk_nbt_n` FOREIGN KEY (`note_book_type_id`) REFERENCES `type` (`type_id`),
  CONSTRAINT `fk_u_n` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of note_book
-- ----------------------------
INSERT INTO `note_book` VALUES ('0881d652-e751-40db-9894-6ac42a68f80d', '86063', '0', 'singing', '唱歌方面的笔记', '1565381738', null);
INSERT INTO `note_book` VALUES ('1026a6c4-b452-441d-958e-ae05d2bf357b', '86063', '0', 'music', '音乐笔记本', '1565188136161', null);
INSERT INTO `note_book` VALUES ('158dbd42-3ef1-41cf-af6c-eabaca376419', '86063', '0', 'dancing', '有关于跳舞方面的笔记', '1565381178', null);
INSERT INTO `note_book` VALUES ('3f15d730-a536-41e3-8712-fa9dbeb45943', '79519', '0', 'learning', 'the note of the way of learning', '1565382072', null);
INSERT INTO `note_book` VALUES ('4162484d-70cc-4eea-a2f5-63b1fbc67624', '79519', '0', 'english', '英语笔记本', '1564795513', null);
INSERT INTO `note_book` VALUES ('5b5f8989-bde0-40d4-8ef5-9b04379f9d06', '86063', '0', 'chinese', '语文笔记本', '1564795738', null);
INSERT INTO `note_book` VALUES ('6419a47a-c980-4531-b538-3f8ade974f7c', '79519', '0', 'default', '默认笔记本', '1564795513', null);
INSERT INTO `note_book` VALUES ('6a1be498-e732-405b-8817-2f029b0fd015', '79519', '0', 'talking', 'some notes about talking', '1565625226', null);
INSERT INTO `note_book` VALUES ('70c8b3e5-4235-4ea6-aaad-d9efa6ca7779', '79519', '0', 'teaching', 'something about teaching', '1565383782', null);
INSERT INTO `note_book` VALUES ('7c67fc47-ba75-4271-b56f-5f9999079795', '79519', '0', 'math', '数学笔记本', '1564795631', null);
INSERT INTO `note_book` VALUES ('a1fc9dee-cafc-4df0-a320-2474e15d1f70', '86063', '0', 'playing', 'playing笔记本', '1565397009', null);
INSERT INTO `note_book` VALUES ('a586d56f-7c99-4016-9bfd-1c5966820d61', '86063', '0', 'default', '默认笔记本', '1564795513', null);
INSERT INTO `note_book` VALUES ('a73d5d84-7220-40fa-8dcc-30e0a2593011', '86063', '0', 'eating', '有关于吃方面的笔记', '1565381531', null);
INSERT INTO `note_book` VALUES ('ae703f3f-4901-4080-b705-b748fee98279', '79519', '0', 'science', '科技类的笔记', '1565379966', null);
INSERT INTO `note_book` VALUES ('b7f8faef-8e0e-4fa2-9bd9-58fd8ec3f326', '79519', '0', 'physical', '物理方面的笔记', '1565380325', null);
INSERT INTO `note_book` VALUES ('c83c18cf-eec6-4aa9-9b8e-8b22a5de5348', '79519', '0', 'chemical', '化学方面的笔记', '1565380443', null);
INSERT INTO `note_book` VALUES ('c8454fe3-4cb1-4fb6-9e80-04cbd0a89abf', '86063', '0', 'math', '数学笔记本', '1564795661', null);
INSERT INTO `note_book` VALUES ('f402538b-624b-47db-a6ac-55f6a53bf642', '86063', '0', 'history', '历史方面的笔记', '1565380833', null);
INSERT INTO `note_book` VALUES ('f94aad4d-0a74-46f8-9033-22db9467eeb1', '86063', '0', 'computer', '计算机方面的笔记', '1565216133', null);

-- ----------------------------
-- Table structure for `status`
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `status_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `satus_explaination` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of status
-- ----------------------------
INSERT INTO `status` VALUES ('1', 'normal', 'normal', '正常');
INSERT INTO `status` VALUES ('2', 'abandoned', 'abandoned', '在回收站');
INSERT INTO `status` VALUES ('3', 'deleted', 'deleted', '被删除的');
INSERT INTO `status` VALUES ('4', 'stared', 'stared', '被收藏的');

-- ----------------------------
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `type_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type_name` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type_explaination` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('0', '', '笔记本', '每个人都会有的默认笔记本');
INSERT INTO `type` VALUES ('1', null, '笔记', '笔记本中的笔记');

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
