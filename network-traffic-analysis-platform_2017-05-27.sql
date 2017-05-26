# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.13)
# Database: network-traffic-analysis-platform
# Generation Time: 2017-05-26 18:47:26 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table dashboard
# ------------------------------------------------------------

DROP TABLE IF EXISTS `dashboard`;

CREATE TABLE `dashboard` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `note` varchar(100) DEFAULT NULL,
  `link` varchar(300) DEFAULT '',
  `title` varchar(30) DEFAULT NULL,
  `content` varchar(200) DEFAULT '',
  `from` int(11) DEFAULT NULL,
  `add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `is_del` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `dashboard` WRITE;
/*!40000 ALTER TABLE `dashboard` DISABLE KEYS */;

INSERT INTO `dashboard` (`id`, `note`, `link`, `title`, `content`, `from`, `add_time`, `is_del`)
VALUES
	(1,NULL,'<iframe height=498 width=1300 src=\'http://player.youku.com/embed/XMjc4NTEyMjE1Mg==\' frameborder=0 \'allowfullscreen\'></iframe>','一天HTTP变化','该图表展示了在2016-8-10号一天流量在不同时间点的值',3,'2017-05-18 01:03:00',0),
	(3,NULL,'11aa链接','11aa链接','11aa链接',10,'2017-05-25 19:52:08',0),
	(4,NULL,'11aa链接','11aa链接','11aa链接',9,'2017-05-25 19:52:10',1);

/*!40000 ALTER TABLE `dashboard` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table token
# ------------------------------------------------------------

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `token` varchar(50) NOT NULL DEFAULT '',
  `master` varchar(50) DEFAULT NULL,
  `remark` varchar(30) DEFAULT NULL,
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expire_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;

INSERT INTO `token` (`id`, `token`, `master`, `remark`, `add_time`, `expire_time`)
VALUES
	(34,'1suebyhrca1hn2semnjebgw0ny1uhzumdz0cji2aslqzn1dz9i','FORGET_PASSWORD','3','2017-05-21 16:49:29','2017-05-21 17:49:29'),
	(35,'gxam2a11l1g5gtzhe5l8pj8ieua1yw6lbbp8xw70gaxjsxy247','ACTIVE_ACCOUNT','25','2017-05-24 14:42:24','2017-05-25 00:42:24'),
	(36,'tchjg5l3pdwwv94bbr6rxmxm0zazp66l6e6r6ygfttsq6x2j8c','ACTIVE_ACCOUNT','26','2017-05-24 14:43:03','2017-05-25 00:43:03'),
	(37,'fy4aaleooex9pg1op1ooap1dcb2zadx7eqtbhrbnli763fmex6','ACTIVE_ACCOUNT','27','2017-05-24 16:21:17','2017-05-25 02:21:17');

/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `password`, `salt`, `nick_name`, `email`, `phone_number`, `role`, `login_failure_count`, `is_del`, `is_stop`, `add_time`, `update_time`, `last_time`, `login_ip`)
VALUES
	(3,'AIxhmOg0KJhvsI1NF3naC3pD79ErU2k7hU3/eUsrwLVbX/PNgLZ3283hD81SRKjwBUA6hU4w0o7AUT2kq3HsUPs=','fN5s8Bjdd4/ua+4r1+59SnCHTnSGlyhatjRFijI00ig=','2013011428@mail.bistu.edu.cn','2013011428@mail.bistu.edu.cn',NULL,'ROLE_ELEMENTARY',0,0,0,'2017-04-14 21:19:58','2017-04-14 21:19:58','2017-05-21 16:48:43','0:0:0:0:0:0:0:1'),
	(4,'APkxsaxWwHeV8fj6jexB3BQQlDYpz7SkYJCb4ciOFS+PRef1NP3+wThGmeqvrUR9MrIGU1n8mCprdRpEAOiT/sQ=','5QtL5YEfXDoBCrqTGi/P3v123eNbK+2LSjrOUxf6Q3s=','18610280794@163.com','18610280794@163.com',NULL,'ROLE_ELEMENTARY',0,0,0,'2017-04-14 21:20:21','2017-04-14 21:20:21','2017-04-14 21:20:21',NULL),
	(5,'AFL00RQdH97wPkWToacQfp2JdK1SJLTNTgrwJVSRMQ8LVsljYqGtGu0Al2KuOnnG7D3bkRHmRq6/+R/lcOkgs8Y=','YDhTaV8/grFTebsSk2EiX1Yq7d50tdcOAgir0uHiNcI=','hy19950714@gmail.com','hy19950714@gmail.com',NULL,'ROLE_ELEMENTARY',0,0,0,'2017-04-14 21:20:36','2017-04-14 21:20:36','2017-04-14 21:20:36',NULL),
	(6,'APVHkH7VRh3TX5sOhUE6i6MgJffgcCE/VRVJcNgZjI+aXYpNlXYkovaNXzQWAq0jpygzFYswjLyhnvWfkCGCiuo=','LZyerGGWHVKNVt0rNxDoS8x8NtVNXVT2FbyeRNUCb+E=','hy19950714@outlook.com','hy19950714@outlook.com',NULL,'ROLE_ELEMENTARY',0,0,0,'2017-04-14 21:20:48','2017-04-14 21:20:48','2017-05-27 02:35:48','0:0:0:0:0:0:0:1'),
	(9,'APbDiOwcE5svcr2AyZVdw5MjgmAVDDfyXH5ap4OfG82QzZ0fgGt33ybD6f8tYUvlrMBRbyTd16/S7nM3nGyXuYE=','XX2UDrGD0aDHZUSb884Y2J/z6HyFvJZAQKV3bWEXwHU=','洪宇','513736920@qq.com','18610280794','ROLE_ADVANCED',0,0,0,'2017-05-01 17:35:17','2017-05-03 10:12:02','2017-05-27 02:44:10','0:0:0:0:0:0:0:1'),
	(10,'AEZMA9TjwmdlEWHXFa8Fw71PqrhTCnTQPaHp1p9QuL+Tkwf6s7eeyfwPs7niUtkPS9bdgTo0mgpCLrInfzIqMWM=','4pGbZKsvRthivvQQTzTyiLT9XoO9qNNq8VH0I0RfO/Q=','张老师','mouchawei@163.com','13701185549','ROLE_ADMIN',0,0,0,'2017-05-01 17:37:29','2017-05-24 14:37:25','2017-05-27 02:43:55','0:0:0:0:0:0:0:1'),
	(16,'ALSwkarDkU49MvrmbF1Wb/C7EWFJeK0nKi6yHCitY+dSy2Jdk0/xV/HDFEY02BJV63RGEDLjMIg1wk3h4HsLqXU=','oVcwE5rQ2u/+kvOfvVJTEbkAPkeCBG1rDZlSg4EfWOY=','拿铁','petrel2015@foxmail.com',NULL,'ROLE_ELEMENTARY',0,0,0,'2017-05-03 15:51:57','2017-05-03 15:51:57','2017-05-03 15:52:40','0:0:0:0:0:0:0:1'),
	(22,'AJKrMtF/EDQCnAscbm8h57ZD1sPQF0eyh7RGz5fnzsu8L2g8tMOTQnTLG7HwCGiEKt50oz5NWuTQapSQR8MlSlA=','l3NCaVU/JMfeENwe0k9oupwDTdhzSF5nwbVSp9zcc8o=','teacher@jozif.org','teacher@jozif.org',NULL,'ROLE_ADVANCED',0,0,0,'2017-05-04 09:21:56','2017-05-04 09:21:56','2017-05-04 09:23:34','0:0:0:0:0:0:0:1'),
	(26,'AGGVnaS/cQRbo0ZiXYP8u0ICxdlqzoxwDGVy/4k99SC7WyfNksma76c6xhbPQkbWo2cVcDLHcxLwnCVyUS31Y3g=','ZJfDojPLO70yd87cIXhpDR2kmfA1s63vwMhkhttGgM0=','洪宇111','student@jozif.org',NULL,'ROLE_ELEMENTARY',0,0,1,'2017-05-24 14:43:03','2017-05-24 14:43:03','2017-05-24 14:43:03',NULL),
	(27,'AOtxdQO2zz/6KPzFHdyBvMHOyXph8UE24h8AM7+/g4Tj5Cug/LnpV2VDXv5yP5uSecO43lCdv/KjoczsVoK1w74=','oSeoJuwhU6pztLtt+0yWEUHn4Dovnew6WvkXsTdcdXc=','admin@jozif.org','admin@jozif.org',NULL,'ROLE_ADVANCED',0,0,1,'2017-05-24 16:21:17','2017-05-24 16:21:17','2017-05-24 16:21:17',NULL);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
