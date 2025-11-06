<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte: Docentes con más de dos Materias</title>
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

    <h1>Reporte: Docentes con más de dos Materias</h1>

    <table>
        <thead>
            <tr>
                <th>ID Docente</th>
                <th>Nombre</th>
                <th>Cargo</th>
                <th>Materias</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="docente" items="${docentesVariasMaterias}">
                <tr>
                    <td><c:out value="${docente.iddocente}"/></td>
                    <td><c:out value="${docente.nombre}"/></td>
                    <td><c:out value="${docente.cargo}"/></td>
                    <td>
                        <c:forEach var="materia" items="${docente.materias}" varStatus="loop">
                            <c:out value="${materia.nombre}"/>${!loop.last ? ', ' : ''}
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty docentesVariasMaterias}">
                <tr>
                    <td colspan="4">No se encontraron docentes que dicten más de dos materias.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/vistas/consultasJPQLEJ6.jsp">Volver a Consultas JPQL</a>

</body>
</html>
