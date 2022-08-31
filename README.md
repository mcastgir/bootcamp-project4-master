# Project Master
> Microservice **Master** Bootcamp NTT Data

This project include:
- Spring Boot Webflux
- Spring Boot Data MongoDB Reactive
- Spring Cloud Config
- Spring Cloud Eureka Client
- Spring Cloud Bootstrap
- Spring Boot Actuator
- Kafka Consumer
- WebTestClient
- JUnit
- Mockito
- SonarQube
- Lombok
- Github Actions
- Docker

### Docker

1. Create Image Config Server in Docker
```  
docker build -t master .
```

2. Create Container

```
docker run -p 8085:8085 --name master-instance --link config-server-instance:latest -d master:latest
```

### SonarQube

```
mvn clean verify sonar:sonar -Dsonar.projectKey=master -Dsonar.host.url=http://localhost:9093 -Dsonar.login=sqp_921441d58d5e078fff3e748786a74ca80bbd3645
```