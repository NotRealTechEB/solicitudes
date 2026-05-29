FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY build/libs/*-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-Xmx256m","-Xms128m","-jar","app.jar"]