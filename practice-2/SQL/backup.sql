-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: geeksystem
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fk_permission_id` bigint NOT NULL,
  `fk_role_id` bigint NOT NULL,
  `fk_store_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `admins_stores_FK` (`fk_store_id`),
  KEY `admins_roles_FK` (`fk_role_id`),
  KEY `admins_permissions_FK` (`fk_permission_id`),
  CONSTRAINT `admins_permissions_FK` FOREIGN KEY (`fk_permission_id`) REFERENCES `permissions` (`id`),
  CONSTRAINT `admins_roles_FK` FOREIGN KEY (`fk_role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `admins_stores_FK` FOREIGN KEY (`fk_store_id`) REFERENCES `stores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,'太郎','田中','12345@com','0000111221','$2a$10$D5uS7w1C2z5av6C7wsy3eOeilbnxSl4axQT3KdBhpcRTGWpidwow2','2026-01-24 15:00:00','2026-02-01 15:42:59',1,1,1),(6,'太郎','Geek','testuser@com','1234567890','$2a$10$cew9rRCAGdWomhpwqCzL7O3KA.sdJvjk3Y/QFju6SnqnPH8jH8uhW','2026-01-31 07:35:58','2026-01-31 07:36:19',1,1,2);
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fk_small_id` bigint NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `item_info` varchar(255) DEFAULT NULL,
  `image` tinyblob,
  `cost_price` decimal(38,2) NOT NULL,
  `fk_maker_id` bigint NOT NULL,
  `maker_price` decimal(38,2) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `items_makers_FK` (`fk_maker_id`),
  KEY `items_small_categories_FK` (`fk_small_id`),
  CONSTRAINT `items_makers_FK` FOREIGN KEY (`fk_maker_id`) REFERENCES `makers` (`id`),
  CONSTRAINT `items_small_categories_FK` FOREIGN KEY (`fk_small_id`) REFERENCES `small_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,1,'SR-IKJ-01','いい感じの冷蔵庫です',NULL,50000.00,1,70000.00,'2026-01-10 01:58:43','2026-01-10 01:58:43'),(2,6,'YDC-DSK-1001','いい感じのドラム式洗濯乾燥機です',NULL,120000.00,2,178000.00,'2026-01-10 01:58:43','2026-01-10 01:58:43'),(3,14,'danson-slim-01','いい感じのサイクロン式掃除機です',NULL,30000.00,8,49800.00,'2026-01-10 01:58:43','2026-01-10 01:58:43'),(4,18,'FD-1221','いい感じの電子レンジです',NULL,20000.00,3,34800.00,'2026-01-10 01:58:43','2026-01-10 01:58:43'),(5,21,'FS-2001','いい感じの炊飯器です',NULL,15000.00,3,25000.00,'2026-01-10 01:58:43','2026-01-10 01:58:43'),(6,20,'DN-SOR-d1','いい感じのスチームオーブンレンジです',NULL,32000.00,7,56000.00,'2026-01-10 01:58:43','2026-01-10 01:58:43'),(7,38,'N-ETV-1111','いい感じの液晶テレビです',NULL,110000.00,6,143200.00,'2026-01-10 01:58:43','2026-01-10 01:58:43'),(8,16,'Poomba-01','いい感じのロボット掃除機です',NULL,42000.00,9,52000.00,'2026-01-10 01:58:43','2026-01-10 01:58:43'),(9,21,'MT-S01','いい感じの炊飯器です',NULL,105000.00,4,138000.00,'2026-01-10 01:58:43','2026-01-10 01:58:43'),(10,22,'TZ-HJ-11','いい感じの保温ジャーです',NULL,40000.00,5,69800.00,'2026-01-10 01:58:43','2026-01-10 01:58:43');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `large_categories`
--

DROP TABLE IF EXISTS `large_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `large_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `large_name` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `large_categories`
--

LOCK TABLES `large_categories` WRITE;
/*!40000 ALTER TABLE `large_categories` DISABLE KEYS */;
INSERT INTO `large_categories` VALUES (1,'冷蔵庫・洗濯機・掃除機','2025-12-30 08:32:46','2025-12-30 08:32:46'),(2,'電子レンジ・炊飯器','2025-12-30 08:32:46','2025-12-30 08:32:46'),(3,'エアコン・空調','2025-12-30 08:32:46','2025-12-30 08:32:46'),(4,'テレビ・レコーダー','2025-12-30 08:32:46','2025-12-30 08:32:46');
/*!40000 ALTER TABLE `large_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `makers`
--

DROP TABLE IF EXISTS `makers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `makers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `maker_name` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `makers`
--

LOCK TABLES `makers` WRITE;
/*!40000 ALTER TABLE `makers` DISABLE KEYS */;
INSERT INTO `makers` VALUES (1,'三角電機','2025-12-30 08:11:19','2025-12-30 08:11:19'),(2,'夕立','2025-12-30 08:17:10','2025-12-30 08:17:52'),(3,'Fanasonic','2025-12-30 08:17:10','2025-12-30 08:17:57'),(4,'マイリス・トーヤマ','2025-12-30 08:17:10','2025-12-30 08:17:59'),(5,'虎印','2025-12-30 08:17:10','2025-12-30 08:18:01'),(6,'西芝','2025-12-30 08:17:10','2025-12-30 08:18:02'),(7,'DALNUDA','2025-12-30 08:17:10','2025-12-30 08:18:03'),(8,'ダンソン','2025-12-30 08:17:10','2025-12-30 08:18:05'),(9,'gRobot','2025-12-30 08:17:10','2025-12-30 08:18:08');
/*!40000 ALTER TABLE `makers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `middle_categories`
--

DROP TABLE IF EXISTS `middle_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `middle_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fk_large_id` bigint NOT NULL,
  `middle_name` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `large_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `middle_categories_large_categories_FK` (`fk_large_id`),
  CONSTRAINT `middle_categories_large_categories_FK` FOREIGN KEY (`fk_large_id`) REFERENCES `large_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `middle_categories`
--

LOCK TABLES `middle_categories` WRITE;
/*!40000 ALTER TABLE `middle_categories` DISABLE KEYS */;
INSERT INTO `middle_categories` VALUES (1,1,'冷蔵庫・冷凍庫','2025-12-30 09:04:08','2025-12-30 09:04:08',0),(2,1,'洗濯機・洗濯乾燥機','2025-12-30 09:04:08','2025-12-30 09:04:08',0),(3,1,'掃除機・クリーナー','2025-12-30 09:04:08','2025-12-30 09:04:08',0),(4,2,'オーブンレンジ・電子レンジ','2025-12-30 09:04:08','2025-12-30 09:04:08',0),(5,2,'炊飯器','2025-12-30 09:04:08','2025-12-30 09:04:08',0),(6,3,'エアコン・窓用エアコン','2025-12-30 09:04:08','2025-12-30 09:04:08',0),(7,3,'扇風機・サーキュレーター','2025-12-30 09:04:08','2025-12-30 09:04:08',0),(8,3,'暖房器具','2025-12-30 09:04:08','2025-12-30 09:04:08',0),(9,4,'テレビ','2025-12-30 09:04:08','2025-12-30 09:04:08',0),(10,4,'レコーダー','2025-12-30 09:04:08','2025-12-30 09:04:08',0),(11,4,'プロジェクター','2025-12-30 09:04:08','2025-12-30 09:04:08',0);
/*!40000 ALTER TABLE `middle_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_history`
--

DROP TABLE IF EXISTS `order_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fk_admin_id` bigint NOT NULL,
  `fk_store_item_id` bigint NOT NULL,
  `order_count` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `order_history_admins_FK` (`fk_admin_id`),
  KEY `order_history_store_items_FK` (`fk_store_item_id`),
  CONSTRAINT `order_history_admins_FK` FOREIGN KEY (`fk_admin_id`) REFERENCES `admins` (`id`),
  CONSTRAINT `order_history_store_items_FK` FOREIGN KEY (`fk_store_item_id`) REFERENCES `store_items` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_history`
--

LOCK TABLES `order_history` WRITE;
/*!40000 ALTER TABLE `order_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (1,'管理者','2025-12-30 08:05:53','2025-12-30 08:05:53'),(2,'一般','2025-12-30 08:05:53','2025-12-30 08:05:53');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'店長','2025-12-30 07:49:23','2025-12-30 07:49:23'),(2,'副店長','2025-12-30 07:49:23','2025-12-30 07:49:23'),(3,'マネージャー','2025-12-30 07:49:23','2025-12-30 07:49:23'),(4,'一般従業員','2025-12-30 07:49:23','2025-12-30 07:49:23'),(5,'パート・アルバイト','2025-12-30 07:49:25','2025-12-30 07:49:25');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `small_categories`
--

DROP TABLE IF EXISTS `small_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `small_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fk_middle_id` bigint NOT NULL,
  `small_name` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `middle_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `small_categories_middle_categories_FK` (`fk_middle_id`),
  CONSTRAINT `small_categories_middle_categories_FK` FOREIGN KEY (`fk_middle_id`) REFERENCES `middle_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `small_categories`
--

LOCK TABLES `small_categories` WRITE;
/*!40000 ALTER TABLE `small_categories` DISABLE KEYS */;
INSERT INTO `small_categories` VALUES (1,1,'冷蔵庫','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(2,1,'冷凍庫','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(3,1,'保冷・冷温ボックス','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(4,1,'製氷機','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(5,1,'冷蔵庫関連品','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(6,2,'ドラム式洗濯乾燥機','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(7,2,'縦型洗濯機','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(8,2,'2槽式洗濯機','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(9,2,'ハンディ・小型洗濯機','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(10,2,'衣類乾燥機','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(11,2,'洗濯機関連品','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(12,2,'衣類乾燥機・関連品','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(13,3,'スティッククリーナー','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(14,3,'サイクロン式掃除機','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(15,3,'紙パック式掃除機','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(16,3,'ロボット掃除機','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(17,3,'ハンディクリーナー','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(18,4,'電子レンジ','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(19,4,'オーブンレンジ','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(20,4,'スチームオーブンレンジ','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(21,5,'炊飯器','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(22,5,'保温ジャー','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(23,5,'ガス炊飯器','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(24,6,'エアコン','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(25,6,'窓用エアコン','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(26,6,'エアコン関連品','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(27,7,'リビング扇風機','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(28,7,'タワー型扇風機','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(29,7,'羽根無し扇風機','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(30,7,'サーキュレーター','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(31,7,'携帯型扇風機','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(32,7,'卓上型扇風機','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(33,8,'電気ファンヒーター','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(34,8,'電気ストーブ','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(35,8,'セラミックヒーター','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(36,8,'こたつ・こたつ布団','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(37,8,'ホットカーペット・関連品','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(38,9,'液晶テレビ','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(39,9,'有機ELテレビ','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(40,9,'ポータブルテレビ','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(41,9,'テレビ関連品','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(42,10,'ブルーレイレコーダー','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(43,10,'HDDレコーダー','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(44,10,'レコーダー関連品','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(45,11,'プロジェクター本体','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(46,11,'プロジェクター関連品','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(47,11,'プロジェクタースクリーン','2025-12-30 09:11:26','2025-12-30 09:11:26',0),(48,11,'プロジェクタースクリーン関連品','2025-12-30 09:11:26','2025-12-30 09:11:26',0);
/*!40000 ALTER TABLE `small_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_items`
--

DROP TABLE IF EXISTS `store_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fk_store_id` bigint NOT NULL,
  `fk_item_id` bigint NOT NULL,
  `store_price` decimal(38,2) NOT NULL,
  `stock` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `item_id` int NOT NULL,
  `store_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `store_items_stores_FK` (`fk_store_id`),
  KEY `store_items_items_FK` (`fk_item_id`),
  CONSTRAINT `store_items_items_FK` FOREIGN KEY (`fk_item_id`) REFERENCES `items` (`id`),
  CONSTRAINT `store_items_stores_FK` FOREIGN KEY (`fk_store_id`) REFERENCES `stores` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_items`
--

LOCK TABLES `store_items` WRITE;
/*!40000 ALTER TABLE `store_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `store_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stores` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `store_name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES (1,'Geek電機 渋谷店','東京都渋谷区Geek坂 1-1-1','2025-12-30 07:56:59','2026-02-08 14:54:42'),(2,'Geek電機 新宿店','東京都新宿区西Geek 1-1-1','2025-12-30 07:56:59','2025-12-30 07:56:59'),(3,'Geek電機 池袋店です','東京都豊島区GeekShine通り 1-1-1','2025-12-30 07:56:59','2026-02-08 15:26:13');
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-23 17:39:53
