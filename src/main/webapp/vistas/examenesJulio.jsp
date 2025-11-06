<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte: Exámenes de Julio</title>
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

    <h1>Reporte: Exámenes de Julio</h1>

    <table>
        <thead>
            <tr>
                <th>Nombre del Alumno</th>
                <th>Nota Obtenida</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="examen" items="${examenesJulio}">
                <tr>
                    <td><c:out value="${examen.alumnoNombre}"/></td>
                    <td><c:out value="${examen.nota}"/></td>
                </tr>
            </c:forEach>
            <c:if test="${empty examenesJulio}">
                <tr>
                    <td colspan="2">No se encontraron exámenes en julio de 2025.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">Volver al Menú Principal</a>

</body>
</html>
