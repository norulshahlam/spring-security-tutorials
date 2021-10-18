create table IF NOT EXISTS  product(
id int AUTO_INCREMENT PRIMARY KEY,
name varchar(20),
description varchar(100),
price decimal(8,3) 
);

create table IF NOT EXISTS coupon(
id int AUTO_INCREMENT PRIMARY KEY,
code varchar(20) UNIQUE,
discount decimal(8,3),
exp_date varchar(100) 
);


CREATE TABLE IF NOT EXISTS user 
(
id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(20),
last_name VARCHAR(20),
email VARCHAR(20),
password VARCHAR(256), 
PRIMARY KEY (id),
UNIQUE KEY (email)
);

CREATE TABLE role 
(
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(20),
PRIMARY KEY (id)
);

CREATE TABLE user_role(
user_id int,
role_id int,
FOREIGN KEY (user_id)
REFERENCES user(id),
FOREIGN KEY (role_id)
REFERENCES role(id)
);