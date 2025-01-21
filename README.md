# Book Store Hub

**Book Store Hub** es una aplicación diseñada para gestionar y consultar libros almacenados en una base de datos local o remota.  
La aplicación permite a los usuarios realizar consultas avanzadas y obtener información detallada sobre libros y autores en diferentes idiomas. Además, utiliza la **API de Gutendex** para obtener datos de libros externos. Todos los resultados se muestran directamente en la consola.

---

## Funcionalidades principales
- **Almacenamiento de libros**: Registra libros con información sobre sus autores, idiomas y otros detalles relevantes en una base de datos.
- **Consulta de libros y autores**: Busca libros específicos y obtén información sobre los autores registrados.
- **Búsqueda por idioma**: Permite buscar libros registrados en un idioma específico.
- **Top 10 por idioma**: Encuentra y almacena los 10 libros más populares o destacados por idioma.
- **Integración con la API de Gutendex**: Recupera datos externos sobre libros utilizando herramientas modernas de HTTP.

---

## Tecnologías utilizadas

- **Java 17**: Lenguaje principal utilizado para el desarrollo de la aplicación.
- **JDBC y JPA (Java Persistence API)**: Para la conexión y manipulación de bases de datos locales y remotas.
- **SQLite o PostgreSQL**: Bases de datos compatibles para almacenar la información de los libros.
- **Jackson (ObjectMapper)**: Biblioteca utilizada para el manejo y mapeo de datos JSON.
- **Spring Boot Starter Data JPA**: Para simplificar la integración con bases de datos.
- **Gutendex API**: Proveedor de datos externos sobre libros.
- **Herramientas HTTP (HttpClient, HttpRequest, HttpResponse)**: Para realizar solicitudes a la API.

---

## Requisitos de instalación
1. **Java 11** o superior instalado en tu sistema.  
   Verifica tu versión con el comando:
   ```bash
   java -version

Descárgalo aquí en caso de que no lo tengas:
[Descargar Java](https://www.oracle.com/java/technologies/javase-downloads.html)

2. **Base de datos:**

SQLite: Descárgalo desde:
[Descargar SQLite](https://www.sqlite.org/download.html)
PostgreSQL (opcional): Descárgalo desde:
[Descargar PostgreSQL](https://www.postgresql.org/download/)

3. **Bibliotecas requeridas: Descarga los siguientes archivos .jar:**

[Jackson Core 2.18](https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.18/jackson-core-2.18.0.jar)

[Jackson Annotations 2.18](https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.18/jackson-annotations-2.18.0.jar)

[Jackson Databind 2.18](https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.18/jackson-databind-2.18.0.jar)

[Spring Boot Starter Data JPA 3.3.7](https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-data-jpa/3.3.7/spring-boot-starter-data-jpa-3.3.7.jar)

4. **Configuración de la base de datos y variables de entorno:**

Crea un archivo ``application.properties`` y configura las propiedades de tu base de datos.
Ejemplo para PostgreSQL:

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/bookstore
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```
Configura las variables de entorno necesarias, como la clave de la API de Gutendex, por ejemplo:

```bash
export GUTENDEX_API_KEY=tu_clave_de_api
```
Esto es muy importante para evitar colocar datos sensibles dentro de tu código: Instructivo para crear variables de entorno dentro de Windows y otros S.O:

[Creando Variables de entorno](https://www.aluracursos.com/blog/como-configurar-variables-de-entorno-en-windows-linux-y-macos)

---
## Instrucciones de instalación

Descarga y configura Java, SQLite o PostgreSQL en tu sistema.
Descarga y coloca los archivos .jar requeridos en el directorio del proyecto.
Configura el archivo application.properties con las credenciales de tu base de datos.
Define las variables de entorno necesarias para tu entorno local.
Compila y ejecuta la aplicación:

```bash
javac -cp .:lib/* Main.java
java -cp .:lib/* Main
```
---
## Uso de la aplicación
Inicia la aplicación desde la consola o tu entorno de desarrollo.
Ingresa comandos para realizar las siguientes acciones:
Almacenar libros y autores.
Consultar libros por idioma.
Obtener el Top 10 por idioma desde la base de datos.
Consultar datos externos de libros utilizando la API de Gutendex.

---
## Licencia
Este proyecto está licenciado bajo la licencia ``MIT``. Consulta el archivo ``LICENSE`` para más detalles sobre los permisos y restricciones.

---
## Contribuciones
¡Las contribuciones son bienvenidas! Si encuentras errores o deseas añadir funcionalidades, abre un ``issue`` o un ``pull request``.

---
## Autor

Desarrollado por [faberleondev](https://github.com/faberleondev)