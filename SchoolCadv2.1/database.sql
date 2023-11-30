DROP DATABASE IF EXISTS escola;
CREATE DATABASE escola;

USE escola;


CREATE TABLE empresa (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         nome VARCHAR(128),
                         registro VARCHAR(64),
                         area VARCHAR(64)
);

CREATE TABLE turma (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       ano varchar(10),
                       cod_sala VARCHAR(64),
                       id_professor INT
);

CREATE TABLE professor (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           nome VARCHAR(128),
                           registro VARCHAR(64),
                           materia VARCHAR(64),
                           id_turma INT
);

CREATE TABLE aluno (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       nome VARCHAR(128),
                       registro VARCHAR(64) UNIQUE,
                       id_turma INT,
                       id_estagio INT,
                       FOREIGN KEY (id_turma) REFERENCES turma(id),
                       FOREIGN KEY (id_estagio) REFERENCES empresa(id)
);
