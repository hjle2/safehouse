-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- Schema safehouse

-- Schema safehouse

CREATE SCHEMA IF NOT EXISTS safehouse DEFAULT CHARACTER SET utf8 ;
USE safehouse ;

-- Table safehouse.dongcode

DROP TABLE IF EXISTS safehouse.dongcode ;

CREATE TABLE IF NOT EXISTS safehouse.dongcode (
  dongCode VARCHAR(10) NOT NULL,
  sidoName VARCHAR(45) NULL,
  gugunName VARCHAR(45) NULL,
  dongName VARCHAR(45) NULL,
  PRIMARY KEY (dongCode))
ENGINE = InnoDB;

-- Table safehouse.members

DROP TABLE IF EXISTS safehouse.members ;

CREATE TABLE IF NOT EXISTS safehouse.members (
  id VARCHAR(45) NOT NULL,
  pwd VARCHAR(45) NOT NULL,
  name VARCHAR(45) NOT NULL,
  addr VARCHAR(45) NULL,
  tel VARCHAR(45) NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;

-- Table safehouse.houseinfo

DROP TABLE IF EXISTS safehouse.houseinfo ;

CREATE TABLE IF NOT EXISTS safehouse.houseinfo (
  aptCode VARCHAR(10) NOT NULL,
  aptName VARCHAR(45) NULL,
  dongCode VARCHAR(45) NULL,
  dongName VARCHAR(45) NULL,
  jibun VARCHAR(45) NULL,
  PRIMARY KEY (aptCode),
  INDEX fk_houseinfo_dongcode1_idx (dongCode ASC) VISIBLE,
  CONSTRAINT fk_houseinfo_dongcode
    FOREIGN KEY (dongCode)
    REFERENCES safehouse.dongcode (dongCode)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- Table safehouse.housedeal

DROP TABLE IF EXISTS safehouse.housedeal ;

CREATE TABLE IF NOT EXISTS safehouse.housedeal (
  no INT NOT NULL,
  aptCode VARCHAR(10) NULL,
  dealAmount VARCHAR(45) NULL,
  dealYear INT NULL,
  dealMonth INT NULL,
  dealDay INT NULL,
  area VARCHAR(45) NULL,
  floor VARCHAR(45) NULL,
  PRIMARY KEY (no),
  INDEX fk_housedeal_houseinfo_idx (aptCode ASC) VISIBLE,
  CONSTRAINT fk_housedeal_houseinfo
    FOREIGN KEY (aptCode)
    REFERENCES safehouse.houseinfo (aptCode)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- Table safehouse.policestation

DROP TABLE IF EXISTS safehouse.policestation ;

CREATE TABLE IF NOT EXISTS safehouse.policestation (
  name VARCHAR(45) NOT NULL,
  addr VARCHAR(45) NULL,
  dongcode VARCHAR(10) NULL,
  PRIMARY KEY (name))
ENGINE = InnoDB;

-- Table safehouse.favoritearea

DROP TABLE IF EXISTS safehouse.favoritearea ;

CREATE TABLE IF NOT EXISTS safehouse.favoritearea (
  no INT NOT NULL,
  userId VARCHAR(45) NULL,
  dongCode VARCHAR(10) NULL,
  PRIMARY KEY (no),
  INDEX fk_favoritearea_members1_idx (userId ASC) VISIBLE,
  INDEX fk_favoritearea_dongcode1_idx (dongCode ASC) VISIBLE,
  CONSTRAINT fk_favoritearea_members1
    FOREIGN KEY (userId)
    REFERENCES safehouse.members (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_favoritearea_dongcode1
    FOREIGN KEY (dongCode)
    REFERENCES safehouse.dongcode (dongCode)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- Table safehouse.crime

DROP TABLE IF EXISTS safehouse.crime ;

CREATE TABLE IF NOT EXISTS safehouse.crime (
  year INT NOT NULL,
  policestation VARCHAR(45) NOT NULL,
  murder INT NULL,
  rob INT NULL,
  theft INT NULL,
  assault INT NULL,
  PRIMARY KEY (year),
  INDEX fk_crime_policestation1_idx (policestation ASC) VISIBLE,
  CONSTRAINT fk_crime_policestation1
    FOREIGN KEY (policestation)
    REFERENCES safehouse.policestation (name)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- Table safehouse.qna

DROP TABLE IF EXISTS safehouse.qna ;

CREATE TABLE IF NOT EXISTS safehouse.qna (
  no INT NOT NULL AUTO_INCREMENT,
  id VARCHAR(45) NULL,
  title VARCHAR(100) NULL,
  content VARCHAR(500) NULL,
  regtime DATETIME NULL,
  PRIMARY KEY (no),
  INDEX fk_board_members1_idx (id ASC) VISIBLE,
  CONSTRAINT fk_board_members1
    FOREIGN KEY (id)
    REFERENCES safehouse.members (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- Table safehouse.news

DROP TABLE IF EXISTS safehouse.news ;

CREATE TABLE IF NOT EXISTS safehouse.news (
  no INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(200) NULL,
  link VARCHAR(200) NULL,
  PRIMARY KEY (no))
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

