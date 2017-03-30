DROP TABLE IF EXISTS dependent;
DROP TABLE IF EXISTS employee;

CREATE TABLE IF NOT EXISTS employee(emp_id int PRIMARY KEY ,name varchar(20),experience double);

CREATE TABLE IF NOT EXISTS dependent(dep_id int ,name varchar(20),dependent_on int,relation varchar(20),age int , FOREIGN KEY(dependent_on) REFERENCES employee(emp_id));