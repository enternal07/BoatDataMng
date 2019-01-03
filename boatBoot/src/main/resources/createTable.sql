CREATE DATABASE  IF NOT EXISTS mysql_dingqi ;
USE mysql_dingqi;

DROP TABLE IF EXISTS bigmetadata;
CREATE TABLE bigmetadata (
  pk varchar(36) NOT NULL,
  sample_pk varchar(36) DEFAULT NULL,
  testmodel_pk varchar(36) DEFAULT NULL,
  testsystem_pk varchar(36) DEFAULT NULL,
  temparture float DEFAULT NULL,
  press int(11) DEFAULT NULL,
  ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  sample_name varchar(45) DEFAULT NULL,
  testmodel_name varchar(45) DEFAULT NULL,
  testsystem_name varchar(45) DEFAULT NULL,
  PRIMARY KEY (pk)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS contractionmetadata;
CREATE TABLE contractionmetadata (
  pk varchar(36) NOT NULL,
  samplename varchar(36) DEFAULT NULL,
  testtime varchar(36) DEFAULT NULL,
  ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  backgroundtype varchar(10) DEFAULT NULL,
  backgroundtyplogo varchar(45) DEFAULT NULL,
  samplelogo varchar(45) DEFAULT NULL,
  sampledescription varchar(1024) DEFAULT NULL,
  PRIMARY KEY (pk)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS item;
CREATE TABLE item (
  pk varchar(36) NOT NULL,
  rate int(11) DEFAULT NULL,
  refect  decimal(32,8) DEFAULT NULL,
  transmission  decimal(32,8) DEFAULT NULL,
  bondacust  decimal(32,8) DEFAULT NULL,
  ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  small_pk varchar(36) DEFAULT NULL,
  PRIMARY KEY (pk)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS item_scale;
CREATE TABLE item_scale (
  pk varchar(36) NOT NULL,
  rate int(11) DEFAULT NULL,
  light_shell_ts  decimal(32,8) DEFAULT NULL,
  light_shell_sp  decimal(32,8) DEFAULT NULL,
  laying_shell_ts  decimal(32,8) DEFAULT NULL,
  laying_shell_sp  decimal(32,8) DEFAULT NULL,
  reduction_ts  decimal(32,8) DEFAULT NULL,
  reduction_sp  decimal(32,8) DEFAULT NULL,
  scalemata_pk varchar(36) DEFAULT NULL,
  ts timestamp NULL DEFAULT NULL,
  PRIMARY KEY (pk)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS itembig;
CREATE TABLE itembig (
  pk varchar(36) NOT NULL,
  samplepk varchar(36) DEFAULT NULL,
  rate int(11) DEFAULT NULL,
  refect  decimal(32,8) DEFAULT NULL,
  transmission  decimal(32,8) DEFAULT NULL,
  bondacust  decimal(32,8) DEFAULT NULL,
  echoes  decimal(32,8) DEFAULT NULL,
  radiation int(11) DEFAULT NULL,
  radiationlose  decimal(32,8) DEFAULT NULL,
  ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (pk)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS itemcontraction;
CREATE TABLE itemcontraction (
  pk varchar(36) NOT NULL,
  samplepk varchar(36) DEFAULT NULL,
  rate int(11) DEFAULT NULL,
  target float DEFAULT NULL,
  radiation int(11) DEFAULT NULL,
  radiationlose float DEFAULT NULL,
  ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (pk)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS laying_scheme;
CREATE TABLE laying_scheme (
  pk varchar(36) NOT NULL,
  name varchar(45) DEFAULT NULL,
  shell_surface_outer varchar(45) DEFAULT NULL,
  shell_surface_iner varchar(45) DEFAULT NULL,
  inner_shell varchar(45) DEFAULT NULL,
  ribs varchar(45) DEFAULT NULL,
  other varchar(512) DEFAULT NULL,
  logo varchar(126) DEFAULT NULL,
  ts timestamp NULL DEFAULT NULL,
  PRIMARY KEY (pk)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS samllbacking;
CREATE TABLE samllbacking (
  pk varchar(36) NOT NULL,
  backing_name varchar(50) DEFAULT NULL,
  logo varchar(50) DEFAULT NULL,
  front_medium varchar(50) DEFAULT NULL,
  end_medium varchar(50) DEFAULT NULL,
  other varchar(50) DEFAULT NULL,
  sample_pk varchar(36) DEFAULT NULL,
  ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (pk)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS sample;
CREATE TABLE sample (
  pk varchar(36) NOT NULL,
  sample_name varchar(50) DEFAULT NULL,
  logo varchar(50) DEFAULT NULL,
  density varchar(50) DEFAULT NULL,
  flexible_model varchar(50) DEFAULT NULL,
  poisson_ratio varchar(50) DEFAULT NULL,
  sound_speed varchar(50) DEFAULT NULL,
  thickness varchar(50) DEFAULT NULL,
  other varchar(128) DEFAULT NULL,
  userpk varchar(36) DEFAULT NULL,
  small tinyint(4) DEFAULT NULL,
  ts timestamp NULL DEFAULT NULL,
  PRIMARY KEY (pk)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS scale_mata;
CREATE TABLE scale_mata (
  pk varchar(36) NOT NULL,
  test_model_obj_name varchar(45) DEFAULT NULL,
  test_model_obj_pk varchar(36) DEFAULT NULL,
  laying_scheme_name varchar(45) DEFAULT NULL,
  laying_scheme_pk varchar(36) DEFAULT NULL,
  test_condition_name varchar(45) DEFAULT NULL,
  test_condition_pk varchar(36) DEFAULT NULL,
  ts timestamp NULL DEFAULT NULL,
  PRIMARY KEY (pk)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS smallmetadata;
CREATE TABLE smallmetadata (
  pk varchar(36) NOT NULL,
  samplename varchar(36) DEFAULT NULL,
  backgroundtype varchar(10) DEFAULT NULL,
  temparture float DEFAULT NULL,
  press int(11) DEFAULT NULL,
  ts timestamp NULL DEFAULT NULL,
  small tinyint(4) DEFAULT '0',
  sample_pk varchar(36) DEFAULT NULL,
  backing_pk varchar(36) DEFAULT NULL,
  PRIMARY KEY (pk)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS test_condition;
CREATE TABLE test_condition (
  pk varchar(36) NOT NULL,
  name varchar(45) DEFAULT NULL,
  test_time varchar(45) DEFAULT NULL,
  test_place varchar(45) DEFAULT NULL,
  duration varchar(45) DEFAULT NULL,
  water_depth varchar(45) DEFAULT NULL,
  test_depth varchar(45) DEFAULT NULL,
  other varchar(512) DEFAULT NULL,
  logo varchar(126) DEFAULT NULL,
  ts timestamp NULL DEFAULT NULL,
  PRIMARY KEY (pk)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS test_model_obj;
CREATE TABLE test_model_obj (
  pk varchar(36) NOT NULL,
  name varchar(45) DEFAULT NULL,
  shell_type varchar(45) DEFAULT NULL,
  shape_size varchar(76) DEFAULT NULL,
  weight varchar(45) DEFAULT NULL,
  displacement varchar(45) DEFAULT NULL,
  other varchar(512) DEFAULT NULL,
  logo varchar(512) DEFAULT NULL,
  ts timestamp NULL DEFAULT NULL,
  PRIMARY KEY (pk)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS testmodel;
CREATE TABLE testmodel (
  pk varchar(36) NOT NULL,
  testmodel_name varchar(50) DEFAULT NULL,
  logo varchar(50) DEFAULT NULL,
  size varchar(50) DEFAULT NULL,
  double_shell_spacing varchar(50) DEFAULT NULL,
  inner_shell_thickness varchar(50) DEFAULT NULL,
  inner_shell_backend varchar(50) DEFAULT NULL,
  shell_thickness varchar(50) DEFAULT NULL,
  ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  other varchar(50) DEFAULT NULL,
  PRIMARY KEY (pk)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS testsystem;
CREATE TABLE testsystem (
  pk varchar(36) NOT NULL,
  testsystem_name varchar(50) DEFAULT NULL,
  describ varchar(2000) DEFAULT NULL,
  ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (pk)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS spha_base_user;
CREATE TABLE spha_base_user (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(60) DEFAULT NULL COMMENT '用户名',
  pass varchar(60) DEFAULT NULL COMMENT '密码',
  usertype varchar(60) DEFAULT NULL COMMENT '用户类别',
  updatetime varchar(30) DEFAULT NULL COMMENT '更新时间',
  ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO spha_base_user (id, username, pass, usertype, updatetime, ts) VALUES ('1', 'admin', 'admin', '管理员', NULL, '2018-08-31 20:46:28');
INSERT INTO spha_base_user (id, username, pass, usertype, updatetime, ts) VALUES ('2', 'user', 'user', '普通用户', NULL, '2018-08-31 20:46:41');

DROP TABLE IF EXISTS spha_photo;
CREATE TABLE spha_photo (
  pk varchar(36) NOT NULL,
  infotype varchar(10) NOT NULL COMMENT '数据类别',
  model_pk varchar(50) DEFAULT NULL COMMENT '模型pk',
  name varchar(200) DEFAULT NULL COMMENT '文件名',
  prevname varchar(200) DEFAULT NULL COMMENT '源文件名',
  url text NOT NULL COMMENT '文件路径',
  deleted int(11) DEFAULT '0' COMMENT '是否删除，默认未删除',
  updatetime varchar(20) DEFAULT NULL COMMENT '更新时间',
  ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  absurl varchar(255) DEFAULT NULL,
  PRIMARY KEY (pk)
) ; 
/*修改类型字段*/
alter  table smallmetadata modify press FLOAT ; 
alter  table bigmetadata modify press FLOAT ; 
alter table spha_photo modify infotype varchar(60) not null ; 
/*添加名称*/
alter table smallmetadata add column name varchar(200) ; 
alter table bigmetadata add column name varchar(200); 
alter table scale_mata add column name varchar(200) ; 

/*20181223 名称唯一索引*/
alter table smallmetadata add unique(name) ;
alter table bigmetadata add unique(name) ; 
alter table scale_mata add unique(name) ; 
/*名称唯一索引 */
alter table sample add unique(sample_name) ;
alter table samllbacking add unique(backing_name) ; 
alter table test_condition add unique(name) ;
alter table testmodel add unique(testmodel_name) ;
alter table testsystem add unique(testsystem_name) ; 
alter table test_model_obj add unique(name) ;
alter table laying_scheme add unique(name) ;
/*名称唯一索引*/
alter table item add unique(rate,small_pk) ;
alter table itembig add unique(rate,samplepk) ; 
alter table item_scale add unique(rate,scalemata_pk) ; 

/**/
alter table spha_photo modify url longtext  ; 

