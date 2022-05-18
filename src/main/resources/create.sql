CREATE SCHEMA somos_mas_db;
USE somos_mas_db;

CREATE TABLE role
(id_role DOUBLE PRIMARY KEY AUTO_INCREMENT,
name_role VARCHAR (40) NOT NULL,
description_role VARCHAR (100),
created_at DATETIME,
modified_at DATETIME,
deleted BOOLEAN);

CREATE TABLE category
(id_category DOUBLE PRIMARY KEY AUTO_INCREMENT,
name_category VARCHAR (40) NOT NULL,
description_category VARCHAR (100),
image_category VARCHAR (130),
created_at DATETIME,
modified_at DATETIME,
deleted BOOLEAN);

CREATE TABLE user
(id_user DOUBLE PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR (40),
last_name VARCHAR (40),
email VARCHAR (40),
pass VARCHAR (40),
photo VARCHAR (4000),
role_id DOUBLE,
created_at DATETIME,
modified_at DATETIME,
deleted BOOLEAN,
CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES role(id_role));

CREATE TABLE member
(id_member DOUBLE PRIMARY KEY AUTO_INCREMENT,
name_member VARCHAR(40),
facebook_url VARCHAR(500),
instagram_url VARCHAR(500),
linkedin_url VARCHAR(500),
image_member VARCHAR(4000),
description_member VARCHAR(500),
created_at DATETIME,
modified_at DATETIME,
deleted BOOLEAN);

CREATE TABLE organization
(id_organization DOUBLE PRIMARY KEY AUTO_INCREMENT,
name_organization VARCHAR(40),
image_organization VARCHAR(4000),
adress VARCHAR(150),
phone INTEGER,
email VARCHAR(50),
welcome_text VARCHAR(1000),
about_us VARCHAR(1000),
created_at DATETIME,
modified_at DATETIME,
deleted BOOLEAN);

CREATE TABLE news
(id_news DOUBLE PRIMARY KEY AUTO_INCREMENT,
name_news VARCHAR(200),
content_news VARCHAR(1000),
category_id DOUBLE,
created_at DATETIME,
modified_at DATETIME,
deleted BOOLEAN,
CONSTRAINT category_fk FOREIGN KEY(category_id) REFERENCES category(id_category));

CREATE TABLE testimonial
(id_testimonial DOUBLE PRIMARY KEY AUTO_INCREMENT,
name_testimonial VARCHAR(50),
image_testimonial VARCHAR(4000),
content_testimonial VARCHAR(1000),
created_at DATETIME,
modified_at DATETIME,
deleted BOOLEAN);

CREATE TABLE activity
(id_activity DOUBLE PRIMARY KEY AUTO_INCREMENT,
name_activity VARCHAR(50),
content_activity VARCHAR(1000),
image_activity VARCHAR(1000),
created_at DATETIME,
modified_at DATETIME,
deleted BOOLEAN);
