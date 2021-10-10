## version 1 - setup coupon and product service

## create microservices with spring security

## key security components

[![Image](key-security-components.PNG "Deploying Spring Boot Apps to AWS using Elastic Beanstalk")](https://cognizant.udemy.com/course/spring-security-fundamentals/learn/lecture/22681997#overview)

## usecase: 

[![Image](spring-security-usecase.JPG "Deploying Spring Boot Apps to AWS using Elastic Beanstalk")](https://cognizant.udemy.com/course/spring-security-fundamentals/learn/lecture/18358026#overview)

### create coupon service
[https://cognizant.udemy.com/course/spring-security-fundamentals/learn/lecture/18358034#overview]

1a. create model, repo, controller for coupon table
1b. configure data source for both services

  spring.datasource.url=jdbc:mysql://localhost:3306/mydb
  spring.datasource.username=root
  spring.datasource.password=root
  server.port=9091

1c. Use docker for your db! {HOSTPORT:CONTAINERPORT}

`pull mysql image`
  docker pull mysql

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
 
1d. test:

 You need to start your db server before running this.
 
`run!`
	docker run --detach --env MYSQL_ROOT_PASSWORD=root --env MYSQL_DATABASE=mydb --env MYSQL_PASSWORD=root --env MYSQL_USER=admin --name localhost --publish 3306:3306 mysql:8.0

`open postman:`

  POST http://localhost:9091/couponapi/coupons/ 
  {
    "code":"SUPERSALE2",
    "discount":"10",
    "expDate":"12/12/2020"
  }
 GET http://localhost:8080/couponapi/coupons/SUPERSALE2  

1e. check your db if data is persisted

  
  `run mysql in cli using docker`
    docker exec -it localhost bash

  `connect to mysql`
    mysql -u admin -proot
    
  `test`
    use mydb;
    select * from coupon;


### create product service

2a. Run sql queries (tables.sql) to create db,product table

    create table coupon(
    id int AUTO_INCREMENT PRIMARY KEY,
    code varchar(20) UNIQUE,
    discount decimal(8,3),
    exp_date varchar(100) 
    );

2b. create model, repo, controller for product table
2c. configure data source

    spring.datasource.url=jdbc:mysql://localhost:3306/mydb
    spring.datasource.username=root
    spring.datasource.password=root
    server.port=9090

2d. test

 You need to start your db server before running this.
 
`run!`
	docker run --detach --env MYSQL_ROOT_PASSWORD=root --env MYSQL_DATABASE=mydb --env MYSQL_PASSWORD=root --env MYSQL_USER=admin --name localhost --publish 3306:3306 mysql:8.0

`open postman:`

  POST http://localhost:9090/productapi/products/ 
  {
    "name":"iPhone",
    "description":"Awesome",
    "price": 10
  }

2e. check your db if data is persisted

  `run mysql in cli using docker`
    docker exec -it localhost bash

  `connect to mysql`
    mysql -u admin -proot
    
  `test`
    use mydb;
    select * from product;

 


