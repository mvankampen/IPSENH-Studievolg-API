FROM openjdk:8-jre
COPY configuration.yml /opt/dropwizard/
COPY target/IPSENH-API*.jar /opt/dropwizard/IPSENH-Studievolg.jar

WORKDIR /opt/dropwizard
EXPOSE 8080
RUN java -version
ENTRYPOINT [“java”, “-jar”, “IPSENH-Studievolg.jar”]
CMD [“server”, “-Done-jar.silent=true”, “configuration.yml”]
