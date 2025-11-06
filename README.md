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

### Prerrequisitos

-   JDK (Java Development Kit) 11 o superior.
-   Apache Maven 3.6.0 o superior.
-   GlassFish Server 6 o superior.
-   Servidor MySQL.
-   MySQL Workbench (o cliente SQL de preferencia).

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
