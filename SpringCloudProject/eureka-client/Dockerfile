FROM java:8-jre
ENV VERSION="0.0.1-SNAPSHOT"
WORKDIR "/local"
ADD target/eureka-client-0.0.1-SNAPSHOT.jar /local/
ADD entrypoint.sh ./entrypoint.sh
RUN chmod u+x /local/entrypoint.sh
ENTRYPOINT ./entrypoint.sh