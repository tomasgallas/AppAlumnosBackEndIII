<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Docentes</title>
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
        .error-message { color: red; background-color: #ffe0e0; border: 1px solid red; padding: 10px; margin-bottom: 15px; border-radius: 5px; }
    </style>
</head>
<body>

    <c:if test="${not empty sessionScope.errorMessage}">
        <div class="error-message">
            <c:out value="${sessionScope.errorMessage}"/>
        </div>
        <c:remove var="errorMessage" scope="session"/>
    </c:if>

    <h1>Gestión de Docentes</h1>

    <a href="${pageContext.request.contextPath}/DocenteServlet?action=nuevo" class="add-button">Agregar Nuevo Docente</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Cargo</th>
                <th>Materias</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="docente" items="${listaDocentes}">
                <tr>
                    <td><c:out value="${docente.iddocente}"/></td>
                    <td><c:out value="${docente.nombre}"/></td>
                    <td><c:out value="${docente.cargo}"/></td>
                    <td>
                        <c:forEach var="materia" items="${docente.materias}" varStatus="loop">
                            <c:out value="${materia.nombre}"/>${!loop.last ? ', ' : ''}
                        </c:forEach>
                    </td>
                    <td class="actions">
                        <a href="${pageContext.request.contextPath}/DocenteServlet?action=editar&id=${docente.iddocente}">Editar</a>
                        <a href="${pageContext.request.contextPath}/DocenteServlet?action=eliminar&id=${docente.iddocente}" onclick="return confirm('¿Estás seguro de que deseas eliminar este docente?')">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty listaDocentes}">
                <tr>
                    <td colspan="5">No hay docentes registrados.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">Volver al Menú Principal</a>

</body>
</html>
