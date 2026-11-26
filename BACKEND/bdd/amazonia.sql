-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema java_2993_amazonia
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema java_2993_amazonia
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `java_2993_amazonia` DEFAULT CHARACTER SET utf8mb4 ;
USE `java_2993_amazonia` ;

-- -----------------------------------------------------
-- Table `java_2993_amazonia`.`productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `java_2993_amazonia`.`productos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `precio` DECIMAL(38,2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java_2993_amazonia`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `java_2993_amazonia`.`clientes` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `apellidos` VARCHAR(100) NULL,
  `nif` CHAR(9) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nif_UNIQUE` (`nif` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java_2993_amazonia`.`facturas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `java_2993_amazonia`.`facturas` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `numero` CHAR(9) NOT NULL,
  `fecha` DATE NOT NULL,
  `clientes_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `numero_UNIQUE` (`numero` ASC) VISIBLE,
  INDEX `fk_facturas_clientes_idx` (`clientes_id` ASC) VISIBLE,
  CONSTRAINT `fk_facturas_clientes`
    FOREIGN KEY (`clientes_id`)
    REFERENCES `java_2993_amazonia`.`clientes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java_2993_amazonia`.`facturas_tiene_productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `java_2993_amazonia`.`facturas_tiene_productos` (
  `facturas_id` BIGINT NOT NULL,
  `productos_id` BIGINT NOT NULL,
  `cantidad` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`facturas_id`, `productos_id`),
  INDEX `fk_facturas_tiene_productos_productos1_idx` (`productos_id` ASC) VISIBLE,
  INDEX `fk_facturas_tiene_productos_facturas1_idx` (`facturas_id` ASC) VISIBLE,
  CONSTRAINT `fk_facturas_tiene_productos_facturas1`
    FOREIGN KEY (`facturas_id`)
    REFERENCES `java_2993_amazonia`.`facturas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_facturas_tiene_productos_productos1`
    FOREIGN KEY (`productos_id`)
    REFERENCES `java_2993_amazonia`.`productos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java_2993_amazonia`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `java_2993_amazonia`.`usuarios` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `clientes_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_usuarios_clientes1_idx` (`clientes_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuarios_clientes1`
    FOREIGN KEY (`clientes_id`)
    REFERENCES `java_2993_amazonia`.`clientes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java_2993_amazonia`.`hoteles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `java_2993_amazonia`.`hoteles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `java_2993_amazonia`.`habitaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `java_2993_amazonia`.`habitaciones` (
  `numero` CHAR(3) NOT NULL,
  `descripcion` VARCHAR(50) NOT NULL,
  `hoteles_id` BIGINT NOT NULL,
  PRIMARY KEY (`numero`, `hoteles_id`),
  INDEX `fk_habitaciones_hoteles1_idx` (`hoteles_id` ASC) VISIBLE,
  CONSTRAINT `fk_habitaciones_hoteles1`
    FOREIGN KEY (`hoteles_id`)
    REFERENCES `java_2993_amazonia`.`hoteles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
