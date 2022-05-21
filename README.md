# Agenda-Telefonica
Proyecto Agenda Telefónica

## Objetivo:
Construir una Api Rest que permita agendar números de teléfono fijo de Argentina.

## Tecnologías
- Java
- Spring
- Git
- GitHub
- MySql

## Dependencias:
1. ✔️ Spring Data JPA
2. ✔️ MySQL DRIVER
3. ✔️ Spring Web
4. ✔️ Springboot Starter Validation
5. ✔️ Springboot Devtools
6. ✔️ Lombok

## Configuración
### 1. Clonar el repositorio
```java
https://github.com/Valle1989/Agenda-Telefonica.git
```
### 2. Crear una base de datos en MySql
```java
create database agenda
```
### 3. Inicializar la App
```java
mvn spring-boot:run
```
La app inicia en http://localhost:8080.

## Funcionalidad
### Creación y eliminación de la persona y su respectivo teléfono
La api posee los servicios de creación y eliminación de personas. Usando respectivamente los métodos HTTP POST Y DELETE.

### Búsqueda de números de teléfono por nombre de la persona.
Permite buscar por nombre, y filtrar de esta manera su número de teléfono.

- GET /person?name=nombre

### Listado de personas.
Muestra solamente los campos name y phone. El endpoint deberá ser:
- GET /person


