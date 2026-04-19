# BACKEND BIBLIOTECA ONLINE CODIGOJAVA

## 1. Descripción del proyecto

Este proyecto corresponde a la parte backend de una biblioteca en línea.
Desarrollado con Spring Boot + java, siguiendo una arquitectura monolito con un patŕon de diseño MVC estricta y utilizando Dataholders para la validación de entrada y DTOS para la salida.
Incluye módulos para gestionar Libros, Autores, Categorías, Editoriales, Usuarios, Préstamos, Historiales y Autenticación, con endpoints documentados y validados.

La persistencia de datos se maneja mediante JPA, conectado a una base de datos MySQL que se ejecuta en un contenedor Docker.

---

## 2. Instalación Local del Proyecto Backend
### 2.1 Clonar el proyecto

```bash
git clone https://github.com/DEVSAM1966/Biblioteca-codigojava.git
cd Biblioteca-codigojava
```

### 2.2 Instalación del Backend

Para que este proyecto funcione se necesita tener instalado en local la version de 17 de Java.  Para ver la actual versión instalada en local hacer:
```bash
java -version
```

El motivo de usar esta versión de Java es debido al uso de las librerias **mapstruct** y **mapstruct-processor** que 
en un nivel estable (un nivel superior no es estable).   A su vez con la versión 17 de Java nos obliga a usar la versión 
**Spring 3.3.6**.


También para programar se recomienda el uso del **IDE Intellij IDEA** que es el estandar de la industria en Java.

### 2.3 Instalación del Contenedor Docker

Inicia el contenedor de la base de datos (Docker Desktop debe estar instalado en Windows, o su equivalente en Linux) lanzando el siguiente comando docker en el directorio de trabajo del proyecto (directorio Biblioteca-codigojava):

```bash
docker-compose up
```

Esto creará el servicio de base de datos con las credenciales definidas en ``docker-compose.yml``. Se genera el contendor docker con el esquema **biblio-codejava** y los datos en la distintas tablas.

Internamente en el momento de crear el contenedor con el motor de MySQL 8.0 se ha ejecutado los siguientes scripts SQL que están contenidos el el directorio **Biblioteca-codigojava/sql**:

- **create_schema.sql**:  Este script es el responsable de creación del esquema, usuario app_user, permisos del usuario, tablas.

- **data.sql**:  Añade a las tablas un juego de datos básico para hacer pruebas.

---

## 3. Acceso Directo al Contenedor Docker (Verificación de la Base de Datos)

Para verificar que la base de datos está correctamente configurada, accede al contenedor (asegúrate de que esté en ejecución).

### 3.1 Acceso al Contenedor Docker

Desde CMD o terminal Linux, ejecuta:

```bash
docker exec -it biblio_codigojava_mysql mysql -u root -p
```
La contraseña de MySQL es: **Jean-Luc_Picard_1966**

### 3.2 Vista General de las Tablas (Descripción Breve)

La base de datos del proyecto contiene las siguientes tablas/entidades:

 -   authors: autores principales.

 -   publishers: editoriales con datos de contacto.

 -   categories: clasificación temática de libros.

 -   books: metadatos de libros, autor principal (author_id) y coautores opcionales.

 -   users: usuarios registrados con roles (USER, SUPPORT, ADMIN).

 -   loans: préstamos activos e históricos.

 -   histories: comentarios de usuarios sobre libros prestados.

A continuación, ejecutaremos instrucciones MySQL para verificar la correcta importación de la estructura y los datos.

#### Paso 1 – Cambiar de Esquema

```sql
use biblio_codigojava
```

Resultado esperado: **Database changed**

#### Paso 2 – Verificar Tablas

```sql
show tables;
```

#### Paso 3 – Describir Tablas

```sql
desc authors;
desc books;
desc categories;
desc histories;
desc loans;
desc publishers;
desc users;
```
Resultado esperado:  **Lista de campos, tipos de datos y propiedades de cada tabla**.

#### Paso 4 – Consultar Datos

```sql
SELECT * FROM authors;
SELECT * FROM publishers;
SELECT * FROM categories;
SELECT * FROM books;
SELECT * FROM users;
SELECT * FROM loans;
```

Resultado esperado: **authors, books, categories, histories, loans, publishers, users**

### 3.3 Usuarios de MySQL

Los usuarios que existen en la Base de Datos son:

1. **root**  con password  **Jean-Luc_Picard_1966** (usuario creado en el contenedor Docker)
2. **app_user**  con password  **Egdpababpec** (usuario creado en el script create_schema.sql y ejecutado en el momento de creación del contenedor Docker).

---

## 4. Pasos para Ejecutar Correctamente la Aplicación
### 4.1 Crear Directorios para Guardar Portadas y Libros

Desde Intellij IDEA, dentro de la carpeta del proyecto Biblioteca-codigojava, crea una carpeta llamada uploads, y dentro de esta carpeta (uploads) creamos estas otras carpetas:

 -   cover
 -   file

**Biblioteca-codigojava/uploads/cover** almacenará portadas en formato JPEG.
**Biblioteca-codigojava/uploads/file** almacenará los archivos PDF de los libros.

El archivo .gitignore excluye la carpeta uploads/ para evitar subir archivos pesados al repositorio.

### 4.2 Configuración del archivo: application.properties

Este proyecto utiliza Spring Boot y requiere una configuración básica para conectarse a la base de datos, gestionar seguridad JWT y cargar variables externas.
A continuación se explica cada propiedad para que cualquier desarrollador que clone el repositorio pueda configurarlo correctamente.

```java
spring.application.name=biblioteca
server.port=9800
```
- **spring.application.name**: Nombre interno de la aplicación (aparece en logs y herramientas de Spring).
- **server.port**: Puerto donde se ejecuta el backend (http://localhost:9800).

```java
spring.config.import=optional:file:/home/sam/SAM-PROYECTOS/Biblioteca-codigojava-secrets/application-secret.properties
```

Permite cargar credenciales y secretos desde un archivo externo no incluido en el repositorio, evitando exponer datos sensibles.

    Si clonas el proyecto, deberás crear tu propio archivo 
    application-secret.properties con las variables necesarias.
    Recuerda que el directorio donde se ubica application-secret.properties 
    cambiara (aqui en el ejemplo muestro donde lo ubico en local).

    IMPORTANTE:  Este fichero esta fuera del proyecto y por tanto deberás 
    de crearlo manualmente, variara la ruta por supesto.


El contenido de mi **application-secrect.properties** es el siguiente:
```java
# Credenciales de MySQL 
BIBLIO_USER=root
BIBLIO_SECRET=Jean-Luc_Picard_1966

# Clave secreta para JWT
JWT_SECRET=f06756f8d66b7f85619c0672eeca1cdd
```
El valor de JWT_SECRET fue elegido aleatoriamente, pueden colocar otro string.


La configuración de la base de datos MySQL:
```java
spring.datasource.url=jdbc:mysql://localhost:3310/biblio_codigojava?useUnicode=true&characterEncoding=UTF-8&connectionCollation=utf8mb4_unicode_ci&serverTimezone=UTC
spring.datasource.username=${BIBLIO_USER}
spring.datasource.password=${BIBLIO_SECRET}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
- **spring.datasource.url**: URL de conexión a MySQL (puerto 3310 en este proyecto).

- **spring.datasource.username / password**: Se obtienen desde variables externas (BIBLIO_USER, BIBLIO_SECRET).

- **driver-class-name**: Driver JDBC de MySQL.

Asegúrate de tener MySQL corriendo en el puerto correcto y la base de datos creada.


Para la configuración de JPA / Hibernate.
```java
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
```
- **ddl-auto=none**: No modifica la estructura de la base de datos (modo seguro para producción).

- **show-sql=true**: Muestra las consultas SQL en consola (útil para desarrollo).

- **open-in-view=false**: Cierra la sesión de Hibernate al salir del servicio.
Mejora la arquitectura y evita consultas inesperadas durante la serialización.


Definición de la subida de archivos.
```java
spring.servlet.multipart.max-file-size=50MB
spring.servlet.multipart.max-request-size=50MB
```
Permite subir archivos grandes (hasta 50 MB), útil si el proyecto maneja imágenes o documentos.


Seguridad JWT.
```java
api.security.token.secret=${JWT_SECRET}
```
Clave secreta para firmar y validar tokens JWT.
Debe definirse en el archivo de secretos externo (ver más arriba en application-secret.properties).

Resumen para quien clone el proyecto.

1. **MySQL** corriendo en localhost:3310.

2. Una base de datos llamada **biblio_codigojava**.

3- Un archivo externo llamado **application-secrect.properties** (debe estar fuera del directorio del proyecto) con las variables:

```java
BIBLIO_USER=usuario_mysql
BIBLIO_SECRET=contraseña_mysql
JWT_SECRET=clave_para_tokens
```
4. Java 17+ y Maven instalados.


### 4.3 Iniciar la Aplicación

Desde Intellij IDEA localizar el fichero **BibliotecaApplication.java** en el ``src/main/java/com-codigojava.biblioteca``, marcarlo con el mouse, botón derecho y elegir Run.

Si todo va correcto verás:

```bash
/usr/lib/jvm/java-17-openjdk-amd64/bin/java -XX:TieredStopAtLevel=1 -Dspring.output.ansi.enabled=always -javaagent:/snap/intellij-idea-community/709/lib/idea_rt.jar=40481 -Dfile.encoding=UTF-8 -classpath /home/sam/SAM-PROYECTOS/Biblioteca-codigojava/target/classes:/home/sam/.m2/repository/org/springframework/boot/spring-boot-starter-web/3.3.6/spring-boot-starter-web-3.3.6.jar:/home/sam/.m2/repository/org/springframework/boot/spring-boot-starter/3.3.6/spring-boot-starter-3.3.6.jar:/home/sam/.m2/repository/org/springframework/boot/spring-boot/3.3.6/spring-boot-3.3.6.jar:/home/sam/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/3.3.6/spring-boot-autoconfigure-3.3.6.jar:/home/sam/.m2/repository/org/springframework/boot/spring-boot-starter-logging/3.3.6/spring-boot-starter-logging-3.3.6.jar:/home/sam/.m2/repository/ch/qos/logback/logback-classic/1.5.12/logback-classic-1.5.12.jar:/home/sam/.m2/repository/ch/qos/logback/logback-core/1.5.12/logback-core-1.5.12.jar:/home/sam/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.23.1/log4j-to-slf4j-2.23.1.jar:/home/sam/.m2/repository/org/apache/logging/log4j/log4j-api/2.23.1/log4j-api-2.23.1.jar:/home/sam/.m2/repository/org/slf4j/jul-to-slf4j/2.0.16/jul-to-slf4j-2.0.16.jar:/home/sam/.m2/repository/jakarta/annotation/jakarta.annotation-api/2.1.1/jakarta.annotation-api-2.1.1.jar:/home/sam/.m2/repository/org/yaml/snakeyaml/2.2/snakeyaml-2.2.jar:/home/sam/.m2/repository/org/springframework/boot/spring-boot-starter-json/3.3.6/spring-boot-starter-json-3.3.6.jar:/home/sam/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.17.3/jackson-databind-2.17.3.jar:/home/sam/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.17.3/jackson-annotations-2.17.3.jar:/home/sam/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.17.3/jackson-core-2.17.3.jar:/home/sam/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.17.3/jackson-datatype-jdk8-2.17.3.jar:/home/sam/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.17.3/jackson-datatype-jsr310-2.17.3.jar:/home/sam/.m2/repository/com/fasterxml/jackson/module/jackson-module-parameter-names/2.17.3/jackson-module-parameter-names-2.17.3.jar:/home/sam/.m2/repository/org/springframework/boot/spring-boot-starter-tomcat/3.3.6/spring-boot-starter-tomcat-3.3.6.jar:/home/sam/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/10.1.33/tomcat-embed-core-10.1.33.jar:/home/sam/.m2/repository/org/apache/tomcat/embed/tomcat-embed-websocket/10.1.33/tomcat-embed-websocket-10.1.33.jar:/home/sam/.m2/repository/org/springframework/spring-web/6.1.15/spring-web-6.1.15.jar:/home/sam/.m2/repository/org/springframework/spring-beans/6.1.15/spring-beans-6.1.15.jar:/home/sam/.m2/repository/io/micrometer/micrometer-observation/1.13.8/micrometer-observation-1.13.8.jar:/home/sam/.m2/repository/io/micrometer/micrometer-commons/1.13.8/micrometer-commons-1.13.8.jar:/home/sam/.m2/repository/org/springframework/spring-webmvc/6.1.15/spring-webmvc-6.1.15.jar:/home/sam/.m2/repository/org/springframework/spring-aop/6.1.15/spring-aop-6.1.15.jar:/home/sam/.m2/repository/org/springframework/spring-context/6.1.15/spring-context-6.1.15.jar:/home/sam/.m2/repository/org/springframework/spring-expression/6.1.15/spring-expression-6.1.15.jar:/home/sam/.m2/repository/org/springframework/boot/spring-boot-starter-data-jpa/3.3.6/spring-boot-starter-data-jpa-3.3.6.jar:/home/sam/.m2/repository/org/springframework/boot/spring-boot-starter-aop/3.3.6/spring-boot-starter-aop-3.3.6.jar:/home/sam/.m2/repository/org/aspectj/aspectjweaver/1.9.22.1/aspectjweaver-1.9.22.1.jar:/home/sam/.m2/repository/org/springframework/boot/spring-boot-starter-jdbc/3.3.6/spring-boot-starter-jdbc-3.3.6.jar:/home/sam/.m2/repository/com/zaxxer/HikariCP/5.1.0/HikariCP-5.1.0.jar:/home/sam/.m2/repository/org/springframework/spring-jdbc/6.1.15/spring-jdbc-6.1.15.jar:/home/sam/.m2/repository/org/hibernate/orm/hibernate-core/6.5.3.Final/hibernate-core-6.5.3.Final.jar:/home/sam/.m2/repository/jakarta/persistence/jakarta.persistence-api/3.1.0/jakarta.persistence-api-3.1.0.jar:/home/sam/.m2/repository/jakarta/transaction/jakarta.transaction-api/2.0.1/jakarta.transaction-api-2.0.1.jar:/home/sam/.m2/repository/org/jboss/logging/jboss-logging/3.5.3.Final/jboss-logging-3.5.3.Final.jar:/home/sam/.m2/repository/org/hibernate/common/hibernate-commons-annotations/6.0.6.Final/hibernate-commons-annotations-6.0.6.Final.jar:/home/sam/.m2/repository/io/smallrye/jandex/3.1.2/jandex-3.1.2.jar:/home/sam/.m2/repository/com/fasterxml/classmate/1.7.0/classmate-1.7.0.jar:/home/sam/.m2/repository/net/bytebuddy/byte-buddy/1.14.19/byte-buddy-1.14.19.jar:/home/sam/.m2/repository/org/glassfish/jaxb/jaxb-runtime/4.0.5/jaxb-runtime-4.0.5.jar:/home/sam/.m2/repository/org/glassfish/jaxb/jaxb-core/4.0.5/jaxb-core-4.0.5.jar:/home/sam/.m2/repository/org/eclipse/angus/angus-activation/2.0.2/angus-activation-2.0.2.jar:/home/sam/.m2/repository/org/glassfish/jaxb/txw2/4.0.5/txw2-4.0.5.jar:/home/sam/.m2/repository/com/sun/istack/istack-commons-runtime/4.1.2/istack-commons-runtime-4.1.2.jar:/home/sam/.m2/repository/jakarta/inject/jakarta.inject-api/2.0.1/jakarta.inject-api-2.0.1.jar:/home/sam/.m2/repository/org/antlr/antlr4-runtime/4.13.0/antlr4-runtime-4.13.0.jar:/home/sam/.m2/repository/org/springframework/data/spring-data-jpa/3.3.6/spring-data-jpa-3.3.6.jar:/home/sam/.m2/repository/org/springframework/data/spring-data-commons/3.3.6/spring-data-commons-3.3.6.jar:/home/sam/.m2/repository/org/springframework/spring-orm/6.1.15/spring-orm-6.1.15.jar:/home/sam/.m2/repository/org/springframework/spring-tx/6.1.15/spring-tx-6.1.15.jar:/home/sam/.m2/repository/org/slf4j/slf4j-api/2.0.16/slf4j-api-2.0.16.jar:/home/sam/.m2/repository/org/springframework/spring-aspects/6.1.15/spring-aspects-6.1.15.jar:/home/sam/.m2/repository/org/springframework/boot/spring-boot-starter-validation/3.3.6/spring-boot-starter-validation-3.3.6.jar:/home/sam/.m2/repository/org/apache/tomcat/embed/tomcat-embed-el/10.1.33/tomcat-embed-el-10.1.33.jar:/home/sam/.m2/repository/org/hibernate/validator/hibernate-validator/8.0.1.Final/hibernate-validator-8.0.1.Final.jar:/home/sam/.m2/repository/jakarta/validation/jakarta.validation-api/3.0.2/jakarta.validation-api-3.0.2.jar:/home/sam/.m2/repository/com/mysql/mysql-connector-j/8.3.0/mysql-connector-j-8.3.0.jar:/home/sam/.m2/repository/org/projectlombok/lombok/1.18.36/lombok-1.18.36.jar:/home/sam/.m2/repository/org/mapstruct/mapstruct/1.5.5.Final/mapstruct-1.5.5.Final.jar:/home/sam/.m2/repository/org/mapstruct/mapstruct-processor/1.5.5.Final/mapstruct-processor-1.5.5.Final.jar:/home/sam/.m2/repository/jakarta/xml/bind/jakarta.xml.bind-api/4.0.2/jakarta.xml.bind-api-4.0.2.jar:/home/sam/.m2/repository/jakarta/activation/jakarta.activation-api/2.1.3/jakarta.activation-api-2.1.3.jar:/home/sam/.m2/repository/org/springframework/spring-core/6.1.15/spring-core-6.1.15.jar:/home/sam/.m2/repository/org/springframework/spring-jcl/6.1.15/spring-jcl-6.1.15.jar com.codigojava.biblioteca.BibliotecaApplication
██████╗ ██╗██████╗ ██╗     ██╗██████╗
██╔══██╗██║██╔══██╗██║     ██║██╔═══██╗
██████╔╝██║██████╔╝██║     ██║██║   ██║
██╔══██╗██║██╔══██╗██║     ██║██║   ██║
██████╔╝██║██████╔╝███████╗██║███████╔╝
╚═════╝ ╚═╝╚═════╝ ╚══════╝╚═╝ ╚═════╝

██████╗ ██████╗  ██████  ███████╗
██╔═══╝ ██╔═══██╗██╔══█  ██╔════╝
██║     ██║   ██║██   ██ █████╗
██║     ██║   ██║██╔══█  ██╔══╝
╚██████╗╚██████╔╝╚█████╗ ███████╗
 ╚═════╝ ╚═════╝  ╚════╝ ╚══════╝

     ██╗ █████╗ ██╗   ██╗ █████╗
     ██║██╔══██╗██║   ██║██╔══██╗
     ██║███████║██║   ██║███████║
██   ██║██╔══██║██║   ██║██╔══██║
╚█████╔╝██║  ██║╚██████╔╝██║  ██║
 ╚════╝ ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═╝
────────────────────────────────────────────────────────────
📚 Welcome to Java Code Library
🖖 Server available on        http://localhost:9800
🖖 Documentation available on http://localhost:9800/docs/index.html
────────────────────────────────────────────────────────────

2026-02-08T21:11:49.131+01:00  INFO 11895 --- [biblioteca] [           main] c.c.biblioteca.BibliotecaApplication     : Starting BibliotecaApplication using Java 17.0.17 with PID 11895 (/home/sam/SAM-PROYECTOS/Biblioteca-codigojava/target/classes started by sam in /home/sam/SAM-PROYECTOS/Biblioteca-codigojava)
2026-02-08T21:11:49.142+01:00  INFO 11895 --- [biblioteca] [           main] c.c.biblioteca.BibliotecaApplication     : No active profile set, falling back to 1 default profile: "default"
2026-02-08T21:11:50.700+01:00  INFO 11895 --- [biblioteca] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2026-02-08T21:11:50.844+01:00  INFO 11895 --- [biblioteca] [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 131 ms. Found 4 JPA repository interfaces.
2026-02-08T21:11:51.734+01:00  INFO 11895 --- [biblioteca] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 9800 (http)
2026-02-08T21:11:51.756+01:00  INFO 11895 --- [biblioteca] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2026-02-08T21:11:51.756+01:00  INFO 11895 --- [biblioteca] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.33]
2026-02-08T21:11:51.841+01:00  INFO 11895 --- [biblioteca] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2026-02-08T21:11:51.843+01:00  INFO 11895 --- [biblioteca] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 2511 ms
2026-02-08T21:11:52.113+01:00  INFO 11895 --- [biblioteca] [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2026-02-08T21:11:52.194+01:00  INFO 11895 --- [biblioteca] [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.5.3.Final
2026-02-08T21:11:52.244+01:00  INFO 11895 --- [biblioteca] [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2026-02-08T21:11:52.735+01:00  INFO 11895 --- [biblioteca] [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2026-02-08T21:11:52.779+01:00  INFO 11895 --- [biblioteca] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2026-02-08T21:11:53.331+01:00  INFO 11895 --- [biblioteca] [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@754de353
2026-02-08T21:11:53.337+01:00  INFO 11895 --- [biblioteca] [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2026-02-08T21:11:55.039+01:00  INFO 11895 --- [biblioteca] [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2026-02-08T21:11:55.044+01:00  INFO 11895 --- [biblioteca] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2026-02-08T21:11:55.673+01:00  WARN 11895 --- [biblioteca] [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2026-02-08T21:11:55.706+01:00  INFO 11895 --- [biblioteca] [           main] o.s.b.a.w.s.WelcomePageHandlerMapping    : Adding welcome page: class path resource [static/index.html]
2026-02-08T21:11:56.250+01:00  INFO 11895 --- [biblioteca] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 9800 (http) with context path '/'
2026-02-08T21:11:56.267+01:00  INFO 11895 --- [biblioteca] [           main] c.c.biblioteca.BibliotecaApplication     : Started BibliotecaApplication in 8.149 seconds (process running for 9.098)
```
El puerto de la aplicación es el 9800.  Para conocer los endpoints implementado, una vez arracanda la aplicación desde un navegador de internet acceder a: http://localhost:9800/docs/index.html
Esta documentación esta implementa con Redoc OpenAPI.

---

## Autores.
- **Sebastián Asunción** - **desarrollo.devsam@gmail.com**
- **Iván Toledo**
- **Mabel Cárdenas Fernández**


    NOTA:  Si revisa en profundidad el código de este proyecto verá que los estilos de programación pueden variar.

    Este proyecto colaboraron 3 desarrolladores con sus estilos y formas de programación en java.
