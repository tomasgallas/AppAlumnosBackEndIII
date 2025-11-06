# AppTomas Maven - Gestión Académica

Este proyecto es una aplicación web de gestión académica desarrollada con tecnologías Jakarta EE (anteriormente Java EE), utilizando Maven como herramienta de construcción y JPA/Hibernate para la persistencia de datos.

## Descripción del Proyecto

La aplicación permite la gestión de diversas entidades académicas como Materias, Facultades, Carreras, Alumnos, Exámenes y Docentes. Incluye funcionalidades CRUD (Crear, Leer, Actualizar, Eliminar) para cada entidad y reportes específicos generados mediante consultas JPQL.

## Funcionalidades Implementadas

-   **Gestión de Entidades (CRUD completo):**
    -   Materias
    -   Facultades
    -   Carreras
    -   Alumnos
    -   Exámenes
    -   Docentes (incluyendo asignación de múltiples materias)
-   **Menú Dinámico de Facultades:** El menú principal muestra las facultades cargadas dinámicamente desde la base de datos, permitiendo navegar a las carreras de cada facultad.
-   **Manejo de Errores en Eliminación:** Mensajes amigables al usuario cuando se intenta eliminar una entidad con relaciones asociadas (ej. no se puede eliminar una Facultad con Carreras).
-   **Reportes JPQL (Ejercicio 6):**
    1.  Listado de mesas de examen del turno de julio (año 2025), mostrando nombre del alumno y nota.
    2.  Listado de alumnos que no han rendido ninguna materia durante el año 2025.
    3.  Listado de docentes que dictan más de dos materias.

## Tecnologías Utilizadas

-   **Lenguaje:** Java
-   **Plataforma:** Jakarta EE (anteriormente Java EE)
-   **Gestión de Proyectos:** Apache Maven
-   **Persistencia:** JPA (Jakarta Persistence API) con Hibernate como proveedor.
-   **Componentes EJB:** Enterprise JavaBeans (Stateless Session Beans) para la lógica de negocio y acceso a datos (Facades).
-   **Servlets:** Para el control de las peticiones HTTP y la lógica de la aplicación web.
-   **Vistas:** JSP (JavaServer Pages) y JSTL (JSP Standard Tag Library) para la interfaz de usuario.
-   **Base de Datos:** MySQL
-   **Servidor de Aplicaciones:** GlassFish Server

## Configuración e Instalación

### Prerrequisitos

-   JDK (Java Development Kit) 11 o superior.
-   Apache Maven 3.6.0 o superior.
-   GlassFish Server 6 o superior.
-   Servidor MySQL.
-   MySQL Workbench (o cliente SQL de preferencia).

### 1. Configuración de la Base de Datos

1.  Crea una base de datos llamada `bdalumnos` en tu servidor MySQL.

### 2. Configuración en GlassFish Server

1.  Accede a la consola de administración de GlassFish (normalmente en `http://localhost:4848`).
2.  **Crear JDBC Connection Pool:**
    -   Ve a `Resources > JDBC > JDBC Connection Pools`.
    -   Haz clic en `New...`.
    -   `Pool Name`: `bdalumnosPool`
    -   `Resource Type`: `javax.sql.DataSource`
    -   `Database Driver Vendor`: `MySQL`
    -   Haz clic en `Next`.
    -   En `Additional Properties`, configura:
        -   `User`: `tu_usuario_mysql`
        -   `Password`: `tu_password_mysql`
        -   `URL`: `jdbc:mysql://localhost:3306/bdalumnos?zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=UTC`
        -   `Datasource Classname`: `com.mysql.cj.jdbc.MysqlDataSource`
    -   Haz clic en `Finish`.
3.  **Crear JDBC Resource:**
    -   Ve a `Resources > JDBC > JDBC Resources`.
    -   Haz clic en `New...`.
    -   `JNDI Name`: `jdbc/bdalumnos`
    -   `Pool Name`: `bdalumnosPool` (el que acabas de crear)
    -   Haz clic en `OK`.

### 3. Despliegue del Proyecto

1.  Abre el proyecto en tu IDE (NetBeans, IntelliJ IDEA, Eclipse).
2.  Realiza un `Clean and Build` del proyecto Maven.
3.  Despliega el archivo `.war` generado (ubicado en `target/AppTomasMaven-1.0-SNAPSHOT.war`) en tu servidor GlassFish.

## Uso de la Aplicación

Una vez desplegada, accede a la aplicación a través de la URL de tu servidor GlassFish (ej. `http://localhost:8080/AppTomasMaven-1.0-SNAPSHOT/`).

Desde el menú principal podrás:
-   Gestionar (CRUD) cada una de las entidades.
-   Navegar por las facultades y ver sus carreras asociadas.
-   Acceder a los reportes JPQL.

## Estructura del Proyecto

-   `src/main/java/modelo`: Clases de entidad JPA (Alumno, Carrera, Docente, Examen, Facultad, Materia).
-   `src/main/java/modelo/viewmodel`: Clases ViewModel para la presentación de datos en las vistas.
-   `src/main/java/facade`: Enterprise JavaBeans (EJBs) que actúan como Facades para la lógica de negocio y acceso a datos, extendiendo `AbstractFacade`.
-   `src/main/java/controlador`: Servlets que manejan las peticiones web y orquestan la lógica de negocio.
-   `src/main/webapp/vistas`: Archivos JSP para la interfaz de usuario.
-   `src/main/resources/META-INF/persistence.xml`: Configuración de JPA.
