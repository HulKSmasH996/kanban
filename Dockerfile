FROM openjdk:8-alpine
ADD target/kanban-0.0.1-SNAPSHOT.jar kanban-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh", "-c", "java -jar /kanban-0.0.1-SNAPSHOT.jar"]