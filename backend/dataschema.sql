-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema safehouse
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema safehouse
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `safehouse` DEFAULT CHARACTER SET utf8mb3 ;
USE `safehouse` ;

-- -----------------------------------------------------
-- Table `safehouse`.`policestation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safehouse`.`policestation` (
  `name` VARCHAR(45) NOT NULL,
  `addr` VARCHAR(45) NULL DEFAULT NULL,
  `lat` VARCHAR(64) NULL DEFAULT NULL,
  `lng` VARCHAR(64) NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `safehouse`.`crime`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safehouse`.`crime` (
  `year` INT NOT NULL,
  `policestation` VARCHAR(45) NOT NULL,
  `murder` INT NULL DEFAULT NULL,
  `rob` INT NULL DEFAULT NULL,
  `theft` INT NULL DEFAULT NULL,
  `assault` INT NULL DEFAULT NULL,
  `murdergrade` INT NULL,
  `robgrade` INT NULL,
  `theftgrade` INT NULL,
  `robgrade` INT NULL,
  PRIMARY KEY (`year`),
  INDEX `fk_crime_policestation1_idx` (`policestation` ASC) VISIBLE,
  CONSTRAINT `fk_crime_policestation1`
    FOREIGN KEY (`policestation`)
    REFERENCES `safehouse`.`policestation` (`name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `safehouse`.`dongcode`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safehouse`.`dongcode` (
  `dongCode` VARCHAR(10) NOT NULL,
  `sidoName` VARCHAR(45) NULL DEFAULT NULL,
  `gugunName` VARCHAR(45) NULL DEFAULT NULL,
  `dongName` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`dongCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `safehouse`.`members`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safehouse`.`members` (
  `id` VARCHAR(45) NOT NULL,
  `pwd` VARCHAR(64) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `addr` VARCHAR(45) NULL DEFAULT NULL,
  `tel` VARCHAR(45) NULL DEFAULT NULL,
  `refreshtoken` VARCHAR(64) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `safehouse`.`favoritearea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safehouse`.`favoritearea` (
  `no` INT NOT NULL,
  `userId` VARCHAR(45) NULL DEFAULT NULL,
  `dongCode` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`no`),
  INDEX `fk_favoritearea_members1_idx` (`userId` ASC) VISIBLE,
  INDEX `fk_favoritearea_dongcode1_idx` (`dongCode` ASC) VISIBLE,
  CONSTRAINT `fk_favoritearea_dongcode1`
    FOREIGN KEY (`dongCode`)
    REFERENCES `safehouse`.`dongcode` (`dongCode`),
  CONSTRAINT `fk_favoritearea_members1`
    FOREIGN KEY (`userId`)
    REFERENCES `safehouse`.`members` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `safehouse`.`houseinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safehouse`.`houseinfo` (
  `aptCode` VARCHAR(10) NOT NULL,
  `aptName` VARCHAR(45) NULL DEFAULT NULL,
  `dongCode` VARCHAR(45) NULL DEFAULT NULL,
  `dongName` VARCHAR(45) NULL DEFAULT NULL,
  `jibun` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`aptCode`),
  INDEX `fk_houseinfo_dongcode1_idx` (`dongCode` ASC) VISIBLE,
  CONSTRAINT `fk_houseinfo_dongcode`
    FOREIGN KEY (`dongCode`)
    REFERENCES `safehouse`.`dongcode` (`dongCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `safehouse`.`housedeal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safehouse`.`housedeal` (
  `no` INT NOT NULL,
  `aptCode` VARCHAR(10) NULL DEFAULT NULL,
  `dealAmount` VARCHAR(45) NULL DEFAULT NULL,
  `dealYear` INT NULL DEFAULT NULL,
  `dealMonth` INT NULL DEFAULT NULL,
  `dealDay` INT NULL DEFAULT NULL,
  `area` VARCHAR(45) NULL DEFAULT NULL,
  `floor` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`no`),
  INDEX `fk_housedeal_houseinfo_idx` (`aptCode` ASC) VISIBLE,
  CONSTRAINT `fk_housedeal_houseinfo`
    FOREIGN KEY (`aptCode`)
    REFERENCES `safehouse`.`houseinfo` (`aptCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `safehouse`.`qna`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `safehouse`.`qna` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(45) NULL DEFAULT NULL,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(500) NULL DEFAULT NULL,
  `regtime` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`no`),
  INDEX `fk_board_members1_idx` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_board_members1`
    FOREIGN KEY (`id`)
    REFERENCES `safehouse`.`members` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
