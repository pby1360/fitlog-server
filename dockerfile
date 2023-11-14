FROM openjdk:21-jdk

# 작업 디렉토리 설정
WORKDIR /app

# JAR 파일 복사
COPY /build/libs/fitlog-server-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

# 애플리케이션 실행 명령어
CMD ["java", "-jar", "-Dspring.profiles.active=product", "fitlog-server-0.0.1-SNAPSHOT.jar"]