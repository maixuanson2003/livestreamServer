# Sử dụng JDK 17 chính thức từ Eclipse Temurin
FROM eclipse-temurin:17-jdk

# Thư mục làm việc trong container
WORKDIR /app

# Copy file .jar vào container
COPY target/learn-0.0.1-SNAPSHOT.jar app.jar

# Mở cổng 8080 (Spring Boot mặc định)
EXPOSE 8080

# Lệnh khởi động ứng dụng
CMD ["java", "-jar", "app.jar"]