FROM java:8-jre
WORKDIR /local
ADD target/eureka-server-0.0.1-SNAPSHOT.jar ./eureka-server.jar
ADD entrypoint.sh ./entrypoint.sh
RUN cd /local/ && chmod u+x ./entrypoint.sh
ENTRYPOINT ./entrypoint.sh