You need to start your db server before running this.

Use docker for your db!

	
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

	
`test`

	use mydb;
	show tables;
	select * from user;
	select * from role;
	select * from user_role;
	
`run in postman`

	