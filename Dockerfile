FROM openjdk:8
ADD /target/restful-web-services-All-0.0.1-SNAPSHOT.jar restful-web-services-All.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","restful-web-services-All.jar"]