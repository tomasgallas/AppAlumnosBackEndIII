<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Docente</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
        h1 { color: #333; }
        form { background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); max-width: 500px; margin: auto; }
        form input[type='text'], form select { width: 100%; padding: 8px; margin-bottom: 10px; border-radius: 4px; border: 1px solid #ddd; box-sizing: border-box; }
        form input[type='submit'] { background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }
        form input[type='submit']:hover { background-color: #0056b3; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
    </style>
</head>
<body>

    <c:set var="esNuevo" value="${empty docente.iddocente}"/>

    <h1><c:out value="${esNuevo ? 'Registrar Nuevo Docente' : 'Editar Docente'}"/></h1>

    <form action="${pageContext.request.contextPath}/DocenteServlet" method="post">
        
        <c:if test="${!esNuevo}">
            <input type="hidden" name="iddocente" value="<c:out value='${docente.iddocente}'/>">
        </c:if>
        <input type="hidden" name="action" value="${esNuevo ? 'registrar' : 'actualizar'}">

        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="<c:out value='${docente.nombre}'/>" required>
        </div>

        <div class="form-group">
            <label for="cargo">Cargo:</label>
            <select id="cargo" name="cargo" required>
                <option value="">Seleccione un cargo</option>
                <option value="TITULAR" ${docente.cargo == 'TITULAR' ? 'selected' : ''}>Titular</option>
                <option value="ADJUNTO" ${docente.cargo == 'ADJUNTO' ? 'selected' : ''}>Adjunto</option>
                <option value="JTP" ${docente.cargo == 'JTP' ? 'selected' : ''}>Jefe de Trabajos Prácticos</option>
            </select>
        </div>

        <div class="form-group">
            <label for="materias">Materias:</label>
            <select id="materias" name="materias" multiple="multiple" style="height: 150px;">
                <c:forEach var="materia" items="${listaMaterias}">
                    <c:set var="isSelected" value="false"/>
                    <c:forEach var="docenteMateria" items="${docente.materias}">
                        <c:if test="${docenteMateria.idmateria == materia.idmateria}">
                            <c:set var="isSelected" value="true"/>
                        </c:if>
                    </c:forEach>
                    <option value="${materia.idmateria}" ${isSelected ? 'selected' : ''}>
                        <c:out value="${materia.nombre}"/>
                    </option>
                </c:forEach>
            </select>
        </div>

        <input type="submit" value="${esNuevo ? 'Registrar' : 'Actualizar'}">
    </form>

    <br>
    <div style="text-align: center;">
        <a href="${pageContext.request.contextPath}/DocenteServlet">Volver al Listado</a>
    </div>

</body>
</html>
