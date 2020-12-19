ALTER TABLE usuario 
CHANGE COLUMN `senha` `senha` VARCHAR(150) NOT NULL ;

INSERT INTO usuario(`idusuario`, `nome`, `email`, `senha`) VALUES (1,'Administrador', 'admin@gmail.com', '$2a$10$KjTcLpgAjHdkZA03ZTeoTuhxg3w3dkXsB4Xvjty5u5NEOLI4HtiFK');
INSERT INTO usuario(`idusuario`, `nome`, `email`, `senha`) VALUES (2, 'Maria Silva', 'maria@gmail.com', '$2a$10$l8PEv5GtStRcpV2ol4A.ZexJ488VNYDRfG7n.X8f5zY0vIr8Z0Yve');

INSERT INTO permissao(`idpermissao`, `descricao`) VALUES (1, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao(`idpermissao`, `descricao`) VALUES (2, 'ROLE_PESQUISAR_CATEGORIA');
INSERT INTO permissao(`idpermissao`, `descricao`) VALUES (3, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao(`idpermissao`, `descricao`) VALUES (4, 'ROLE_REMOVER_PESSOA');
INSERT INTO permissao(`idpermissao`, `descricao`) VALUES (5, 'ROLE_PESQUISAR_PESSOA');
INSERT INTO permissao(`idpermissao`, `descricao`) VALUES (6, 'ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao(`idpermissao`, `descricao`) VALUES (7, 'ROLE_REMOVER_LANCAMENTO');
INSERT INTO permissao(`idpermissao`, `descricao`) VALUES (8, 'ROLE_PESQUISAR_LANCAMENTO');
