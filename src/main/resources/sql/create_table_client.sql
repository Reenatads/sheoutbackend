CREATE TABLE client
(
    id  bigint(20) NOT NULL AUTO_INCREMENT,
    cpf varchar(255) DEFAULT NULL,
    cep varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
);