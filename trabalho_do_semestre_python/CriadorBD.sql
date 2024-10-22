CREATE DATABASE produtos_bd;
USE produtos_bd;

DROP TABLE IF EXISTS PRODUTOS;

CREATE TABLE PRODUTOS(
                         PRODUTOS_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                         NOME VARCHAR(225),
                         DESCRICAO VARCHAR(1024),
                         MARCA VARCHAR(225),
                         CATEGORIA VARCHAR(225),
                         PRECO DECIMAL(20,2),
                         CUSTO DECIMAL(20,2)
);


INSERT INTO PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO)
VALUES ('Camiseta Radiohead', 'Camiseta preta com a cor do álbum', 'Hering', 'Camiseta', 25, 10);

INSERT INTO PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO)
VALUES ('CD Wilco', 'Album de Ghost is born', 'Virgin Records', 'CD', 15.5,  8);

INSERT INTO PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO)
VALUES ('CD Hollywoods Bleending', 'Album de Post Malone', 'Republic Record', 'CD', 50, 28);

INSERT INTO PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO)
VALUES ('CD Live in London', 'Album de Jorge e Mateus', 'Som Livre', 'CD', 50, 19);

INSERT INTO PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO)
VALUES ('Blu-ray Metallica', 'Album  Orquestra Sinfônica de San Francisco', 'Blackened Recordings', 'Blu-ray', 59.99, 35);

INSERT INTO PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO)
VALUES ('Fone de Ouvido Bluetooth', 'Fone de ouvido sem fio', 'Sony', 'Acessório', 199.9, 120);



INSERT INTO PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO)
VALUES ('Camiseta Foo Fighters', 'Camiseta preta com a cor do álbum', 'JhonJhon', 'Camiseta', 45, 25);

INSERT INTO PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO)
VALUES ('Quadro Rock', 'Quadro com várias bandas de rock', 'Frames', 'Quadro', 70, 40);

INSERT INTO PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO)
VALUES ('CD The color and the Shape', 'Album Foo fighters', 'Capitol Records', 'CD', 40, 20);

INSERT INTO PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO)
VALUES ('Camisa Red Hot Chili Peppers', 'Camisa banda', 'Rock Store', 'Camisa', 80, 60);

INSERT INTO PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO)
VALUES ('MP4', 'MP4 para ouvir músicas', 'Sony', 'Eletrônico', 150, 90);

INSERT INTO PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO)
VALUES ('Fone de ouvido com fio', 'Fone de ouvido com fio', 'JBL', 'Acessório', 230, 180);

INSERT INTO PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO)
VALUES ('CD All Hope is Gone', 'Album Slipknot', 'Capitol Records', 'CD', 35, 15);

INSERT INTO PRODUTOS (NOME, DESCRICAO, MARCA, CATEGORIA, PRECO, CUSTO)
VALUES ('CD Continuum', 'Album John Mayer', 'Capitol Records', 'CD', 40, 20);

CREATE USER 'Adm'@'localhost' IDENTIFIED BY 'Secret_123';
GRANT ALL PRIVILEGES ON produtos_bd.* TO 'Adm'@'localhost';