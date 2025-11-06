<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Exámenes</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
        h1 { color: #333; }
        table { width: 100%; border-collapse: collapse; background: #fff; box-shadow: 0 2px 4px rgba(0,0,0,0.1); margin-bottom: 20px; }
        th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }
        th { background-color: #007bff; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        a { color: #007bff; text-decoration: none; }
        a:hover { text-decoration: underline; }
        .actions a { margin-right: 10px; }
        .add-button { background-color: #28a745; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; text-decoration: none; display: inline-block; margin-bottom: 20px; }
        .add-button:hover { background-color: #218838; }
    </style>
</head>
<body>

    <h1>Gestión de Exámenes</h1>

    <a href="${pageContext.request.contextPath}/ExamenServlet?action=nuevo" class="add-button">Registrar Nuevo Examen</a>

    <table>
        <thead>
            <tr>
                <th>Alumno</th>
                <th>Materia</th>
                <th>Fecha</th>
                <th>Nota</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="examen" items="${listaExamenes}">
                <tr>
                    <td><c:out value="${examen.nombreAlumno}"/></td>
                    <td><c:out value="${examen.nombreMateria}"/></td>
                    <td><fmt:formatDate value="${examen.fecha}" pattern="dd/MM/yyyy HH:mm"/></td>
                    <td><c:out value="${examen.nota}"/></td>
                    <td class="actions">
                        <a href="${pageContext.request.contextPath}/ExamenServlet?action=editar&idalumno=${examen.alumno_idalumno}&idmateria=${examen.materia_idmateria}">Editar</a>
                        <a href="${pageContext.request.contextPath}/ExamenServlet?action=eliminar&idalumno=${examen.alumno_idalumno}&idmateria=${examen.materia_idmateria}" onclick="return confirm('¿Estás seguro de que deseas eliminar esta nota?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty listaExamenes}">
                <tr>
                    <td colspan="5">No hay exámenes registrados.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">Volver al Menú Principal</a>

</body>
</html>
