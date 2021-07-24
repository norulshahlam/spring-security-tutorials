## version 1

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

 


