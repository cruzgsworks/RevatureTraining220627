-- To create a new schema (which we'll do for each individual project)
-- right click on schemas folder,

-- Then, to create this file, right click schema > SQL Editor > New SQL Script

-- Data Definition Languagse (DDL): CREATE, ALTER, TRUNCATE, DROP
-- DDL is any SQL command that relates to building the structure of your data (typically tables)

-- The CREATE command will create a Database table
CREATE TABLE IF NOT EXISTS postgres.ddl_datatypes.users(
	--username is the name of a column, text is the datatype
	username TEXT
);

-- we created a (bad) table of users that simply tracks username

-- we can view this table by Right clicking the schema > view diagram
-- don't forget to right click schema and refresh for changes to reflect

-- oh no, I meant to add an age column to my table. I can use the ALTER command to change or add columns 

ALTER TABLE postgres.ddl_datatypes.users ADD user_age int;

-- TRUNCATE wipes all data from a table
-- TRUNCATE TABLE users;

-- DROP deletes table
-- DROP users;

-- SQL DATA Types
-- Demonstrate various SQL data types
-- Bad table because there's no primary key, not normalized

-- Each column is delimited by commas
CREATE TABLE IF NOT EXISTS postgres.ddl_datatypes.datatypes(
	small_number int2,
	-- 2 bytes, for smaller numbers
	normal_number int,
	-- 4 bytes, most common int type
	big_number int8,
	-- 8 bytes, for big number like long IN java
	standard_decimal decimal(
		10,
		2
	),
	--2 parameters: number of digits, number of decimal places
	--this has 10 total digits with 2 decimal places
	"boolean" boolean,
	-- double quotes let you use keywords as column names, etc
	fixed_length_text char(2),
	--text field that can gold the amount of characters specified
	variable_length_text varchar(15),
	--text field that can hold up to the amount of characters given
	unlimited_length_text TEXT, -- unlimited length, best used if you don't need to limit your characters
	"date" date -- lets you input a date in YYYY-MM-DD format
);

SELECT * FROM postgres.ddl_datatypes.users;
SELECT * FROM postgres.ddl_datatypes.datatypes;

DROP TABLE postgres.ddl_datatypes.users;

DROP TABLE postgres.ddl_datatypes.datatypes;