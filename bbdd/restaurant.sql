CREATE DATABASE  IF NOT EXISTS `restaurant` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `restaurant`;
-- MySQL dump 10.13  Distrib 5.5.55, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: restaurant
-- ------------------------------------------------------
-- Server version	5.5.55-0ubuntu0.14.04.1

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
-- Table structure for table `cocinero`
--

DROP TABLE IF EXISTS `cocinero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cocinero` (
  `nombre` varchar(60) NOT NULL,
  `telefono` varchar(9) NOT NULL,
  `sexo` varchar(10) NOT NULL,
  `edad` int(11) NOT NULL,
  `experiencia` int(11) NOT NULL,
  `especialidad` varchar(45) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cocinero`
--

LOCK TABLES `cocinero` WRITE;
/*!40000 ALTER TABLE `cocinero` DISABLE KEYS */;
INSERT INTO `cocinero` VALUES ('Antonia Kitchen','658787878','Mujer',30,10,'Entrantes'),('Blablabla Cocinando','123456789','mujer',23,2,'Platos principales'),('Eva Arguiñano','345232323','Mujer',48,20,'postres'),('Karlos Arguiñano','123456789','hombre',63,44,'Platos principales'),('Mar La cocinera','888888888','Mujer',23,5,'Postres'),('Maria Lapera','888888888','mujer',30,8,'Entrantes'),('Miguel El Cocinero','888888888','Hombre',24,1,'Entrantes'),('Pepa','888888888','mujer',24,2,'Postres'),('Pepe','123456789','Hombre',30,10,'Postres'),('Pepe El Cocinero','123456789','Hombre',18,1,'Platos principales'),('Susana Raton','222222222','mujer',32,9,'Platos principales'),('Tiramisu','888888888','Mujer',20,2,'Entrantes');
/*!40000 ALTER TABLE `cocinero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plato`
--

DROP TABLE IF EXISTS `plato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plato` (
  `nombre` varchar(50) NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `precio` decimal(5,2) NOT NULL,
  `cocinero` varchar(45) NOT NULL,
  PRIMARY KEY (`nombre`),
  KEY `fk_plato_1_idx` (`cocinero`),
  CONSTRAINT `fk_plato_1` FOREIGN KEY (`cocinero`) REFERENCES `cocinero` (`nombre`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plato`
--

LOCK TABLES `plato` WRITE;
/*!40000 ALTER TABLE `plato` DISABLE KEYS */;
INSERT INTO `plato` VALUES ('Ensalada césar','Entrante',5.00,'Maria Lapera'),('Lentejas','Plato principal',5.20,'Susana Raton'),('Sopa','Plato principal',4.00,'Susana Raton'),('Tiramisu','Postre',4.50,'Karlos Arguiñano');
/*!40000 ALTER TABLE `plato` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-01 19:08:41
