CREATE TABLE tabela_frete
(
    id                 INT NOT NULL PRIMARY KEY,
    descricao_curta    varchar(500) NOT NULL,
    km_percorrido      numeric NOT NULL,
    taxa_administracao numeric NOT NULL
);

CREATE SEQUENCE seq_tabela_frete START 1;

CREATE UNIQUE INDEX idx_descricao_curta
    ON tabela_frete(descricao_curta);

CREATE TABLE custo_entrega
(
    id                  INT NOT NULL PRIMARY KEY,
    cep_origem_entrega  varchar(1000) NOT NULL,
    cep_destino_entrega varchar(1000) NOT NULL,
    id_tabela_frete     int NOT NULL,
    FOREIGN KEY (id_tabela_frete) REFERENCES tabela_frete (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE SEQUENCE seq_custo_entrega START 1;

CREATE TABLE entrega
(
    id                  INT NOT NULL PRIMARY KEY,
    descricao_carga     varchar(1000),
    cep_origem_entrega  varchar(1000),
    cep_destino_entrega varchar(1000),
    id_entregador       int,
    id_tabela_frete    int,
    FOREIGN KEY (id_tabela_frete) REFERENCES tabela_frete (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE SEQUENCE seq_entrega START 1;