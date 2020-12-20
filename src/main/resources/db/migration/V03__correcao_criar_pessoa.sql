CREATE TABLE pessoa (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
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

INSERT INTO pessoa (`id`, `name`, `ativo`, `logradouro`, `numero`, `complemento`, `bairro`, `cep`, `cidade`, `estado`) VALUES ('1', 'Fabricio', true, 'Rua francisco', '1595', '105', 'manaira', '58.038-521', 'João Pessoa', 'PB');
INSERT INTO pessoa (`id`, `name`, `ativo`, `logradouro`, `numero`, `complemento`, `bairro`, `cep`, `cidade`, `estado`) VALUES ('2', 'Joana', true, 'Maria Mendonca', '190', '90', 'Manaira', '78.038-568', 'João Pessoa', 'PB');

