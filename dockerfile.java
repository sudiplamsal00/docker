ARG BASE_FILE

FROM custom_maven:3.8.4 as build_war
WORKDIR /build/
COPY hello-world-war/src /build/src
COPY hello-world-war/pom.xml /build/

FROM custom_maven:3.8.4 as build_jar
WORKDIR /build/
COPY spring-boot-hello-world/src /build/src
COPY spring-boot-hello-world/pom.xml /build/

FROM build_$BASE_FILE as builder
RUN ls -al /build/src/
RUN mvn -f pom.xml clean package
RUN ls /build/target/

FROM installer:1.0 
COPY run.sh /run.sh
COPY --from=builder /build/target/ /tmp/

RUN if [[ "$BASE_FILE" = "war" ]] ; then rm -rf /deployments/tomcat/webapps/ && \
    cp /tmp/*.war /deployments/tomcat/webapps/ ;  \
    else /tmp/*jar /deployment/ ; \
    fi
RUN ls -al /deployments/tomcat/webapps/ && chmod +x /run.sh

EXPOSE 8080

USER root

ENTRYPOINT ["sh", "/run.sh"]
