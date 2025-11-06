<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.Carrera" %>
<%@ page import="modelo.Facultad" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Carrera</title>
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

    <% 
        Carrera carrera = (Carrera) request.getAttribute("carrera");
        List<Facultad> listaFacultades = (List<Facultad>) request.getAttribute("listaFacultades");
        boolean esNuevo = (carrera == null);
    %>

    <h1><%= esNuevo ? "Registrar Nueva Carrera" : "Editar Carrera" %></h1>

    <form action="${pageContext.request.contextPath}/CarreraServlet" method="post">
        
        <% if (!esNuevo) { %>
            <input type="hidden" name="idcarrera" value="<%= carrera.getIdcarrera() %>">
            <input type="hidden" name="action" value="actualizar">
        <% } else { %>
            <input type="hidden" name="action" value="registrar">
        <% } %>

        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="<%= esNuevo ? "" : carrera.getNombre() %>" required>
        </div>

        <div class="form-group">
            <label for="facultad_idfacultad">Facultad:</label>
            <select id="facultad_idfacultad" name="facultad_idfacultad" required>
                <option value="">Seleccione una facultad</option>
                <% 
                    if (listaFacultades != null) {
                        for (Facultad facultad : listaFacultades) {
                            String selected = "";
                            if (!esNuevo && carrera.getFacultad() != null && carrera.getFacultad().getIdfacultad() == facultad.getIdfacultad()) {
                                selected = "selected";
                            }
                %>
                <option value="<%= facultad.getIdfacultad() %>" <%= selected %>><%= facultad.getNombre() %></option>
                <% 
                        }
                    }
                %>
            </select>
        </div>

        <input type="submit" value="<%= esNuevo ? "Registrar" : "Actualizar" %>">
    </form>

    <br>
    <div style="text-align: center;">
        <a href="${pageContext.request.contextPath}/CarreraServlet">Volver al Listado</a>
    </div>

</body>
</html>
