FROM openjdk:14
LABEL responsable="lucasvannier@gmail.com"
EXPOSE 8081:8081
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} patientapi.jar
ENTRYPOINT ["java","-jar","/patientapi.jar"]