# pokemon
this is a basic rest application.

to run it you have to:
- install tomcat
- install mysql
- install maven
- install java jdk

1)create schema under mysql with name pokemon
2)move to project folder (example C:\pokemon)
3)launch mvn package command
3)copy the war under the project(example C:\pokemon\target) into tomcat folder(example : C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps)
4)run tomcat
5)you can access to swagger documentation (example http://localhost:8080/pokemon-0.0.1-SNAPSHOT/swagger-ui.html)
6)you can invoke the application using GET PUT POST DELETE method ( the application could be available under address http://localhost:8080/pokemon-0.0.1-SNAPSHOT/ )
7)use postman to call the aplication, there is a postma collection to do it(there is a basic auth user: ash pass: ash)
