CREATE TABLE usuario (
  `idusuario` BIGINT(20) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `senha` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idusuario`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `algamoneyapi`.`permissao` (
  `idpermissao` BIGINT(20) NOT NULL,
  `descricao` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idpermissao`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `algamoneyapi`.`usuario_permissao`(
  `id_usuario` BIGINT(20) NOT NULL,
  `id_permissao` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id_usuario`, `id_permissao`),
  FOREIGN KEY (`id_usuario`) REFERENCES usuario(`idusuario`),
  FOREIGN KEY (`id_permissao`) REFERENCES permissao(`idpermissao`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
