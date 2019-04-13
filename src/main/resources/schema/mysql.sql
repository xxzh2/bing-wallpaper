/********************
 * Ver 1.2
 * 2019/3/12
 *******************/

CREATE DATABASE `fms` COLLATE 'utf8_general_ci' ;

USE fms;

CREATE TABLE `fms_app_auth` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`app_id` VARCHAR(50) NULL DEFAULT NULL,
	`app_key` VARCHAR(50) NULL DEFAULT NULL,
	`app_info` VARCHAR(50) NULL DEFAULT NULL,
	`app_host` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `fms_file_list` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` TINYTEXT NULL,
	`type` VARCHAR(50) NULL DEFAULT NULL,
	`sha1` VARCHAR(128) NULL DEFAULT NULL,
	`size` VARCHAR(50) NULL DEFAULT NULL,
	`owner` VARCHAR(50) NULL DEFAULT NULL,
	`share` VARCHAR(50) NULL DEFAULT NULL,
	`count` TINYINT(4) NULL DEFAULT '0',
	`desc` TEXT NULL,
	`create_date` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `share` (`share`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;

CREATE TABLE `fms_file_store` (
	`sha1` VARCHAR(128) NOT NULL,
	`crc` VARCHAR(64) NULL DEFAULT NULL,
	`path` TEXT NOT NULL,
	`exist` VARCHAR(5) NULL DEFAULT 0,
	`update_date` VARCHAR(50) NULL DEFAULT NULL,
	`create_date` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`sha1`),
	UNIQUE INDEX `crc` (`crc`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

