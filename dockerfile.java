ARG BASE_FILE
FROM custom_maven:3.8.6 as build_war
WORKDIR /build/
COPY hello-world-war/src /build/src
COPY hello-world-war/pom.xml /build/

FROM custom_maven:3.8.6 as build_jar
WORKDIR /build/
COPY spring-boot-hello-world/src /build/src
COPY spring-boot-hello-world/pom.xml /build/

FROM build_$BASE_FILE as builder
RUN ls -al /build/src/
RUN mvn -f pom.xml clean package
RUN ls /build/target/

FROM base_installer:1.0 
ARG BASE_FILE
COPY entrypoint.sh /entrypoint.sh
COPY --from=builder /build/target/ /tmp/
USER root
ENV FILE_NAME=${BASE_FILE}
RUN ls -al /deployments/ && ls -al /tmp/*
RUN if [[ "$(echo $FILE_NAME)" = "war" ]] ; then rm -rf /deployments/tomcat/webapps/* && \
    cp /tmp/*.war /deployments/tomcat/webapps/ ;  \
    else cp /tmp/*jar /deployments/ ; \
    fi
RUN echo $FILE_NAME
RUN ls -al /deployments/ && chmod +x /entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["sh", "/entrypoint.sh"]
