CREATE TABLE `empresa` (
	`id` bigint(20) NOT NULL,
	`cnpj` varchar(225) NOT NULL,
	`data_criacao` datetime NOT NULL,
	`data_atualizacao` datetime NOT NULL,
	`razao_social` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `funcionario` (
	`id` bigint(20) NOT NULL,
	`cpf` varchar(225) NOT NULL,
	`data_criacao` datetime NOT NULL,
	`data_atualizacao` datetime NOT NULL,
	`email` varchar(225) NOT NULL,
	`nome` varchar(225) NOT NULL,
	`perfil` varchar(225) NOT NULL,
	`senha` varchar(225) NOT NULL,
	`qtd_hora_almoco` float DEFAULT NULL,
	`qtd_hora_trabalho_dia` float DEFAULT NULL,
	`valor_hora` decimal(19,2) DEFAULT NULL,
	`empresa_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lancamento` (
	`id` bigint(20) NOT NULL,
	`cnpj` varchar(225) NOT NULL,
	`data_criacao` datetime NOT NULL,
	`data_atualizacao` datetime NOT NULL,
	`razao_social` varchar(225) NOT NULL,
	`funcionario_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `empresa`
	ADD PRIMARY KEY (`id`);

ALTER TABLE `funcionario`
	ADD PRIMARY KEY (`id`),
	ADD KEY `FK_funcionario_empresa` (`empresa_id`);
	
ALTER TABLE `lancamento`
	ADD PRIMARY KEY (`id`),
	ADD KEY `FK_lancamento_funcionario` (`funcionario_id`);
	
ALTER TABLE `empresa`
	MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
	
ALTER TABLE `funcionario`
	MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
	
ALTER TABLE `lancamento`
	MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
	
ALTER TABLE `funcionario`
	ADD CONSTRAINT `FK_funcionario_empresa` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`id`);

ALTER TABLE `lancamento`
	ADD CONSTRAINT `FK_lancamento_funcionario` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`);