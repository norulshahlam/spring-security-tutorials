# create microservices with spring security
### this project has many branch, v1 to v7. Go to v1 to begin with and slowly progress to v2,v3,...and finally v7 where jwt is implemented

### key security components

[![Image](key-security-components.PNG "Deploying Spring Boot Apps to AWS using Elastic Beanstalk")](https://cognizant.udemy.com/course/spring-security-fundamentals/learn/lecture/22681997#overview)

### usecase: 

[![Image](spring-security-usecase.JPG "Deploying Spring Boot Apps to AWS using Elastic Beanstalk")](https://cognizant.udemy.com/course/spring-security-fundamentals/learn/lecture/18358026#overview)


# version 3 - secure microservices

### only registered users can access the data. diff user will have diff roles can do diff requests.

3a. start your docker myqsl

3b. add data for users and roles in db

  i. create table for role, user, user_role

    CREATE TABLE user 
    (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    email VARCHAR(20),
    PASSWORD VARCHAR(256), 
    PRIMARY KEY (id),
    UNIQUE KEY (email)
    );

    CREATE TABLE role 
    (
    id INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(20),
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

  ii. add data

    insert into user(first_name,last_name,email,password) values ('doug','bailey','doug@bailey.com','$2a$10$U2STWqktwFbvPPsfblVeIuy11vQ1S/0LYLeXQf1ZL0cMXc9HuTEA2');
    insert into user(first_name,last_name,email,password) values ('john','ferguson','john@ferguson.com','$2a$10$YzcbPL.fnzbWndjEcRkDmO1E4vOvyVYP5kLsJvtZnR1f8nlXjvq/G');

    insert into role values(1,'ROLE_ADMIN');
    insert into role values(2,'ROLE_USER');

    insert into user_role values(1,1);
    insert into user_role values(2,2);

3c. check your db if data is persisted
  
  `run mysql in cli using docker`
    docker exec -it localhost bash

  `connect to mysql`
    mysql -u admin -proot
    
  `test`
    use mydb;
    select * from user;
    select * from role;
    select * from user_role;

3d. add security dependencies in coupon.

3e. in coupon api, create user & role entity, define relationship, create repo. 

3f. implement userdetailsservice & web security config  

3g. test - we r doing the same test as last time, this time using credentials - using the credentials:

  doug is admin - GET & POST requests
  id: doug@bailey.com, 
  password: doug

  john is user - GET requests
  id: john@ferguson.com,
  password: john

  `make sure john is unable to do POST but doug is able to`  
  `make sure you dont insert duplicate coupon`

  use postman > Authorisation > Type > Basic Auth > your credentials

  POST http://localhost:9091/couponapi/coupons/ 
  {
    "code":"SUPERSALE2",
    "discount":"10",
    "expDate":"12/12/2020"
  }
  GET http://localhost:9091/couponapi/coupons/SUPERSALE2  















**************************************************






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

 



































## version 3

## create microservices with spring security

## key security components

[![Image](key-security-components.PNG "Deploying Spring Boot Apps to AWS using Elastic Beanstalk")](https://cognizant.udemy.com/course/spring-security-fundamentals/learn/lecture/22681997#overview)

## usecase: 

[![Image](spring-security-usecase.JPG "Deploying Spring Boot Apps to AWS using Elastic Beanstalk")](https://cognizant.udemy.com/course/spring-security-fundamentals/learn/lecture/18358026#overview)

### create coupon service
[https://cognizant.udemy.com/course/spring-security-fundamentals/learn/lecture/18358034#overview]

1. Run sql queries (tables.sql) to create db,coupon table

    use mydb;

    create table product(
    id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(20),
    description varchar(100),
    price decimal(8,3) 
    );
 
2. create model, repo, controller for coupon table
3. configure data source
4. test
  POST http://localhost:8080/couponapi/coupons/ 
  {
    "code":"SUPERSALE2",
    "discount":"10",
    "expDate":"12/12/2020"
  }
 GET http://localhost:8080/couponapi/coupons/SUPERSALE2  

### create product service

5. Run sql queries (tables.sql) to create db,product table

    create table coupon(
    id int AUTO_INCREMENT PRIMARY KEY,
    code varchar(20) UNIQUE,
    discount decimal(8,3),
    exp_date varchar(100) 
    );

6. create model, repo, controller for product table
7. configure data source
8. test
  POST http://localhost:9090/productapi/products/ 
  {
    "name":"iPhone",
    "description":"Awesome",
    "price": 10
  }

 Go to mysql to check if data is added

 ### integrate both api

9. add dto model for coupon
10. reconfigure controller for product api to fetch from coupon api 
11. reconfigure product model
12. reconfigure data source
13. add rest template beans in main method
14. test!
    POST http://localhost:9090/productapi/products/ 
  {
    "name":"android",
    "description":"Awesome",
    "price": 10000,
    "couponCode":"SUPERSALE"
  }

  depending on the coupon code, it will minus off and the price will reflect the adjusted price.

### secure rest api for coupon api

15. create table for role, user, user_role

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
  FOREIGN KEY (user_id)
  REFERENCES user(id),
  FOREIGN KEY (role_id)
  REFERENCES role(id)
  );

16. add data

  insert into user(first_name,last_name,email,password) values ('doug','bailey','doug@bailey.com','$2a$10$U2STWqktwFbvPPsfblVeIuy11vQ1S/0LYLeXQf1ZL0cMXc9HuTEA2');
  insert into user(first_name,last_name,email,password) values ('john','ferguson','john@ferguson.com','$2a$10$YzcbPL.fnzbWndjEcRkDmO1E4vOvyVYP5kLsJvtZnR1f8nlXjvq/G');

  insert into role values(1,'ROLE_ADMIN');
  insert into role values(2,'ROLE_USER');

  insert into user_role values(1,1);
  insert into user_role values(2,2);

17. test in mysql if data is added  
18. add security dependencies in copuon  
19. create user, role, user_role in coupon api  
20 define relationship in the above 2 entities    
21. create repo  
21. implement userdetailsservice  
22. implement web security config  
23. test - using the credentials

  doug is admin - can get & add
   doug@bailey.com, doug

  john is user - can get only
    john@ferguson.com, john

    POST http://localhost:8080/couponapi/coupons/ 
  {
    "code":"SUPERSALE2",
    "discount":"10",
    "expDate":"12/12/2020"
  }

    GET http://localhost:8080/couponapi/coupons/SUPERSALE2  







