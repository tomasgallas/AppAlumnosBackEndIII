<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Alumno</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
        h1 { color: #333; }
        form { background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); max-width: 500px; margin: auto; }
        form input[type='text'], form input[type='number'], form select { width: 100%; padding: 8px; margin-bottom: 10px; border-radius: 4px; border: 1px solid #ddd; box-sizing: border-box; }
        form input[type='submit'] { background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }
        form input[type='submit']:hover { background-color: #0056b3; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
    </style>
</head>
<body>

    <c:set var="esNuevo" value="${empty alumno.idalumno}"/>

    <h1><c:out value="${esNuevo ? 'Registrar Nuevo Alumno' : 'Editar Alumno'}"/></h1>

    <form action="${pageContext.request.contextPath}/AlumnoServlet" method="post">
        
        <c:choose>
            <c:when test="${!esNuevo}">
                <input type="hidden" name="idalumno" value="<c:out value='${alumno.idalumno}'/>">
                <input type="hidden" name="action" value="actualizar">
            </c:when>
            <c:otherwise>
                <input type="hidden" name="action" value="registrar">
            </c:otherwise>
        </c:choose>

        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="<c:out value='${alumno.nombre}'/>" required>
        </div>

        <div class="form-group">
            <label for="registro">Registro:</label>
            <input type="number" id="registro" name="registro" value="<c:out value='${alumno.registro}'/>" required>
        </div>

        <div class="form-group">
            <label for="carrera_idcarrera">Carrera:</label>
            <select id="carrera_idcarrera" name="carrera_idcarrera" required>
                <option value="">Seleccione una carrera</option>
                <c:forEach var="carrera" items="${listaCarreras}">
                    <c:set var="selected" value="${!esNuevo && alumno.carrera.idcarrera == carrera.idcarrera ? 'selected' : ''}"/>
                    <option value="${carrera.idcarrera}" ${selected}><c:out value="${carrera.nombre}"/></option>
                </c:forEach>
            </select>
        </div>

        <input type="submit" value="${esNuevo ? 'Registrar' : 'Actualizar'}">
    </form>

    <br>
    <div style="text-align: center;">
        <a href="${pageContext.request.contextPath}/AlumnoServlet">Volver al Listado</a>
    </div>

</body>
</html>
