<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.Materia" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Materia</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
        h1 { color: #333; }
        form { background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); max-width: 500px; margin: auto; }
        form input[type='text'] { width: 95%; padding: 8px; margin-bottom: 10px; border-radius: 4px; border: 1px solid #ddd; }
        form input[type='submit'] { background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }
        form input[type='submit']:hover { background-color: #0056b3; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
    </style>
</head>
<body>

    <% 
        Materia materia = (Materia) request.getAttribute("materia");
        boolean esNuevo = (materia == null);
    %>

    <h1><%= esNuevo ? "Registrar Nueva Materia" : "Editar Materia" %></h1>

    <form action="${pageContext.request.contextPath}/MateriaServlet" method="post">
        
        <%-- Si es una edición, necesitamos enviar el ID y la acción "actualizar" --%>
        <% if (!esNuevo) { %>
            <input type="hidden" name="idmateria" value="<%= materia.getIdmateria() %>">
            <input type="hidden" name="action" value="actualizar">
        <% } else { %>
            <input type="hidden" name="action" value="registrar">
        <% } %>

        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="<%= esNuevo ? "" : materia.getNombre() %>" required>
        </div>

        <input type="submit" value="<%= esNuevo ? "Registrar" : "Actualizar" %>">
    </form>

    <br>
    <div style="text-align: center;">
        <a href="${pageContext.request.contextPath}/MateriaServlet">Volver al Listado</a>
    </div>

</body>
</html>
