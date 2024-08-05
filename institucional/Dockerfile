# Etapa de build
FROM maven:3.6.3-jdk-11 AS builder

# Define o diretório de trabalho no estágio de build
WORKDIR /app

# Copia o arquivo pom.xml e baixa as dependências necessárias
COPY pom.xml .

RUN mvn dependency:go-offline

# Copia o código-fonte do aplicativo para o contêiner
COPY src ./src

# Configurações do Maven
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

# Executa o build do projeto, pulando os testes
RUN mvn package -DskipTests

# Verifica se o arquivo JAR foi gerado
RUN ls -l /app/target/

# Etapa final
FROM openjdk:11-jre-slim

# Define o diretório de trabalho no estágio final
WORKDIR /app

# Copia o arquivo JAR do estágio de build para o estágio final
COPY --from=builder /app/target/*.jar /app/app.jar

# Verifica se o arquivo JAR foi copiado
RUN ls -l /app/

EXPOSE 9090

# Comando para iniciar o aplicativo
CMD ["java", "-jar", "/app/app.jar"]
