# ETAPA 1: Construir la aplicación
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Copiamos los archivos necesarios para Maven
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Descargamos las dependencias (optimiza el caché de capas)
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copiamos el código fuente y compilamos
COPY src src
RUN ./mvnw clean package -DskipTests

# ETAPA 2: Crear la imagen final ligera
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copiamos el jar generado desde la etapa anterior
# Ajusta el nombre del jar según lo que genere tu proyecto (target/*.jar)
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Ejecutamos la aplicación
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]