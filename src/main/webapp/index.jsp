<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión Académica</title>
        <style>
            body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; background-color: #e9ecef; color: #343a40; display: flex; justify-content: center; align-items: center; min-height: 100vh; }
            .container { background-color: #ffffff; padding: 30px; border-radius: 10px; box-shadow: 0 8px 16px rgba(0,0,0,0.1); max-width: 900px; width: 100%; text-align: center; }
            h1 { color: #0056b3; margin-bottom: 20px; font-size: 2.5em; }
            h2 { color: #007bff; margin-top: 30px; margin-bottom: 15px; font-size: 1.8em; border-bottom: 2px solid #007bff; padding-bottom: 5px; display: inline-block; }
            ul { list-style-type: none; padding: 0; margin: 0; display: flex; flex-wrap: wrap; justify-content: center; gap: 20px; }
            li { margin: 10px; background-color: #f8f9fa; border: 1px solid #dee2e6; border-radius: 8px; transition: all 0.3s ease; flex: 1 1 calc(33% - 40px); min-width: 250px; }
            li:hover { transform: translateY(-5px); box-shadow: 0 12px 24px rgba(0,0,0,0.15); }
            a { text-decoration: none; color: #007bff; font-size: 1.3em; padding: 15px 20px; display: block; border-radius: 8px; transition: color 0.3s ease; }
            a:hover { color: #0056b3; background-color: #e2e6ea; }
            .menu-category { font-weight: bold; color: #495057; font-size: 1.5em; margin-bottom: 10px; display: block; padding: 10px 0; border-bottom: 1px solid #ced4da; }
            .submenu { list-style-type: none; padding: 0; margin-top: 10px; display: flex; flex-direction: column; gap: 5px; }
            .submenu li { background-color: #e9ecef; border: none; margin: 0; padding: 0; flex: none; min-width: auto; }
            .submenu li a { font-size: 1em; padding: 8px 15px; color: #212529; }
            .submenu li a:hover { background-color: #d6d8db; color: #000; }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Gestión Académica (Maven + JPA)</h1>
            
            <h2>Menú Principal</h2>
            <ul>
                <li>
                    <span class="menu-category">Gestión</span>
                    <ul class="submenu">
                        <li><a href="${pageContext.request.contextPath}/MateriaServlet">Gestionar Materias</a></li>
                        <li><a href="${pageContext.request.contextPath}/FacultadServlet">Gestionar Facultades</a></li>
                        <li><a href="${pageContext.request.contextPath}/CarreraServlet">Gestionar Carreras</a></li>
                        <li><a href="${pageContext.request.contextPath}/AlumnoServlet">Gestionar Alumnos</a></li>
                        <li><a href="${pageContext.request.contextPath}/ExamenServlet">Gestionar Exámenes</a></li>
                        <li><a href="${pageContext.request.contextPath}/DocenteServlet">Gestionar Docentes</a></li>
                    </ul>
                </li>
                <li>
                    <span class="menu-category">Navegación</span>
                    <ul class="submenu">
                        <li>
                            <span class="menu-header">Facultades (Dinámico)</span>
                            <ul class="submenu">
                                <c:forEach var="facu" items="${facultades}">
                                    <li><a href="Facultad?codigoFacu=${facu.idfacultad}">${facu.nombre}</a></li>
                                </c:forEach>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li>
                    <span class="menu-category">Reportes</span>
                    <ul class="submenu">
                        <li><a href="${pageContext.request.contextPath}/vistas/consultasJPQLEJ6.jsp">Consultas JPQL (Ejercicio 6)</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </body>
</html>
