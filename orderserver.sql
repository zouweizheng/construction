/*
Navicat MySQL Data Transfer

Source Server         : 阿里云
Source Server Version : 50634
Source Host           : rm-wz99n6r6h00g9v27uo.mysql.rds.aliyuncs.com:3306
Source Database       : yxb_back1

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2018-06-15 12:49:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bi_order`
-- ----------------------------
DROP TABLE IF EXISTS `bi_order`;
CREATE TABLE `bi_order` (
  `bill_id` varchar(25) NOT NULL COMMENT '基建单号',
  `bill_name` varchar(100) DEFAULT '基建单名称',
  `bill_type` varchar(20) DEFAULT '基建单类型',
  `bill_status` varchar(20) DEFAULT NULL,
  `create_person_id` varchar(40) DEFAULT NULL,
  `create_person_name` varchar(100) DEFAULT NULL,
  `project_id` varchar(100) DEFAULT NULL,
  `project_name` varchar(200) DEFAULT NULL,
  `money` double(100,0) DEFAULT NULL,
  `create_time` datetime(1) DEFAULT NULL,
  `process_instance_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bi_order
-- ----------------------------
INSERT INTO `bi_order` VALUES ('1', '基建单名称', '基建单类型', '2', '2', '2', null, '2', '2', '2017-11-18 18:04:44.0', null);
INSERT INTO `bi_order` VALUES ('bi18020714352892', '2018年02月02日账单测试单', '账单', null, 'zouweizheng@gd.cmcc', null, null, null, null, '2018-02-07 14:35:28.9', null);
INSERT INTO `bi_order` VALUES ('bi18021316571494', '2018年02月02日账单测试单', '账单', 'verify', 'zouweizheng@gd.cmcc', null, null, '万科A项目', null, '2018-02-13 16:57:14.6', '2148016');
INSERT INTO `bi_order` VALUES ('bi18021415030297', '测试单', '账单', 'verify', 'zouweizheng@gd.cmcc', null, null, '测试0214', null, '2018-02-14 15:03:02.1', '2148129');
INSERT INTO `bi_order` VALUES ('bi18022500494822', '账单测试0225账单名称', '账单', 'verify', 'zouweizheng@gd.cmcc', null, null, '账单测试0225项目', null, '2018-02-25 00:49:48.4', '2148166');
INSERT INTO `bi_order` VALUES ('bi18022520084617', '项目0225的账单', '账单', 'verify', 'zouweizheng@gd.cmcc', null, null, '项目0225', null, '2018-02-25 20:08:46.9', '2148198');
INSERT INTO `bi_order` VALUES ('bi18030422270939', '2018年3月8日', '机械费', 'verify', 'zouweizheng@gd.cmcc', null, null, '南海万科广场', null, '2018-03-04 22:27:09.3', '2148294');
INSERT INTO `bi_order` VALUES ('bi18030423042481', '2018年3月费用', '账单', 'verify', 'zouweizheng@gd.cmcc', null, null, '保利肇庆汇通', null, '2018-03-04 23:04:24.8', '2148334');
INSERT INTO `bi_order` VALUES ('bi18030514521542', '2018年', '人工费', 'verify', 'zouweizheng@gd.cmcc', null, null, '万科', null, '2018-03-05 14:52:15.3', '2148704');
INSERT INTO `bi_order` VALUES ('bi18031618073584', '2月份', '账单', 'verify', 'zouweizheng@gd.cmcc', null, null, '万科123344', null, '2018-03-16 18:07:35.4', '2160079');
INSERT INTO `bi_order` VALUES ('bi18031621424868', '账单0316', '账单', 'verify', 'zouweizheng@gd.cmcc', null, null, '测试项目0316-4', null, '2018-03-16 21:42:48.1', '2160190');
INSERT INTO `bi_order` VALUES ('bi18031711120784', '2017年10份工程款', '机型费', 'verify', 'zouweizheng@gd.cmcc', null, null, '万科广场', null, '2018-03-17 11:12:07.0', '2160199');
INSERT INTO `bi_order` VALUES ('bi18031717232811', '2018年3月梁志锋付款', '账单', 'verify', 'zouweizheng@gd.cmcc', null, null, '万科A项目', null, '2018-03-17 17:23:28.9', '2160565');
INSERT INTO `bi_order` VALUES ('bi18031717255995', '122', '账单', 'verify', 'zouweizheng@gd.cmcc', null, null, '788', null, '2018-03-17 17:25:59.2', '2160573');
INSERT INTO `bi_order` VALUES ('bi18031717330369', '2018年1月份', '账单', 'verify', 'zouweizheng@gd.cmcc', null, null, '梁志锋', null, '2018-03-17 17:33:03.1', '2160581');
INSERT INTO `bi_order` VALUES ('bi18032010395194', '佛山保利珑门四期项目土方工程2018年02月对账单', '账单', 'verify', 'zouweizheng@gd.cmcc', null, null, '佛山保利珑门四期项目土方工程', null, '2018-03-20 10:39:51.1', '2160736');
INSERT INTO `bi_order` VALUES ('bi18042409173287', '佛山保利珑门四期项目土方工程2018年04月对账单', '运输及机型费', 'verify', 'zouweizheng@gd.cmcc', null, null, '佛山保利珑门四期项目土方工程', null, '2018-04-24 09:17:32.6', '2160926');
INSERT INTO `bi_order` VALUES ('bi18042610280486', '佛山保利珑门四期项目土方工程2018年04月对账单', '账单', 'verify', 'zouweizheng@gd.cmcc', null, null, '佛山保利珑门四期项目土方工程', null, '2018-04-26 10:28:04.2', '2160939');
INSERT INTO `bi_order` VALUES ('bi18050321064622', '佛山保利珑门四期项目土方工程2018年05月对账单', '账单', 'verify', 'zouweizheng@gd.cmcc', null, null, '佛山保利珑门四期项目土方工程', null, '2018-05-03 21:06:46.5', '2160974');
INSERT INTO `bi_order` VALUES ('bi18050823494550', '佛山万科中央大街项目土方工程2018年05月对账单', '账单', 'verify', 'zouweizheng@gd.cmcc', null, null, '佛山万科中央大街项目土方工程', null, '2018-05-08 23:49:45.8', '2161046');
INSERT INTO `bi_order` VALUES ('bi18051517083738', '佛山保利珑门四期项目土方工程2018年05月对账单', '账单', 'verify', 'zouweizheng@gd.cmcc', null, null, '佛山保利珑门四期项目土方工程', null, '2018-05-15 17:08:37.3', '2161077');

-- ----------------------------
-- Table structure for `bi_order_op_operation`
-- ----------------------------
DROP TABLE IF EXISTS `bi_order_op_operation`;
CREATE TABLE `bi_order_op_operation` (
  `op_operation_id` int(20) NOT NULL,
  `bi_order_id` varchar(30) NOT NULL,
  `id` int(50) unsigned zerofill NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bi_order_op_operation
-- ----------------------------
INSERT INTO `bi_order_op_operation` VALUES ('19', 'bi18020714352892', '00000000000000000000000000000000000000000000000001');
INSERT INTO `bi_order_op_operation` VALUES ('47', 'bi18031621424868', '00000000000000000000000000000000000000000000000005');
INSERT INTO `bi_order_op_operation` VALUES ('51', 'bi18032010395194', '00000000000000000000000000000000000000000000000007');
INSERT INTO `bi_order_op_operation` VALUES ('53', 'bi18042409173287', '00000000000000000000000000000000000000000000000010');

-- ----------------------------
-- Table structure for `con_order`
-- ----------------------------
DROP TABLE IF EXISTS `con_order`;
CREATE TABLE `con_order` (
  `order_id` varchar(30) NOT NULL COMMENT '基建单号',
  `order_name` varchar(100) DEFAULT '基建单名称',
  `order_type` varchar(20) DEFAULT '基建单类型',
  `order_status` varchar(20) DEFAULT '0' COMMENT '基建单状态',
  `create_person_id` varchar(40) DEFAULT NULL COMMENT '创建人id',
  `create_person_name` varchar(100) DEFAULT NULL COMMENT '创建人姓名',
  `print_num` int(2) unsigned zerofill DEFAULT '00' COMMENT '打印次数',
  `bind_operation` int(1) unsigned zerofill DEFAULT '0',
  `belong_person_id` varchar(40) DEFAULT NULL COMMENT '所属人id',
  `belong_person_name` varchar(100) DEFAULT NULL COMMENT '所属人姓名',
  `project_id` varchar(30) DEFAULT NULL COMMENT '所属项目id',
  `project_name` varchar(200) DEFAULT NULL COMMENT '所属项目名称',
  `money` double(100,0) DEFAULT NULL COMMENT '总价格',
  `create_time` datetime(1) DEFAULT NULL COMMENT '创建时间',
  `motorcade_id` varchar(50) DEFAULT NULL COMMENT '所属车队id',
  `motorcade_name` varchar(100) DEFAULT NULL COMMENT '所属车队名称',
  `fee_type` varchar(30) DEFAULT NULL COMMENT '费用类型',
  `fee_second_type` varchar(30) DEFAULT NULL COMMENT '费用小类',
  `machine_type` varchar(30) DEFAULT NULL COMMENT '机械类型',
  `work_type` varchar(30) DEFAULT NULL COMMENT '施工类型',
  `provision_type` varchar(30) DEFAULT NULL COMMENT '供应类型',
  `destination_id` varchar(30) DEFAULT NULL COMMENT '目的项目',
  `destination_name` varchar(100) DEFAULT NULL COMMENT '目的项目名称',
  `num` int(20) DEFAULT NULL COMMENT '数量',
  `unit_price` double(100,0) DEFAULT NULL COMMENT '单价',
  `first_pic` varchar(500) DEFAULT NULL COMMENT '车头图片',
  `secondly_pic` varchar(500) DEFAULT NULL COMMENT '车尾图片',
  `thirdly_pic` varchar(500) DEFAULT NULL COMMENT '备用图片',
  `id` int(20) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `process_instance_id` varchar(40) DEFAULT NULL COMMENT '当前流程id',
  `plate_number` varchar(10) DEFAULT NULL COMMENT '车牌号码',
  `unit_word` varchar(10) DEFAULT '元/次' COMMENT '单位',
  `is_bind` varchar(10) DEFAULT 'notBind' COMMENT '是否绑定',
  `start_time` datetime(1) DEFAULT NULL COMMENT '台班开始时间',
  `end_time` datetime(1) DEFAULT NULL COMMENT '台班结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=844 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of con_order
-- ----------------------------
INSERT INTO `con_order` VALUES ('18031714393685', '基建单名称', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', null, '00', '0', null, null, null, '保利肇庆汇通', '350', '2018-03-17 14:39:37.0', '粤A1zm98', '保利肇庆汇通', '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '保利肇庆汇通', '7', '50', '20180317/20180317143920833-1521268754726.jpg', '20180317/2018031714393058-1521268763992.jpg', null, '00000000000000000718', '2160510', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18031714402325', '基建单名称', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', null, '00', '0', null, null, null, '保利肇庆汇通', '350', '2018-03-17 14:40:23.7', '粤A1zm98', '保利肇庆汇通', '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '保利肇庆汇通', '7', '50', '20180317/20180317143920833-1521268754726.jpg', '20180317/2018031714393058-1521268763992.jpg', null, '00000000000000000719', '2160518', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18031714403365', '基建单名称', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', null, '00', '0', null, null, null, '保利肇庆汇通', '350', '2018-03-17 14:40:33.8', '粤A1zm98', '保利肇庆汇通', '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '保利肇庆汇通', '7', '50', '20180317/20180317143920833-1521268754726.jpg', '20180317/2018031714393058-1521268763992.jpg', null, '00000000000000000720', '2160526', null, '天/次', 'binded', null, null);
INSERT INTO `con_order` VALUES ('18031715204956', '基建单名称', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', null, '01', '0', null, null, null, '保利肇庆汇通', '50', '2018-03-17 15:20:49.9', '佛山市航晟运输有航公司-粤Y37273', '保利肇庆汇通', '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '保利肇庆汇通', '1', '50', '20180317/20180317152013740-1521271205287.jpg', '20180317/20180317152022291-1521271216456.jpg', null, '00000000000000000721', '2160536', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18031715211011', '基建单名称', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', null, '02', '0', null, null, null, '保利肇庆汇通', '50', '2018-03-17 15:21:10.4', '佛山市航晟运输有航公司-粤Y37273', '保利肇庆汇通', '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '保利肇庆汇通', '1', '50', '20180317/20180317152013740-1521271205287.jpg', '20180317/20180317152022291-1521271216456.jpg', null, '00000000000000000722', '2160544', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18031721033833', '基建单名称', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', null, '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-03-17 21:03:39.0', '佛山市航晟运输有限公司-粤Y37295', '佛山保利珑门四期项目土方工程', '机械费', '50', 'PC350', '台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '20180317/20180317210324986-1521291798327.jpg', '20180317/20180317210335969-1521291807643.jpg', null, '00000000000000000723', '2160612', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18031910240192', '基建单名称', '基建单类型', 'construction', 'ruanjunjie@gd.cmcc', null, '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-03-19 10:24:02.0', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', '20180319/2018031910235286-1521426226332.jpg', '20180319/20180319102400743-1521426234631.jpg', null, '00000000000000000724', '2160625', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18031910430994', '基建单名称', '基建单类型', 'construction', 'ruanjunjie@gd.cmcc', null, '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-03-19 10:43:09.9', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', '20180319/20180319104302944-1521427377445.jpg', '20180319/20180319104308809-1521427382961.jpg', null, '00000000000000000725', '2160633', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18031910503798', '基建单名称', '基建单类型', 'construction', 'ruanjunjie@gd.cmcc', null, '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-03-19 10:50:37.7', '粤123', null, '机械费', '柴油费', 'PC200', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', '20180319/2018031910502045-1521427813140.jpg', '20180319/20180319105035414-1521427829728.jpg', null, '00000000000000000726', '2160641', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18031911433830', '测试0319工单', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '测试0319', '1100', '2018-03-19 11:43:38.9', null, null, '车辆运输费', null, '农用车', '外运', null, null, '广州', '11', '100', '20180319/20180319114336451-addVisitor-bg.jpg', null, null, '00000000000000000727', '2160649', null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18031917202166', '基建单名称', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', null, '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '100', '2018-03-19 17:20:21.1', '', '佛山保利珑门四期项目土方工程', '机械费', '50', 'PC350', '台班', null, null, '佛山保利珑门四期项目土方工程', '2', '50', '20180319/20180319172015964-1521451206931.jpg', '20180319/2018031917201781-1521451213282.jpg', null, '00000000000000000728', '2160657', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18031918035533', '基建单名称', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', null, '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-03-19 18:03:55.2', '', '佛山保利珑门四期项目土方工程', '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '20180319/20180319180345889-1521453820101.jpg', '20180319/20180319180352568-1521453828403.jpg', null, '00000000000000000729', '2160700', null, '天/次', 'binded', null, null);
INSERT INTO `con_order` VALUES ('18031918041891', '基建单名称', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', null, '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-03-19 18:04:18.3', '', '佛山保利珑门四期项目土方工程', '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '20180319/20180319180345889-1521453820101.jpg', '20180319/20180319180352568-1521453828403.jpg', null, '00000000000000000730', '2160708', null, '天/次', 'binded', null, null);
INSERT INTO `con_order` VALUES ('18032011421666', '项目0320工单', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '项目0320', '242', '2018-03-20 11:42:16.4', null, null, '其他费用', null, '场外中转费用', null, null, null, '广州', '22', '11', '20180320/20180320114208624-addVisitor-bg.jpg', null, null, '00000000000000000731', '2160744', null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18032015300779', '测试0320-1名称', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '测试0320-1', '2200', '2018-03-20 15:30:07.2', null, null, '车辆运输费', null, '农用车', '台班', null, null, '广州', '20', '110', '20180320/20180320153004999-addVisitor-bg.jpg', null, null, '00000000000000000732', '2160765', null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18032017385066', '基建单名称', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', null, '01', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-03-20 17:38:50.5', '', '佛山保利珑门四期项目土方工程', '机械费', '50', 'PC200', '台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '20180320/20180320173845275-1521538712778.jpg', '20180320/20180320173847778-1521538723435.jpg', null, '00000000000000000733', '2160774', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18032117584432', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', null, '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '200', '2018-03-21 17:58:44.7', '588888', '佛山保利珑门四期项目土方工程', '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '4', '50', '', '', null, '00000000000000000734', '2160791', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18032208370948', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-03-22 08:37:10.0', '', null, '车辆运输', '20', '孖担车（标准型）', '台班', null, null, '佛山保利珑门四期项目土方工程', null, '20', '20180322/20180322083703409-1521679018179.jpg', '20180322/20180322083707999-1521679023528.jpg', null, '00000000000000000735', '2160799', null, '元/次', 'notBind', '2018-03-22 08:36:00.0', '2018-03-23 08:36:00.0');
INSERT INTO `con_order` VALUES ('18032208390263', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '万科佛山金辉', '660', '2018-03-22 08:39:02.6', '', null, '车辆运输', '20', '孖担车（标准型）', '台班', null, null, '佛山保利珑门四期项目土方工程', '33', '20', '20180322/20180322083857205-1521679132320.jpg', '20180322/20180322083901572-1521679136864.jpg', null, '00000000000000000736', '2160807', null, '元/次', 'notBind', '2018-03-22 08:38:00.0', '2018-03-22 08:38:00.0');
INSERT INTO `con_order` VALUES ('18032208414328', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-03-22 08:41:43.4', '', null, '车辆运输', '20', '孖担车（标准型）', '台班', null, null, '佛山保利珑门四期项目土方工程', null, '20', '20180322/20180322084134260-1521679289110.jpg', '20180322/20180322084140468-1521679294965.jpg', null, '00000000000000000737', '2160815', null, '元/次', 'notBind', '2018-03-22 08:40:00.0', '2018-03-24 08:41:00.0');
INSERT INTO `con_order` VALUES ('18032209481344', '基建单名称', '基建单类型', 'construction', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-03-22 09:48:13.8', '', null, '车辆运输', '20', '孖担车（标准型）', '台班', null, null, '佛山保利珑门四期项目土方工程', null, '20', '20180322/20180322094806750-1521683281821.jpg', '20180322/20180322094811974-1521683287789.jpg', null, '00000000000000000738', '2160823', null, '元/次', 'binded', '2018-03-22 09:47:00.0', '2018-03-26 09:47:00.0');
INSERT INTO `con_order` VALUES ('18032220134918', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-03-22 20:13:49.6', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', '20180322/20180322201343374-1521720816696.jpg', '20180322/20180322201348159-1521720823197.jpg', null, '00000000000000000739', '2160832', null, '天/噢', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18032220162733', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '120', '2018-03-22 20:16:27.9', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '3', '40', '20180322/20180322201600504-1521720955433.jpg', '20180322/20180322201625660-1521720979445.jpg', null, '00000000000000000740', '2160840', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18032220313077', '基建单名称', '基建单类型', 'construction', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '60', '2018-03-22 20:31:30.2', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '2', '30', '20180322/20180322203123214-1521721875572.jpg', '20180322/20180322203128379-1521721884185.jpg', null, '00000000000000000741', '2160848', null, '天/哦', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18032615225674', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', null, '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '100', '2018-03-26 15:22:56.0', '', '佛山保利珑门四期项目土方工程', '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '2', '50', '20180326/20180326152245623-1522048954038.jpg', '20180326/20180326152249595-1522048964408.jpg', null, '00000000000000000742', '2160856', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18032615232026', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', null, '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '100', '2018-03-26 15:23:20.0', '', '佛山保利珑门四期项目土方工程', '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '2', '50', '20180326/20180326152245623-1522048954038.jpg', '20180326/20180326152249595-1522048964408.jpg', null, '00000000000000000743', '2160864', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18033016062159', '测试0330工地单', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '测试0330项目', '220', '2018-03-30 16:06:21.0', null, null, '其他费用', null, '场外中转费用', null, null, null, '广州', '20', '11', '20180330/20180330160618536-addVisitor-bg.jpg', null, null, '00000000000000000744', '2160882', null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18033016080680', '测试0330-2', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '测试0330-2项目', '2442', '2018-03-30 16:08:06.3', null, null, '车辆运输费', null, '农用车', '外运', null, null, '广州', '22', '111', '20180330/20180330160804466-addVisitor-bg.jpg', null, null, '00000000000000000745', '2160890', null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18033016201837', '测试西门', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '测试测试', '242', '2018-03-30 16:20:18.7', null, null, '其他费用', null, '场外中转费用', null, null, null, '湖南', '22', '11', '20180330/20180330162014865-addVisitor-bg.jpg', null, null, '00000000000000000746', '2160898', null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18033016231933', '324', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '稳稳稳', '51948', '2018-03-30 16:23:19.1', null, null, '车辆运输费', null, '农用车', '外运', null, null, '测', '234', '222', '20180330/20180330162316565-addVisitor-bg.jpg', null, null, '00000000000000000747', '2160906', null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18040214200166', '测试0402', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '测试0402', '220', '2018-04-02 14:20:01.2', null, null, '其他费用', null, '场外中转费用', null, null, null, '广州', '20', '11', null, null, null, '00000000000000000748', '2160914', null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18050321012011', '项目0503工单', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '项目0503', '220', '2018-05-03 21:01:20.1', null, null, '车辆运输费', null, '农用车', '外运', null, null, '广州', '22', '10', null, null, null, '00000000000000000749', '2160948', null, '元/次', 'binded', null, null);
INSERT INTO `con_order` VALUES ('18050823282526', '邹伟政0508基建测试', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '邹伟政0508基建测试', '44', '2018-05-08 23:28:25.4', null, null, '机械费', null, '拖拉机', null, null, null, '22', '2', '22', null, null, null, '00000000000000000750', '2160986', null, '元/次', 'binded', null, null);
INSERT INTO `con_order` VALUES ('18050823325163', '邹伟政0508基建测试-未通过', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '邹伟政0508基建测试-未通过', '4', '2018-05-08 23:32:51.8', null, null, '其他费用', null, '场外中转费用', null, null, null, '11', '2', '2', null, null, null, '00000000000000000751', '2161007', null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18050823353883', '邹伟政0508基建测试2', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '邹伟政0508基建测试2', '1', '2018-05-08 23:35:38.3', null, null, '其他费用', null, '场外中转费用', null, null, null, '22', '1', '1', null, null, null, '00000000000000000752', '2161015', null, '元/次', 'binded', null, null);
INSERT INTO `con_order` VALUES ('18051512515774', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', null, '00', '0', null, null, null, '万科A项目', '0', '2018-05-15 12:51:57.6', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '万科A项目', null, '50', '', '', null, '00000000000000000753', '2161060', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18051512520684', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', null, '00', '0', null, null, null, '万科A项目', '0', '2018-05-15 12:52:06.1', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '万科A项目', null, '50', '', '', null, '00000000000000000754', '2161068', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052121293228', '测试项目0521工单', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '测试项目0521', '200', '2018-05-21 21:29:32.6', null, null, '其他费用', null, '场外中转费用', null, null, null, '广州', '20', '10', null, null, null, '00000000000000000755', '2162506', null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311165872', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-05-23 11:16:58.7', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/2', null, '00000000000000000756', '2162519', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311184846', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-05-23 11:18:48.1', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045456970.jpg', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045471600.jpg', null, '00000000000000000757', '2162527', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311185618', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-05-23 11:18:56.5', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045456970.jpg', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045471600.jpg', null, '00000000000000000758', '2162535', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311191431', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-05-23 11:19:14.2', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045456970.jpg', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045471600.jpg', null, '00000000000000000759', '2162543', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311191879', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-05-23 11:19:18.2', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045456970.jpg', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045471600.jpg', null, '00000000000000000760', '2162551', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311192194', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-05-23 11:19:21.5', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045456970.jpg', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045471600.jpg', null, '00000000000000000761', '2162559', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311194867', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-05-23 11:19:48.3', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045579548.jpg', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045586454.jpg', null, '00000000000000000762', '2162567', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311195089', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-05-23 11:19:50.9', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045579548.jpg', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045586454.jpg', null, '00000000000000000763', '2162575', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311202526', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-05-23 11:20:25.3', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045579548.jpg', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045586454.jpg', null, '00000000000000000764', '2162583', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311211645', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-05-23 11:21:16.4', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045657605.jpg', 'https://gdfsc-construction.oss-cn-shenzhen.aliyuncs.com/1527045664581.jpg', null, '00000000000000000765', '2162591', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311212668', '11', '基建单类型', '0', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '11', '1', '2018-05-23 11:21:26.9', null, null, '其他费用', null, '场外中转费用', null, null, null, '11', '1', '1', null, null, null, '00000000000000000766', '2162599', null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311251014', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-05-23 11:25:10.2', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', '1527045877230.jpg', '1527045902026.jpg', null, '00000000000000000767', '2162607', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311252593', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-05-23 11:25:25.8', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', '1527045877230.jpg', '1527045902026.jpg', null, '00000000000000000768', '2162615', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311273617', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-05-23 11:27:36.3', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', '1527045877230.jpg', '1527045902026.jpg', null, '00000000000000000769', '2162623', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311302180', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-05-23 11:30:21.9', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', '1527045877230.jpg', '1527045902026.jpg', null, '00000000000000000770', '2162631', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311312827', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '0', '2018-05-23 11:31:28.6', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', 'gg', 'aa', null, '00000000000000000771', '2162639', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311373031', '2', '基建单类型', '0', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '2', '4', '2018-05-23 11:37:30.7', null, null, '人工费', null, '杂工费', null, null, null, '2', '2', '2', null, null, null, '00000000000000000772', '2162647', null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311544254', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 11:54:42.6', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '1527047672488.jpg', '1527047679653.jpg', null, '00000000000000000773', '2162655', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311544533', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 11:54:45.5', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '1527047672488.jpg', '1527047679653.jpg', null, '00000000000000000774', '2162663', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311544827', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 11:54:48.8', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '1527047672488.jpg', '1527047679653.jpg', null, '00000000000000000775', '2162671', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311554896', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 11:55:48.8', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '1527047729497.jpg', '1527047743065.jpg', null, '00000000000000000776', '2162679', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052311583121', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 11:58:31.8', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '1527047729497.jpg', '1527047743065.jpg', null, '00000000000000000777', '2162687', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052312001147', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 12:00:11.1', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', null, '50', '1527047729497.jpg', '1527047743065.jpg', null, '00000000000000000778', '2162695', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052314114467', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 14:11:44.2', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '1527055891298.jpg', '1527055900680.jpg', null, '00000000000000000779', '2162703', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052314121687', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 14:12:16.8', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '1527055891298.jpg', '1527055900680.jpg', null, '00000000000000000780', '2162711', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052314253318', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 14:25:33.2', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '1527056713620.jpg', '1527056718579.jpg', null, '00000000000000000781', '2162719', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052314290423', '基建单名称', '基建单类型', '0', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '200', '2018-05-23 14:29:04.9', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '4', '50', 'gg', 'aa', null, '00000000000000000782', '2162727', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052314291336', '基建单名称', '基建单类型', '0', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '200', '2018-05-23 14:29:13.0', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '4', '50', 'gg', 'aa', null, '00000000000000000783', '2162735', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052314294376', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 14:29:43.2', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '1527056713620.jpg', '1527056718579.jpg', null, '00000000000000000784', '2162743', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052314310989', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 14:31:09.7', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '1527056713620.jpg', '1527056718579.jpg', null, '00000000000000000785', '2162751', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052314315140', '基建单名称', '基建单类型', '0', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 14:31:51.6', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '1527056713620.jpg', '1527056718579.jpg', null, '00000000000000000786', '2162759', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052314375950', '2017年12月19日基建测试单', '工地单', '0', 'zouweizheng@gd.cmcc', null, '00', '0', null, null, 'wk1001', '万科A项目', '3322', '2018-05-23 14:37:59.1', null, null, '运输费', '车辆运输、柴油台班、柴油费、工程材料', '孖担车', '外运、中转（场外）、台班', '', 'bl1078', '保利B工地', '89', '49', '', '', '', '00000000000000000787', '2162767', null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052314450848', '2017年12月19日基建测试单', '工地单', '0', 'zouweizheng@gd.cmcc', null, '00', '0', null, null, 'wk1001', '万科A项目', '3322', '2018-05-23 14:45:08.6', null, null, '运输费', '车辆运输、柴油台班、柴油费、工程材料', '孖担车', '外运、中转（场外）、台班', '', 'bl1078', '保利B工地', '89', '49', '', '', '', '00000000000000000788', null, null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052314453696', '2017年12月19日基建测试单', '工地单', 'verify', 'zouweizheng@gd.cmcc', null, '00', '0', null, null, 'wk1001', '万科A项目', '3322', '2018-05-23 14:45:36.1', null, null, '运输费', '车辆运输、柴油台班、柴油费、工程材料', '孖担车', '外运、中转（场外）、台班', '', 'bl1078', '保利B工地', '89', '49', '', '', '', '00000000000000000789', '2165005', null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052314460090', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 14:46:00.5', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '1527056713620.jpg', '1527056718579.jpg', null, '00000000000000000790', '2165013', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052314460269', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 14:46:02.7', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '1527056713620.jpg', '1527056718579.jpg', null, '00000000000000000791', '2165021', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052314481354', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 14:48:13.6', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '1527058085000.jpg', '1527058092201.jpg', null, '00000000000000000792', '2165029', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052314535249', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 14:53:52.3', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '1527058424346.jpg', '1527058431249.jpg', null, '00000000000000000793', '2165037', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052315542724', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 15:54:27.6', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527061959173.jpg', '2018-05-23/1527061981057.jpg', null, '00000000000000000794', '2167505', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052315570377', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 15:57:03.1', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527062215666.jpg', '2018-05-23/1527062221443.jpg', null, '00000000000000000795', '2167513', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052316355431', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 16:35:54.2', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527064318453.jpg', '2018-05-23/1527064547734.jpg', null, '00000000000000000796', '2167521', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052316361868', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '02', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 16:36:18.4', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527064318453.jpg', '2018-05-23/1527064547734.jpg', null, '00000000000000000797', '2167529', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052316380328', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山万科中央大街项目土方工程', '50', '2018-05-23 16:38:03.6', '', null, '机械费', '50', 'PC200', '外运（车）', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527064671310.jpg', '2018-05-23/1527064680684.jpg', null, '00000000000000000798', '2167537', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052316382035', '基建单名称', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山万科中央大街项目土方工程', '50', '2018-05-23 16:38:20.1', '', null, '机械费', '50', 'PC200', '外运（车）', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527064671310.jpg', '2018-05-23/1527064680684.jpg', null, '00000000000000000799', '2167545', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052316383889', '基建单名称', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山万科中央大街项目土方工程', '50', '2018-05-23 16:38:38.6', '', null, '机械费', '50', 'PC200', '外运（车）', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527064671310.jpg', '2018-05-23/1527064680684.jpg', null, '00000000000000000800', '2167553', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052316390014', '基建单名称', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山万科中央大街项目土方工程', '50', '2018-05-23 16:39:00.1', '', null, '机械费', '50', 'PC200', '外运（车）', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527064671310.jpg', '2018-05-23/1527064680684.jpg', null, '00000000000000000801', '2167561', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052316391552', '基建单名称', '基建单类型', 'construction', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山万科中央大街项目土方工程', '50', '2018-05-23 16:39:15.5', '', null, '机械费', '50', 'PC200', '外运（车）', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527064671310.jpg', '2018-05-23/1527064680684.jpg', null, '00000000000000000802', '2167569', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052319522715', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 19:52:27.6', '', null, '车辆运输', '柴油用班', 'PC200', '台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527076342716.jpg', '2018-05-23/1527076342716.jpg', null, '00000000000000000803', '2167609', null, '天/次', 'notBind', '2018-05-23 19:52:00.0', '2018-05-23 19:52:00.0');
INSERT INTO `con_order` VALUES ('18052319524052', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 19:52:40.2', '', null, '车辆运输', '柴油用班', 'PC200', '台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527076342716.jpg', '2018-05-23/1527076342716.jpg', null, '00000000000000000804', '2167617', null, '天/次', 'notBind', '2018-05-23 19:52:00.0', '2018-05-23 19:52:00.0');
INSERT INTO `con_order` VALUES ('18052319524644', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 19:52:46.6', '', null, '车辆运输', '柴油用班', 'PC200', '台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527076342716.jpg', '2018-05-23/1527076342716.jpg', null, '00000000000000000805', '2167625', null, '天/次', 'notBind', '2018-05-23 19:52:00.0', '2018-05-23 19:52:00.0');
INSERT INTO `con_order` VALUES ('18052319525220', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 19:52:52.4', '', null, '车辆运输', '柴油用班', 'PC200', '台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527076342716.jpg', '2018-05-23/1527076342716.jpg', null, '00000000000000000806', '2167633', null, '天/次', 'notBind', '2018-05-23 19:52:00.0', '2018-05-23 19:52:00.0');
INSERT INTO `con_order` VALUES ('18052319530430', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 19:53:04.1', '', null, '车辆运输', '柴油用班', 'PC200', '台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527076342716.jpg', '2018-05-23/1527076342716.jpg', null, '00000000000000000807', '2167641', null, '天/次', 'notBind', '2018-05-23 19:52:00.0', '2018-05-23 19:52:00.0');
INSERT INTO `con_order` VALUES ('18052319530414', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 19:53:04.3', '', null, '车辆运输', '柴油用班', 'PC200', '台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527076342716.jpg', '2018-05-23/1527076342716.jpg', null, '00000000000000000808', '2167649', null, '天/次', 'notBind', '2018-05-23 19:52:00.0', '2018-05-23 19:52:00.0');
INSERT INTO `con_order` VALUES ('18052319530435', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 19:53:04.6', '', null, '车辆运输', '柴油用班', 'PC200', '台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527076342716.jpg', '2018-05-23/1527076342716.jpg', null, '00000000000000000809', '2167657', null, '天/次', 'notBind', '2018-05-23 19:52:00.0', '2018-05-23 19:52:00.0');
INSERT INTO `con_order` VALUES ('18052319530496', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 19:53:04.8', '', null, '车辆运输', '柴油用班', 'PC200', '台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527076342716.jpg', '2018-05-23/1527076342716.jpg', null, '00000000000000000810', '2167665', null, '天/次', 'notBind', '2018-05-23 19:52:00.0', '2018-05-23 19:52:00.0');
INSERT INTO `con_order` VALUES ('18052319530560', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 19:53:05.1', '', null, '车辆运输', '柴油用班', 'PC200', '台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527076342716.jpg', '2018-05-23/1527076342716.jpg', null, '00000000000000000812', '2167681', null, '天/次', 'notBind', '2018-05-23 19:52:00.0', '2018-05-23 19:52:00.0');
INSERT INTO `con_order` VALUES ('18052319530588', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 19:53:05.3', '', null, '车辆运输', '柴油用班', 'PC200', '台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527076342716.jpg', '2018-05-23/1527076342716.jpg', null, '00000000000000000813', '2167689', null, '天/次', 'notBind', '2018-05-23 19:52:00.0', '2018-05-23 19:52:00.0');
INSERT INTO `con_order` VALUES ('18052319533884', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 19:53:39.0', '', null, '车辆运输', '柴油用班', 'PC200', '台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527076342716.jpg', '2018-05-23/1527076342716.jpg', null, '00000000000000000814', '2167697', null, '天/次', 'notBind', '2018-05-23 19:52:00.0', '2018-05-23 19:52:00.0');
INSERT INTO `con_order` VALUES ('18052319572052', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 19:57:21.0', '', null, '车辆运输', '柴油用班', 'PC200', '台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527076342716.jpg', '2018-05-23/1527076342716.jpg', null, '00000000000000000815', '2167705', null, '天/次', 'notBind', '2018-05-23 19:52:00.0', '2018-05-23 19:52:00.0');
INSERT INTO `con_order` VALUES ('18052319574022', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 19:57:40.8', '', null, '车辆运输', '柴油用班', 'PC200', '台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527076342716.jpg', '2018-05-23/1527076342716.jpg', null, '00000000000000000816', '2167713', null, '天/次', 'notBind', '2018-05-23 19:52:00.0', '2018-05-23 19:52:00.0');
INSERT INTO `con_order` VALUES ('18052320372763', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 20:37:27.7', '', null, '机械费', '柴油费', '农用车', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527078990135.jpg', '2018-05-23/1527079038647.jpg', null, '00000000000000000817', '2167721', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052320375324', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 20:37:54.0', '', null, '机械费', '柴油费', '农用车', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527078990135.jpg', '2018-05-23/1527079038647.jpg', null, '00000000000000000818', '2167729', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052320380294', '基建单名称', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', 'zouweizheng@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-23 20:38:02.2', '', null, '机械费', '柴油费', '农用车', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-23/1527078990135.jpg', '2018-05-23/1527079038647.jpg', null, '00000000000000000819', '2167737', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052418581111', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-24 18:58:11.9', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-24/1527159461522.jpg', '2018-05-24/1527159489216.jpg', null, '00000000000000000820', '2167745', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052419001495', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-05-24 19:00:14.6', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-05-24/1527159602238.jpg', '2018-05-24/1527159609319.jpg', null, '00000000000000000822', '2167761', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18052815325342', '测试项目0528工单', '基建单类型', 'verify', 'zouweizheng@gd.cmcc', '邹伟政', '00', '0', null, null, null, '测试项目0528', '2000', '2018-05-28 15:32:53.8', null, null, '车辆运输费', null, '农用车', '中转', null, null, '广州', '20', '100', null, null, null, '00000000000000000823', '2167769', null, '元/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609144162', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:14:41.8', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528247665957.jpg', '2018-06-06/1528247678669.jpg', null, '00000000000000000824', '2167777', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609144864', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:14:48.3', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528247665957.jpg', '2018-06-06/1528247678669.jpg', null, '00000000000000000825', '2167785', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609150364', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:15:03.3', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528247665957.jpg', '2018-06-06/1528247678669.jpg', null, '00000000000000000826', '2167793', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609150527', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:15:05.9', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528247665957.jpg', '2018-06-06/1528247678669.jpg', null, '00000000000000000827', '2167801', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609364529', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:36:45.1', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528248990384.jpg', '2018-06-06/1528249001507.jpg', null, '00000000000000000828', '2167809', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609382241', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:38:22.5', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528249071116.jpg', '2018-06-06/1528249086983.jpg', null, '00000000000000000829', '2167817', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609395880', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:39:58.3', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528249172739.jpg', '2018-06-06/1528249195477.jpg', null, '00000000000000000830', '2167825', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609424488', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:42:44.4', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528249351910.jpg', '2018-06-06/1528249362069.jpg', null, '00000000000000000831', '2167833', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609445286', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:44:52.1', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528249483618.jpg', '2018-06-06/1528249489817.jpg', null, '00000000000000000832', '2167841', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609445934', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:44:59.2', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528249483618.jpg', '2018-06-06/1528249489817.jpg', null, '00000000000000000833', '2167849', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609450443', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:45:04.6', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528249483618.jpg', '2018-06-06/1528249489817.jpg', null, '00000000000000000834', '2167857', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609452385', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:45:23.5', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528249483618.jpg', '2018-06-06/1528249489817.jpg', null, '00000000000000000835', '2167865', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609452966', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:45:29.6', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528249483618.jpg', '2018-06-06/1528249489817.jpg', null, '00000000000000000836', '2167873', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609453584', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:45:35.5', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528249483618.jpg', '2018-06-06/1528249489817.jpg', null, '00000000000000000837', '2167881', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609454323', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:45:43.3', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528249483618.jpg', '2018-06-06/1528249489817.jpg', null, '00000000000000000838', '2167889', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060609455084', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 09:45:50.1', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528249483618.jpg', '2018-06-06/1528249489817.jpg', null, '00000000000000000839', '2167897', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060610363368', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 10:36:33.7', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528252583995.jpg', '2018-06-06/1528252590644.jpg', null, '00000000000000000840', '2167905', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060610364043', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 10:36:40.3', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528252583995.jpg', '2018-06-06/1528252590644.jpg', null, '00000000000000000841', '2167913', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060616003467', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 16:00:34.9', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528272023989.jpg', '2018-06-06/1528272032553.jpg', null, '00000000000000000842', '2167921', null, '天/次', 'notBind', null, null);
INSERT INTO `con_order` VALUES ('18060616005571', '基建单名称', '基建单类型', 'verify', 'ruanjunjie@gd.cmcc', 'ruanjunjie@gd.cmcc', '00', '0', null, null, null, '佛山保利珑门四期项目土方工程', '50', '2018-06-06 16:00:55.1', '', null, '车辆运输', '柴油费', '孖担车（标准型）', '柴油台班', null, null, '佛山保利珑门四期项目土方工程', '1', '50', '2018-06-06/1528272023989.jpg', '2018-06-06/1528272032553.jpg', null, '00000000000000000843', '2167929', null, '天/次', 'notBind', null, null);

-- ----------------------------
-- Table structure for `op_operation`
-- ----------------------------
DROP TABLE IF EXISTS `op_operation`;
CREATE TABLE `op_operation` (
  `id` int(25) unsigned NOT NULL AUTO_INCREMENT COMMENT '基建单号',
  `operation_name` varchar(100) DEFAULT '基建单名称',
  `operation_status` varchar(20) DEFAULT '0' COMMENT '审批状态',
  `op_order_id` varchar(40) DEFAULT NULL,
  `fee_type` varchar(20) DEFAULT '基建单类型',
  `project_id` varchar(30) DEFAULT NULL,
  `project_name` varchar(200) DEFAULT NULL,
  `belong_person_id` varchar(40) DEFAULT NULL,
  `belong_person_name` varchar(100) DEFAULT NULL,
  `money` double(100,0) DEFAULT NULL,
  `update_time` datetime(1) DEFAULT NULL,
  `is_invoice` int(1) DEFAULT NULL,
  `discount` double(10,0) DEFAULT NULL,
  `deduction` double(10,0) DEFAULT NULL,
  `op_order_name` varchar(100) DEFAULT NULL,
  `pay_status` varchar(20) DEFAULT 'notPay' COMMENT '付款状态',
  `bill_order_id` varchar(40) DEFAULT 'billNotBinded' COMMENT '绑定账单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of op_operation
-- ----------------------------
INSERT INTO `op_operation` VALUES ('19', '万科A项目-运输费', '0', '333', '运输费', 'wk1001', '万科A项目', 'zouweizheng', '邹伟政', '3299', '2018-01-01 15:52:30.0', '1', '1', '23', '邹伟政12月份对账单', null, 'billNotBinded');
INSERT INTO `op_operation` VALUES ('21', null, '0', '2332', null, null, null, null, null, null, '2018-01-11 17:29:19.0', null, null, null, null, null, 'billNotBinded');
INSERT INTO `op_operation` VALUES ('33', '万科B项目-机械费', '0', 'op2018011516393009785', '机械费', null, '万科B项目', null, null, '306', '2018-01-25 15:42:27.0', '1', '1', '34', '2018年01月12日对单测试单', null, 'billNotBinded');
INSERT INTO `op_operation` VALUES ('39', '万科A项目-运输费', '0', 'op2018011516422623199', '运输费', 'wk1001', '万科A项目', null, null, '368631', '2018-01-30 17:45:34.0', '111', '111', '111', '11', null, 'billNotBinded');
INSERT INTO `op_operation` VALUES ('40', '万科B项目-车辆运输', '0', 'op18021414501114', '车辆运输', null, '万科B项目', null, null, '49', '2018-02-14 14:51:14.0', '1', '1', '1', '1', null, 'billNotBinded');
INSERT INTO `op_operation` VALUES ('41', '项目0225-车辆运输费', '0', 'op18022520022371', '车辆运输费', null, '项目0225', 'pn_huangdongcheng', '黄东成', '900', '2018-02-25 20:03:01.0', null, '1', '100', '对单0225', 'notPay', 'billNotBinded');
INSERT INTO `op_operation` VALUES ('42', '保利肇庆汇通-机械费', '0', 'op18030422585566', '机械费', null, '保利肇庆汇通', 'pn_huangdongcheng', '黄东成', '147', '2018-03-04 23:00:40.0', null, '3', '3', '保利万科', 'notPay', 'billNotBinded');
INSERT INTO `op_operation` VALUES ('43', '测试项目0315-2-车辆运输费', '0', 'op18031615270218', '车辆运输费', null, '测试项目0315-2', 'pn_huangdongcheng', '黄东成', '-200', '2018-03-16 15:29:26.0', null, '0', '200', '对单0315', 'notPay', 'billNotBinded');
INSERT INTO `op_operation` VALUES ('44', '保利肇庆汇通-车辆运输', '0', 'op18030423171192', '车辆运输', null, '保利肇庆汇通', 'pn_huangdongcheng', '黄东成', '147', '2018-03-16 16:10:19.0', null, '3', '3', '梁志锋', 'notPay', 'billNotBinded');
INSERT INTO `op_operation` VALUES ('45', '保利肇庆汇通-机械费', '0', 'op18030423171192', '机械费', null, '保利肇庆汇通', 'pn_huangdongcheng', '黄东成', '147', '2018-03-16 16:17:37.0', null, '3', '3', '梁志锋', 'notPay', 'billNotBinded');
INSERT INTO `op_operation` VALUES ('46', '万科A项目-车辆运输', '0', 'op18030516260253', '车辆运输', null, '万科A项目', 'pn_huangdongcheng', '黄东成', '-144', '2018-03-16 16:30:27.0', null, '3', '444', '王强', 'notPay', 'billNotBinded');
INSERT INTO `op_operation` VALUES ('47', '测试项目0316-4-人工费', '0', 'op18031621331939', '人工费', null, '测试项目0316-4', 'pn_huangdongcheng', '黄东成', '32700', '2018-03-16 21:35:49.0', null, '30', '300', '测试对单0316', 'notPay', 'billNotBinded');
INSERT INTO `op_operation` VALUES ('48', '测试项目0316-4-人工费', '0', 'op18031621384934', '人工费', null, '测试项目0316-4', 'pn_huangdongcheng', '黄东成', '32700', '2018-03-16 21:41:05.0', null, '30', '300', '测试对单0316-1', 'notPay', 'billNotBinded');
INSERT INTO `op_operation` VALUES ('49', '万科佛山金辉-机械费', '0', 'op18031711451310', '机械费', null, '万科佛山金辉', 'pn_huangdongcheng', '黄东成', '127', '2018-03-17 11:45:41.0', null, '1', '23', '梁志锋', 'notPay', 'billNotBinded');
INSERT INTO `op_operation` VALUES ('50', '保利肇庆汇通-车辆运输', '0', 'op18031715293971', '车辆运输', null, '保利肇庆汇通', 'pn_lihuiteng', '李辉腾', '-333334', '2018-03-17 15:35:12.0', null, '0', '333334', '梁志锋', 'notPay', 'billNotBinded');
INSERT INTO `op_operation` VALUES ('51', '佛山保利珑门四期项目土方工程-机械费', '0', 'op18031715293971', '机械费', null, '佛山保利珑门四期项目土方工程', 'pn_lihuiteng', '李辉腾', '-333334', '2018-03-17 21:06:21.0', null, '0', '333334', '梁志锋', 'notPay', 'billNotBinded');
INSERT INTO `op_operation` VALUES ('52', '佛山保利珑门四期项目土方工程-车辆运输', '0', 'op18030422232458', '车辆运输', null, '佛山保利珑门四期项目土方工程', 'pn_lihuiteng', '李辉腾', '27', '2018-03-19 18:06:56.0', '1', '1', '23', '2018年3月8日', 'notPay', 'billNotBinded');
INSERT INTO `op_operation` VALUES ('53', '佛山保利珑门四期项目土方工程-车辆运输', '0', 'op18032011452290', '车辆运输', null, '佛山保利珑门四期项目土方工程', 'pn_huangdongcheng', '黄东成', '-100', '2018-03-26 20:20:17.0', null, '1', '100', '2018年3月20日黄东成核对单', 'notPay', 'bi18042409173287');
INSERT INTO `op_operation` VALUES ('54', '项目0503-车辆运输费', '0', 'op18050321052341', '车辆运输费', null, '项目0503', 'pn_huangdongcheng', '黄东成', '120', '2018-05-03 21:05:49.0', null, '1', '100', '2018年5月3日黄东成核对单', 'notPay', 'billNotBinded');
INSERT INTO `op_operation` VALUES ('55', '邹伟政0508基建测试-机械费', '0', 'op18050823315630', '机械费', null, '邹伟政0508基建测试', 'pn_huangdongcheng', '黄东成', '44', '2018-05-08 23:33:48.0', null, '1', '0', '2018年5月8日黄东成核对单', 'notPay', 'billNotBinded');
INSERT INTO `op_operation` VALUES ('56', '邹伟政0508基建测试2-其他费用', '0', 'op18050823315630', '其他费用', null, '邹伟政0508基建测试2', 'pn_huangdongcheng', '黄东成', '1', '2018-05-08 23:36:12.0', null, '1', '0', '2018年5月8日黄东成核对单', 'notPay', 'billNotBinded');
INSERT INTO `op_operation` VALUES ('57', '测试测试-其他费用', '0', 'op18050823445290', '其他费用', null, '测试测试', 'pn_lihuiteng', '李辉腾', '242', '2018-05-12 17:22:03.0', null, '1', '0', '2018年5月8日李辉腾核对单', 'notPay', 'billNotBinded');

-- ----------------------------
-- Table structure for `op_order`
-- ----------------------------
DROP TABLE IF EXISTS `op_order`;
CREATE TABLE `op_order` (
  `order_id` varchar(25) NOT NULL COMMENT '基建单号',
  `order_name` varchar(100) DEFAULT '基建单名称',
  `order_type` varchar(20) DEFAULT '基建单类型',
  `order_status` varchar(20) DEFAULT NULL,
  `create_person_id` varchar(40) DEFAULT NULL,
  `create_person_name` varchar(100) DEFAULT NULL,
  `belong_person_id` varchar(40) DEFAULT NULL,
  `belong_person_name` varchar(100) DEFAULT NULL,
  `create_time` datetime(1) DEFAULT NULL,
  `is_invoice` int(1) DEFAULT NULL,
  `discount` double(10,0) DEFAULT NULL,
  `deduction` double(10,0) DEFAULT NULL,
  `process_instance_id` varchar(100) DEFAULT NULL,
  `id` int(30) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of op_order
-- ----------------------------
INSERT INTO `op_order` VALUES ('333', '邹伟政12月份对账单', '对账单', '0', 'liangbiying', '梁碧莹', 'zouweizheng', '邹伟政', '2018-01-01 13:10:49.0', '1', '1', '23', null, '1');
INSERT INTO `op_order` VALUES ('op2018010721090795787', '2017年12月19日基建核对单测试单', '对单', 'construction', 'zouweizheng@gd.cmcc', null, null, null, '2018-01-07 21:09:08.0', null, null, null, null, '3');
INSERT INTO `op_order` VALUES ('op18011211245494', '2018年01月12日对单测试单', '对单', null, 'zouweizheng@gd.cmcc', null, null, null, '2018-01-12 11:24:54.6', '1', '1', '34', null, '5');
INSERT INTO `op_order` VALUES ('op18011211264050', '2018年01月12日对单测试单', '对单', null, 'zouweizheng@gd.cmcc', null, null, null, '2018-01-12 11:26:40.5', '1', '1', '34', null, '7');
INSERT INTO `op_order` VALUES ('op2018011511422489615', '2018年01月12日对单测试单', '对单', null, 'zouweizheng@gd.cmcc', null, null, null, '2018-01-15 11:42:24.9', '1', '1', '34', null, '9');
INSERT INTO `op_order` VALUES ('op2018011516393009785', '2018年01月12日对单测试单', '对单', null, 'zouweizheng@gd.cmcc', null, null, null, '2018-01-15 16:39:30.1', '1', '1', '34', null, '11');
INSERT INTO `op_order` VALUES ('op2018011516422623199', '11', '对单', null, 'zouweizheng@gd.cmcc', null, null, null, '2018-01-15 16:42:26.2', '111', '111', '111', null, '13');
INSERT INTO `op_order` VALUES ('op18021316500615', '2018', '22', 'verify', 'zouweizheng@gd.cmcc', null, null, null, '2018-02-13 16:50:06.5', '1', '1', '34', '2148008', '15');
INSERT INTO `op_order` VALUES ('op18021414501114', '1', '1', 'construction', 'zouweizheng@gd.cmcc', null, null, null, '2018-02-14 14:50:11.3', '1', '1', '1', '2148121', '16');
INSERT INTO `op_order` VALUES ('op18022500491272', '测试0225', '222', 'verify', 'zouweizheng@gd.cmcc', null, 'pn_huangdongcheng', '黄东成', '2018-02-25 00:49:12.7', '22', '1', '2', '2148158', '17');
INSERT INTO `op_order` VALUES ('op18022520022371', '对单0225', '对单类型1', 'construction', 'zouweizheng@gd.cmcc', null, 'pn_huangdongcheng', '黄东成', '2018-02-25 20:02:23.4', null, '1', '100', '2148190', '18');
INSERT INTO `op_order` VALUES ('op18030422232458', '2018年3月8日', '运输费', 'construction', 'zouweizheng@gd.cmcc', null, 'pn_lihuiteng', '李辉腾', '2018-03-04 22:23:24.1', '1', '1', '23', '2148286', '19');
INSERT INTO `op_order` VALUES ('op18030422280354', 'bi18030422270939', '1', 'construction', 'zouweizheng@gd.cmcc', null, 'pn_huangdongcheng', '黄东成', '2018-03-04 22:28:03.9', '1', '1', '1', '2148302', '20');
INSERT INTO `op_order` VALUES ('op18030422585566', '保利万科', '机械费', 'construction', 'zouweizheng@gd.cmcc', null, 'pn_huangdongcheng', '黄东成', '2018-03-04 22:58:55.1', null, '3', '3', '2148326', '21');
INSERT INTO `op_order` VALUES ('op18030423171192', '梁志锋', '机械费', 'verify', 'zouweizheng@gd.cmcc', null, 'pn_huangdongcheng', '黄东成', '2018-03-04 23:17:11.9', null, '3', '3', '2148352', '22');
INSERT INTO `op_order` VALUES ('op18030516260253', '王强', '机械费', 'construction', 'zouweizheng@gd.cmcc', null, 'pn_huangdongcheng', '黄东成', '2018-03-05 16:26:02.7', null, '3', '444', '2148712', '23');
INSERT INTO `op_order` VALUES ('op18031615270218', '对单0315', '基建', 'construction', 'zouweizheng@gd.cmcc', null, 'pn_huangdongcheng', '黄东成', '2018-03-16 15:27:02.4', null, '0', '200', '2160061', '24');
INSERT INTO `op_order` VALUES ('op18031621331939', '测试对单0316', '基建单', 'construction', 'zouweizheng@gd.cmcc', null, 'pn_huangdongcheng', '黄东成', '2018-03-16 21:33:19.2', null, '30', '300', '2160164', '25');
INSERT INTO `op_order` VALUES ('op18031621384934', '测试对单0316-1', '基建单', 'construction', 'zouweizheng@gd.cmcc', null, 'pn_huangdongcheng', '黄东成', '2018-03-16 21:38:49.7', null, '30', '300', '2160177', '26');
INSERT INTO `op_order` VALUES ('op18031711170467', '梁志锋', '机械费', 'construction', 'zouweizheng@gd.cmcc', null, 'pn_huangdongcheng', '黄东成', '2018-03-17 11:17:04.4', null, '30', '9999', '2160207', '27');
INSERT INTO `op_order` VALUES ('op18031711451310', '梁志锋', '机械费', 'construction', 'zouweizheng@gd.cmcc', null, 'pn_huangdongcheng', '黄东成', '2018-03-17 11:45:13.7', null, '1', '23', '2160253', '28');
INSERT INTO `op_order` VALUES ('op18031715293971', '梁志锋', '机械费', 'construction', 'zouweizheng@gd.cmcc', null, 'pn_lihuiteng', '李辉腾', '2018-03-17 15:29:39.9', null, '0', '333334', '2160552', '29');
INSERT INTO `op_order` VALUES ('op18032011452290', '2018年3月20日黄东成核对单', '基建单', 'construction', 'zouweizheng@gd.cmcc', null, 'pn_huangdongcheng', '黄东成', '2018-03-20 11:45:22.7', null, '1', '100', '2160757', '30');
INSERT INTO `op_order` VALUES ('op18050321052341', '2018年5月3日黄东成核对单', '对单', 'construction', 'zouweizheng@gd.cmcc', null, 'pn_huangdongcheng', '黄东成', '2018-05-03 21:05:23.8', null, '1', '100', '2160961', '31');
INSERT INTO `op_order` VALUES ('op18050823315630', '2018年5月8日黄东成核对单', '核对单', 'construction', 'zouweizheng@gd.cmcc', null, 'pn_huangdongcheng', '黄东成', '2018-05-08 23:31:56.2', null, '1', '0', '2160999', '32');
INSERT INTO `op_order` VALUES ('op18050823445290', '2018年5月8日李辉腾核对单', '地方', 'construction', 'zouweizheng@gd.cmcc', null, 'pn_lihuiteng', '李辉腾', '2018-05-08 23:44:52.4', null, '1', '0', '2161033', '33');
INSERT INTO `op_order` VALUES ('op18052316440897', '2018年5月23日黄东成核对单', '机型费', 'verify', 'zouweizheng@gd.cmcc', null, 'pn_huangdongcheng', '黄东成', '2018-05-23 16:44:08.6', null, '1', '100', '2167577', '34');

-- ----------------------------
-- Table structure for `op_order_con_order`
-- ----------------------------
DROP TABLE IF EXISTS `op_order_con_order`;
CREATE TABLE `op_order_con_order` (
  `op_order_id` varchar(30) NOT NULL,
  `con_order_id` varchar(30) NOT NULL,
  `id` int(40) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of op_order_con_order
-- ----------------------------
INSERT INTO `op_order_con_order` VALUES ('op2018010721090795787', 'con2017121916010054884', '17');
INSERT INTO `op_order_con_order` VALUES ('op2018011516422623199', 'con2017122814302402771', '34');
INSERT INTO `op_order_con_order` VALUES ('op2018011516393009785', 'con2018011320082872531', '39');
INSERT INTO `op_order_con_order` VALUES ('op18021414501114', '18021409175832', '42');
INSERT INTO `op_order_con_order` VALUES ('op18022520022371', '18022520004691', '43');
INSERT INTO `op_order_con_order` VALUES ('op18030422585566', '18030422555250', '44');
INSERT INTO `op_order_con_order` VALUES ('op18030422585566', '18030422550360', '45');
INSERT INTO `op_order_con_order` VALUES ('op18030423171192', '18030423101574', '50');
INSERT INTO `op_order_con_order` VALUES ('op18031615270218', '18031516584284', '51');
INSERT INTO `op_order_con_order` VALUES ('op18030516260253', '18021310185438', '52');
INSERT INTO `op_order_con_order` VALUES ('op18031621384934', '18031621305730', '56');
INSERT INTO `op_order_con_order` VALUES ('op18031711451310', '18031711443097', '57');
INSERT INTO `op_order_con_order` VALUES ('op18031711451310', '18031618110170', '58');
INSERT INTO `op_order_con_order` VALUES ('op18031715293971', '18031714403365', '59');
INSERT INTO `op_order_con_order` VALUES ('op18031715293971', '18031721033833', '60');
INSERT INTO `op_order_con_order` VALUES ('op18030422232458', '18031918035533', '61');
INSERT INTO `op_order_con_order` VALUES ('op18030422232458', '18031918041891', '62');
INSERT INTO `op_order_con_order` VALUES ('op18032011452290', '18032209481344', '63');
INSERT INTO `op_order_con_order` VALUES ('op18050321052341', '18050321012011', '64');
INSERT INTO `op_order_con_order` VALUES ('op18050823315630', '18050823282526', '66');
INSERT INTO `op_order_con_order` VALUES ('op18050823315630', '18050823353883', '67');
INSERT INTO `op_order_con_order` VALUES ('op18050823445290', '18033016201837', '68');
DROP TRIGGER IF EXISTS `bi_order_after_update`;
DELIMITER ;;
CREATE TRIGGER `bi_order_after_update` AFTER UPDATE ON `bi_order` FOR EACH ROW BEGIN

if new.bill_status != old.bill_status && new.bill_status = "payed" then

update op_operation set pay_status = "payed" where op_operation.id in (select op_operation_id from bi_order_op_operation where bi_order_id = new.bill_id);

end if;

END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `bi_order_op_operation_after_insert`;
DELIMITER ;;
CREATE TRIGGER `bi_order_op_operation_after_insert` AFTER INSERT ON `bi_order_op_operation` FOR EACH ROW BEGIN


set @money = (select money from op_operation where op_operation.id = new.op_operation_id);
set @now_time = now();
set @bill_id = new.bi_order_id;
set @op_operation_id = new.op_operation_id;
set @nowMoney = @money + (select money from bi_order where bill_id = @bill_id);
update op_operation set bill_order_id = @bill_id where op_operation.id = @op_operation_id;
update bi_order set money = @nowMoney where bill_id = @bill_id;


END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `bi_order_op_operation_after_delete`;
DELIMITER ;;
CREATE TRIGGER `bi_order_op_operation_after_delete` AFTER DELETE ON `bi_order_op_operation` FOR EACH ROW BEGIN


set @money = (select money from op_operation where op_operation.id = old.op_operation_id);
set @now_time = now();
set @bill_id = old.bi_order_id;
set @op_operation_id = old.op_operation_id;
set @nowMoney = (select money from bi_order where bill_id = @bill_id) - @money;
update op_operation set bill_order_id = "billNotBinded" where op_operation.id = @op_operation_id;
update bi_order set money = @nowMoney where bill_id = @bill_id;


END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `op_order_after_update`;
DELIMITER ;;
CREATE TRIGGER `op_order_after_update` AFTER UPDATE ON `op_order` FOR EACH ROW BEGIN

if new.order_status != old.order_status && new.order_status = "construction" then

update con_order set is_bind = "binded" where con_order.order_id in (select con_order_id from op_order_con_order where op_order_id = new.order_id);

end if;

END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `insert_op_order_con_order`;
DELIMITER ;;
CREATE TRIGGER `insert_op_order_con_order` AFTER INSERT ON `op_order_con_order` FOR EACH ROW BEGIN

set @fee_type = (select fee_type from con_order where con_order.order_id = new.con_order_id);
set @project_id = (select project_id from con_order where con_order.order_id = new.con_order_id);
set @project_name = (select project_name from con_order where con_order.order_id = new.con_order_id);
set @money = (select money from con_order where con_order.order_id = new.con_order_id);
set @order_name = (select order_name from op_order where op_order.order_id = new.op_order_id);
set @belong_person_id = (select belong_person_id from op_order where op_order.order_id = new.op_order_id);
set @belong_person_name = (select belong_person_name from op_order where op_order.order_id = new.op_order_id);
set @is_invoice = (select is_invoice from op_order where op_order.order_id = new.op_order_id);
set @discount = (select discount from op_order where op_order.order_id = new.op_order_id);
set @deduction = (select deduction from op_order where op_order.order_id = new.op_order_id);
set @operation_name = (select concat(project_name,"-",fee_type) from con_order where con_order.order_id = new.con_order_id);
set @now_time = now();

set @num = (select count(*) from op_operation where fee_type = @fee_type and project_name = @project_name and op_order_id = new.op_order_id);
if @num = 0 then
insert into op_operation(fee_type,project_id,project_name,money,belong_person_id,belong_person_name,is_invoice,discount,deduction,op_order_name,op_order_id,operation_name,update_time) 
values(@fee_type,@project_id,@project_name,@money*@discount-@deduction,@belong_person_id,@belong_person_name,@is_invoice,@discount,@deduction,@order_name,new.op_order_id,@operation_name,@now_time);
else 
update op_operation set money = money+@money*@discount-@deduction , update_time = @now_time  where fee_type = @fee_type and project_id = @project_id and op_order_id = new.op_order_id;
end if;

END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `op_order_con_order_after_delete`;
DELIMITER ;;
CREATE TRIGGER `op_order_con_order_after_delete` AFTER DELETE ON `op_order_con_order` FOR EACH ROW BEGIN

set @fee_type = (select fee_type from con_order where con_order.order_id = old.con_order_id);
set @project_id = (select project_id from con_order where con_order.order_id = old.con_order_id);
set @con_money = (select money from con_order where con_order.order_id = old.con_order_id);
set @op_money = (select money from op_operation where op_operation.op_order_id = old.op_order_id);
set @discount = (select discount from op_order where op_order.order_id = old.op_order_id);
set @deduction = (select deduction from op_order where op_order.order_id = old.op_order_id);

set @remain_money = @op_money - @con_money;
if @remain_money <= 0 then
delete from op_operation where fee_type = @fee_type and project_id = @project_id and op_order_id = old.op_order_id;
else 
update op_operation set money = money-@con_money*@discount-@deduction , update_time = @now_time  where fee_type = @fee_type and project_id = @project_id and op_order_id = old.op_order_id;
end if;

END
;;
DELIMITER ;
