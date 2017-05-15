FROM openjdk:8-jre
COPY configuration.yml /opt/dropwizard/
COPY target/IPSENH-Studievolg*.jar /opt/dropwizard/IPSENH-Studievolg.jar
EXPOSE 80
WORKDIR /opt/dropwizard
RUN java -version
ENTRYPOINT [“java”, “-jar”, “IPSENH-Studievolg.jar”]
CMD [“server”, “-Done-jar.silent=true”, “configuration.yml”]
