<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.Examen" %>
<%@ page import="modelo.Alumno" %>
<%@ page import="modelo.Materia" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Examen</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f4; }
        h1 { color: #333; }
        form { background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); max-width: 500px; margin: auto; }
        form input, form select { width: 100%; padding: 8px; margin-bottom: 10px; border-radius: 4px; border: 1px solid #ddd; box-sizing: border-box; }
        form input[type='submit'] { background-color: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }
        form input[type='submit']:hover { background-color: #0056b3; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
    </style>
</head>
<body>

    <% 
        Examen examen = (Examen) request.getAttribute("examen");
        List<Alumno> listaAlumnos = (List<Alumno>) request.getAttribute("listaAlumnos");
        List<Materia> listaMaterias = (List<Materia>) request.getAttribute("listaMaterias");
        boolean esNuevo = (examen == null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        // Estos objetos son provistos por el Servlet en modo edición
        Alumno alumnoActual = (Alumno) request.getAttribute("alumnoActual");
        Materia materiaActual = (Materia) request.getAttribute("materiaActual");
    %>

    <h1><%= esNuevo ? "Registrar Nuevo Examen" : "Editar Examen" %></h1>

    <form action="${pageContext.request.contextPath}/ExamenServlet" method="post">
        
        <% if (!esNuevo) { %>
            <input type="hidden" name="materia_idmateria" value="<%= examen.getExamenPK().getMateriaIdmateria() %>">
            <input type="hidden" name="alumno_idalumno" value="<%= examen.getExamenPK().getAlumnoIdalumno() %>">
            <input type="hidden" name="action" value="actualizar">
        <% } else { %>
            <input type="hidden" name="action" value="registrar">
        <% } %>

        <div class="form-group">
            <label for="alumno_idalumno">Alumno:</label>
            <select id="alumno_idalumno" name="alumno_idalumno" required <%= !esNuevo ? "disabled" : "" %>>
                <% if (esNuevo) { %>
                    <option value="">Seleccione un alumno</option>
                    <% 
                        if (listaAlumnos != null) {
                            for (Alumno alumno : listaAlumnos) {
                    %>
                    <option value="<%= alumno.getIdalumno() %>"><%= alumno.getNombre() %></option>
                    <% 
                            }
                        }
                    %>
                <% } else { 
                    // En modo edición, mostramos el alumno pero no permitimos cambiarlo.
                %>
                    <option value="<%= alumnoActual.getIdalumno() %>" selected><%= alumnoActual.getNombre() %></option>
                <% } %>
            </select>
        </div>

        <div class="form-group">
            <label for="materia_idmateria">Materia:</label>
            <select id="materia_idmateria" name="materia_idmateria" required <%= !esNuevo ? "disabled" : "" %>>
                 <% if (esNuevo) { %>
                    <option value="">Seleccione una materia</option>
                    <% 
                        if (listaMaterias != null) {
                            for (Materia materia : listaMaterias) {
                    %>
                    <option value="<%= materia.getIdmateria() %>"><%= materia.getNombre() %></option>
                    <% 
                            }
                        }
                    %>
                <% } else { 
                    // En modo edición, mostramos la materia pero no permitimos cambiarla.
                %>
                    <option value="<%= materiaActual.getIdmateria() %>" selected><%= materiaActual.getNombre() %></option>
                <% } %>
            </select>
        </div>

        <div class="form-group">
            <label for="fecha">Fecha y Hora:</label>
            <input type="datetime-local" id="fecha" name="fecha" value="<%= esNuevo ? "" : sdf.format(examen.getFecha()) %>" required>
        </div>

        <div class="form-group">
            <label for="nota">Nota:</label>
            <input type="number" id="nota" name="nota" min="0" max="10" value="<%= esNuevo ? "" : examen.getNota() %>" required>
        </div>

        <input type="submit" value="<%= esNuevo ? "Registrar" : "Actualizar" %>">
    </form>

    <br>
    <div style="text-align: center;">
        <a href="${pageContext.request.contextPath}/ExamenServlet">Volver al Listado</a>
    </div>

</body>
</html>
