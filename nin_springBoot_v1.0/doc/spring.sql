/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.27 : Database - spring
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`spring` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `spring`;

/*Table structure for table `learn_resource` */

DROP TABLE IF EXISTS `learn_resource`;

CREATE TABLE `learn_resource` (
  `id`  int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `author` varchar(20) not null DEFAULT '' COMMENT '作者',
  `title` varchar(100) not null DEFAULT '' COMMENT '描述',
  `url` varchar(100) not null DEFAULT '' COMMENT '地址链接',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1029 DEFAULT CHARSET=utf8;

/*Data for the table `learn_resource` */

insert  into `learn_resource`(`id`,`author`,`title`,`url`) values (999,'官方SpriongBoot例子','官方SpriongBoot例子','https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples'),(1000,'龙果学院','Spring Boot 教程系列学习','http://www.roncoo.com/article/detail/124661'),(1001,'嘟嘟MD独立博客','Spring Boot干货系列','http://tengj.top/'),(1002,'后端编程嘟','Spring Boot视频教程','http://www.toutiao.com/m1559096720023553/'),(1003,'程序猿DD','Spring Boot系列','http://www.roncoo.com/article/detail/125488'),(1004,'纯洁的微笑','Sping Boot系列文章','http://www.ityouknow.com/spring-boot'),(1005,'CSDN——小当博客专栏','Sping Boot学习','http://blog.csdn.net/column/details/spring-boot.thymeleaf'),(1006,'梁桂钊的博客','Spring Boot 揭秘与实战','http://blog.csdn.net/column/details/spring-boot.thymeleaf'),(1007,'林祥纤博客系列','从零开始学Spring Boot','http://412887952-qq-com.iteye.com/category/356333'),(1028,'杜琪','关于Spring Boot的博客集合','http://www.jianshu.com/p/7e2e5e7b32ab');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int UNSIGNED  NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(100) not null DEFAULT '' COMMENT '用户名',
  `password` varchar(100) not null DEFAULT '' COMMENT '密码',
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`) values (1,'admin','admin');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
