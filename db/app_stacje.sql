-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: app
-- ------------------------------------------------------
-- Server version	5.7.22-log

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
-- Table structure for table `stacje`
--

DROP TABLE IF EXISTS `stacje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stacje` (
  `idstacje` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa_stacji` varchar(45) DEFAULT NULL,
  `miejscowosc` varchar(45) DEFAULT NULL,
  `adres` varchar(45) DEFAULT NULL,
  `cena_benzyny_95` double NOT NULL,
  `cena_benzyny_98` double NOT NULL,
  `cena_oleju_napedowego` double NOT NULL,
  PRIMARY KEY (`idstacje`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COMMENT='lista stacji';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stacje`
--

LOCK TABLES `stacje` WRITE;
/*!40000 ALTER TABLE `stacje` DISABLE KEYS */;
INSERT INTO `stacje` VALUES (1,'Orlen','Wroclaw','szybowcowa',5,0,4.8),(2,'BP','Wroclaw','na ostatnim groszu',4.95,0,4.9),(3,'Shell','Wroclaw','bielany',4.9,0,4.7),(7,'Orlen','Wroclaw','Rynek',5,5.5,4.9),(8,'Orlen','Wroclaw','Rynek',5,5.5,4.9),(9,'Orlen','Wroclaw','Rynek',5,5.5,4.9);
/*!40000 ALTER TABLE `stacje` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-10 17:54:40
