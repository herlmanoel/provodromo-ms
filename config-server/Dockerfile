FROM maven:3.6.3-jdk-11 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN echo '<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" \
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" \
        xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 \
        https://maven.apache.org/xsd/settings-1.0.0.xsd"> \
      <mirrors> \
        <mirror> \
          <id>central</id> \
          <mirrorOf>central</mirrorOf> \
          <url>https://repo.maven.apache.org/maven2</url> \
        </mirror> \
      </mirrors> \
    </settings>' > /root/.m2/settings.xml

RUN mvn package -DskipTests

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=builder /app/target/*.war app.war

CMD ["java", "-jar", "app.war"]

EXPOSE 8888
