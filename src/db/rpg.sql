-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for explorers
CREATE DATABASE IF NOT EXISTS `explorers` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `explorers`;

-- Dumping structure for table explorers.cls
CREATE TABLE IF NOT EXISTS `cls` (
  `id_cs` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cls_nm` varchar(50) DEFAULT NULL,
  `img_cls` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id_cs`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table explorers.cls: ~7 rows (approximately)
INSERT INTO `cls` (`id_cs`, `cls_nm`, `img_cls`, `description`) VALUES
	('00001', 'Duelist', 'src\\img\\plchldr.png', 'It\'s main job is to attack from a mid range, have a well balanced defense and attack'),
	('00002', 'Magi', 'src\\img\\plchldr.png', 'It\'s main job is to pierce enemies\' defense, have a very high int yet low on defense'),
	('00003', 'Healer', 'src\\img\\plchldr.png', 'It\'s main job is to heal or take back to safety their injured teamates, have a good amount of int and luck'),
	('00004', 'Support', 'src\\img\\plchldr.png', 'It\'s main job is to support their teamates, have a good amount of int and luck'),
	('00005', 'Swiftstrike', 'src\\img\\plchldr.png', 'It\'s main job is to end the fight quickly, have a very high speed and attack, but low on defense'),
	('00006', 'Ranged', 'src\\img\\plchldr.png', 'It\'s main job is to attack from long range, have a good amount of speed and dex'),
	('00007', 'Defender', 'src\\img\\plchldr.png', 'it\'s main job is to protect their team, have a very high defense');

-- Dumping structure for table explorers.guild
CREATE TABLE IF NOT EXISTS `guild` (
  `id_guild` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nm` varchar(50) DEFAULT NULL,
  `race` varchar(50) DEFAULT NULL,
  `subclass` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `profile_pic` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_guild`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table explorers.guild: ~11 rows (approximately)
INSERT INTO `guild` (`id_guild`, `nm`, `race`, `subclass`, `profile_pic`) VALUES
	('DF00000704', 'Astraeilon Arion', 'World Dragon', 'Tank', 'src\\img\\drhr.png'),
	('DL09800101', 'John Doe', 'Human', 'Swordman', 'src\\img\\plchldr.png'),
	('DL13550387', 'Al\'ya Quartz', 'Dragon', 'Brawler', 'src\\img\\al.png'),
	('DL13940601', 'Sir Linwood Mountain', 'Human', 'Knight', 'src\\img\\mountain.png'),
	('HL11960223', 'Marin Falkins', 'Homicanid', 'Anchorer', 'src\\img\\Marin Falkins.png'),
	('MG11800101', 'Stonegard', 'Tssiar', 'Archmage', 'src\\img\\loka.png'),
	('MG13960701', 'Alisa Juni', 'Human', 'Astrologist', 'src\\img\\june.png'),
	('RG09800101', 'Jane Doe', 'Elf', 'Deadeye', 'src\\img\\plchldr.png'),
	('SP00000103', 'Luminael', 'Angel', 'Messanger', 'src\\img\\plchldr.png'),
	('SP00000808', 'Seller Man', 'Mimic', 'Setter', 'src\\img\\shopman.png'),
	('SP13940408', 'Meelisa Lau\'id', 'Homiconnee', 'Alchemist', 'src\\img\\conee.png');

-- Dumping structure for table explorers.map_reg
CREATE TABLE IF NOT EXISTS `map_reg` (
  `id_chkpoint` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `chk_name` varchar(50) DEFAULT NULL,
  `distance` double DEFAULT NULL,
  `map_img` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_chkpoint`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table explorers.map_reg: ~4 rows (approximately)
INSERT INTO `map_reg` (`id_chkpoint`, `chk_name`, `distance`, `map_img`) VALUES
	('000B', 'SkyPoint', 0, 'src\\img\\sky.gif'),
	('021A', 'Aroz Farm', 21, 'src\\img\\farm.gif'),
	('122Z', 'Root Tavern', 122, 'src\\img\\tav.png'),
	('453C', 'Selva Forest', 453, 'src\\img\\forest.gif');

-- Dumping structure for table explorers.race
CREATE TABLE IF NOT EXISTS `race` (
  `id_rc` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `race_nm` varchar(50) DEFAULT NULL,
  `effect_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  `img_rc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_rc`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table explorers.race: ~10 rows (approximately)
INSERT INTO `race` (`id_rc`, `race_nm`, `effect_desc`, `description`, `img_rc`) VALUES
	('00001', 'Dragon', 'have a sturdy scale', 'Standing tall with glittering scales beneath an acrylic sky, their veins carry the blood of the rulers of the heavens, but is it all true?', 'src\\img\\dg.png'),
	('00002', 'Elf', 'have a very high attack precision', 'Elves carry the weight of a thousand forgotten sunsets in their eyes, walking among short-lived mortals like ghosts in a crowded room', 'src\\img\\elf.png'),
	('00003', 'Tssiar', 'born with either fire or rock or both magic', 'Born with the power of fire and minerals, they have 4 ears, yet often they are unwilling to listen to those they do not deem worthy.', 'src\\img\\Ts.png'),
	('00004', 'Human', 'can adapt rather quickly', 'Short-lived, quite ambitious, yet stubborn, and found in every corner of the world.', 'src\\img\\hum.png'),
	('00005', 'Homiconnee', 'run fast, fast reflexes', 'Commonly called Conny, they look like rabbits but have horns.', 'src\\img\\co.png'),
	('00006', 'Homicanid', 'have a high raw power', 'Canids in short, they usually have friendly and trusting personalities.. and are quite easily fooled.', 'src\\img\\can.png'),
	('00045', 'Mimic', 'good disguise, nott really', 'Unicellular organisms that combine into an intelligent being. They use their surroundings for camouflage.', 'src\\img\\Mim.png'),
	('00077', 'Orc', 'Have unyielding rage (if you annoy them) and green', 'Green', 'src\\img\\or.png'),
	('00889', 'World Dragon', 'is immortal', 'Legend has it that he is older than the planet, or even the universe itself. It is believed that he wards off demons with angels at its side.', 'src\\img\\dg.png'),
	('09997', 'Angel', 'cannot be hurt by mortal species', 'It is believed that its appearance will occur when the hero in the prophecy appears.', 'src\\img\\Ang.png');

-- Dumping structure for table explorers.shop
CREATE TABLE IF NOT EXISTS `shop` (
  `item_id` char(5) NOT NULL,
  `nm_brg` varchar(50) DEFAULT NULL,
  `tarif` double DEFAULT NULL,
  `stok` int DEFAULT NULL,
  `descrp` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `img_brg` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table explorers.shop: ~6 rows (approximately)
INSERT INTO `shop` (`item_id`, `nm_brg`, `tarif`, `stok`, `descrp`, `img_brg`) VALUES
	('CN001', 'Potion', 450, 999, 'A thick, aquamarine liquid that smells of crushed mint. Restores your energy.', 'src\\img\\pot.png'),
	('CN002', 'Apple', 85, 145, 'The apple is so green that it makes the grass around it feel inferior. Restores a small amount of health.', 'src\\img\\apple.png'),
	('CN003', 'Carrot', 80, 133, 'Keeps your eyes sharp, even in the darkest dungeons. "Surprisingly Homiconnee like this one."', 'src\\img\\carot.png'),
	('CN777', 'StarEscape', 700, 800, '"No matter how deep you venture into the dark, there is always a way home."', 'src\\img\\staresc.png'),
	('WP003', 'Empty Amulet', 10000, 23, 'This item itself is purely cosmetic. Adventurers alike usually combine it with special stones to aid their journey.', 'src\\img\\amul.png'),
	('WP004', 'Empty Ring', 7600, 12, 'Can be combined with magic stones to increase your stats.. Or maybe you can give it to someone special', 'src\\img\\ring.png');

-- Dumping structure for table explorers.stats
CREATE TABLE IF NOT EXISTS `stats` (
  `id_guild` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `STR` int DEFAULT NULL,
  `AGI` int DEFAULT NULL,
  `DEX` int DEFAULT NULL,
  `INT` int DEFAULT NULL,
  `CON` int DEFAULT NULL,
  `LUK` int DEFAULT NULL,
  `region` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `race` char(5) DEFAULT NULL,
  `classes` char(5) DEFAULT NULL,
  KEY `FK_stats_guild` (`id_guild`),
  KEY `FK_stats_map_reg` (`region`),
  KEY `FK_stats_race` (`race`),
  KEY `FK_stats_cls` (`classes`),
  CONSTRAINT `FK_stats_cls` FOREIGN KEY (`classes`) REFERENCES `cls` (`id_cs`),
  CONSTRAINT `FK_stats_guild` FOREIGN KEY (`id_guild`) REFERENCES `guild` (`id_guild`),
  CONSTRAINT `FK_stats_map_reg` FOREIGN KEY (`region`) REFERENCES `map_reg` (`id_chkpoint`),
  CONSTRAINT `FK_stats_race` FOREIGN KEY (`race`) REFERENCES `race` (`id_rc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table explorers.stats: ~11 rows (approximately)
INSERT INTO `stats` (`id_guild`, `STR`, `AGI`, `DEX`, `INT`, `CON`, `LUK`, `region`, `race`, `classes`) VALUES
	('DL13550387', 80, 55, 54, 25, 78, 2, '000B', '00001', '00001'),
	('MG13960701', 10, 45, 44, 98, 57, 7, '000B', '00004', '00002'),
	('DF00000704', 780, 320, 100, 100, 120, 4, '453C', '00889', '00007'),
	('RG09800101', 45, 120, 45, 78, 43, 10, '453C', '00002', '00006'),
	('DL09800101', 135, 95, 80, 65, 80, 4, '453C', '00004', '00001'),
	('SP00000103', 690, 900, 999, 777, 999, 7, '453C', '09997', '00004'),
	('SP13940408', 23, 78, 45, 128, 45, 10, '000B', '00005', '00004'),
	('SP00000808', 102, 107, 78, 130, 100, 8, '000B', '00045', '00004'),
	('DL13940601', 100, 25, 80, 25, 70, 5, '122Z', '00004', '00001'),
	('MG11800101', 88, 97, 50, 145, 90, 8, '122Z', '00003', '00002'),
	('HL11960223', 100, 80, 10, 69, 50, 1, '021A', '00006', '00003');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
