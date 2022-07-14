-- schema will model a krusty krab employee management system;
-- one to many relationship;
CREATE TABLE IF NOT EXISTS postgres.krusty_krab.roles (
    role_id serial PRIMARY KEY,
    role_title TEXT,
    role_salary int
) ;

INSERT INTO postgres.krusty_krab.roles (role_id, role_title, role_salary)
VALUES (DEFAULT, 'Manager', 100000), (DEFAULT, 'Fry Cook', 50000), (DEFAULT, 'Cashier', 40000), (DEFAULT, 'Marketing Director', 100000) ;

SELECT *
FROM postgres.krusty_krab.roles ;

CREATE TABLE IF NOT EXISTS postgres.krusty_krab.employee (
    employee_id serial PRIMARY KEY,
    first_name TEXT,
    last_name TEXT,
    role_id_fk int REFERENCES postgres.krusty_krab.roles (role_id)
) ;

INSERT INTO postgres.krusty_krab.employee (employee_id, first_name, last_name, role_id_fk)
VALUES (DEFAULT, 'Eugune', 'Krabs', 1), (DEFAULT, 'Spongebob', 'Squarepants', 2), (DEFAULT, 'Squidward', 'Tentacles', 3), (DEFAULT, 'Patrick', 'Star', 4);

SELECT *
FROM postgres.krusty_krab.employee ;

-- TRUNCATE TABLE postgres.krusty_krab.employee, postgres.krusty_krab.roles RESTART IDENTITY CASCADE;

CREATE TABLE IF NOT EXISTS postgres.krusty_krab.users (
    user_id serial PRIMARY KEY,
    username TEXT,
    password TEXT
);

INSERT INTO postgres.krusty_krab.users (username, password) VALUES ('user', 'password'); 