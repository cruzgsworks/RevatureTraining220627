-- Demonstrate normalization 1st-3rd form

-- Demo joins and set 

-- Table in 1st NF
-- Tables must have primary key (can be a composite key, pk of multiple columns -> bad)
-- Columns should be atomic (smallest pieces of data as possible)


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
) ON CONFLICT (hero_name, first_name) DO NOTHING;
-- Spiderman records are similar except for names. Legal in 1st NF
-- Spiderman differentiated by their real names

SELECT * FROM postgres.nf_joins_setop.superheroes;

DROP TABLE postgres.nf_joins_setop.superheroes;

-- 2nd NF
-- remove partial dependencies with a single column PK

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

SELECT * FROM postgres.nf_joins_setop.superheroes_2nf;

DROP TABLE postgres.nf_joins_setop.superheroes_2nf;

-- 3rd nf

CREATE TABLE IF NOT EXISTS postgres.nf_joins_setop.homes (
  home_id serial PRIMARY KEY,
  home_base TEXT,
  street_address TEXT
);

CREATE TABLE IF NOT EXISTS postgres.nf_joins_setop.superheroes_3nf (
  hero_id serial PRIMARY KEY,
  hero_name TEXT,
  hero_power TEXT,
  first_name TEXT,
  last_name TEXT,
  home_id_fk serial REFERENCES postgres.nf_joins_setop.homes(home_id)
);

INSERT
  INTO
  postgres.nf_joins_setop.homes (
    home_id,
    home_base,
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
    home_id_fk
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

SELECT * FROM postgres.nf_joins_setop.superheroes_3nf;

SELECT * FROM postgres.nf_joins_setop.homes;

DROP TABLE postgres.nf_joins_setop.superheroes_3nf;

DROP TABLE postgres.nf_joins_setop.homes;

