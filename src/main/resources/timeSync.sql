/*
 Navicat Premium Data Transfer

 Source Server         : docker_mysql
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : timeSync

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 13/09/2023 09:48:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`) USING BTREE,
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE,
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`) USING BTREE,
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_locks` (`SCHED_NAME`, `LOCK_NAME`) VALUES ('quartzScheduler', 'TRIGGER_ACCESS');
COMMIT;

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE,
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`) USING BTREE,
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `param_key` varchar(200) NOT NULL COMMENT '参数名',
  `param_value` varchar(200) DEFAULT NULL COMMENT '参数值',
  `status` tinyint(1) unsigned NOT NULL COMMENT '状态',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_param_key` (`param_key`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` (`id`, `param_key`, `param_value`, `status`, `remark`) VALUES (1, 'attendance_start_time', '06:00', 1, '上班考勤开始时间');
INSERT INTO `sys_config` (`id`, `param_key`, `param_value`, `status`, `remark`) VALUES (2, 'attendance_time', '08:30', 1, '上班时间');
INSERT INTO `sys_config` (`id`, `param_key`, `param_value`, `status`, `remark`) VALUES (3, 'attendance_end_time', '09:30', 1, '上班考勤截止时间');
INSERT INTO `sys_config` (`id`, `param_key`, `param_value`, `status`, `remark`) VALUES (4, 'closing_start_time', '16:30', 1, '下班考勤开始时间');
INSERT INTO `sys_config` (`id`, `param_key`, `param_value`, `status`, `remark`) VALUES (5, 'closing_time', '17:30', 1, '下班时间');
INSERT INTO `sys_config` (`id`, `param_key`, `param_value`, `status`, `remark`) VALUES (6, 'closing_end_time', '23:59', 1, '下班考勤截止时间');
COMMIT;

-- ----------------------------
-- Table structure for tb_action
-- ----------------------------
DROP TABLE IF EXISTS `tb_action`;
CREATE TABLE `tb_action` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `action_code` varchar(200) NOT NULL COMMENT '行为编号',
  `action_name` varchar(200) NOT NULL COMMENT '行为名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_action_name` (`action_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='行为表';

-- ----------------------------
-- Records of tb_action
-- ----------------------------
BEGIN;
INSERT INTO `tb_action` (`id`, `action_code`, `action_name`) VALUES (1, 'INSERT', '添加');
INSERT INTO `tb_action` (`id`, `action_code`, `action_name`) VALUES (2, 'DELETE', '删除');
INSERT INTO `tb_action` (`id`, `action_code`, `action_name`) VALUES (3, 'UPDATE', '修改');
INSERT INTO `tb_action` (`id`, `action_code`, `action_name`) VALUES (4, 'SELECT', '查询');
INSERT INTO `tb_action` (`id`, `action_code`, `action_name`) VALUES (5, 'APPROVAL', '审批');
INSERT INTO `tb_action` (`id`, `action_code`, `action_name`) VALUES (6, 'EXPORT', '导出');
INSERT INTO `tb_action` (`id`, `action_code`, `action_name`) VALUES (7, 'BACKUP', '备份');
COMMIT;

-- ----------------------------
-- Table structure for tb_checkin
-- ----------------------------
DROP TABLE IF EXISTS `tb_checkin`;
CREATE TABLE `tb_checkin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户ID',
  `address` varchar(255) DEFAULT NULL COMMENT '签到地址',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `district` varchar(255) DEFAULT NULL COMMENT '区划',
  `status` tinyint(3) unsigned NOT NULL COMMENT '考勤结果',
  `risk` int(255) unsigned DEFAULT '0' COMMENT '风险等级',
  `date` date NOT NULL COMMENT '签到日期',
  `create_time` datetime NOT NULL COMMENT '签到时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_user_id` (`user_id`,`date`) USING BTREE,
  KEY `idx_date` (`date`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='签到表';

-- ----------------------------
-- Records of tb_checkin
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_city
-- ----------------------------
DROP TABLE IF EXISTS `tb_city`;
CREATE TABLE `tb_city` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `city` varchar(200) NOT NULL COMMENT '城市名称',
  `code` varchar(200) NOT NULL COMMENT '拼音简称',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_city` (`city`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=330 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='疫情城市列表';

-- ----------------------------
-- Records of tb_city
-- ----------------------------
BEGIN;
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (1, '阿坝市', 'ab');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (2, '安康市', 'ak');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (3, '阿克苏市', 'aks');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (4, '阿拉善市', 'alsm');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (5, '安顺市', 'anshun');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (6, '安庆市', 'aq');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (7, '鞍山市', 'as');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (8, '安阳市', 'ay');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (9, '百色市', 'baise');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (10, '白山市', 'baishan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (11, '宝鸡市', 'baoji');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (12, '巴中市', 'bazhong');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (13, '蚌埠市', 'bb');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (14, '白城市', 'bc');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (15, '保定市', 'bd');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (16, '博尔塔拉市', 'betl');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (17, '北海市', 'bh');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (18, '毕节市', 'bijie');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (19, '北京市', 'bj');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (20, '西双版纳市', 'bn');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (21, '亳州市', 'bozhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (22, '保山市', 'bs');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (23, '包头市', 'bt');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (24, '本溪市', 'bx');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (25, '白银市', 'by');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (26, '巴彦淖尔市', 'bycem');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (27, '巴音郭楞市', 'bygl');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (28, '滨州市', 'bz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (29, '沧州市', 'cangzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (30, '长春市', 'cc');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (31, '成都市', 'cd');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (32, '赤峰市', 'cf');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (33, '常德市', 'changde');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (34, '长治市', 'changzhi');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (35, '潮州市', 'chaozhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (36, '承德市', 'chengde');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (37, '郴州市', 'chenzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (38, '池州市', 'chizhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (39, '崇左市', 'chongzuo');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (40, '滁州市', 'chuzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (41, '昌吉市', 'cj');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (42, '重庆市', 'cq');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (43, '长沙市', 'cs');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (44, '楚雄市', 'cx');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (45, '朝阳市', 'cy');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (46, '常州市', 'cz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (47, '大理市', 'dali');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (48, '达州市', 'dazhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (49, '丹东市', 'dd');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (50, '德阳市', 'deyang');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (51, '东莞市', 'dg');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (52, '德宏市', 'dh');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (53, '迪庆市', 'diqing');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (54, '大连市', 'dl');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (55, '大庆市', 'dq');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (56, '大同市', 'dt');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (57, '定西市', 'dx');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (58, '大兴安岭市', 'dxal');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (59, '东营市', 'dy');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (60, '德州市', 'dz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (61, '鄂尔多斯市', 'erds');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (62, '恩施市', 'es');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (63, '鄂州市', 'ez');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (64, '防城港市', 'fcg');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (65, '佛山市', 'fs');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (66, '抚顺市', 'fushun');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (67, '抚州市', 'fuzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (68, '阜新市', 'fx');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (69, '阜阳市', 'fy');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (70, '福州市', 'fz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (71, '广安市', 'ga');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (72, '赣州市', 'ganzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (73, '甘孜市', 'ganzi');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (74, '贵港市', 'gg');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (75, '桂林市', 'gl');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (76, '广元市', 'guangyuan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (77, '果洛市', 'guoluo');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (78, '固原市', 'guyuan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (79, '贵阳市', 'gy');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (80, '广州市', 'gz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (81, '淮安市', 'ha');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (82, '海北市', 'haibei');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (83, '海东市', 'haidong');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (84, '海南市', 'hainan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (85, '汉中市', 'hanzhong');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (86, '鹤壁市', 'hb');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (87, '河池市', 'hc');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (88, '邯郸市', 'hd');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (89, '哈尔滨市', 'heb');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (90, '鹤岗市', 'hegang');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (91, '黑河市', 'heihe');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (92, '河源市', 'heyuan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (93, '菏泽市', 'heze');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (94, '贺州市', 'hezhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (95, '合肥市', 'hf');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (96, '黄冈市', 'hg');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (97, '怀化市', 'hh');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (98, '呼伦贝尔市', 'hlbe');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (99, '葫芦岛市', 'hld');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (100, '哈密市', 'hm');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (101, '淮南市', 'hn');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (102, '红河市', 'honghe');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (103, '衡水市', 'hs');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (104, '黄石市', 'hshi');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (105, '和田市', 'ht');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (106, '呼和浩特市', 'hu');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (107, '淮北市', 'huaibei');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (108, '黄南市', 'huangnan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (109, '黄山市', 'huangshan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (110, '惠州市', 'huizhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (111, '湖州市', 'huzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (112, '海西市', 'hx');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (113, '衡阳市', 'hy');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (114, '杭州市', 'hz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (115, '吉安市', 'ja');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (116, '晋城市', 'jc');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (117, '景德镇市', 'jdz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (118, '金华市', 'jh');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (119, '焦作市', 'jiaozuo');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (120, '金昌市', 'jinchang');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (121, '荆门市', 'jingmen');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (122, '荆州市', 'jingzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (123, '济宁市', 'jining');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (124, '锦州市', 'jinzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (125, '鸡西市', 'jixi');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (126, '济源市', 'jiyuan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (127, '九江市', 'jj');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (128, '吉林市', 'jl');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (129, '江门市', 'jm');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (130, '佳木斯市', 'jms');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (131, '济南市', 'jn');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (132, '酒泉市', 'jq');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (133, '嘉兴市', 'jx');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (134, '揭阳市', 'jy');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (135, '嘉峪关市', 'jyg');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (136, '晋中市', 'jz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (137, '喀什市', 'kashi');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (138, '开封市', 'kf');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (139, '克拉玛依市', 'klmy');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (140, '昆明市', 'km');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (141, '克孜勒苏市', 'kzls');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (142, '六安市', 'la');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (143, '来宾市', 'lb');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (144, '聊城市', 'lc');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (145, '娄底市', 'ld');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (146, '乐山市', 'leshan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (147, '廊坊市', 'lf');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (148, '漯河市', 'lh');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (149, '凉山市', 'liangshan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (150, '辽阳市', 'liaoyang');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (151, '辽源市', 'liaoyuan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (152, '临沧市', 'lincang');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (153, '临汾市', 'linfen');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (154, '临沂市', 'linyi');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (155, '丽水市', 'lishui');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (156, '柳州市', 'liuzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (157, '丽江市', 'lj');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (158, '吕梁市', 'll');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (159, '陇南市', 'ln');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (160, '龙岩市', 'longyan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (161, '六盘水市', 'lps');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (162, '泸州市', 'luzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (163, '洛阳市', 'ly');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (164, '连云港市', 'lyg');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (165, '兰州市', 'lz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (166, '马鞍山市', 'mas');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (167, '牡丹江市', 'mdj');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (168, '茂名市', 'mm');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (169, '眉山市', 'ms');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (170, '绵阳市', 'my');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (171, '梅州市', 'mz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (172, '南充市', 'nanchong');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (173, '宁波市', 'nb');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (174, '南昌市', 'nc');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (175, '宁德市', 'nd');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (176, '内江市', 'neijiang');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (177, '南京市', 'nj');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (178, '南宁市', 'nn');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (179, '南平市', 'np');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (180, '南通市', 'nt');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (181, '怒江市', 'nujiang');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (182, '南阳市', 'ny');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (183, '平顶山市', 'pds');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (184, '普洱市', 'pe');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (185, '盘锦市', 'pj');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (186, '平凉市', 'pl');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (187, '莆田市', 'pt');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (188, '萍乡市', 'px');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (189, '濮阳市', 'py');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (190, '攀枝花市', 'pzh');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (191, '青岛市', 'qd');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (192, '黔东南市', 'qdn');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (193, '秦皇岛市', 'qhd');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (194, '潜江市', 'qianjiang');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (195, '庆阳市', 'qingyang');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (196, '钦州市', 'qinzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (197, '曲靖市', 'qj');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (198, '黔南市', 'qn');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (199, '齐齐哈尔市', 'qqhr');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (200, '七台河市', 'qth');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (201, '衢州市', 'quzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (202, '黔西南市', 'qxn');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (203, '清远市', 'qy');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (204, '泉州市', 'qz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (205, '日照市', 'rz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (206, '三亚市', 'sanya');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (207, '韶关市', 'sg');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (208, '上海市', 'sh');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (209, '邵阳市', 'shaoyang');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (210, '十堰市', 'shiyan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (211, '朔州市', 'shuozhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (212, '石家庄市', 'sjz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (213, '商洛市', 'sl');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (214, '三明市', 'sm');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (215, '三门峡市', 'smx');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (216, '神农架市', 'snj');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (217, '松原市', 'songyuan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (218, '四平市', 'sp');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (219, '商丘市', 'sq');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (220, '上饶市', 'sr');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (221, '汕头市', 'st');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (222, '宿州市', 'su');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (223, '绥化市', 'suihua');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (224, '遂宁市', 'suining');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (225, '随州市', 'suizhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (226, '宿迁市', 'suqian');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (227, '苏州市', 'suzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (228, '汕尾市', 'sw');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (229, '绍兴市', 'sx');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (230, '沈阳市', 'sy');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (231, '双鸭山市', 'sys');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (232, '深圳市', 'bendibao.com/news/yqdengji/');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (233, '石嘴山市', 'szs');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (234, '泰安市', 'ta');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (235, '塔城市', 'tacheng');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (236, '泰州市', 'taizhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (237, '铜川市', 'tc');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (238, '通化市', 'th');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (239, '天水市', 'tianshui');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (240, '天津市', 'tj');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (241, '吐鲁番市', 'tlf');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (242, '天门市', 'tm');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (243, '通辽市', 'tongliao');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (244, '铜陵市', 'tongling');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (245, '铜仁市', 'tr');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (246, '唐山市', 'ts');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (247, '太原市', 'ty');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (248, '台州市', 'tz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (249, '威海市', 'weihai');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (250, '潍坊市', 'wf');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (251, '武汉市', 'wh');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (252, '乌兰察布市', 'wlcb');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (253, '乌鲁木齐市', 'wlmq');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (254, '渭南市', 'wn');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (255, '文山市', 'ws');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (256, '乌海市', 'wuhai');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (257, '芜湖市', 'wuhu');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (258, '武威市', 'wuwei');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (259, '吴忠市', 'wuzhong');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (260, '梧州市', 'wuzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (261, '无锡市', 'wx');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (262, '温州市', 'wz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (263, '五指山市', 'wzs');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (264, '西安市', 'xa');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (265, '兴安市', 'xam');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (266, '许昌市', 'xc');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (267, '襄阳市', 'xf');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (268, '孝感市', 'xg');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (269, '湘潭市', 'xiangtan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (270, '湘西市', 'xiangxi');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (271, '咸宁市', 'xianning');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (272, '仙桃市', 'xiantao');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (273, '咸阳市', 'xianyang');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (274, '新余市', 'xinyu');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (275, '忻州市', 'xinzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (276, '锡林郭勒市', 'xl');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (277, '厦门市', 'xm');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (278, '西宁市', 'xn');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (279, '邢台市', 'xt');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (280, '宣城市', 'xuancheng');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (281, '新乡市', 'xx');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (282, '信阳市', 'xy');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (283, '徐州市', 'xz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (284, '雅安市', 'ya');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (285, '延边市', 'yanbian');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (286, '盐城市', 'yancheng');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (287, '阳泉市', 'yangquan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (288, '宜宾市', 'yb');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (289, '银川市', 'yc');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (290, '云浮市', 'yf');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (291, '伊春市', 'yich');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (292, '宜昌市', 'yichang');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (293, '宜春市', 'yichun');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (294, '伊犁市', 'yili');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (295, '鹰潭市', 'yingtan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (296, '益阳市', 'yiyang');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (297, '阳江市', 'yj');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (298, '营口市', 'yk');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (299, '榆林市', 'yl');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (300, '延安市', 'yn');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (301, '永州市', 'yongzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (302, '玉树市', 'ys');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (303, '烟台市', 'yt');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (304, '玉林市', 'yulin');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (305, '运城市', 'yuncheng');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (306, '玉溪市', 'yx');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (307, '岳阳市', 'yy');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (308, '扬州市', 'yz');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (309, '枣庄市', 'zaozhuang');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (310, '淄博市', 'zb');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (311, '自贡市', 'zg');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (312, '珠海市', 'zh');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (313, '张掖市', 'zhangye');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (314, '漳州市', 'zhangzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (315, '湛江市', 'zhanjiang');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (316, '舟山市', 'zhoushan');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (317, '株洲市', 'zhuzhou');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (318, '镇江市', 'zj');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (319, '张家界市', 'zjj');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (320, '张家口市', 'zjk');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (321, '周口市', 'zk');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (322, '驻马店市', 'zmd');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (323, '肇庆市', 'zq');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (324, '中山市', 'zs');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (325, '昭通市', 'zt');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (326, '遵义市', 'zunyi');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (327, '中卫市', 'zw');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (328, '资阳市', 'zy');
INSERT INTO `tb_city` (`id`, `city`, `code`) VALUES (329, '郑州市', 'zz');
COMMIT;

-- ----------------------------
-- Table structure for tb_dept
-- ----------------------------
DROP TABLE IF EXISTS `tb_dept`;
CREATE TABLE `tb_dept` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dept_name` varchar(200) NOT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_dept_name` (`dept_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_dept
-- ----------------------------
BEGIN;
INSERT INTO `tb_dept` (`id`, `dept_name`) VALUES (11, '保安部');
INSERT INTO `tb_dept` (`id`, `dept_name`) VALUES (5, '后勤部');
INSERT INTO `tb_dept` (`id`, `dept_name`) VALUES (4, '市场部');
INSERT INTO `tb_dept` (`id`, `dept_name`) VALUES (3, '技术部');
INSERT INTO `tb_dept` (`id`, `dept_name`) VALUES (1, '管理部');
INSERT INTO `tb_dept` (`id`, `dept_name`) VALUES (2, '行政部');
COMMIT;

-- ----------------------------
-- Table structure for tb_face_model
-- ----------------------------
DROP TABLE IF EXISTS `tb_face_model`;
CREATE TABLE `tb_face_model` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键值',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户ID',
  `face_model` text NOT NULL COMMENT '用户人脸模型',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_face_model
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_holidays
-- ----------------------------
DROP TABLE IF EXISTS `tb_holidays`;
CREATE TABLE `tb_holidays` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `date` date NOT NULL COMMENT '日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_date` (`date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='节假日表';

-- ----------------------------
-- Records of tb_holidays
-- ----------------------------
BEGIN;
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (2, '2022-12-31');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (1, '2023-01-01');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (3, '2023-01-02');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (4, '2023-01-21');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (5, '2023-01-22');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (6, '2023-01-23');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (9, '2023-01-24');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (10, '2023-01-25');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (7, '2023-01-26');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (8, '2023-01-27');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (11, '2023-04-05');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (16, '2023-04-29');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (14, '2023-04-30');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (15, '2023-05-01');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (12, '2023-05-02');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (13, '2023-05-03');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (17, '2023-06-22');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (19, '2023-06-23');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (18, '2023-06-24');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (20, '2023-09-29');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (21, '2023-09-30');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (22, '2023-10-01');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (23, '2023-10-02');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (24, '2023-10-03');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (25, '2023-10-04');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (26, '2023-10-05');
INSERT INTO `tb_holidays` (`id`, `date`) VALUES (27, '2023-10-06');
COMMIT;

-- ----------------------------
-- Table structure for tb_meeting
-- ----------------------------
DROP TABLE IF EXISTS `tb_meeting`;
CREATE TABLE `tb_meeting` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(200) DEFAULT NULL COMMENT 'UUID',
  `title` varchar(200) NOT NULL COMMENT '会议题目',
  `creator_id` bigint(200) NOT NULL COMMENT '创建人ID',
  `date` date NOT NULL COMMENT '日期',
  `place` varchar(200) DEFAULT NULL COMMENT '开会地点',
  `start` time NOT NULL COMMENT '开始时间',
  `end` time NOT NULL COMMENT '结束时间',
  `type` smallint(6) NOT NULL COMMENT '会议类型（1在线会议，2线下会议）',
  `members` json NOT NULL COMMENT '参与者',
  `desc` varchar(200) NOT NULL COMMENT '会议内容',
  `instance_id` varchar(200) DEFAULT NULL COMMENT '工作流实例ID',
  `status` smallint(6) NOT NULL COMMENT '状态（1未开始，2进行中，3已结束）',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_creator_id` (`creator_id`) USING BTREE,
  KEY `idx_date` (`date`) USING BTREE,
  KEY `idx_type` (`type`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='会议表';

-- ----------------------------
-- Records of tb_meeting
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `permissionId` int(11) DEFAULT NULL COMMENT '权限表外键',
  `type` int(11) DEFAULT NULL COMMENT '1、2、3',
  `parent` int(11) DEFAULT NULL COMMENT '父id',
  `createAt` datetime DEFAULT NULL,
  `updateAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
BEGIN;
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (1, 'Monitor', '系统总览', '/main/analysis', NULL, 1, NULL, '2023-09-11 14:28:17', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (2, 'DataAnalysis', '系统纵览', '/main/analysis/dashboard', NULL, 2, 1, '2023-09-11 14:33:44', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (3, 'Setting', '系统管理', '/main/system', NULL, 1, NULL, '2023-09-11 14:41:04', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (4, 'User', '用户管理', '/main/system/user', NULL, 2, 3, '2023-09-11 15:19:42', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (5, NULL, '用户增加', NULL, 1, 3, 4, '2023-09-11 15:22:59', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (6, NULL, '用户删除', NULL, 2, 3, 4, '2023-09-11 15:32:22', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (7, NULL, '用户修改', NULL, 3, 3, 4, '2023-09-11 15:34:09', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (8, NULL, '用户查询', NULL, 4, 3, 4, '2023-09-11 15:34:26', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (9, 'Aim', '角色管理', '/main/system/role', NULL, 2, 3, '2023-09-11 15:37:55', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (10, NULL, '角色增加', NULL, 5, 3, 9, '2023-09-11 15:38:32', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (11, NULL, '角色删除', NULL, 6, 3, 9, '2023-09-11 15:38:55', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (12, NULL, '角色修改', NULL, 7, 3, 9, '2023-09-11 15:39:10', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (13, NULL, '角色查询', NULL, 8, 3, 9, '2023-09-11 15:39:25', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (14, 'EditPen', '考勤管理', '/main/system/check', NULL, 2, 3, '2023-09-11 15:40:39', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (15, NULL, '考勤增加', NULL, 9, 3, 14, '2023-09-11 15:41:02', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (16, NULL, '考勤删除', NULL, 10, 3, 14, '2023-09-11 15:41:22', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (17, NULL, '考勤修改', NULL, 11, 3, 14, '2023-09-11 15:41:39', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (18, NULL, '考勤查询', NULL, 12, 3, 14, '2023-09-11 15:41:56', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (19, 'ChatDotSquare', '会议管理', '/main/system/meeting', NULL, 2, 3, '2023-09-11 15:42:35', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (20, NULL, '会议增加', NULL, 13, 3, 19, '2023-09-11 15:43:05', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (21, NULL, '会议删除', NULL, 14, 3, 19, '2023-09-11 15:43:19', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (22, NULL, '会议修改', NULL, 15, 3, 19, '2023-09-11 15:43:36', NULL);
INSERT INTO `tb_menu` (`id`, `icon`, `name`, `url`, `permissionId`, `type`, `parent`, `createAt`, `updateAt`) VALUES (23, NULL, '会议查询', NULL, 16, 3, 19, '2023-09-11 15:43:49', NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_module
-- ----------------------------
DROP TABLE IF EXISTS `tb_module`;
CREATE TABLE `tb_module` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module_code` varchar(200) NOT NULL COMMENT '模块编号',
  `module_name` varchar(200) NOT NULL COMMENT '模块名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_module_id` (`module_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='模块资源表';

-- ----------------------------
-- Records of tb_module
-- ----------------------------
BEGIN;
INSERT INTO `tb_module` (`id`, `module_code`, `module_name`) VALUES (1, 'USER', '用户管理');
INSERT INTO `tb_module` (`id`, `module_code`, `module_name`) VALUES (2, 'ROLE', '角色管理');
INSERT INTO `tb_module` (`id`, `module_code`, `module_name`) VALUES (3, 'CHECK', '考勤管理');
INSERT INTO `tb_module` (`id`, `module_code`, `module_name`) VALUES (4, 'MEETING', '会议管理');
INSERT INTO `tb_module` (`id`, `module_code`, `module_name`) VALUES (5, 'WORKFLOW', '工作流管理');
COMMIT;

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_name` varchar(200) NOT NULL COMMENT '权限',
  `module_id` int(10) unsigned NOT NULL COMMENT '模块ID',
  `action_id` int(10) unsigned NOT NULL COMMENT '行为ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_permission` (`permission_name`) USING BTREE,
  UNIQUE KEY `unq_complex` (`module_id`,`action_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
BEGIN;
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (0, 'ROOT', 0, 0);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (1, 'USER:INSERT', 1, 1);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (2, 'USER:DELETE', 1, 2);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (3, 'USER:UPDATE', 1, 3);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (4, 'USER:SELECT', 1, 4);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (5, 'ROLE:INSERT', 2, 1);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (6, 'ROLE:DELETE', 2, 2);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (7, 'ROLE:UPDATE', 2, 3);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (8, 'ROLE:SELECT', 2, 4);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (9, 'CHECK:INSERT', 3, 1);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (10, 'CHECK:DELETE', 3, 2);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (11, 'CHECK:UPDATE', 3, 3);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (12, 'CHECK:SELECT', 3, 4);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (13, 'MEETING:INSERT', 4, 1);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (14, 'MEETING:DELETE', 4, 2);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (15, 'MEETING:UPDATE', 4, 3);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (16, 'MEETING:SELECT', 4, 4);
INSERT INTO `tb_permission` (`id`, `permission_name`, `module_id`, `action_id`) VALUES (17, 'WORKFLOW:APPROVAL', 5, 5);
COMMIT;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(200) NOT NULL COMMENT '角色名称',
  `permissions` json NOT NULL COMMENT '权限集合',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_role_name` (`role_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_role` (`id`, `role_name`, `permissions`) VALUES (0, '超级管理员', '[0]');
INSERT INTO `tb_role` (`id`, `role_name`, `permissions`) VALUES (1, '总经理', '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17]');
INSERT INTO `tb_role` (`id`, `role_name`, `permissions`) VALUES (2, '部门经理', '[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]');
INSERT INTO `tb_role` (`id`, `role_name`, `permissions`) VALUES (3, '普通员工', '[1, 2, 3, 4, 5, 6, 7, 8]');
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `open_id` varchar(200) DEFAULT NULL COMMENT '长期授权字符串',
  `nickname` varchar(200) DEFAULT NULL COMMENT '昵称',
  `photo` varchar(200) DEFAULT NULL COMMENT '头像网址',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` enum('男','女') DEFAULT NULL COMMENT '性别',
  `tel` char(11) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `hiredate` date DEFAULT NULL COMMENT '入职日期',
  `role` json NOT NULL COMMENT '角色',
  `root` tinyint(1) NOT NULL COMMENT '是否是超级管理员',
  `dept_id` int(10) unsigned DEFAULT NULL COMMENT '部门编号',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_open_id` (`open_id`) USING BTREE,
  KEY `unq_email` (`email`) USING BTREE,
  KEY `idx_dept_id` (`dept_id`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` (`id`, `open_id`, `nickname`, `photo`, `name`, `sex`, `tel`, `email`, `hiredate`, `role`, `root`, `dept_id`, `status`, `create_time`) VALUES (18, 'oy7Ez5A7ePX1iG2MgGK1WOgz4X0k', 'fishx', 'user/1bbchznd.jpeg', NULL, NULL, '15073058367', NULL, NULL, '[0]', 1, NULL, 1, '2023-09-12 19:10:11');
COMMIT;

-- ----------------------------
-- Table structure for tb_workday
-- ----------------------------
DROP TABLE IF EXISTS `tb_workday`;
CREATE TABLE `tb_workday` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `date` date DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_date` (`date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tb_workday
-- ----------------------------
BEGIN;
INSERT INTO `tb_workday` (`id`, `date`) VALUES (1, '2023-01-28');
INSERT INTO `tb_workday` (`id`, `date`) VALUES (2, '2023-01-29');
INSERT INTO `tb_workday` (`id`, `date`) VALUES (3, '2023-04-23');
INSERT INTO `tb_workday` (`id`, `date`) VALUES (4, '2023-05-06');
INSERT INTO `tb_workday` (`id`, `date`) VALUES (5, '2023-06-25');
INSERT INTO `tb_workday` (`id`, `date`) VALUES (7, '2023-10-07');
INSERT INTO `tb_workday` (`id`, `date`) VALUES (6, '2023-10-08');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
