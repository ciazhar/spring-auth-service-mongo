# Mongo Authorization Server

>Authorization Server using MongoDB as Database.

## Stack

This application build using :

- Spring Framework
- Spring Boot 1.5.8.RELEASE
- Spring Data MongoDB
- Spring Security
- Spring Security OAuth2
- Spring Security JWT
- Lombok
- Guava

## Execute Program

To execute program use this command and use MongoDB as Token Repository.

```bash
    mvn clean spring-boot:run
```

## Running Using JWT

To run Using JWT as Token add this command while execute program.

```bash
    mvn clean spring-boot:run -Dspring.profiles.active=jwttoken
```

## TODO

- Tambah kondisi saat `PostConstruct` pada main class agar tidak Drop Collection.
- Riset untuk security di Inner Project