FROM openjdk:8-jre
COPY configuration.yml /opt/dropwizard/
COPY target/IPSENH-API*.jar /opt/dropwizard/IPSENH-Studievolg.jar

WORKDIR /opt/dropwizard
EXPOSE 8090
RUN java -version
CMD [“java”, “-jar”, “IPSENH-Studievolg.jar”, “server”, “-Done-jar.silent=true”, “configuration.yml”]
