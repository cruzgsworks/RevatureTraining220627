-- Demonstrate normalization 1st-3rd form;
-- Demo joins and set ;
-- Table in 1st NF
-- Tables must have primary key (can be a composite key, pk of multiple columns -> bad)
-- Columns should be atomic (smallest pieces of data as possible);
CREATE TABLE IF NOT EXISTS postgres.nf_joins_setop.superheroes (
  hero_name TEXT,
  hero_power TEXT,
  first_name TEXT,
  last_name TEXT,
  home_base TEXT,
  street_address TEXT,
  PRIMARY KEY (
    hero_name,
    first_name
  )
);

INSERT
    INTO
    postgres.nf_joins_setop.superheroes(
    hero_name,
    hero_power,
    first_name,
    last_name,
    home_base,
    street_address
  )
VALUES (
  'Homelander',
  'Laser Eyes',
  'John',
  'Doe',
  'New York',
  '123 Street St'
),
(
  'Spiderman',
  'Web Boy',
  'Peter',
  'Parker',
  'With his aunt',
  '567 Street Ave'
),
(
  'Spiderman',
  'Web Boy',
  'Miles',
  'Morales',
  'With his aunt',
  '567 Street Ave'
) ON
CONFLICT (hero_name,
first_name) DO NOTHING;
-- Spiderman records are similar except for names. Legal in 1st NF
-- Spiderman differentiated by their real names;
SELECT
    *
FROM
    postgres.nf_joins_setop.superheroes;

DROP TABLE postgres.nf_joins_setop.superheroes;
-- 2nd NF
-- remove partial dependencies with a single column PK;
CREATE TABLE IF NOT EXISTS postgres.nf_joins_setop.superheroes_2nf (
  hero_id serial PRIMARY KEY,
  hero_name TEXT,
  hero_power TEXT,
  first_name TEXT,
  last_name TEXT,
  home_base TEXT,
  street_address TEXT
);

INSERT
    INTO
    postgres.nf_joins_setop.superheroes_2nf(
    hero_id,
    hero_name,
    hero_power,
    first_name,
    last_name,
    home_base,
    street_address
  )
VALUES (
  DEFAULT,
  'Homelander',
  'Laser Eyes',
  'John',
  'Doe',
  'New York',
  '123 Street St'
),
(
  DEFAULT,
  'Spiderman',
  'Web Boy',
  'Peter',
  'Parker',
  'With his aunt',
  '567 Street Ave'
),
(
  DEFAULT,
  'Spiderman',
  'Web Boy',
  'Miles',
  'Morales',
  'With his aunt',
  '567 Street Ave'
) ON
CONFLICT (hero_id) DO NOTHING;

SELECT
    *
FROM
    postgres.nf_joins_setop.superheroes_2nf;

DROP TABLE postgres.nf_joins_setop.superheroes_2nf;

-- 3rd nf;

CREATE TABLE IF NOT EXISTS postgres.nf_joins_setop.homes (
  home_id serial PRIMARY KEY,
  home_name TEXT,
  street_address TEXT
);

CREATE TABLE IF NOT EXISTS postgres.nf_joins_setop.superheroes_3nf (
  hero_id serial PRIMARY KEY,
  hero_name TEXT,
  hero_power TEXT,
  first_name TEXT,
  last_name TEXT,
  home_base_fk int REFERENCES postgres.nf_joins_setop.homes(home_id)
);

INSERT
    INTO
    postgres.nf_joins_setop.homes (
    home_id,
    home_name,
    street_address
  )
VALUES (
  DEFAULT,
  'Homelander''s',
  '123 Street St'
),
(
  DEFAULT,
  'With his aunt',
  '567 Street Ave'
) ON
CONFLICT (home_id) DO NOTHING;

INSERT
    INTO
    postgres.nf_joins_setop.superheroes_3nf(
    hero_id,
    hero_name,
    hero_power,
    first_name,
    last_name,
    home_base_fk
  )
VALUES (
  DEFAULT,
  'Homelander',
  'Laser Eyes',
  'John',
  'Doe',
  1
),
(
  DEFAULT,
  'Spiderman',
  'Web Boy',
  'Peter',
  'Parker',
  2
),
(
  DEFAULT,
  'Spiderman',
  'Web Boy',
  'Miles',
  'Morales',
  2
) ON
CONFLICT (hero_id) DO NOTHING;

SELECT
    *
FROM
    postgres.nf_joins_setop.superheroes_3nf;

SELECT
    *
FROM
    postgres.nf_joins_setop.homes;

-- JOINS
-- Helpful when SELECTing data from multiple tables
-- insert records to each table for demonstrations;

INSERT
    INTO
    postgres.nf_joins_setop.superheroes_3nf (
    hero_id,
    hero_name,
    hero_power,
    first_name,
    last_name
    )
VALUES(
  DEFAULT,
  'hancock',
  'slappin'' people',
  'will',
  'smith'
) ON
CONFLICT (hero_id) DO NOTHING;

INSERT
    INTO
    postgres.nf_joins_setop.homes (
    home_id,
    home_name,
    street_address
  )
VALUES(
  DEFAULT,
  'panera bread',
  '1337 bread st'
) ON
CONFLICT (home_id) DO NOTHING ;

-- INNER JOIN demo
-- returns all records with matching data;
SELECT
    *
FROM
    postgres.nf_joins_setop.superheroes_3nf
INNER JOIN postgres.nf_joins_setop.homes ON
    home_id = home_base_fk;
--Notice we don't get Hancock here, or Panera Bread, because they don't have any FK/PK matches

-- FULL OUTER JOIN
-- returns all records regardless of matches
SELECT
    *
FROM
    postgres.nf_joins_setop.superheroes_3nf
FULL OUTER JOIN homes ON
    home_id = home_base_fk; 

--LEFT OUTER JOIN
--return everything in the LEFT TABLE, and matching rows in the right table
SELECT * FROM postgres.nf_joins_setop.superheroes_3nf LEFT OUTER JOIN homes ON home_id = home_base_fk; 
--we get hancock since superheros are on the left, but we don't get panera, because it's on the right


--RIGHT OUTER JOIN
--return everything in the RIGHT TABLE, and matching rows in the left table
SELECT * FROM postgres.nf_joins_setop.superheroes_3nf RIGHT OUTER JOIN homes ON home_id = home_base_fk; 
--we get panera since it's on the right table, but not hancock, because it's on the left with no matches.

--Joins are how we make our select statements include more data (spanning multiple tables)
--we can select * from superheros, and the most home data we would get is the home_id
--what if we need all the data from heros and homes? Better use a join!

--(SET OPERATIONS)--------------------------------------

SELECT * FROM postgres.nf_joins_setop.superheroes_3nf ;
SELECT * FROM postgres.nf_joins_setop.superheroes_3nf ;

-- UNION
-- All distinct records from both queries no duplicates

SELECT
    home_base_fk
FROM
    postgres.nf_joins_setop.superheroes_3nf
UNION
SELECT
    home_id
FROM
    postgres.nf_joins_setop.homes ;

-- UNION all
-- All distinct records from both queries including duplicates

SELECT
    home_base_fk
FROM
    postgres.nf_joins_setop.superheroes_3nf
UNION ALL
SELECT
    home_id
FROM
    postgres.nf_joins_setop.homes ;

-- Intersect
-- return unique rows found in both queries

SELECT
    home_base_fk
FROM
    postgres.nf_joins_setop.superheroes_3nf
INTERSECT
SELECT
    home_id
FROM
    postgres.nf_joins_setop.homes ;

-- Except
-- return unique rows not found in both queries

SELECT
    home_base_fk
FROM
    postgres.nf_joins_setop.superheroes_3nf
EXCEPT
SELECT
    home_id
FROM
    postgres.nf_joins_setop.homes ;

DROP TABLE postgres.nf_joins_setop.superheroes_3nf;
DROP TABLE postgres.nf_joins_setop.homes;
