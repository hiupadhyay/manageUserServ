#tell the docker repo
FROM openjdk:8
#provide jar and the location FOr docker host it
ADD target/manageserv.jar manageserv.jar
#provide port no
EXPOSE 8091
#tell command to docker to run our application
ENTRYPOINT ["java","-jar","manageserv.jar"]

