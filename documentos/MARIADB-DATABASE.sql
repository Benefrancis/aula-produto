-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.6.7-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para deposito-benezinho-tds
CREATE DATABASE IF NOT EXISTS `deposito-benezinho-tds` /*!40100 DEFAULT CHARACTER SET utf8mb3 */;
USE `deposito-benezinho-tds`;

-- Copiando estrutura para tabela deposito-benezinho-tds.deposito
CREATE TABLE IF NOT EXISTS `deposito` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela deposito-benezinho-tds.deposito: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `deposito` DISABLE KEYS */;
INSERT IGNORE INTO `deposito` (`ID`, `NOME`) VALUES
	(1, 'XANGAI');
/*!40000 ALTER TABLE `deposito` ENABLE KEYS */;

-- Copiando estrutura para tabela deposito-benezinho-tds.produto
CREATE TABLE IF NOT EXISTS `produto` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOME` varchar(255) NOT NULL,
  `DESCRICAO` longtext DEFAULT NULL,
  `VALOR` decimal(20,6) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- Copiando dados para a tabela deposito-benezinho-tds.produto: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT IGNORE INTO `produto` (`ID`, `NOME`, `DESCRICAO`, `VALOR`) VALUES
	(1, 'CHARLOTTE HORNETS', 'Boné da NBA', 199.990000),
	(2, 'CAMISA DO CORINTHIANS', 'Mundial da FIFA', 499.990000),
	(8, 'CAMISA CORINTHIANS', 'Campeão Brasileiro', 299.990000),
	(9, 'CAMISA CORINTHIANS', 'Campeão Brasileiro', 299.990000);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
