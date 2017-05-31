-- MySQL dump 10.13  Distrib 5.7.13, for linux-glibc2.5 (x86_64)
--
-- Host: jozif.org    Database: network-traffic-analysis-platform
-- ------------------------------------------------------
-- Server version	5.6.32

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL COMMENT '盐值',
  `nick_name` varchar(30) DEFAULT NULL COMMENT '昵称',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(30) DEFAULT NULL COMMENT '手机号',
  `role` varchar(20) DEFAULT 'ROLE_STUDENT' COMMENT '角色',
  `login_failure_count` int(11) DEFAULT '0' COMMENT '登录错误次数',
  `is_del` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  `is_stop` tinyint(4) DEFAULT '1' COMMENT '是否停用',
  `add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `last_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `login_ip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone_number` (`phone_number`),
  UNIQUE KEY `nick_name` (`nick_name`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (9,'APbDiOwcE5svcr2AyZVdw5MjgmAVDDfyXH5ap4OfG82QzZ0fgGt33ybD6f8tYUvlrMBRbyTd16/S7nM3nGyXuYE=','XX2UDrGD0aDHZUSb884Y2J/z6HyFvJZAQKV3bWEXwHU=','洪宇','513736920@qq.com','18610280794','ROLE_ELEMENTARY',0,0,0,'2017-05-01 09:35:17','2017-05-03 02:12:02','2017-05-30 11:24:16','0:0:0:0:0:0:0:1'),(10,'AEZMA9TjwmdlEWHXFa8Fw71PqrhTCnTQPaHp1p9QuL+Tkwf6s7eeyfwPs7niUtkPS9bdgTo0mgpCLrInfzIqMWM=','4pGbZKsvRthivvQQTzTyiLT9XoO9qNNq8VH0I0RfO/Q=','杨老师','mouchawei@163.com','18601282954','ROLE_ADMIN',0,0,0,'2017-05-01 09:37:29','2017-05-30 08:12:17','2017-05-30 13:03:04','0:0:0:0:0:0:0:1'),(32,'AEZMA9TjwmdlEWHXFa8Fw71PqrhTCnTQPaHp1p9QuL+Tkwf6s7eeyfwPs7niUtkPS9bdgTo0mgpCLrInfzIqMWM=','4pGbZKsvRthivvQQTzTyiLT9XoO9qNNq8VH0I0RfO/Q=','i@jozif.org','i@jozif.org','18610280780','ROLE_ADVANCED',0,0,0,'2017-05-28 19:29:24','2017-05-30 11:18:09','2017-05-30 11:18:03','0:0:0:0:0:0:0:1'),(34,'AApeW2fZvY0C50tqlHY6y3uGG4Ccif0tjUSe51gkwhS/kenr5thL30oHEqy3kpfoqsuQtZiPWy4dJPf0x7vc10g=','QfndeuL2obq+BjJCYyjLSCK0XuYck6PLacMlOtZX7xQ=','赵世博','1097449919@qq.com','18610280781','ROLE_ADVANCED',0,0,0,'2017-05-30 12:44:11','2017-05-30 13:13:40','2017-05-30 13:13:23','0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-30 21:18:45
