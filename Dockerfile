FROM openjdk:19
COPY server/target/server-*.jar /server.jar
ENTRYPOINT ["java","-jar","/server.jar"]