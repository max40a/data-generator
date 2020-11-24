FROM adoptopenjdk:11.0.7_10-jre-hotspot
WORKDIR /app
COPY ./build/libs/*.jar /app
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "data-generator-0.0.1.jar"]
