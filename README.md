# PersonResourceProject
Springboot project with rest api and jpa to support CRUD operations for Person resource

Technologies used-
1) Spring Boot version 2.1.13.BUILD-SNAPSHOT
2) RESTful web services
3) Spring JPA
4) Java version 8
5) Swagger 2 for documentation

Software Used-
1) Eclipse IDE
2) Postman app to interact with apis
3) In-memory database H2

Steps to start the application,
1) Download or clone the project and import the same in your IDE
2) Do a 'maven clean install' on the project to get all the dependencies
3) Start the application by running the 'custom.project.personresource.PersonResourceApplication.java' class as a java application

About the project,
1) There are two person resources available on start as mentioned in the question(John Wick, Sarah Raven)
2) Access to in-memory db, hit the url, 'http://localhost:8080/h2-console' and make sure the the value for JDBC URL is 'jdbc:h2:mem:testdb'
3) Documentation for the api's available - http://localhost:8080/swagger-ui.html  
            GET /persons - to retrieve all users  
            POST /persons - to create a single person resource  
            DELETE /persons/{id} - to remove a specific person resource by their id  
            GET /persons/{id} - to retrieve a specific person based on id  
            PUT /persons/{id} - to update a person resource if exists or create new one if doesn't exist  
4) Use Postman app to test your apis  

Cheers!! :)




