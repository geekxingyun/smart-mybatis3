DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
`customerId` bigint(20) NOT NULL AUTO_INCREMENT,
`customerScore` bigint(20) DEFAULT NULL,
`customerPhoto` varchar(255) DEFAULT NULL,
`customerAge` int(11) DEFAULT NULL,
`customerToken` varchar(255) DEFAULT NULL,
`customerRealName` varchar(255) DEFAULT NULL,
`customerLevelId` bigint(20) DEFAULT NULL,
`customerSex` int(11) DEFAULT NULL,
`customerAccount` varchar(255) DEFAULT NULL,
`customerNikeName` varchar(255) DEFAULT NULL,
`customerRegisterTime` bigint(20) DEFAULT NULL,
`customerPassword` varchar(255) DEFAULT NULL,
PRIMARY KEY (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;