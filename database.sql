-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.26 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 registertable 的数据库结构
CREATE DATABASE IF NOT EXISTS `registertable` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `registertable`;

-- 导出  表 registertable.t_luntan 结构
CREATE TABLE IF NOT EXISTS `t_luntan` (
  `id` bigint(20) NOT NULL,
  `detailmsg` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `imgname` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reviewnum` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `posttime` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  registertable.t_luntan 的数据：2 rows
/*!40000 ALTER TABLE `t_luntan` DISABLE KEYS */;
INSERT INTO `t_luntan` (`id`, `detailmsg`, `imgname`, `reviewnum`, `title`, `posttime`) VALUES
	(61, 'lxt', '8.png', '2', 'hello world', '2019-06-12 11:18:26'),
	(59, 'wei', '3.png', '1', 'title content', '2019-06-12 11:10:54');
/*!40000 ALTER TABLE `t_luntan` ENABLE KEYS */;

-- 导出  表 registertable.t_nav 结构
CREATE TABLE IF NOT EXISTS `t_nav` (
  `id` bigint(20) NOT NULL,
  `msg` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  registertable.t_nav 的数据：8 rows
/*!40000 ALTER TABLE `t_nav` DISABLE KEYS */;
INSERT INTO `t_nav` (`id`, `msg`) VALUES
	(1, '技术'),
	(2, '创意'),
	(3, '好玩'),
	(4, 'Apple'),
	(5, '工作'),
	(6, '交易'),
	(7, '城市'),
	(8, '问答');
/*!40000 ALTER TABLE `t_nav` ENABLE KEYS */;

-- 导出  表 registertable.t_review 结构
CREATE TABLE IF NOT EXISTS `t_review` (
  `id` bigint(20) DEFAULT NULL,
  `owerUserName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reviewUserName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `reviewMsg` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  registertable.t_review 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `t_review` DISABLE KEYS */;
INSERT INTO `t_review` (`id`, `owerUserName`, `reviewUserName`, `reviewMsg`) VALUES
	(61, 'lxt', 'weiqin', '6666'),
	(61, 'lxt', 'weiqin', 'for'),
	(59, 'wei', 'weiqin', 'hhhhh');
/*!40000 ALTER TABLE `t_review` ENABLE KEYS */;

-- 导出  表 registertable.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  registertable.t_user 的数据：3 rows
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`id`, `email`, `password`, `username`) VALUES
	(9, 'weiqin@163.com', '123', 'weiqin'),
	(57, 'wei@163.com', '123', 'wei'),
	(35, 'lxt@163.com', '123', 'lxt');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
