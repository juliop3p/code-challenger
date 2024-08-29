# Use uma imagem base com o OpenJDK
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o JAR para o diretório de trabalho
COPY your-application.jar app.jar

# Expõe a porta que o Spring Boot usa por padrão
EXPOSE 8080

# Define o comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
