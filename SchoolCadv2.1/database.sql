DROP DATABASE IF EXISTS escola;
CREATE DATABASE escola;

USE escola;

CREATE TABLE professor (
    id INT PRIMARY KEY auto_increment,
    nome VARCHAR(128),
    registro VARCHAR(64),
    materia VARCHAR(64)
);

CREATE TABLE turma (
    id INT PRIMARY KEY auto_increment,
    ano INT,
    cod_sala VARCHAR(64)
);

CREATE TABLE empresa	 (
    id INT PRIMARY KEY auto_increment,
    nome VARCHAR(128),
    registro VARCHAR(64),
    area VARCHAR(64)
);

CREATE TABLE aluno (
    id INT PRIMARY KEY auto_increment,
    nome VARCHAR(128),
    registro VARCHAR(64),
    serie INT,
    id_turma INT,
    id_estagio INT,
    FOREIGN KEY (id_turma) REFERENCES turma(id),
    FOREIGN KEY (id_estagio) REFERENCES empresa(id)
);

CREATE TABLE professor_turma (
    id_professor INT,
    id_turma INT,
    PRIMARY KEY (id_professor, id_turma),
    FOREIGN KEY (id_professor) REFERENCES professor(id),
    FOREIGN KEY (id_turma) REFERENCES turma(id)
);