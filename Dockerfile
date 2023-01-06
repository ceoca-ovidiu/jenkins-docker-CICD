FROM openjdk:11
COPY server/target/server-*.jar /server.jar
ENTRYPOINT ["java","-jar","/server.jar"]