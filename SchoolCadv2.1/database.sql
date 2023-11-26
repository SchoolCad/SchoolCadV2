DROP DATABASE escola;
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
    id_turma INT,
    FOREIGN KEY (id_turma) REFERENCES turma(id)
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

-- Insert data into the professor table
INSERT INTO professor (id, nome, registro, materia, id_turma) VALUES
(1, 'Professor A', 'P001', 'Mathematics', 1),
(2, 'Professor B', 'P002', 'Physics', 2),
(3, 'Professor C', 'P003', 'Chemistry', 1),
(4, 'Professor D', 'P004', 'Biology', 3),
(5, 'Professor E', 'P005', 'Computer Science', 2);

-- Insert data into the turma table
INSERT INTO turma (id, ano, cod_sala, id_professor) VALUES
(1, '1 ano', 'S101', 1),
(2, '2 ano', 'S102', 2),
(3, '3 ano', 'S103', 3),
(4, '4 ano', 'S104', 4),
(5, '5 ano', 'S105', 5);

-- Insert data into the empresa table
INSERT INTO empresa (id, nome, registro, area) VALUES
(1, 'Company A', 'C001', 'Technology'),
(2, 'Company B', 'C002', 'Finance'),
(3, 'Company C', 'C003', 'Healthcare'),
(4, 'Company D', 'C004', 'Manufacturing'),
(5, 'Company E', 'C005', 'Education');

-- Insert data into the aluno table
INSERT INTO aluno (id, nome, registro, id_turma, id_estagio) VALUES
(1, 'Student A', 'S001', 1, 1),
(2, 'Student B', 'S002', 2, 2),
(3, 'Student C', 'S003', 1, 3),
(4, 'Student D', 'S004', 3, 4),
(5, 'Student E', 'S005', 2, 5);