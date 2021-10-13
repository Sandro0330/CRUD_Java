CREATE database 'jsp_crud';

Use jsp_crud;

create table users (
	id int(3) NOT NULL AUTO_INCREMENT,
	name varchar(120) NOT NULL,
	email varchar(220) NOT NULL,
	pais varchar(50),
	PRIMARY KEY (id)
);