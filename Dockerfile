FROM openjdk:17.0.2-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ./arial.ttf ./font/arial.ttf
COPY ./resumes ./resumes
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]