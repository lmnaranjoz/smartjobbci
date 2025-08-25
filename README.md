# Prueba Java

Este programa se encarga de administrar la gestión de usuarios junto con sus respectivos números de teléfono. Ofrece una interfaz para registrar nuevos usuarios en el sistema, proporcionando sus datos básicos, como nombre, correo electrónico y contraseña, así como también permite asociar uno o varios números de teléfono a cada usuario creado para posteriormente poder consultarlo.

## Implementación

Para ejecutar este proyecto, ejecuta el método principal de la clase main.

## Unit tests

Para ejecutar las pruebas unitarias, clic derecho en el paquete de pruebas y selecciona la opción "Run tests".

## Api Reference

#### Obtener todos los usuarios

```http
GET /api/v1/user
```

#### Obtener un usuario por su ID

```http
GET /api/v1/user/{id}
```

#### Crear usuario

```http
POST /api/v1/users
```

| Parámetro | Tipo     | Descripción                       |
| :-------- | :------- | :-------------------------------- |
| `name`    | `string` | **Requerido**. Nombre del usuario |
| `email`   | `string` | **Requerido**. Correo electrónico del usuario |
| `password`| `string` | **Requerido**. Contraseña del usuario |
| `phones`  | `array`  | **Requerido**. Teléfonos del usuario |


#### Modificar usuario

```http
PUT /api/v1/users/{id}
```

| Parámetro | Tipo     | Descripción                       |
| :-------- | :------- | :-------------------------------- |
| `name`    | `string` | **Requerido**. Nombre del usuario |
| `email`   | `string` | **Requerido**. Correo electrónico del usuario |
| `password`| `string` | **Requerido**. Contraseña del usuario |
| `phones`  | `array`  | **Requerido**. Teléfonos del usuario |


#### Eliminar usuario

```http
DELETE /api/v1/user/{id}
```

### Password criteria

- Debe tener al menos 8 caracteres y maximo 30 caracteres.
- Debe componerse exclusivamente de letras mayúsculas y minúsculas, dígitos numéricos y los caracteres especiales mencionados (!@#$%.^&*()\-+=).


### JSON Excample

```json
{
  "name": "lmnz1",
  "email": "lmnz1@example.com",
  "password": "lmnz12345",
  "phones": [
    {
      "number": "12345",
      "citycode": 57,
      "contrycode": 57
    }
  ]
}
```

### Ejemplo de Respuesta JSON

```json
{
  "name": "lmnz1",
  "email": "lmnz1@example.com",
  "phones": [
    {
      "number": "12345",
      "citycode": 57,
      "contrycode": 57
    }
  ],
  "id": "b6c31224-211c-4336-8b08-dbc5875dc690",
  "created": "2025-08-24T19:49:37.2934427",
  "modified": "2025-08-24T19:49:37.2934427",
  "last_login": "2025-08-24T19:49:37.2934427",
  "token": "c1738a64-42cb-4031-83e0-a386aebadbca",
  "isactive": true
}
```

## Pila Tecnológica

**Cliente:** Swagger

**Servidor:** Java, Srping Boot JPA, Gradle, JUnit 5

Para acceder a Swagger, utiliza: [http://localhost:8181/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html) y
para acceder a la base de datos, utiliza: [http://localhost:8181/h2-console/login.do](http://localhost:8081/h2-console/login.jsp), el usuario es **smartjobbci** y la contraseña es **password**.

En el paquete de recursos se encuentra el diagrama de clases.

classDiagram

    class UserEntity {
        + id: UUID
        + name: String
        + email: String (unique)
        + password: String
        + creatioDate LocalDateTime
        + updateDate LocalDateTime
        + lastLogin LocalDateTime
        + token: String (CHAR(36))
        + isActive: Boolean
        + phones: List<PhoneEntity>
    }
    
    class PhoneEntity {
        + id: Long
        + number: String
        + citycode: Integer
        + contrycode: Integer
        + user: UserEntity
    }

![Diagrama de clases](/src/main/resources//diagrama_clases.png)


## Autor

- Luis Miguel Naranjo Z.
