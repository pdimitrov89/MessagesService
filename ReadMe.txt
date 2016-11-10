This project implements the specification described in "QaiWare_Technicaltask.pdf". 
The project is intended for skills demonstration purposes only.

The project uses SpringMVC for REST services implementation, Hibernate for database persistence and Spring for DI. 
The build system used is Maven 3.0.3. 

How to test the implementation?
In order to setup the system follow this steps:
1. create MySQL database using the DDL in src\main\resources\dbShemeDDL.sql.
2. provide the connection.url, connection.username and connection.password properties in src\main\resources\hibernate.cfg.
3. build the project with maven.
4. deploy on tomcat or glassfish server.

After the application is deployed connect to it using a rest client. 

The application contains also Unit tests that guarantees the functionality is implemented correctly.
There is test case which tests the persistance against in-memory DB, and test case which tests 
the rest controller against mock servlet environment. 