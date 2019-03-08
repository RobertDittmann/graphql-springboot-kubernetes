FROM openjdk:8
ADD target/docker-graphql-springboot.jar docker-graphql-springboot.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "docker-graphql-springboot.jar"]