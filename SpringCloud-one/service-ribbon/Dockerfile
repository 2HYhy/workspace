FROM java:8-jre
VOLUME /tmp
ADD target/service-ribbon-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 9003

# 基本是通用的Dockerfile格式