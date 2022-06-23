CREATE database if not exists movie_library;
use movie_library;
CREATE TABLE if not exists  movies (
id               SERIAL PRIMARY KEY,
title            TEXT NOT NULL,
primary_director TEXT,
year_released    INT,
genre            TEXT,
actors           TEXT
REFERENCES library1
);

INSERT INTO movies (title,  primary_director, year_released, genre)
VALUES ('The Lost World', 'Steven Spielberg', 1997, 'sci-fi');

INSERT INTO movies (title,  primary_director, year_released, genre)
VALUES ('Pirates of the Caribbean: The Curse of the Black Pearl', 'Gore Verbinski', 2003, 'fantasy');

INSERT INTO movies (title,  primary_director, year_released, genre)
VALUES ('Harry Potter and Goblet of Fire', 'Mike Newell', 2005, 'fantasy');

INSERT INTO movies (title,  primary_director, year_released, genre)
VALUES ('The Hobbit: An Unexpected Journey', 'Peter Jackson', 2012, 'fantasy');

INSERT INTO movies (title,  primary_director, year_released, genre)
VALUES ('The Hobbit: The Desolation of Smaug', 'Peter Jackson', 2013, 'fantasy');

INSERT INTO movies (title,  primary_director, year_released, genre)
VALUES ('The Hobbit: The Battle of the Five Armies', 'Peter Jackson', 2014, 'fantasy');

INSERT INTO movies (title,  primary_director, year_released, genre)
VALUES ('The Lord of the Rings: The Return of the King', 'Peter Jackson', 2003, 'fantasy');

INSERT INTO movies (title,  primary_director, year_released, genre)
VALUES ('The Lord of the Rings: The Fellowship of the Ring', 'Peter Jackson', 2001, 'fantasy');

INSERT INTO movies (title,  primary_director, year_released, genre)
VALUES ('The Lord of the Rings: The Two Towers', 'Peter Jackson', 2002, 'fantasy');

CREATE TABLE ratings (
  id SERIAL PRIMARY KEY,
  rating TEXT
);

INSERT INTO ratings (rating) VALUES ('G');
INSERT INTO ratings (rating) VALUES ('PG');
INSERT INTO ratings (rating) VALUES ('PG-13');
INSERT INTO ratings (rating) VALUES ('R');

ALTER TABLE movies ADD COLUMN rating_id INTEGER NULL REFERENCES ratings (id);

UPDATE movies SET rating_id = 1 WHERE id in (1,2,3);
UPDATE movies SET rating_id = 2 WHERE id in (4,5,6);
UPDATE movies SET rating_id = 3 WHERE id in (7,8,9);

SELECT *
FROM movies
JOIN ratings ON movies.rating_id = ratings.id;

SELECT movies.title, ratings.rating
FROM movies
JOIN ratings ON movies.rating_id = ratings.id;
