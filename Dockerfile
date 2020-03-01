FROM openjdk:8-alpine

COPY target/uberjar/mortgag-calculator.jar /mortgag-calculator/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/mortgag-calculator/app.jar"]
