-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2016 年 03 月 28 日 03:18
-- 服务器版本: 5.6.12-log
-- PHP 版本: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `simplelogin`
--
CREATE DATABASE IF NOT EXISTS `simplelogin` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `simplelogin`;

-- --------------------------------------------------------

--
-- 表的结构 `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 NOT NULL,
  `encrypted_password` varchar(20) CHARACTER SET utf8 NOT NULL,
  `salt` varchar(20) CHARACTER SET utf8 NOT NULL,
  `contact` varchar(20) CHARACTER SET utf8 NOT NULL,
  `created_at` varchar(20) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- 转存表中的数据 `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `encrypted_password`, `salt`, `contact`, `created_at`) VALUES
(5, 'ss', '123@qq.com', ' 0inTUhCcdyFVuNtel9V', ' 0c4870f330', '123', '2016/03/22'),
(6, 'ss', '1234@qq.com', ' lmvJyxhcK+D/hOTbaNH', ' 2d8616d4cd', '1234', '2016/03/22'),
(7, 'su', '321@qq.com', ' dvPsmAPNer2c27Inh6a', ' 55de59d66d', '321', '2016/03/25'),
(8, 'su', '4321@qq.com', ' 4rtzNCkHhqISV689WDY', ' 4071d7e4b2', '4321', '2016/03/25');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
