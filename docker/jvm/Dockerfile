FROM eclipse-temurin:17-jdk-jammy
RUN mkdir /opt/app

# To make a build.
RUN mkdir /opt/app/sources
COPY . /opt/app/sources
WORKDIR /opt/app/sources
RUN ./mvnw clean install -DskipTests
RUN cp /opt/app/sources/target/ontology-tool-demo-0.0.1-SNAPSHOT.jar /opt/app/ontology-tool-demo-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "/opt/app/ontology-tool-demo-0.0.1-SNAPSHOT.jar"]