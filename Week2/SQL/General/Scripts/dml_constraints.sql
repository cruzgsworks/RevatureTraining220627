-- create two tables with a relationship
-- using primary keys and foreign keys

-- this schema will track owners and their capybaras

-- owners table
CREATE TABLE IF NOT EXISTS postgres.dml_constraints.owners(
  -- primary key uniquely indentifies each record in a table (ex. ID)
  -- serial is an auto-incrementing data type. no need to give it value when we insert data
  -- whenever we insert a new owner, owner_id will be auto-generated
  owner_id serial PRIMARY KEY,
  owner_name TEXT NOT NULL
);

-- capybaras table
CREATE TABLE IF NOT EXISTS postgres.dml_constraints.capybaras(
	capy_id serial PRIMARY KEY,
	fur_color TEXT,
	-- check no baby capybaras
	"age" int CHECK(age > 1),
	-- bound to the owner_id key in owners table
	owner_id_fk int REFERENCES owners (owner_id)
);

-- owners table must exist first so capybaras can reference it
-- make the other table first if there are tables depend on it 

-- will not work since there are tables that references it
-- DROP TABLE owners;

-- this will cascade any changes to all dependent tables. changes "cascades" over
DROP TABLE postgres.dml_constraints.owners CASCADE;

-- DML

-- owners table
CREATE TABLE IF NOT EXISTS postgres.dml_constraints.owners(
  -- primary key uniquely indentifies each record in a table (ex. ID)
  -- serial is an auto-incrementing data type. no need to give it value when we insert data
  -- whenever we insert a new owner, owner_id will be auto-generated
  owner_id serial PRIMARY KEY,
  owner_name TEXT NOT NULL
);

-- INSERT
-- specify which table to insert to, fields to be populated, and values for each field
-- owner_id is generated automatically because it is serial
INSERT INTO owners (owner_name)
VALUES ('Constantine'), ('Sekhar'), ('Matthew');

-- SELECT clause
-- query for records in the table(s) specified
-- wildcard * means get all records
SELECT * FROM owners o;

-- INSERT 
INSERT INTO capybaras (fur_color, age, owner_id_fk)
VALUES ('brown', 5, 1), ('brown', 10, 1), ('tan', 6, 2), ('black', 2, 3);

-- WHERE clause
SELECT * FROM capybaras WHERE fur_color = 'brown';

SELECT * FROM capybaras WHERE fur_color != 'brown';

SELECT * FROM capybaras WHERE fur_color = 'brown';

SELECT * FROM capybaras WHERE age > 7;

SELECT * FROM owners WHERE owner_name LIKE 'C%';

SELECT * FROM owners WHERE owner_name LIKE '%e%';

SELECT * FROM capybaras c WHERE age BETWEEN 5 AND 8;

SELECT * FROM capybaras c WHERE owner_id_fk = 2 OR owner_id_fk = 3;

SELECT
  *
FROM
  capybaras c
WHERE
  owner_id_fk IN (
    2, 3
  );
  
-- ORDER BY 
SELECT * FROM capybaras c ORDER BY age;

SELECT * FROM capybaras c ORDER BY age DESC;

-- functions

-- scalar functions take in one value (or zero values), and return a value 
SELECT now();

-- aggregate functions take in multiple values and return a value 
SELECT avg(age) FROM capybaras c;

-- AS = alias
SELECT min(age) AS "minimum age" FROM capybaras c; 

-- UPDATE values;
UPDATE owners SET owner_name = 'Matt' WHERE owner_name = 'Matthew';

SELECT * FROM owners o;

-- DELETE rows
DELETE FROM capybaras WHERE capy_id = 1;

-- cannot delete row from owners if there are dependents. use cascade 
DELETE FROM owners WHERE owner_id = 1;

DROP TABLE postgres.dml_constraints.owners CASCADE;

DROP TABLE postgres.dml_constraints.capybaras;
