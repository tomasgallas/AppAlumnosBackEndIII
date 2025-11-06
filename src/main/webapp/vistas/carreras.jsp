<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Carreras</title>
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

    <c:choose>
        <%-- CASO 1: Se está mostrando las carreras de UNA facultad (desde el menú dinámico) --%>
        <c:when test="${not empty facultad}">
            <h1>Carreras de la Facultad: <c:out value="${facultad.nombre}"/></h1>
            
            <a href="${pageContext.request.contextPath}/CarreraServlet?action=nuevo" class="add-button">Agregar Nueva Carrera</a>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="carrera" items="${facultad.carreraList}">
                        <tr>
                            <td><c:out value="${carrera.idcarrera}"/></td>
                            <td><c:out value="${carrera.nombre}"/></td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/CarreraServlet?action=editar&id=${carrera.idcarrera}">Editar</a>
                                <a href="${pageContext.request.contextPath}/CarreraServlet?action=eliminar&id=${carrera.idcarrera}" onclick="return confirm('¿Estás seguro de que deseas eliminar esta carrera?')">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty facultad.carreraList}">
                        <tr>
                            <td colspan="3">No hay carreras registradas para esta facultad.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </c:when>

        <%-- CASO 2: Se están mostrando TODAS las carreras (desde el menú de gestión) --%>
        <c:otherwise>
            <h1>Gestión de Carreras</h1>

            <a href="${pageContext.request.contextPath}/CarreraServlet?action=nuevo" class="add-button">Agregar Nueva Carrera</a>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Facultad</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="carreraVM" items="${listaCarreras}">
                        <tr>
                            <td><c:out value="${carreraVM.idcarrera}"/></td>
                            <td><c:out value="${carreraVM.nombre}"/></td>
                            <td><c:out value="${carreraVM.nombreFacultad}"/></td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/CarreraServlet?action=editar&id=${carreraVM.idcarrera}">Editar</a>
                                <a href="${pageContext.request.contextPath}/CarreraServlet?action=eliminar&id=${carreraVM.idcarrera}" onclick="return confirm('¿Estás seguro de que deseas eliminar esta carrera?')">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                     <c:if test="${empty listaCarreras}">
                        <tr>
                            <td colspan="4">No hay carreras registradas.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

    <br>
    <a href="${pageContext.request.contextPath}/index.jsp">Volver al Menú Principal</a>

</body>
</html>
