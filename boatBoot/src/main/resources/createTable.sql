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
-- Dumping data for table `bigmetadata`
--

LOCK TABLES `bigmetadata` WRITE;
/*!40000 ALTER TABLE `bigmetadata` DISABLE KEYS */;
INSERT INTO `bigmetadata` VALUES ('8982ec4b-bc38-4749-8a9f-0c79ffa5a119','243bb1cf-5613-4a2b-9979-5ea68d8cc950','092e9933-d953-4c8f-80e8-bfaeeca28807','ad9bb684-2f9d-416c-bb0e-d74f50f147e1',15,1,'2018-08-29 14:12:53','阿波罗','双层局域实尺度试验模型','测试系统名称');
/*!40000 ALTER TABLE `bigmetadata` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `contractionmetadata`
--

LOCK TABLES `contractionmetadata` WRITE;
/*!40000 ALTER TABLE `contractionmetadata` DISABLE KEYS */;
INSERT INTO `contractionmetadata` VALUES ('5082bac6-db74-4cc7-ae28-f069c8e7c0b4','阿波罗','20170801','2018-07-21 07:50:33','30mm',NULL,NULL,NULL);
/*!40000 ALTER TABLE `contractionmetadata` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES ('084df680-67f3-4048-8b9e-1cda0d9e8162',20,0.2,0.47,0.7391,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('118a5cfc-2cd4-4ad3-ad07-b301c6a3c12d',10,0.8,0.5,0.11,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('1b4c23e6-389d-4c57-9e1d-15691b198111',40,0.4,0.51,0.5799,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('1f6b3d1d-1252-4f93-8258-b835a2d072ed',70,0.7,0.57,0.1851,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('1fd1425b-ceb0-445f-9d93-82b303bd83d8',30,0.3,0.49,0.6699,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('21d4364d-d0c8-45f9-8e7d-9b89f021ea89',150,0.54,0.73,0.1755,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('23010418-c500-4b60-a66f-1f50471fd197',110,0.62,0.65,0.1931,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('2964d16a-be7b-4cbe-b381-9048c5775b8a',210,0.42,0.85,0.1011,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('2e74926a-c81a-44f1-b345-a9fa83f4e329',90,0.66,0.61,0.1923,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('37f0103d-9da5-425a-bf3f-894a2cd54946',160,0.52,0.75,0.1671,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('4d81a059-0591-4cad-900f-3c4da0c5f83f',220,0.4,0.81,0.1839,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('53de4640-ce25-4137-812e-c462b7d4cd8e',60,0.6,0.55,0.3375,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('5af71f9b-cb0b-47bb-bafc-a6f932a7e8d0',250,0.34,0.69,0.4083,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('5ef54f0d-3365-4a73-bb5c-c4883bd2b579',170,0.5,0.77,0.1571,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('615f66b7-b566-4172-8782-e6b37f6433fa',270,0.3,0.61,0.5379,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('7af618cb-193c-4d39-a1e4-fb51f488928f',230,0.38,0.77,0.2627,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('8738fadd-744a-4094-99b0-9e15a1ecabb3',290,0.7,0.53,0.2291,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('8c8f7825-bd2b-4bcd-81a7-0435200170b2',200,0.44,0.83,0.1175,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('962a0866-b812-4d8a-9efe-f10041c97475',100,0.64,0.63,0.1935,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('9c13af92-1e9c-4dd6-897c-3bb404eedde7',240,0.36,0.73,0.3375,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('ada76f65-5710-4993-b023-278552496dc7',180,0.48,0.79,0.1455,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('bbd38d90-c726-4e31-ae55-28247b26e6ea',120,0.6,0.67,0.1911,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('c5099e08-4912-4e93-9f63-f77c2cb126b3',280,0.71,0.57,0.171,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('e1d6a3c0-ad68-4f5a-9cb0-731148e5ac91',190,0.46,0.81,0.1323,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('e9258afc-8a1f-4fdc-b77e-0aff2b28f7e9',140,0.56,0.71,0.1823,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('ee546026-e4e9-45e6-877b-0e1a973baac2',260,0.32,0.65,0.4751,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('f1464297-aa86-43b4-a140-e3aebf0ee016',80,0.68,0.59,0.1895,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('f2e9f4e2-3f65-4fe4-be6d-3f8ae919f8cd',50,0.5,0.53,0.4691,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2'),('f6bd0c99-6ca1-4aae-8417-f5ac40197855',130,0.58,0.69,0.1875,'2018-08-29 15:02:02','a57de754-16cb-4b78-bdf1-80b7277b41f2');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `itembig`
--

LOCK TABLES `itembig` WRITE;
/*!40000 ALTER TABLE `itembig` DISABLE KEYS */;
INSERT INTO `itembig` VALUES ('0e1094e0-5c48-4c17-8859-3ae5846891a5','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',50,0.5,0.53,0.4691,3.8,106,8,'2018-08-29 14:21:28'),('0fa30d8c-ce95-43c5-8629-38cc089655b1','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',110,0.62,0.65,0.1931,4.4,112,9.2,'2018-08-29 14:21:28'),('111c0712-39af-470e-b72c-d8c5f2e3bb35','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',250,0.34,0.69,0.4083,5.8,126,12,'2018-08-29 14:21:28'),('1176ae7b-e98a-4526-9cca-46c8cc7f5d35','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',10,0.1,0.45,0.7875,3.4,102,7.2,'2018-08-29 14:21:28'),('1a412d40-7790-4da8-a518-b0852fcac743','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',170,0.5,0.77,0.1571,5,118,10.4,'2018-08-29 14:21:28'),('33e86695-be21-4e2b-b877-ee6be01e0511','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',210,0.42,0.85,0.1011,5.4,122,11.2,'2018-08-29 14:21:28'),('3ec357eb-b9a5-4e6d-85fa-df78622dbce8','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',60,0.6,0.55,0.3375,3.9,107,8.2,'2018-08-29 14:21:28'),('418bbd25-d5c8-4ece-9171-09283bcdcba2','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',140,0.56,0.71,0.1823,4.7,115,9.8,'2018-08-29 14:21:28'),('4fcfe5fb-8376-494c-821f-9272ccf30879','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',220,0.4,0.81,0.1839,5.5,123,11.4,'2018-08-29 14:21:28'),('52fd10ea-e432-466c-ae9b-9a756b100964','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',270,0.3,0.61,0.5379,6,128,12.4,'2018-08-29 14:21:28'),('5b8b0069-9f04-4714-9f2b-e4549a5656cf','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',100,0.64,0.63,0.1935,4.3,111,9,'2018-08-29 14:21:28'),('5bff617c-7842-4f93-9d26-efc6d7511a4c','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',240,0.36,0.73,0.3375,5.7,125,11.8,'2018-08-29 14:21:28'),('618a00ea-3535-47f4-b146-237b3a4ccf4d','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',80,0.68,0.59,0.1895,4.1,109,8.6,'2018-08-29 14:21:28'),('6dcb7459-8ade-482d-aad4-ee7fb9ae96fc','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',70,0.7,0.57,0.1851,4,108,8.4,'2018-08-29 14:21:28'),('6e403de9-9f75-4190-bf11-11bba832bffe','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',90,0.66,0.61,0.1923,4.2,110,8.8,'2018-08-29 14:21:28'),('88e142ff-0503-425b-84b1-5b53103836c2','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',20,0.2,0.47,0.7391,3.5,103,7.4,'2018-08-29 14:21:28'),('93de8cd2-273d-4e04-be12-c862589fb7d1','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',180,0.48,0.79,0.1455,5.1,119,10.6,'2018-08-29 14:21:28'),('9564e008-d9e9-470a-b489-fb9494da3dca','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',230,0.38,0.77,0.2627,5.6,124,11.6,'2018-08-29 14:21:28'),('a8a766c6-6b04-415f-9441-fc798ea208f7','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',160,0.52,0.75,0.1671,4.9,117,10.2,'2018-08-29 14:21:28'),('b09e0afe-24a9-4fc7-be29-00e61b4ada6b','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',30,0.3,0.49,0.6699,3.6,104,7.6,'2018-08-29 14:21:28'),('be9f6d03-c163-4677-b19d-f58f70176fa5','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',120,0.6,0.67,0.1911,4.5,113,9.4,'2018-08-29 14:21:28'),('c09c1463-290c-411c-bca5-77dc004d71d1','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',150,0.54,0.73,0.1755,4.8,116,10,'2018-08-29 14:21:28'),('c547222f-1e69-4af1-b8eb-05117772bb21','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',190,0.46,0.81,0.1323,5.2,120,10.8,'2018-08-29 14:21:28'),('d568f985-1c50-4d1f-b861-fb3341a054ab','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',290,0.7,0.53,0.2291,6.2,130,12.8,'2018-08-29 14:21:28'),('d7df4c55-ccbb-425b-ab31-2f621cfd19ed','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',260,0.32,0.65,0.4751,5.9,127,12.2,'2018-08-29 14:21:28'),('ed6e0394-8564-4817-8434-698c6684c909','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',280,0.71,0.57,0.171,6.1,129,12.6,'2018-08-29 14:21:28'),('f0e6351f-1a2d-4fe7-85ae-0f7d763f3438','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',130,0.58,0.69,0.1875,4.6,114,9.6,'2018-08-29 14:21:28'),('fcba3f8b-700a-4e1d-9c10-337244c77d2c','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',40,0.4,0.51,0.5799,3.7,105,7.8,'2018-08-29 14:21:28'),('fe5abf91-19ba-4ba8-bf7f-c40dcee823fa','8982ec4b-bc38-4749-8a9f-0c79ffa5a119',200,0.44,0.83,0.1175,5.3,121,11,'2018-08-29 14:21:28');
/*!40000 ALTER TABLE `itembig` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `itemcontraction`
--

LOCK TABLES `itemcontraction` WRITE;
/*!40000 ALTER TABLE `itemcontraction` DISABLE KEYS */;
INSERT INTO `itemcontraction` VALUES ('056a6d92-c9b6-4b5c-87c2-8f51ca106a85','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',80,4.1,109,8.6,'2018-07-21 07:50:33'),('0bf20aaa-a56d-496f-bbf2-e2f5e6a50e31','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',30,3.6,104,7.6,'2018-07-21 07:50:33'),('1a1a21c4-2666-4203-b64c-5e558481bf60','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',90,4.2,110,8.8,'2018-07-21 07:50:33'),('22bf01da-1ca0-4a89-9d23-3b31809c7fe0','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',290,6.2,130,12.8,'2018-07-21 07:50:33'),('2ddf2e7f-e08a-48bf-99d3-36571bc5b08e','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',220,5.5,123,11.4,'2018-07-21 07:50:33'),('3d590636-ab4f-4295-83e8-2d952b9081ef','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',260,5.9,127,12.2,'2018-07-21 07:50:33'),('3e31759b-0b09-49e2-b715-0a5e25c36783','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',10,3.4,102,7.2,'2018-07-21 07:50:33'),('42890175-aee0-4feb-99e0-0901f9ab8a1f','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',20,3.5,103,7.4,'2018-07-21 07:50:33'),('4a673318-58e5-486d-b948-a0b6e9055e24','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',280,6.1,129,12.6,'2018-07-21 07:50:33'),('5e810e6e-78dc-4b7f-ad46-7c8fa99a4bbd','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',180,5.1,119,10.6,'2018-07-21 07:50:33'),('64c3cd24-48ce-47fe-8ce1-4f7b8a9becf3','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',240,5.7,125,11.8,'2018-07-21 07:50:33'),('74e588a5-2c54-4b01-b6a6-e1cb316f8cf7','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',140,4.7,115,9.8,'2018-07-21 07:50:33'),('7cb6acd4-36e6-4116-9d51-89856bb543ca','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',150,4.8,116,10,'2018-07-21 07:50:33'),('8d112394-cbb2-433c-bc75-6ffb6acb3288','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',170,5,118,10.4,'2018-07-21 07:50:33'),('8d4636b3-77b5-4841-8a48-121ae5a8ee8c','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',270,6,128,12.4,'2018-07-21 07:50:33'),('907fb6e6-0e6b-47c3-8ae9-c8f5802f2699','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',130,4.6,114,9.6,'2018-07-21 07:50:33'),('91d50492-5f34-4b71-aa7a-6761c598e187','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',120,4.5,113,9.4,'2018-07-21 07:50:33'),('983a5e26-6964-4527-8944-8cf92d5d3e49','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',100,4.3,111,9,'2018-07-21 07:50:33'),('a373c66e-da93-4c86-a9f0-359452582a89','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',210,5.4,122,11.2,'2018-07-21 07:50:33'),('ab57db5a-635e-4489-960e-0a96fb7408a0','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',50,3.8,106,8,'2018-07-21 07:50:33'),('b1dee2c2-b1fb-429e-8df2-b74cdaeb9a85','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',190,5.2,120,10.8,'2018-07-21 07:50:33'),('b2a61710-6966-4794-82a3-5a43ff288081','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',160,4.9,117,10.2,'2018-07-21 07:50:33'),('b4949377-848d-4b6c-9ad1-fb583d3cb4f6','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',230,5.6,124,11.6,'2018-07-21 07:50:33'),('bb608b71-3892-4a53-b9e9-bb448f735f7e','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',200,5.3,121,11,'2018-07-21 07:50:33'),('bc123eed-b76b-4050-bae3-172bdaae6c18','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',70,4,108,8.4,'2018-07-21 07:50:33'),('db48a18a-858b-4c52-b518-7e235c06b6ad','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',300,6.3,131,13,'2018-07-21 07:50:33'),('e6c51cf5-ae84-453a-881f-83d4ae2c0220','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',110,4.4,112,9.2,'2018-07-21 07:50:33'),('f08a61f9-f81b-47a7-b92b-8b5fdfe6f7ea','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',250,5.8,126,12,'2018-07-21 07:50:33'),('f48ede81-f759-40b0-9b25-6e65442117e2','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',60,3.9,107,8.2,'2018-07-21 07:50:33'),('fccedc8e-fdfd-4be3-ae29-30a2899548a6','5082bac6-db74-4cc7-ae28-f069c8e7c0b4',40,3.7,105,7.8,'2018-07-21 07:50:33');
/*!40000 ALTER TABLE `itemcontraction` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `samllbacking`
--

LOCK TABLES `samllbacking` WRITE;
/*!40000 ALTER TABLE `samllbacking` DISABLE KEYS */;
INSERT INTO `samllbacking` VALUES ('6ae28509-9a27-490c-a8cb-8221a82310f9','30mm钢03',NULL,'水','空气','文字文字','d39c71d7-899b-4d2b-ad29-a14c0c80c69d','2018-08-29 12:28:03'),('8f25308a-6ff1-4c5f-9465-880147950d6d','30mm钢03',NULL,'水','空气','文字文字','7e88c465-166c-4b63-8a46-dbf17afe1892','2018-08-24 14:48:00'),('c2af1bea-3d5e-4d6b-a057-519c02a6c966','30mm钢03',NULL,'水','空气','文字文字','c3623872-cc96-46c7-819a-61b50c650684','2018-08-29 12:25:27');
/*!40000 ALTER TABLE `samllbacking` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `sample`
--

LOCK TABLES `sample` WRITE;
/*!40000 ALTER TABLE `sample` DISABLE KEYS */;
INSERT INTO `sample` VALUES ('0cc843a9-dad5-4c01-8a11-03c18041adb2','阿波罗',NULL,'1.05kg/cm3','50MPa','0.497','1580m/s','50mm','文字文字',NULL,1,'2018-08-29 13:53:55'),('0d860f22-6258-402a-8351-63d4f1888825','阿波罗',NULL,'1.05kg/cm3','50MPa','0.497','1580m/s','50mm','文字文字',NULL,1,'2018-08-29 14:08:05'),('1970d6a9-03fd-4040-afec-da3d0fd4f3f6','阿波罗',NULL,'1.05kg/cm3','50MPa','0.497','1580m/s','50mm','样品其他',NULL,1,'2018-08-29 14:11:34'),('1dc21044-fead-4308-9538-72f570338ee8','阿波罗',NULL,'1.05kg/cm3','50MPa','0.497','1580m/s','50mm','文字文字',NULL,1,'2018-08-29 13:48:15'),('243bb1cf-5613-4a2b-9979-5ea68d8cc950','阿波罗',NULL,'1.05kg/cm3','50MPa','0.497','1580m/s','50mm','样品其他',NULL,1,'2018-08-29 14:12:53'),('586b3425-53c4-4606-8e55-0debe7e6d8ac','阿波罗',NULL,'1.05kg/cm3','50MPa','0.497','1580m/s','50mm','文字文字',NULL,1,'2018-08-29 13:59:57'),('5e2c7cde-ac12-46e4-ae44-5f002c459fa1','阿波罗',NULL,'1.05kg/cm3','50MPa','0.497','1580m/s','50mm','文字文字',NULL,1,'2018-08-29 13:22:11'),('6d8df31e-56d0-4c16-a690-ebdda9f892ef','阿波罗',NULL,'1.05kg/cm3','50MPa','0.497','1580m/s','50mm','文字文字',NULL,1,'2018-08-29 13:17:32'),('7999e259-b19d-4761-ba6e-c542f7abf76b','阿波罗',NULL,'1.05kg/cm3','50MPa','0.497','1580m/s','50mm','文字文字',NULL,1,'2018-08-29 13:28:25'),('7e88c465-166c-4b63-8a46-dbf17afe1892','阿波罗03',NULL,'1.05kg/cm3','50MPa','0.497','1580m/s','50mm','文字文字',NULL,1,'2018-08-24 14:48:00'),('afa86629-96da-499f-a712-bc0a37358d4c','阿波罗',NULL,'1.05kg/cm3','50MPa','0.497','1580m/s','50mm','文字文字',NULL,1,'2018-08-29 13:52:17'),('c3623872-cc96-46c7-819a-61b50c650684','阿波罗03',NULL,'1.05kg/cm3','50MPa','0.497','1580m/s','50mm','文字文字',NULL,1,'2018-08-29 12:25:27'),('d39c71d7-899b-4d2b-ad29-a14c0c80c69d','阿波罗03',NULL,'1.05kg/cm3','50MPa','0.497','1580m/s','50mm','文字文字',NULL,1,'2018-08-29 12:28:03'),('d8e9196d-3b58-49f3-8ad1-3e946db50904','阿波罗',NULL,'1.05kg/cm3','50MPa','0.497','1580m/s','50mm','文字文字',NULL,0,'2018-08-29 13:30:54'),('dae99713-394f-4cdb-8992-e54ef17744af','阿波罗',NULL,'1.05kg/cm3','50MPa','0.497','1580m/s','50mm','文字文字',NULL,0,'2018-08-29 13:18:27');
/*!40000 ALTER TABLE `sample` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `smallmetadata`
--

LOCK TABLES `smallmetadata` WRITE;
/*!40000 ALTER TABLE `smallmetadata` DISABLE KEYS */;
INSERT INTO `smallmetadata` VALUES ('a57de754-16cb-4b78-bdf1-80b7277b41f2','阿波罗03','30mm钢03',20,2,'2018-08-29 12:28:03',1,'d39c71d7-899b-4d2b-ad29-a14c0c80c69d','6ae28509-9a27-490c-a8cb-8221a82310f9');
/*!40000 ALTER TABLE `smallmetadata` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `testmodel`
--

LOCK TABLES `testmodel` WRITE;
/*!40000 ALTER TABLE `testmodel` DISABLE KEYS */;
INSERT INTO `testmodel` VALUES ('092e9933-d953-4c8f-80e8-bfaeeca28807','双层局域实尺度试验模型',NULL,'长宽高：1.8m*1.6m*1.4m','双层间间距空气','内壳厚度文字文字','内壳后段空气','15','2018-08-29 14:12:53','试验模型其他');
/*!40000 ALTER TABLE `testmodel` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `testsystem`
--

LOCK TABLES `testsystem` WRITE;
/*!40000 ALTER TABLE `testsystem` DISABLE KEYS */;
INSERT INTO `testsystem` VALUES ('ad9bb684-2f9d-416c-bb0e-d74f50f147e1','测试系统名称','测试系统介绍','2018-08-29 14:12:53');
/*!40000 ALTER TABLE `testsystem` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-29 23:23:47


-- ----------------------------
-- Table structure for spha_base_user
-- ----------------------------
DROP TABLE IF EXISTS spha_base_user;
CREATE TABLE spha_base_user (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(60) DEFAULT NULL COMMENT '用户名',
  pass varchar(60) DEFAULT NULL COMMENT '密码',
  usertype varchar(60) DEFAULT NULL COMMENT '用户类别',
  updatetime varchar(30) DEFAULT NULL COMMENT '更新时间',
  ts timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spha_base_user
-- ----------------------------
INSERT INTO spha_base_user VALUES (1, 'admin', 'admin', '管理员', null, '2018-08-31 20:46:28');
INSERT INTO spha_base_user VALUES (2, 'user', 'user', '普通用户', null, '2018-08-31 20:46:41');

