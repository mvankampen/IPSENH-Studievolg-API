FROM openjdk:8-jre
COPY configuration.yml /opt/dropwizard/
COPY target/IPSENH-Studievolg*.jar /opt/dropwizard/IPSENH-Studievolg.jar
ADD pom.xml /opt/dropwizard/pom.xml
RUN cd /opt/dropwizard/ \
    && mvn dependency:go-offline dependency:resolve-plugins \
    && mvn verify clean --fail-never

WORKDIR /opt/dropwizard
EXPOSE 8080
RUN java -version
ENTRYPOINT [“java”, “-jar”, “IPSENH-Studievolg.jar”]
CMD [“server”, “-Done-jar.silent=true”, “configuration.yml”]
