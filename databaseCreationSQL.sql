-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               8.0.17 - MySQL Community Server - GPL
-- Операционная система:         Win64
-- HeidiSQL Версия:              10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных TZ
CREATE DATABASE IF NOT EXISTS `TZ` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `TZ`;

-- Дамп структуры для таблица TZ.table_name
CREATE TABLE IF NOT EXISTS `table_name` (
  `ROW_NUMBER` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_RANDOM` varchar(200) DEFAULT NULL,
  `SECOND_RANDOM` varchar(200) DEFAULT NULL,
  `THIRD_RANDOM` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ROW_NUMBER`),
  UNIQUE KEY `table_name_ROW_NUMBER_uindex` (`ROW_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Экспортируемые данные не выделены.

-- Дамп структуры для таблица TZ.tztable
CREATE TABLE IF NOT EXISTS `tztable` (
  `ROW_NUMBER` int(11) DEFAULT NULL,
  `FIRST_RANDOM` int(11) DEFAULT NULL,
  `SECOND_RANDOM` int(11) DEFAULT NULL,
  `THIRD_RANDOM` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Экспортируемые данные не выделены.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
