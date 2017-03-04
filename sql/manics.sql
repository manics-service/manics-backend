-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.13-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for MANICS
CREATE DATABASE IF NOT EXISTS `MANICS` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `MANICS`;

-- Dumping structure for table MANICS.chat
CREATE TABLE IF NOT EXISTS `chat` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CHAT_ID` int(20) NOT NULL DEFAULT '0',
  `NAME` varchar(100) NOT NULL DEFAULT '0',
  `USER_ID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `chat_CHAT_ID_uindex` (`CHAT_ID`),
  KEY `FK3rey0h7240vbk7odnpw75d87o` (`USER_ID`),
  CONSTRAINT `FK3rey0h7240vbk7odnpw75d87o` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chat_user_ID_fk` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.
-- Dumping structure for table MANICS.chat_message_list
CREATE TABLE IF NOT EXISTS `chat_message_list` (
  `chat_id` int(11) NOT NULL,
  `message_list_id` int(11) NOT NULL,
  UNIQUE KEY `UK_ky2vn77x8c3dkum8tspqv2jdb` (`message_list_id`),
  KEY `FK110d6s9ho96w0mcy357y73m61` (`chat_id`),
  CONSTRAINT `FK110d6s9ho96w0mcy357y73m61` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`ID`),
  CONSTRAINT `FKr0pda6jjirke3ejp2q7yuhiu8` FOREIGN KEY (`message_list_id`) REFERENCES `message` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table MANICS.message
CREATE TABLE IF NOT EXISTS `message` (
  `ID` int(30) NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `BODY` varchar(10000) COLLATE utf8mb4_bin NOT NULL,
  `DATE` bigint(50) NOT NULL,
  `CHAT_ID` int(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `message_chat_ID_fk` (`CHAT_ID`),
  CONSTRAINT `FKmejd0ykokrbuekwwgd5a5xt8a` FOREIGN KEY (`CHAT_ID`) REFERENCES `chat` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `message_chat_ID_fk` FOREIGN KEY (`CHAT_ID`) REFERENCES `chat` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=136883 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Data exporting was unselected.
-- Dumping structure for table MANICS.session
CREATE TABLE IF NOT EXISTS `session` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SESSION` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `session_ID_uindex` (`ID`),
  CONSTRAINT `session_user_ID_fk` FOREIGN KEY (`ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.
-- Dumping structure for table MANICS.user
CREATE TABLE IF NOT EXISTS `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `IDENTIFIER` varchar(20) NOT NULL,
  `FIRST_NAME` varchar(20) NOT NULL,
  `LAST_NAME` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USER_ID_uindex` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
