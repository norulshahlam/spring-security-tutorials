# create microservices with spring security
### this project has many branch, v1 to v7. Go to v1 to begin with and slowly progress to v2,v3,...and finally v7 where jwt is implemented

### key security components

[![Image](key-security-components.PNG "Deploying Spring Boot Apps to AWS using Elastic Beanstalk")](https://cognizant.udemy.com/course/spring-security-fundamentals/learn/lecture/22681997#overview)

### usecase: 

[![Image](spring-security-usecase.JPG "Deploying Spring Boot Apps to AWS using Elastic Beanstalk")](https://cognizant.udemy.com/course/spring-security-fundamentals/learn/lecture/18358026#overview)


# version 2- integrate both api  

  learn about RestTemplate - perform HTTP requests - and how to use it.
  learn about @Transient - Specifies that the property or field is not persistent
  learn about using property values

### product service will get data from coupon service using Data Transfer Object - an object that carries data between processes. we will configure only the product service
### products will be added with the discount code from coupon service.if no discount code, no discount will be used and data will still be added

3a. add dto model for coupon
3b. reconfigure controller for product api to fetch from coupon api 
3c. reconfigure product model
3d. reconfigure data source
3e. add rest template beans in main method

Start your db server using docker before running this.
 
`run!`
	docker run --detach --env MYSQL_ROOT_PASSWORD=root --env MYSQL_DATABASE=mydb --env MYSQL_PASSWORD=root --env MYSQL_USER=admin --name localhost --publish 3306:3306 mysql:8.0

test!

  POST http://localhost:9090/productapi/products/ 
  {
    "name":"android",
    "description":"Awesome",
    "price": 10000,
    "couponCode":"SUPERSALE"
  }

  make sure your coupon code value exists in your db

  depending on the coupon code, it will minus off and the price will reflect the adjusted price.

check your db if data is persisted
  
  `run mysql in cli using docker`
    docker exec -it localhost bash

  `connect to mysql`
    mysql -u admin -proot
    
  `test`
    use mydb;
    select * from coupon;







********************************************************************************

# version 1 - setup coupon and product service

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

 


