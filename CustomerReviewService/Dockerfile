# Temel Image
FROM maven:3.8.4-openjdk-17 as build

# Kaynak Kodunu Kopyala
COPY src /app/src
COPY pom.xml /app

# Çalışma Dizini
WORKDIR /app

# Uygulamayı Build Et
RUN mvn clean package -DskipTests

# Runtime Image
FROM openjdk:17-jdk-alpine

# Çalışma Dizini
WORKDIR /app

# Build Aşamasından Jar Dosyasını Kopyala
COPY --from=build /app/target/*.jar app.jar

# Uygulamayı Çalıştır
ENTRYPOINT ["java","-jar","/app/app.jar"]
