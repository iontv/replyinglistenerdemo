FROM openjdk:8
COPY ./target/replying-listener-demo-1.0-SNAPSHOT.jar /tmp/replying-listener-demo.jar
WORKDIR /tmp
CMD ["java","-jar","replying-listener-demo.jar"]