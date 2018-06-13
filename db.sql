/*
SQLyog Ultimate v9.02 
MySQL - 5.6.17 : Database - db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db`;

/*Table structure for table `factura` */

DROP TABLE IF EXISTS `factura`;

CREATE TABLE `factura` (
  `f_nro` bigint(20) unsigned NOT NULL,
  `f_nombre` varchar(10) NOT NULL,
  `f_monto` double NOT NULL,
  `f_tipo` varchar(54) NOT NULL,
  `f_fecha` varchar(12) NOT NULL,
  `f_pago` varchar(20) NOT NULL,
  PRIMARY KEY (`f_nro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `factura` */

insert  into `factura`(`f_nro`,`f_nombre`,`f_monto`,`f_tipo`,`f_fecha`,`f_pago`) values (123444,'yo no     ',123455,'C','12/12/1212','Cta Cte'),(123455,'pedro',65432,'A','12/12/1221','Cta Cte'),(654321,' fer      ',432,'A','8/4/4','Contado');

/*Table structure for table `producto` */

DROP TABLE IF EXISTS `producto`;

CREATE TABLE `producto` (
  `p_id` bigint(6) unsigned NOT NULL,
  `p_nombre` varchar(45) NOT NULL,
  `p_precio` double NOT NULL,
  `p_cantidad` bigint(45) NOT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `producto` */

insert  into `producto`(`p_id`,`p_nombre`,`p_precio`,`p_cantidad`) values (432,'pedro',65432,54),(32145,'repollo                       ',45,3),(34567,'zanahoria',45,5),(43212,'pedro',65432,54);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
