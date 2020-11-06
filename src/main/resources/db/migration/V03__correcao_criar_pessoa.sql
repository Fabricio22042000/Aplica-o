CREATE TABLE `algamoneyapi`.`pessoa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `ativo` TINYINT NOT NULL,
  `logradouro` VARCHAR(45) NOT NULL,
  `numero` VARCHAR(45) NOT NULL,
  `complemento` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `cep` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

