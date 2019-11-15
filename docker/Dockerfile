FROM openjdk:8
MAINTAINER yuderen yuderen6312@163.com

COPY notify-1.0-SNAPSHOT.jar /home/notify.jar

WORKDIR /home

EXPOSE 9610

CMD ["java", "-jar", "-Dspring.profiles.active=dev", "notify.jar"]