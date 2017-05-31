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
-- Table structure for table `dashboard`
--

DROP TABLE IF EXISTS `dashboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dashboard` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(300) DEFAULT NULL,
  `link` varchar(700) DEFAULT '',
  `is_del` tinyint(4) DEFAULT '0',
  `content` varchar(500) DEFAULT '',
  `from` int(11) DEFAULT NULL,
  `add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `note` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dashboard`
--

LOCK TABLES `dashboard` WRITE;
/*!40000 ALTER TABLE `dashboard` DISABLE KEYS */;
INSERT INTO `dashboard` VALUES (1,'多种协议分析仪表盘','<iframe src=\"http://localhost:5601/goto/0b8ab942881f8e232c853a558c822abc?embed=true\" height=\"800\" width=\"1140\"></iframe>',1,'这个仪表盘由各种协议组成的饼图，各种协议的直方图，各种协议的标签云。我们可以看出在一段时间内，不同协议所占所有流量的百分比，以及他们之间数量的差异。',10,'2017-05-27 01:16:27',NULL),(2,'直观显示访问量前30的域名','<iframe src=\"http://localhost:5601/goto/268a3a98362609fb543a9fd64c32e3b1?embed=true\" height=\"800\" width=\"1140\"></iframe>',1,'这个仪表盘能够直观的展示，在一段时间内访问量前30的域名。如热力图（heatmap），直方图（vertical-bar chart），饼图（pie-chart），云标签（tag-cloud）。热力图颜色越深的地方表示数量越多。直方图的高低可以很明显的看出域名访问量之间的差距。饼图可以看出来不同域名占整个访问量的百分比。云标签图在中间的地方是访问量最多的域名，边缘地区是访问量低的地区。',28,'2017-05-27 01:45:20',NULL),(3,'目的IP世界地图展示-正常比例','<iframe src=\"http://localhost:5601/goto/01f5480464fef908988c4e92cd1fa234?embed=true\" height=\"800\" width=\"1140\"></iframe>',0,'根据所有记录的目的IP的经纬度字段。显示地图上不同地点的访问热度。',10,'2017-05-28 18:46:19',NULL),(4,'目的IP世界地图展示-放大比例','<iframe src=\"http://localhost:5601/goto/7fc30323e807b579ba25073d44c50ad6?embed=true\" height=\"800\" width=\"1140\"></iframe>',0,'根据所有记录的目的IP的经纬度字段。显示地图上不同地点的访问热度。',10,'2017-05-28 18:48:12',NULL),(5,'DNS请求记录分析-正常比例','<iframe src=\"http://localhost:5601/goto/6d787cedb4f47adfaf59da5ef906894d?embed=true\" height=\"800\" width=\"1140\"></iframe>',0,'通过饼图，查看DNS返回代码，以及响应时间（延迟），在一段时间内的所占比例。',10,'2017-05-28 18:50:01',NULL),(6,'DNS请求记录分析-放大比例','<iframe src=\"http://localhost:5601/goto/d73a106148352d28ef5fa7bc9b7fc84e?embed=true\" height=\"800\" width=\"1140\"></iframe>',0,'通过饼图，查看DNS返回代码，以及响应时间（延迟），在一段时间内的所占比例。',10,'2017-05-28 18:51:36',NULL),(7,'多种协议比例分析-通用比例','<iframe src=\"http://localhost:5601/goto/d67d6872139168a1e1618e4efad72261?embed=true\" height=\"800\" width=\"1140\"></iframe>',0,'这个仪表盘由各种协议组成的饼图，各种协议的直方图，各种协议的标签云。我们可以看出在一段时间内，不同协议所占所有流量的百分比，以及他们之间数量的差异。',10,'2017-05-28 18:53:18',NULL),(8,'流量峰值变化展示-正常比例','<iframe src=\"http://localhost:5601/goto/74f96fe62ee3fb454b2dcb3805a71b4d?embed=true\" height=\"800\" width=\"1140\"></iframe>',1,'DNS，SSL，HHTP三种流量峰值的变化趋势。',10,'2017-05-28 18:54:34',NULL),(9,'流量峰值变化展示-正常缩放','<iframe src=\"http://localhost:5601/goto/19e99d3dc5c5ced647040b2d75a34539?embed=true\" height=\"800\" width=\"1140\"></iframe>',1,'DNS，SSL，HHTP三种流量峰值的变化趋势。',10,'2017-05-28 18:55:05',NULL),(22,'TOP20域名分析-通用比例','<iframe src=\"http://localhost:5601/goto/d40cfcc5c7cc870332616d17a3b771fb?embed=true\" height=\"800\" width=\"1140\"></iframe>',1,'该图表能够直观的展示，在一段时间内访问量前20的域名。 ',32,'2017-05-28 19:35:05',NULL);
/*!40000 ALTER TABLE `dashboard` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-30 21:18:44
