# Campus Paradigma server

***Maintainers***

Kevin Rey @SantiRey

Manuel Guil @ImalGil

## Description
This software has been created as part of a personal project for the Paradigma Indie community. This project has no commercial approach, it is maintained by the community and for the community. for that reason all contributions to it are totally welcome.

## Software

### Requirements

this server run under ***java 11*** and ***maven***, then it is necesary to have those requirements

### Database
*Designer @Manuel Gil*

Until the Production bunle the data base is enable in memory.

In order to use Postgres Database you can in ```applicattion.properties``` file change it.

### API
This project use Swagger as Api Documentation it is enable under the url ***```http://localhost:8080/swagger-ui.html```***

## Get started

### DOCKER
In order to use the docker images it is necessary to follow the following information. This project consists of a divided docker file which creates the image then exports the war in a tomcat container and has a Database service.

1. clone the repository

   ```git clone```
   
2. go into the project folder

   ```cd CampusParadigmaServer```
   
3. run der project with docker

   ```docker compose up -d```
   
4. There is a Swagger enable endpoint in order to have the documentation of the Http Request of the Project

   ```http://localhost:8080/campusparadigma/swagger-ui.html```

### Run the software
The project uses Spring Framework this means that it can be executed locally, with use of maven.

1. clone the repository

   ```git clone```

2. go into the project folder
   
   ```cd CampusParadigmaServer```
   
3. run der project with maven
   
   ```mvn clean spring-boot:run```

4. There is a Swagger enable endpoint in order to have the documentation of the Http Request of the Project
   
   ```http://localhost:8080/swagger-ui.html```

### Get the war
The project is packaged in a war file which can be exported to different servers.

1. clone the repository

   ```git clone```

2. go into the project folder

   ```cd CampusParadigmaServer```

3. run der project with maven

   ```mvn clean install```
   
4. go into the project folder

   ```cd CampusParadigmaServer/target/```
   
5. There is a CampusParagimaServer.war file

## How to contribute

## Lincese
