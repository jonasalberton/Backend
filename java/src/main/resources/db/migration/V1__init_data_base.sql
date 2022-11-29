create table permissao
(
    permissao varchar(200)
);

insert into permissao values ('ROLE_ADMIN');

create table usuario
(
    username varchar(200),
    password varchar(800),
    nome     varchar(500)
);

insert into usuario (username, nome, password)
values ('mat', 'Matheus Matias', '$2a$10$zkdGczSkcIqK7DEZs.tJOeikiP4wDY3m7D/V.3xNtlpiV0ex7pfM6');