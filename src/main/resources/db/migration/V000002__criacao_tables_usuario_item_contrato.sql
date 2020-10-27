CREATE TABLE usuario (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(65) NOT NULL,
  senha VARCHAR(45) NOT NULL,
  obs VARCHAR(45) NULL,
  departamento_id INT NOT NULL,
  email VARCHAR(80) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_usuario_departamento1_idx (departamento_id ASC),
  UNIQUE INDEX email_UNIQUE (email ASC),
  UNIQUE INDEX nome_UNIQUE (nome ASC),
  CONSTRAINT fk_usuario_departamento1
    FOREIGN KEY (departamento_id)
    REFERENCES gestao_contratos.departamento (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE contrato (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  data_inicio DATE NOT NULL,
  data_fim DATE NOT NULL,
  data_alerta DATE NULL,
  alerta INT NOT NULL DEFAULT 0,
  descricao VARCHAR(255) NOT NULL,
  obs VARCHAR(255) NULL,
  empresa_id INT NOT NULL,
  email_alerta VARCHAR(60) NOT NULL,
  usuario_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_contrato_empresa_idx (empresa_id ASC),
  INDEX fk_contrato_usuario1_idx (usuario_id ASC),
  CONSTRAINT fk_contrato_empresa
    FOREIGN KEY (empresa_id)
    REFERENCES gestao_contratos.empresa (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_contrato_usuario1
    FOREIGN KEY (usuario_id)
    REFERENCES gestao_contratos.usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE item (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(80) NOT NULL,
  quantidade_total INT NOT NULL DEFAULT 0,
  garantia VARCHAR(45) NULL,
  qtd_restante INT NOT NULL DEFAULT 0,
  contrato_id BIGINT(20) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_item_contrato1_idx (contrato_id ASC),
  CONSTRAINT fk_item_contrato1
    FOREIGN KEY (contrato_id)
    REFERENCES gestao_contratos.contrato (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;