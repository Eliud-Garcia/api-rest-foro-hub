# Foro Hub API REST

Una API RESTful desarrollada con Spring Boot para la gestión de un foro, permitiendo la interacción estructurada mediante Tópicos, Cursos y Usuarios. Este proyecto forma parte del challenge Foro Hub de Alura Latam.

## 🚀 Características Principales

- **Gestión de Tópicos (CRUD completo)**:
  - Creación de nuevos tópicos validando que no existan duplicados (por título y mensaje).
  - Listado de tópicos con **paginación y ordenamiento** automático.
  - Búsqueda detallada de un tópico por su `ID`.
  - Actualización de información garantizando validación de datos en el proceso (`@Transactional`).
  - Eliminación de tópicos por su `ID`.
- **Gestión de Cursos y Usuarios**: Endpoints dedicados para el registro de nuevos cursos y usuarios en el sistema.
- **Reglas de Negocio Inteligentes**: Manejo eficiente de transacciones de base de datos para no guardar datos anómalos ni registros redundantes.
- **Manejo Centralizado de Errores**: Uso de un `GestorErrores` global para proveer respuestas HTTP claras (404 Not Found, 400 Bad Request, etc.) cuando ocurren validaciones de negocio (ej. `ValidacionException` o `EntityNotFoundException`).
- **Seguridad Sólida**: Autenticación y autorización protegidas mediante Spring Security y **JWT (JSON Web Tokens)**.

## 🛠️ Tecnologías y Herramientas

- **Java**
- **Spring Boot 3** (Spring Web y Spring Data JPA)
- **Base de Datos**: MySQL
- **Lombok**: Reducción de código boilerplate (Getters, constructores, etc.).
- **Flyway**: Para el versionado y control de migraciones de la base de datos.
- **Maven**: Herramienta de gestión de dependencias y construcción de proyecto.

## 📌 Endpoints de la API

La API cuenta con los siguientes endpoints principales:

### Tópicos (`/topicos`)
- `POST /topicos`: Registra un nuevo tópico en la base de datos.
- `GET /topicos`: Retorna un listado paginado de tópicos (por defecto devuelve 30 resultados ordenados por `titulo`).
- `GET /topicos/{id}`: Retorna los detalles de un tópico específico según su `ID`.
- `PUT /topicos/{id}`: Actualiza la información de un tópico existente.
- `DELETE /topicos/{id}`: Elimina un tópico del sistema.

### Cursos (`/cursos`)
- `POST /cursos`: Registra la información de un nuevo curso.

### Usuarios (`/usuarios`)
- `POST /usuarios`: Registra la información de un nuevo usuario en la base de datos.

## ⚙️ Configuración y Ejecución Local

1. Clona este repositorio en tu máquina local.
2. Crea un archivo llamado `.env` en la raíz del proyecto para definir tus variables de entorno, como las credenciales de conexión a tu base de datos y la clave secreta para la firma del token JWT.
3. Asegúrate de tener tu servidor de base de datos local encendido (ej. MySQL).
4. Spring Boot, junto con Flyway, creará las tablas necesarias en tu base de datos si las migraciones están configuradas.
5. Ejecuta el proyecto desde tu IDE preferido (IntelliJ IDEA, Eclipse, VS Code) o usando la terminal:
   ```bash
   ./mvnw spring-boot:run
   ```
