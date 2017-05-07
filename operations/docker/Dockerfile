FROM openjdk:8-jre
COPY . /usr/src/IPSENH-Studievolg
WORKDIR /usr/src/IPSENH-Studievolg
EXPOSE 8080
RUN javac Main.java
CMD ["java","-jar","/home/testprj-1.0-SNAPSHOT.jar"]
