FROM java:8-jre
MAINTAINER Alex Trunin <jorki02@gmail.com>
ADD ./target/avito_task.jar /app/
CMD ["java", "-jar", "/app/avito_task.jar"]
EXPOSE 8080