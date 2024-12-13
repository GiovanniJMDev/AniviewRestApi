# Usa una imagen base de Maven
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Crea un directorio para la aplicación
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias antes de copiar el resto de los archivos
COPY pom.xml .

# Descarga todas las dependencias mencionadas en el pom.xml y almacena en caché
RUN mvn dependency:go-offline -B

# Copia el resto del código de la aplicación al contenedor
COPY src /app/src

# Compila el proyecto, omitiendo los tests
RUN mvn clean package -DskipTests

# Usa una imagen JRE con Java 21 para la fase de ejecución
FROM eclipse-temurin:21-jre-alpine

# Copia el archivo JAR desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto
EXPOSE 8080

# Ejecuta la aplicación
CMD ["java", "-jar", "/app.jar"]
