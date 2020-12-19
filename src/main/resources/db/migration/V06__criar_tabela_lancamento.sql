CREATE TABLE lancamento (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(50) NOT NULL,
  `data_vencimento` DATE NOT NULL,
  `data_pagamento` DATE NULL,
  `valor` DECIMAL(10,2) NOT NULL,
  `observacao` VARCHAR(100) NULL,
  `tipo` VARCHAR(20) NOT NULL,
  `id_categoria` BIGINT(20) NOT NULL,
  `id_pessoa` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_categoria_idx` (`id_categoria` ASC),
  INDEX `id_pessoa_idx` (`id_pessoa` ASC),
  CONSTRAINT `id_categoria`
    FOREIGN KEY (`id_categoria`)
    REFERENCES categoria (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_pessoa`
    FOREIGN KEY (`id_pessoa`)
    REFERENCES pessoa (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

