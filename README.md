# Productos-VCSoft-JAVA

## Descripción

API REST simple desarrollada con Spring Boot (Java 17) para gestionar
una entidad: **Producto**. Implementa operaciones CRUD básicas
utilizando Spring Data JPA, Hibernate y PostgreSQL.

## Tecnologías

-   Java 17
-   Spring Boot
-   Spring Data JPA
-   Hibernate
-   PostgreSQL
-   Maven

## Requisitos

-   Java 17
-   Maven 3.8+
-   PostgreSQL

## Base de Datos

Crear la base de datos:

```
CREATE DATABASE product_db;
```

## Configuración (application.properties)

```
spring.datasource.url=jdbc:postgresql://localhost:5432/product_db
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## Ejecución

```
mvn clean install 
mvn spring-boot:run
```

URL Base: http://localhost:8080/api/v1/productos
