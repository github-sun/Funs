CREATE DATABASE funs;

DROP TABLE IF EXISTS `ADMIN`;
CREATE TABLE `ADMIN` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `PASSWORD` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `user_name_unique` (`USER_NAME`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `ROLE`;
CREATE TABLE `ROLE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `role_name_unique` (`ROLE_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


DROP TABLE IF EXISTS `ADMIN_ROLE`;
CREATE TABLE `ADMIN_ROLE` (
  `ADMIN_ID` int(11),
  `ROLE_ID` int(11),
  PRIMARY KEY (`ADMIN_ID`,`ROLE_ID`),
  KEY `admin_role_foreign` (`ROLE_ID`) USING BTREE,
  CONSTRAINT `fk_ref_admin` FOREIGN KEY (`ADMIN_ID`) REFERENCES `ADMIN` (`ID`),
  CONSTRAINT `fk_ref_role` FOREIGN KEY (`ROLE_ID`) REFERENCES `ROLE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
