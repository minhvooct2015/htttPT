# product-service Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/product-service-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)


docker run --name mysql-container-sanpham -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=sanphamDB -e MYSQL_USER=loginUser -e MYSQL_PASSWORD=1234 -p 3307:3306 -d mysql:8.0
docker exec -it mysql-container mysql -u loginUser -p1234 loginDB
# MySQL container for App 1
docker run --name mysql-container-app1 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=loginDB1 -e MYSQL_USER=user1 -e MYSQL_PASSWORD=pass1 -p 3307:3306 -d mysql:8.0
# MySQL container for App 2
docker run --name mysql-container-app2 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=loginDB2 -e MYSQL_USER=user2 -e MYSQL_PASSWORD=pass2 -p 3308:3306 -d mysql:8.0


Run the Zookeeper container:

[//]: # (Kafka)
docker run -d \
--name zookeeper \
-p 2181:2181 \
-e ZOOKEEPER_CLIENT_PORT=2181 \
confluentinc/cp-zookeeper:latest

Start Kafka:

docker run -d \
--name kafka \
-p 9092:9092 \
--link zookeeper \
-e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \
-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
-e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
confluentinc/cp-kafka:latest


delete topic
docker exec -it kafka kafka-topics --delete --topic orders --bootstrap-server localhost:9092


[//]: # (tao topic
docker exec -it kafka kafka-topics --create --topic orders --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
)

[//]: # (consume topic)
docker exec -it kafka kafka-console-consumer --bootstrap-server localhost:9092 --topic orders --from-beginning



[//]: # (list topic )
docker exec -it kafka kafka-topics --list --bootstrap-server localhost:9092
