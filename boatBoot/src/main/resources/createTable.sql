CREATE DATABASE  IF NOT EXISTS `mysql_dingqi` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mysql_dingqi`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: mysql_dingqi
-- ------------------------------------------------------
-- Server version	5.6.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bigmetadata`
--

DROP TABLE IF EXISTS `bigmetadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bigmetadata` (
  `pk` varchar(36) NOT NULL,
  `sample_pk` varchar(36) DEFAULT NULL,
  `testmodel_pk` varchar(36) DEFAULT NULL,
  `testsystem_pk` varchar(36) DEFAULT NULL,
  `temparture` float DEFAULT NULL,
  `press` int(11) DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sample_name` varchar(45) DEFAULT NULL,
  `testmodel_name` varchar(45) DEFAULT NULL,
  `testsystem_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contractionmetadata`
--

DROP TABLE IF EXISTS `contractionmetadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contractionmetadata` (
  `pk` varchar(36) NOT NULL,
  `samplename` varchar(36) DEFAULT NULL,
  `testtime` varchar(36) DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `backgroundtype` varchar(10) DEFAULT NULL,
  `backgroundtyplogo` varchar(45) DEFAULT NULL,
  `samplelogo` varchar(45) DEFAULT NULL,
  `sampledescription` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `pk` varchar(36) NOT NULL,
  `rate` int(11) DEFAULT NULL,
  `refect` float DEFAULT NULL,
  `transmission` float DEFAULT NULL,
  `bondacust` float DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `small_pk` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `item_scale`
--

DROP TABLE IF EXISTS `item_scale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_scale` (
  `pk` varchar(36) NOT NULL,
  `rate` int(11) DEFAULT NULL,
  `light_shell_ts` float DEFAULT NULL,
  `light_shell_sp` float DEFAULT NULL,
  `laying_shell_ts` float DEFAULT NULL,
  `laying_shell_sp` float DEFAULT NULL,
  `reduction_ts` float DEFAULT NULL,
  `reduction_sp` float DEFAULT NULL,
  `scalemata_pk` varchar(36) DEFAULT NULL,
  `ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `itembig`
--

DROP TABLE IF EXISTS `itembig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itembig` (
  `pk` varchar(36) NOT NULL,
  `samplepk` varchar(36) DEFAULT NULL,
  `rate` int(11) DEFAULT NULL,
  `refect` float DEFAULT NULL,
  `transmission` float DEFAULT NULL,
  `bondacust` float DEFAULT NULL,
  `echoes` float DEFAULT NULL,
  `radiation` int(11) DEFAULT NULL,
  `radiationlose` float DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `itemcontraction`
--

DROP TABLE IF EXISTS `itemcontraction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemcontraction` (
  `pk` varchar(36) NOT NULL,
  `samplepk` varchar(36) DEFAULT NULL,
  `rate` int(11) DEFAULT NULL,
  `target` float DEFAULT NULL,
  `radiation` int(11) DEFAULT NULL,
  `radiationlose` float DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `laying_scheme`
--

DROP TABLE IF EXISTS `laying_scheme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `laying_scheme` (
  `pk` varchar(36) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `shell_surface_outer` varchar(45) DEFAULT NULL,
  `shell_surface_iner` varchar(45) DEFAULT NULL,
  `inner_shell` varchar(45) DEFAULT NULL,
  `ribs` varchar(45) DEFAULT NULL,
  `other` varchar(512) DEFAULT NULL,
  `logo` varchar(126) DEFAULT NULL,
  `ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `samllbacking`
--

DROP TABLE IF EXISTS `samllbacking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `samllbacking` (
  `pk` varchar(36) NOT NULL,
  `backing_name` varchar(50) DEFAULT NULL,
  `logo` varchar(50) DEFAULT NULL,
  `front_medium` varchar(50) DEFAULT NULL,
  `end_medium` varchar(50) DEFAULT NULL,
  `other` varchar(50) DEFAULT NULL,
  `sample_pk` varchar(36) DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sample`
--

DROP TABLE IF EXISTS `sample`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sample` (
  `pk` varchar(36) NOT NULL,
  `sample_name` varchar(50) DEFAULT NULL,
  `logo` varchar(50) DEFAULT NULL,
  `density` varchar(50) DEFAULT NULL,
  `flexible_model` varchar(50) DEFAULT NULL,
  `poisson_ratio` varchar(50) DEFAULT NULL,
  `sound_speed` varchar(50) DEFAULT NULL,
  `thickness` varchar(50) DEFAULT NULL,
  `other` varchar(128) DEFAULT NULL,
  `userpk` varchar(36) DEFAULT NULL,
  `small` tinyint(4) DEFAULT NULL,
  `ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `scale_mata`
--

DROP TABLE IF EXISTS `scale_mata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scale_mata` (
  `pk` varchar(36) NOT NULL,
  `test_model_obj_name` varchar(45) DEFAULT NULL,
  `test_model_obj_pk` varchar(36) DEFAULT NULL,
  `laying_scheme_name` varchar(45) DEFAULT NULL,
  `laying_scheme_pk` varchar(36) DEFAULT NULL,
  `test_condition_name` varchar(45) DEFAULT NULL,
  `test_condition_pk` varchar(36) DEFAULT NULL,
  `ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `smallmetadata`
--

DROP TABLE IF EXISTS `smallmetadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `smallmetadata` (
  `pk` varchar(36) NOT NULL,
  `samplename` varchar(36) DEFAULT NULL,
  `backgroundtype` varchar(10) DEFAULT NULL,
  `temparture` float DEFAULT NULL,
  `press` int(11) DEFAULT NULL,
  `ts` timestamp NULL DEFAULT NULL,
  `small` tinyint(4) DEFAULT '0',
  `sample_pk` varchar(36) DEFAULT NULL,
  `backing_pk` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `test_condition`
--

DROP TABLE IF EXISTS `test_condition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_condition` (
  `pk` varchar(36) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `test_time` varchar(45) DEFAULT NULL,
  `test_place` varchar(45) DEFAULT NULL,
  `duration` varchar(45) DEFAULT NULL,
  `water_depth` varchar(45) DEFAULT NULL,
  `test_depth` varchar(45) DEFAULT NULL,
  `other` varchar(512) DEFAULT NULL,
  `logo` varchar(126) DEFAULT NULL,
  `ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `test_model_obj`
--

DROP TABLE IF EXISTS `test_model_obj`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_model_obj` (
  `pk` varchar(36) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `shell_type` varchar(45) DEFAULT NULL,
  `shape_size` varchar(76) DEFAULT NULL,
  `weight` varchar(45) DEFAULT NULL,
  `displacement` varchar(45) DEFAULT NULL,
  `other` varchar(512) DEFAULT NULL,
  `logo` varchar(512) DEFAULT NULL,
  `ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testmodel`
--

DROP TABLE IF EXISTS `testmodel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testmodel` (
  `pk` varchar(36) NOT NULL,
  `testmodel_name` varchar(50) DEFAULT NULL,
  `logo` varchar(50) DEFAULT NULL,
  `size` varchar(50) DEFAULT NULL,
  `double_shell_spacing` varchar(50) DEFAULT NULL,
  `inner_shell_thickness` varchar(50) DEFAULT NULL,
  `inner_shell_backend` varchar(50) DEFAULT NULL,
  `shell_thickness` varchar(50) DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `other` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `testsystem`
--

DROP TABLE IF EXISTS `testsystem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `testsystem` (
  `pk` varchar(36) NOT NULL,
  `testsystem_name` varchar(50) DEFAULT NULL,
  `describ` varchar(2000) DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-09 10:16:58
