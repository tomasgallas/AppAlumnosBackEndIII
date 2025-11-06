<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consultas JPQL (Ejercicio 6)</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
        h1 { color: #333; }
        ul { list-style-type: none; padding: 0; }
        li { margin: 12px 0; }
        a { text-decoration: none; color: #007bff; font-size: 1.2em; transition: color 0.2s; }
        a:hover { color: #0056b3; text-decoration: underline; }
    </style>
</head>
<body>

    <h1>Consultas JPQL (Ejercicio 6)</h1>

    <h2>Reportes Disponibles:</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/ExamenesJulioServlet">1. Exámenes de Julio (Nombre Alumno y Nota)</a></li>
        <li><a href="${pageContext.request.contextPath}/AlumnosSinExamenesServlet">2. Alumnos sin Exámenes (Año 2025)</a></li>
        <li><a href="${pageContext.request.contextPath}/DocentesVariasMateriasServlet">3. Docentes con más de dos Materias</a></li>
    </ul>

    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">Volver al Menú Principal</a>

</body>
</html>
