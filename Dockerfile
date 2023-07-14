# Base 이미지 설정
FROM openjdk:17-jdk
LABEL authors="mingulmangul"

# 메인 애플리케이션 디렉토리 설정
WORKDIR /app

# 애플리케이션 빌드를 위해 필요한 파일 복사
COPY build.gradle .
COPY gradlew .
COPY gradle ./gradle
COPY src ./src

# gradlew 파일 실행 권한 설정
RUN chmod +x ./gradlew

# 빌드된 JAR 파일을 컨테이너 내부로 복사
COPY build/libs/*.jar app.jar

# 컨테이너 실행 명령
CMD ["java", "-jar", "app.jar"]
