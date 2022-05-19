FROM maven:latest

WORKDIR /app

COPY pom.xml ./

# CMD ["mvn", "clean package"]
CMD ["mvn", "compile"]
