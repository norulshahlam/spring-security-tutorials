You need to start your db server before running this.

Use docker to run!

	
	******************************************


*** DOKCERISE MYSQL ***
{HOSTPORT:CONTAINERPORT}

`run!`
	
	 docker run --detach --env MYSQL_ROOT_PASSWORD=root --env MYSQL_DATABASE=mydb --env MYSQL_PASSWORD=root --env MYSQL_USER=admin --name localhost --publish 3306:3306 mysql:8.0
	 
`for debug`
	
	docker container ps -a
	docker container stop 
	docker container prune
	docker volume prune
	

`for aws cli`

	sudo su
	service docker start
	
`stop & remove all running proceses`

	docker rm $(docker ps -a -q) -f

`remove all images`

	docker rmi -f $(docker images -a -q)

`run mysql in cli using docker`

	docker exec -it localhost bash

`connect to mysql`

	mysql -u admin -proot
	
`add data`

	use mydb;
	
	CREATE TABLE USER 
	(
	ID INT NOT NULL AUTO_INCREMENT,
	FIRST_NAME VARCHAR(20),
	LAST_NAME VARCHAR(20),
	EMAIL VARCHAR(20),
	PASSWORD VARCHAR(256), 
	PRIMARY KEY (ID),
	UNIQUE KEY (EMAIL)
	);
	
	CREATE TABLE ROLE 
	(
	ID INT NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(20),
	PRIMARY KEY (ID)
	);
	
	CREATE TABLE USER_ROLE(
	USER_ID int,
	ROLE_ID int,
	FOREIGN KEY (USER_ID)
	REFERENCES USER(ID),
	FOREIGN KEY (ROLE_ID)
	REFERENCES ROLE(ID)
	);
	
	insert into USER(first_name,last_name,email,password) values ('doug','bailey','doug@bailey.com','$2a$10$U2STWqktwFbvPPsfblVeIuy11vQ1S/0LYLeXQf1ZL0cMXc9HuTEA2');
	insert into USER(first_name,last_name,email,password) values ('john','ferguson','john@ferguson.com','$2a$10$YzcbPL.fnzbWndjEcRkDmO1E4vOvyVYP5kLsJvtZnR1f8nlXjvq/G');
	
	insert into ROLE values(1,'ROLE_ADMIN');
	insert into ROLE values(2,'ROLE_USER');
	
	insert into USER_ROLE values(1,1);
	insert into USER_ROLE values(2,2);
	
`test`

	use mydb;
	show tables;
	select * from USER;
	select * from ROLE;
	select * from USER_ROLE;
	
`run in postman`

	