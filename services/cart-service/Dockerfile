FROM eclipse-temurin:17-jre-jammy
EXPOSE 8081
ADD target/cart-service.jar cart-service.jar
ENTRYPOINT ["java","-jar","/cart-service.jar"]