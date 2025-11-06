<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte: Alumnos sin Exámenes (Año 2025)</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
        h1 { color: #333; }
        table { width: 100%; border-collapse: collapse; background: #fff; box-shadow: 0 2px 4px rgba(0,0,0,0.1); margin-bottom: 20px; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #007bff; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        a { color: #007bff; text-decoration: none; }
        a:hover { text-decoration: underline; }
        .add-button { background-color: #28a745; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; display: inline-block; margin-bottom: 20px; }
        .add-button:hover { background-color: #218838; }
    </style>
</head>
<body>

    <h1>Reporte: Alumnos sin Exámenes (Año 2025)</h1>

    <table>
        <thead>
            <tr>
                <th>ID Alumno</th>
                <th>Nombre</th>
                <th>Registro</th>
                <th>Carrera</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="alumno" items="${alumnosSinExamenes}">
                <tr>
                    <td><c:out value="${alumno.idalumno}"/></td>
                    <td><c:out value="${alumno.nombre}"/></td>
                    <td><c:out value="${alumno.registro}"/></td>
                    <td><c:out value="${alumno.carrera.nombre}"/></td>
                </tr>
            </c:forEach>
            <c:if test="${empty alumnosSinExamenes}">
                <tr>
                    <td colspan="4">No se encontraron alumnos sin exámenes en 2025.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/vistas/consultasJPQLEJ6.jsp">Volver a Consultas JPQL</a>

</body>
</html>
