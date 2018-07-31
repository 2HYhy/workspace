FROM java:8-jre
ENV VERSION="0.0.1-SNAPSHOT"
WORKDIR "/app"
ADD target/feign-service-0.0.1-SNAPSHOT.jar /app/
ADD entrypoint.sh ./entrypoint.sh
RUN chmod u+x /app/entrypoint.sh
ENTRYPOINT ./entrypoint.sh