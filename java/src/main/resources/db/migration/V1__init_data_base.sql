CREATE TABLE tabela_frete
(
    id                 INT NOT NULL PRIMARY KEY,
    descricao_curta    varchar(500),
    km_percorrido      numeric,
    taxa_administracao numeric
);

CREATE SEQUENCE seq_tabela_frete START 1;

CREATE TABLE custo_entrega
(
    id                  INT NOT NULL PRIMARY KEY,
    cep_origem_entrega  varchar(1000),
    cep_destino_entrega varchar(1000),
    id_tabela_frete     int,
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