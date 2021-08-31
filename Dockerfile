FROM openjdk:11-jre-slim
LABEL maintainer="tayfuncelikeee@gmail.com"
ENV JAVA_OPTS " -Xms512m -Xmx512m "
COPY target/dockerapp-0.0.1-SNAPSHOT.jar  /demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
