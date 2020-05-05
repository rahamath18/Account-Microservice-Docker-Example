FROM java:8
ARG JAR_FILE=target/Account-Microservice-Docker-Example-1.0-PROD.jar
WORKDIR d:/opt/app
EXPOSE 3001 3306
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]