CREATE DATABASE IF NOT EXISTS familyvideo;

USE familyvideo;

DROP TABLE IF EXISTS movies;
DROP TABLE IF EXISTS actors;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS classifications;
DROP TABLE IF EXISTS directors;
DROP TABLE IF EXISTS sections;
DROP TABLE IF EXISTS employess;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS exemplar;
DROP TABLE IF EXISTS movies_actors;
DROP TABLE IF EXISTS genres_movies;
DROP TABLE IF EXISTS borrowing_exemplar;
DROP TABLE IF EXISTS borrowings;

CREATE TABLE genres (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        genre_name VARCHAR(45),
                        genre_description VARCHAR(100)
);

CREATE TABLE directors (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           director_name VARCHAR(45),
                           director_last_name VARCHAR(45),
                           director_description VARCHAR(100)
);

CREATE TABLE actors (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        actor_name VARCHAR(45),
                        actor_last_name VARCHAR(45),
                        actor_birthday_date DATE
);

CREATE TABLE classifications (
                                 id INT PRIMARY KEY AUTO_INCREMENT,
                                 classification_name VARCHAR(45),
                                 classification_description VARCHAR(45),
                                 classification_age INT
);

CREATE TABLE movies (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        fk_director INT,
                        fk_classification INT,
                        movie_name VARCHAR(45),
                        movie_synopsis VARCHAR(100),
                        movie_release_year INT,
                        FOREIGN KEY (fk_director) REFERENCES directors(id) ON DELETE SET NULL ON UPDATE CASCADE,
                        FOREIGN KEY (fk_classification) REFERENCES classifications(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE genres_movies (
                               fk_movie INT,
                               fk_genre INT,
                               PRIMARY KEY (fk_movie, fk_genre),
                               FOREIGN KEY (fk_movie) REFERENCES movies(id) ON DELETE CASCADE ON UPDATE CASCADE,
                               FOREIGN KEY (fk_genre) REFERENCES genres(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE movies_actors (
                               fk_movie INT,
                               fk_actors INT,
                               PRIMARY KEY (fk_movie, fk_actors),
                               FOREIGN KEY (fk_movie) REFERENCES movies(id) ON DELETE CASCADE ON UPDATE CASCADE,
                               FOREIGN KEY (fk_actors) REFERENCES actors(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE addresses (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           address_street VARCHAR(45),
                           address_number INT,
                           address_postal_code VARCHAR(11),
                           address_city VARCHAR(45),
                           address_country VARCHAR(45)
);

CREATE TABLE exemplar (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          fk_movie INT,
                          exemplar_buy_date DATE,
                          FOREIGN KEY (fk_movie) REFERENCES movies(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE clients (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         fk_address INT,
                         client_name VARCHAR(45),
                         client_last_name VARCHAR(45),
                         FOREIGN KEY (fk_address) REFERENCES addresses(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE sections (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          fk_classification INT,
                          section_name VARCHAR(45),
                          section_description VARCHAR(100),
                          FOREIGN KEY (fk_classification) REFERENCES classifications(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE employees (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           fk_address INT,
                           fk_section INT,
                           employee_name VARCHAR(45),
                           employee_last_name VARCHAR(45),
                           employee_salary FLOAT,
                           employee_birthday_date DATE,
                           FOREIGN KEY (fk_address) REFERENCES addresses(id) ON DELETE SET NULL ON UPDATE CASCADE,
                           FOREIGN KEY (fk_section) REFERENCES sections(id) ON DELETE SET NULL ON UPDATE CASCADE
);


CREATE TABLE borrowings (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            fk_client INT,
                            fk_employee INT,
                            borrowing_is_paid BOOLEAN,
                            borrowing_sale_date DATE,
                            borrowing_devolution_date DATE,
                            FOREIGN KEY (fk_client) REFERENCES clients(id) ON DELETE RESTRICT ON UPDATE CASCADE,
                            FOREIGN KEY (fk_employee) REFERENCES employees(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE borrowing_exemplar (
                                    fk_exemplar INT,
                                    fk_borrowing INT,
                                    PRIMARY KEY (fk_exemplar, fk_borrowing),
                                    FOREIGN KEY (fk_exemplar) REFERENCES exemplar(id) ON DELETE CASCADE ON UPDATE CASCADE,
                                    FOREIGN KEY (fk_borrowing) REFERENCES borrowings(id) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO directors (director_name, director_last_name, director_description) VALUES
('Night', 'Shyamalan', 'A director and screenwriter, which earned him an Oscar nomination.'),
('Christopher', 'Nolan', 'A British director and screenwriter.'),
('Quentin', 'Tarantino', 'A screenwriter, producer, actor, cinematographer, and film critic from the U.S.'),
('Donald', 'Petrie', 'Donald Mark Petrie is an American actor and filmmaker.'),
('Dennis', 'Dugan', 'A film and television director and actor.'),
('John', 'Krasinski', 'An American actor and director.'),
('Hayao', 'Miyazaki', 'A Japanese animator, filmmaker, screenwriter, writer, and manga artist.'),
('Pete', 'Docter', 'An American filmmaker and screenwriter.'),
('Anthony', 'Russo', 'An American film and television director.'),
('Mike', 'Newell', 'An English film director and filmmaker.'),
('Walter', 'Salles', 'An award-winning Brazilian filmmaker.'),
('Lana', 'Wachowski', 'A renowned director of The Matrix.'),
('Jordan', 'Peele', 'An American actor and filmmaker.'),
('Rian', 'Johnson', 'An American screenwriter and film director.'),
('Damien', 'Chazelle', 'An American filmmaker and screenwriter.');

INSERT INTO classifications (classification_name, classification_description, classification_age) VALUES
('All Ages', 'Suitable for all audiences', 0),
('12+', 'Suitable for ages 12 and up', 12),
('14+', 'Suitable for ages 14 and up', 14),
('18+', 'Suitable for ages 18 and up', 18);

INSERT INTO sections (fk_classification, section_name, section_description) VALUES
(2, 'Afternoon Favorites', 'Movies suitable for ages 12 and up, perfect for relaxed afternoons.'),
(1, 'For the Whole Family', 'Movies suitable for all ages, enjoyable by everyone.'),
(4, 'Restricted Zone', 'Movies strictly for ages 18 and up, featuring mature content.'),
(3, 'Plots and Twists', 'Movies for ages 14 and up, with intriguing plots and unexpected twists.');

INSERT INTO genres (genre_name, genre_description) VALUES
('Suspense', 'Movies with tense, thrilling plots that keep the audience on edge.'),
('Science Fiction', 'Movies based on speculative scientific concepts, often set in the future.'),
('Drama', 'Movies that focus on realistic storytelling and emotional themes.'),
('Action', 'Movies that feature intense physical activity and exciting sequences.'),
('Comedy', 'Movies designed to entertain and make the audience laugh.'),
('Romance', 'Movies that focus on romantic relationships and love stories.'),
('Horror', 'Movies designed to evoke fear and suspense in the audience.'),
('Animation', 'Movies created using animation techniques, often appealing to all ages.'),
('Adventure', 'Movies featuring exciting journeys and explorations in new or dangerous settings.'),
('Fantasy', 'Movies that involve magical or supernatural elements.'),
('Crime', 'Movies that focus on crime, investigation, and criminal activities.'),
('Musical', 'Movies featuring music and dance as a primary storytelling device.');

INSERT INTO actors (actor_name, actor_last_name, actor_birthday_date)
VALUES 
('Haley Joel', 'Osment', '1988-04-10'),
('Toni', 'Collete', '1972-11-01'),
('Bruce', 'Willis', '1955-03-19'),
('Matthew', 'McConaughey', '1969-11-04'),
('Jessica', 'Chastain', '1977-03-24'),
('Anne', 'Hathaway', '1982-11-12'),
('Uma', 'Thurman', '1970-04-29'),
('Lucy', 'Liu', '1968-12-02'),
('David', 'Carradine', '1936-12-08'),
('Kate', 'Hudson', '1979-04-19'),
('Kathryn', 'Hahn', '1973-07-23'),
('Adam', 'Sandler', '1966-09-09'),
('Jennifer', 'Aniston', '1969-02-11'),
('Brooklyn', 'Decker', '1987-04-12'),
('Kevin', 'James', '1965-04-26'),
('David', 'Spade', '1964-07-22'),
('John', 'Krasinski', '1979-10-20'),
('Emily', 'Blunt', '1983-02-23'),
('Millicent', 'Simmonds', '2003-03-06'),
('Rumi', 'Hiiragi', '1987-08-01'),
('Daveigh', 'Chase', '1990-07-24'),
('Tara', 'Strong', '1973-02-12'),
('Billy', 'Crystal', '1948-03-12'),
('John', 'Goodman', '1952-06-20'),
('Pete', 'Docter', '1968-10-09'),
('Robert', 'Downey', '1965-04-04'),
('Chris', 'Evans', '1981-06-13'),
('Scarlett', 'Johansson', '1984-11-22'),
('Daniel', 'Radcliffe', '1989-07-23'),
('Rupert', 'Grint', '1988-08-24'),
('Emma', 'Watson', '1990-04-12'),
('Fernanda', 'Torres', '1965-09-12'),
('Fernanda', 'Montenegro', '1929-10-16'),
('Selton', 'Mello', '1972-12-30'),
('Keanu', 'Reeves', '1964-09-02'),
('Carrie-Anne', 'Moss', '1967-08-21'),
('Laurence', 'Fishburne', '1961-07-30'),
('Daniel', 'Kaluuya', '1989-02-24'),
('Allison', 'Williams', '1998-04-13'),
('Lakeith', 'Stanfield', '1991-08-12'),
('Ana', 'de Armas', '1988-04-30'),
('Daniel', 'Craig', '1968-03-02'),
('Emma', 'Stone', '1988-11-06'),
('Ryan', 'Gosling', '1980-11-12'),
('John', 'Legend', '1978-12-28');

INSERT INTO movies (fk_director, fk_classification, movie_name, movie_synopsis, movie_release_year)
VALUES
(1, 2, 'The Sixth Sense', 'A boy communicates with spirits and seeks help.', 1999),
(2, 2, 'Interstellar', 'Explorers travel through a wormhole to save humanity.', 2014),
(3, 4, 'Kill Bill', 'A former assassin seeks revenge on her ex-colleagues.', 2003),
(4, 2, 'How to Lose a Guy in 10 Days', 'A writer tries to make a man fall in love and push him away.', 2003),
(5, 2, 'Just Go With It', 'A plastic surgeon convinces his assistant to pretend to be his ex-wife.', 2011),
(5, 2, 'Grown Ups', 'Childhood friends reunite for fun, reminiscing, and rivalries.', 2010),
(6, 3, 'A Quiet Place', 'A family must live in silence to avoid monsters with acute hearing.', 2018),
(7, 1, 'Spirited Away', 'A girl must rescue her parents from the spirit world.', 2001),
(8, 1, 'Monsters Inc.', 'Two monsters learn that laughter is more powerful than fear.', 2001),
(9, 2, 'Avengers: Infinity War', 'The Avengers unite to stop Thanos from obtaining the Infinity Stones.', 2018),
(10, 3, 'Harry Potter and the Goblet of Fire', 'Harry faces challenges in the Triwizard Tournament.', 2005),
(11, 3, 'Still Life', 'A lonely man searches for his missing wife.', 2006),
(12, 3, 'The Matrix', 'A hacker learns that his reality is a simulation and must fight.', 1999),
(13, 4, 'Get Out', 'A black man uncovers a disturbing secret at his girlfriend’s family.', 2017),
(14, 3, 'Knives Out', 'A detective investigates the death of a patriarch of a wealthy family.', 2019),
(15, 1, 'La La Land', 'An aspiring actress and a jazz musician fall in love in Los Angeles.', 2016);

INSERT INTO genres_movies (fk_movie, fk_genre) 
VALUES 
((SELECT id FROM movies WHERE movie_name = 'The Sixth Sense'), (SELECT id FROM genres WHERE genre_name = 'Suspense')),
((SELECT id FROM movies WHERE movie_name = 'The Sixth Sense'), (SELECT id FROM genres WHERE genre_name = 'Drama')),
((SELECT id FROM movies WHERE movie_name = 'Interstellar'), (SELECT id FROM genres WHERE genre_name = 'Science Fiction')),
((SELECT id FROM movies WHERE movie_name = 'Kill Bill'), (SELECT id FROM genres WHERE genre_name = 'Action')),
((SELECT id FROM movies WHERE movie_name = 'How to Lose a Guy in 10 Days'), (SELECT id FROM genres WHERE genre_name = 'Comedy')),
((SELECT id FROM movies WHERE movie_name = 'How to Lose a Guy in 10 Days'), (SELECT id FROM genres WHERE genre_name = 'Romance')),
((SELECT id FROM movies WHERE movie_name = 'Just Go With It'), (SELECT id FROM genres WHERE genre_name = 'Comedy')),
((SELECT id FROM movies WHERE movie_name = 'Just Go With It'), (SELECT id FROM genres WHERE genre_name = 'Romance')),
((SELECT id FROM movies WHERE movie_name = 'Grown Ups'), (SELECT id FROM genres WHERE genre_name = 'Comedy')),
((SELECT id FROM movies WHERE movie_name = 'A Quiet Place'), (SELECT id FROM genres WHERE genre_name = 'Horror')),
((SELECT id FROM movies WHERE movie_name = 'A Quiet Place'), (SELECT id FROM genres WHERE genre_name = 'Suspense')),
((SELECT id FROM movies WHERE movie_name = 'Spirited Away'), (SELECT id FROM genres WHERE genre_name = 'Animation')),
((SELECT id FROM movies WHERE movie_name = 'Spirited Away'), (SELECT id FROM genres WHERE genre_name = 'Adventure')),
((SELECT id FROM movies WHERE movie_name = 'Monsters Inc.'), (SELECT id FROM genres WHERE genre_name = 'Animation')),
((SELECT id FROM movies WHERE movie_name = 'Monsters Inc.'), (SELECT id FROM genres WHERE genre_name = 'Adventure')),
((SELECT id FROM movies WHERE movie_name = 'Avengers: Infinity War'), (SELECT id FROM genres WHERE genre_name = 'Action')),
((SELECT id FROM movies WHERE movie_name = 'Avengers: Infinity War'), (SELECT id FROM genres WHERE genre_name = 'Fantasy')),
((SELECT id FROM movies WHERE movie_name = 'Harry Potter and the Goblet of Fire'), (SELECT id FROM genres WHERE genre_name = 'Fantasy')),
((SELECT id FROM movies WHERE movie_name = 'Harry Potter and the Goblet of Fire'), (SELECT id FROM genres WHERE genre_name = 'Adventure')),
((SELECT id FROM movies WHERE movie_name = 'Still Life'), (SELECT id FROM genres WHERE genre_name = 'Drama')),
((SELECT id FROM movies WHERE movie_name = 'The Matrix'), (SELECT id FROM genres WHERE genre_name = 'Action')),
((SELECT id FROM movies WHERE movie_name = 'The Matrix'), (SELECT id FROM genres WHERE genre_name = 'Science Fiction')),
((SELECT id FROM movies WHERE movie_name = 'Get Out'), (SELECT id FROM genres WHERE genre_name = 'Horror')),
((SELECT id FROM movies WHERE movie_name = 'Get Out'), (SELECT id FROM genres WHERE genre_name = 'Drama')),
((SELECT id FROM movies WHERE movie_name = 'Get Out'), (SELECT id FROM genres WHERE genre_name = 'Suspense')),
((SELECT id FROM movies WHERE movie_name = 'Knives Out'), (SELECT id FROM genres WHERE genre_name = 'Crime')),
((SELECT id FROM movies WHERE movie_name = 'Knives Out'), (SELECT id FROM genres WHERE genre_name = 'Suspense')),
((SELECT id FROM movies WHERE movie_name = 'La La Land'), (SELECT id FROM genres WHERE genre_name = 'Musical')),
((SELECT id FROM movies WHERE movie_name = 'La La Land'), (SELECT id FROM genres WHERE genre_name = 'Drama'));

INSERT INTO movies_actors (fk_movie, fk_actors) 
VALUES 
((SELECT id FROM movies WHERE movie_name = 'The Sixth Sense'), (SELECT id FROM actors WHERE actor_name = 'Haley Joel' AND actor_last_name = 'Osment')),
((SELECT id FROM movies WHERE movie_name = 'The Sixth Sense'), (SELECT id FROM actors WHERE actor_name = 'Toni' AND actor_last_name = 'Collete')),
((SELECT id FROM movies WHERE movie_name = 'The Sixth Sense'), (SELECT id FROM actors WHERE actor_name = 'Bruce' AND actor_last_name = 'Willis')),
((SELECT id FROM movies WHERE movie_name = 'Interstellar'), (SELECT id FROM actors WHERE actor_name = 'Matthew' AND actor_last_name = 'McConaughey')),
((SELECT id FROM movies WHERE movie_name = 'Interstellar'), (SELECT id FROM actors WHERE actor_name = 'Jessica' AND actor_last_name = 'Chastain')),
((SELECT id FROM movies WHERE movie_name = 'Interstellar'), (SELECT id FROM actors WHERE actor_name = 'Anne' AND actor_last_name = 'Hathaway')),
((SELECT id FROM movies WHERE movie_name = 'Kill Bill'), (SELECT id FROM actors WHERE actor_name = 'Uma' AND actor_last_name = 'Thurman')),
((SELECT id FROM movies WHERE movie_name = 'Kill Bill'), (SELECT id FROM actors WHERE actor_name = 'Lucy' AND actor_last_name = 'Liu')),
((SELECT id FROM movies WHERE movie_name = 'Kill Bill'), (SELECT id FROM actors WHERE actor_name = 'David' AND actor_last_name = 'Carradine')),
((SELECT id FROM movies WHERE movie_name = 'How to Lose a Guy in 10 Days'), (SELECT id FROM actors WHERE actor_name = 'Kate' AND actor_last_name = 'Hudson')),
((SELECT id FROM movies WHERE movie_name = 'How to Lose a Guy in 10 Days'), (SELECT id FROM actors WHERE actor_name = 'Matthew' AND actor_last_name = 'McConaughey')),
((SELECT id FROM movies WHERE movie_name = 'How to Lose a Guy in 10 Days'), (SELECT id FROM actors WHERE actor_name = 'Kathryn' AND actor_last_name = 'Hahn')),
((SELECT id FROM movies WHERE movie_name = 'Just Go With It'), (SELECT id FROM actors WHERE actor_name = 'Adam' AND actor_last_name = 'Sandler')),
((SELECT id FROM movies WHERE movie_name = 'Just Go With It'), (SELECT id FROM actors WHERE actor_name = 'Jennifer' AND actor_last_name = 'Aniston')),
((SELECT id FROM movies WHERE movie_name = 'Just Go With It'), (SELECT id FROM actors WHERE actor_name = 'Brooklyn' AND actor_last_name = 'Decker')),
((SELECT id FROM movies WHERE movie_name = 'Grown Ups'), (SELECT id FROM actors WHERE actor_name = 'Adam' AND actor_last_name = 'Sandler')),
((SELECT id FROM movies WHERE movie_name = 'Grown Ups'), (SELECT id FROM actors WHERE actor_name = 'Kevin' AND actor_last_name = 'James')),
((SELECT id FROM movies WHERE movie_name = 'Grown Ups'), (SELECT id FROM actors WHERE actor_name = 'David' AND actor_last_name = 'Spade')),
((SELECT id FROM movies WHERE movie_name = 'A Quiet Place'), (SELECT id FROM actors WHERE actor_name = 'John' AND actor_last_name = 'Krasinski')),
((SELECT id FROM movies WHERE movie_name = 'A Quiet Place'), (SELECT id FROM actors WHERE actor_name = 'Emily' AND actor_last_name = 'Blunt')),
((SELECT id FROM movies WHERE movie_name = 'A Quiet Place'), (SELECT id FROM actors WHERE actor_name = 'Millicent' AND actor_last_name = 'Simmonds')),
((SELECT id FROM movies WHERE movie_name = 'Spirited Away'), (SELECT id FROM actors WHERE actor_name = 'Rumi' AND actor_last_name = 'Hiiragi')),
((SELECT id FROM movies WHERE movie_name = 'Spirited Away'), (SELECT id FROM actors WHERE actor_name = 'Daveigh' AND actor_last_name = 'Chase')),
((SELECT id FROM movies WHERE movie_name = 'Spirited Away'), (SELECT id FROM actors WHERE actor_name = 'Tara' AND actor_last_name = 'Strong')),
((SELECT id FROM movies WHERE movie_name = 'Monsters Inc.'), (SELECT id FROM actors WHERE actor_name = 'Billy' AND actor_last_name = 'Crystal')),
((SELECT id FROM movies WHERE movie_name = 'Monsters Inc.'), (SELECT id FROM actors WHERE actor_name = 'John' AND actor_last_name = 'Goodman')),
((SELECT id FROM movies WHERE movie_name = 'Monsters Inc.'), (SELECT id FROM actors WHERE actor_name = 'Pete' AND actor_last_name = 'Docter')),
((SELECT id FROM movies WHERE movie_name = 'Avengers: Infinity War'), (SELECT id FROM actors WHERE actor_name = 'Robert' AND actor_last_name = 'Downey')),
((SELECT id FROM movies WHERE movie_name = 'Avengers: Infinity War'), (SELECT id FROM actors WHERE actor_name = 'Chris' AND actor_last_name = 'Evans')),
((SELECT id FROM movies WHERE movie_name = 'Avengers: Infinity War'), (SELECT id FROM actors WHERE actor_name = 'Scarlett' AND actor_last_name = 'Johansson')),
((SELECT id FROM movies WHERE movie_name = 'Harry Potter and the Goblet of Fire'), (SELECT id FROM actors WHERE actor_name = 'Daniel' AND actor_last_name = 'Radcliffe')),
((SELECT id FROM movies WHERE movie_name = 'Harry Potter and the Goblet of Fire'), (SELECT id FROM actors WHERE actor_name = 'Rupert' AND actor_last_name = 'Grint')),
((SELECT id FROM movies WHERE movie_name = 'Harry Potter and the Goblet of Fire'), (SELECT id FROM actors WHERE actor_name = 'Emma' AND actor_last_name = 'Watson')),
((SELECT id FROM movies WHERE movie_name = 'Still Life'), (SELECT id FROM actors WHERE actor_name = 'Fernanda' AND actor_last_name = 'Torres')),
((SELECT id FROM movies WHERE movie_name = 'Still Life'), (SELECT id FROM actors WHERE actor_name = 'Fernanda' AND actor_last_name = 'Montenegro')),
((SELECT id FROM movies WHERE movie_name = 'Still Life'), (SELECT id FROM actors WHERE actor_name = 'Selton' AND actor_last_name = 'Mello')),
((SELECT id FROM movies WHERE movie_name = 'The Matrix'), (SELECT id FROM actors WHERE actor_name = 'Keanu' AND actor_last_name = 'Reeves')),
((SELECT id FROM movies WHERE movie_name = 'The Matrix'), (SELECT id FROM actors WHERE actor_name = 'Carrie-Anne' AND actor_last_name = 'Moss')),
((SELECT id FROM movies WHERE movie_name = 'The Matrix'), (SELECT id FROM actors WHERE actor_name = 'Laurence' AND actor_last_name = 'Fishburne')),
((SELECT id FROM movies WHERE movie_name = 'Get Out'), (SELECT id FROM actors WHERE actor_name = 'Daniel' AND actor_last_name = 'Kaluuya')),
((SELECT id FROM movies WHERE movie_name = 'Get Out'), (SELECT id FROM actors WHERE actor_name = 'Allison' AND actor_last_name = 'Williams')),
((SELECT id FROM movies WHERE movie_name = 'Get Out'), (SELECT id FROM actors WHERE actor_name = 'Lakeith' AND actor_last_name = 'Stanfield')),
((SELECT id FROM movies WHERE movie_name = 'Knives Out'), (SELECT id FROM actors WHERE actor_name = 'Ana' AND actor_last_name = 'de Armas')),
((SELECT id FROM movies WHERE movie_name = 'Knives Out'), (SELECT id FROM actors WHERE actor_name = 'Daniel' AND actor_last_name = 'Craig')),
((SELECT id FROM movies WHERE movie_name = 'Knives Out'), (SELECT id FROM actors WHERE actor_name = 'Chris' AND actor_last_name = 'Evans')),
((SELECT id FROM movies WHERE movie_name = 'La La Land'), (SELECT id FROM actors WHERE actor_name = 'Ryan' AND actor_last_name = 'Gosling')),
((SELECT id FROM movies WHERE movie_name = 'La La Land'), (SELECT id FROM actors WHERE actor_name = 'Emma' AND actor_last_name = 'Stone')),
((SELECT id FROM movies WHERE movie_name = 'La La Land'), (SELECT id FROM actors WHERE actor_name = 'John' AND actor_last_name = 'Legend'));

INSERT INTO addresses (address_street, address_number, address_postal_code, address_city, address_country)
VALUES 
('Rua Gabriel Monteiro da Silva', '422', '37130-001', 'Alfenas', 'Brasil'),
('Avenida Paulista', '1578', '01310-200', 'São Paulo', 'Brasil'),
('Rua das Flores', '25', '80010-110', 'Curitiba', 'Brasil'),
('Avenida Beira-Mar', '145', '60165-120', 'Fortaleza', 'Brasil'),
('Rua XV de Novembro', '520', '80020-310', 'Curitiba', 'Brasil'),
('Avenida Atlântica', '1550', '22070-000', 'Rio de Janeiro', 'Brasil'),
('Rua Oscar Freire', '789', '01426-001', 'São Paulo', 'Brasil'),
('Rua da Aurora', '123', '50050-000', 'Recife', 'Brasil'),
('Avenida Goiás', '345', '74010-020', 'Goiânia', 'Brasil'),
('Rua Bento Gonçalves', '89', '96015-000', 'Pelotas', 'Brasil'),
('Avenida Getúlio Vargas', '1422', '30112-020', 'Belo Horizonte', 'Brasil'),
('Rua General Osório', '215', '99010-030', 'Passo Fundo', 'Brasil'),
('Praça Tiradentes', '58', '35420-000', 'Ouro Preto', 'Brasil'),
('Rua Dr. Flores', '98', '90020-122', 'Porto Alegre', 'Brasil'),
('Avenida Brasil', '1500', '85851-000', 'Foz do Iguaçu', 'Brasil'),
('Rua Maranhão', '1122', '64000-110', 'Teresina', 'Brasil');

INSERT INTO clients (fk_address, client_name, client_last_name)
VALUES
(1, 'Rodrigo', 'Pagliares'), 
(2, 'Felipe', 'Rey'), 
(3, 'Thiago', 'Arruda'), 
(4, 'Luiz Eduardo', 'da Silva'), 
(5, 'Flávio', 'Gonzaga'), 
(6, 'Paulo', 'Bressan'), 
(7, 'Gustavo', 'Rodrigues'), 
(8, 'Leonardo', 'Arantes'), 
(9, 'Maria Luiza', 'Berlarmino'), 
(10, 'Diogo', 'Moreira'), 
(11, 'Abner', 'Guimarães'), 
(12, 'Felipe', 'Correa');

INSERT INTO employees (fk_address, fk_section, employee_name, employee_last_name, employee_salary, employee_birthday_date)
VALUES
(13, 1, 'Nycole', 'Paulino', 7980.00, '2004-11-22'),
(14, 2, 'Maicon', 'Mian', 10670.00, '2004-07-29'), 
(15, 3, 'Pedro', 'Botelho', 6450.00, '2003-10-05'), 
(16, 1, 'Caio', 'Matos', 9320.00, '2003-03-17');

INSERT INTO exemplar (fk_movie, exemplar_buy_date)
VALUES
(1, '2023-06-15'),
(1, '2023-08-10'),
(2, '2023-05-20'),
(2, '2024-01-11'),
(3, '2023-07-30'),
(3, '2024-03-02'),
(4, '2023-09-10'),
(4, '2023-12-05'),
(5, '2023-04-12'),
(5, '2024-02-25'),
(6, '2023-10-20'),
(6, '2023-11-28'),
(7, '2023-06-22'),
(7, '2024-01-30'),
(8, '2023-08-18'),
(8, '2024-04-02'),
(9, '2023-09-25'),
(9, '2023-12-13'),
(10, '2023-07-18'),
(10, '2023-11-15'),
(11, '2023-05-07'),
(11, '2024-03-18'),
(12, '2023-08-01'),
(12, '2024-02-02'),
(13, '2023-04-15'),
(13, '2023-10-10'),
(14, '2023-07-05'),
(14, '2024-01-22'),
(15, '2023-06-08'),
(15, '2023-11-23'),
(16, '2023-05-14'),
(16, '2024-02-10');

INSERT INTO borrowings (fk_client, fk_employee, borrowing_is_paid, borrowing_sale_date, borrowing_devolution_date)
VALUES
(1, 1, TRUE, '2023-05-10', '2023-05-20'),
(2, 2, FALSE, '2023-06-15', '2023-06-25'),
(3, 3, TRUE, '2023-07-01', '2023-07-10'),
(4, 4, FALSE, '2023-08-05', '2023-08-15'),
(5, 1, TRUE, '2023-09-20', '2023-09-30'),
(6, 2, FALSE, '2023-10-10', '2023-10-20'),
(7, 3, TRUE, '2023-11-03', '2023-11-13'),
(8, 4, TRUE, '2023-12-01', '2023-12-10'),
(9, 1, FALSE, '2024-01-10', '2024-01-20'),
(10, 2, TRUE, '2024-02-14', '2024-02-24'),
(11, 3, FALSE, '2024-03-05', '2024-03-15'),
(12, 4, TRUE, '2024-04-18', '2024-04-28');

INSERT INTO borrowing_exemplar (fk_exemplar, fk_borrowing)
VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 3),
(6, 3),
(7, 4),
(8, 4),
(9, 5),
(10, 5),
(11, 6),
(12, 6),
(13, 7),
(14, 7),
(15, 8),
(16, 8),
(1, 9),
(2, 9),
(3, 10),
(4, 10),
(5, 11),
(6, 11),
(7, 12),
(8, 12);




