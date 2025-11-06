package controlador;

import facade.AlumnoFacade;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Alumno;

import java.io.IOException;
import java.util.List;

@WebServlet("/AlumnosSinExamenesServlet")
public class AlumnosSinExamenesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private AlumnoFacade alumnoFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Alumno> alumnosSinExamenes = alumnoFacade.findAlumnosSinExamenes();
        request.setAttribute("alumnosSinExamenes", alumnosSinExamenes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/alumnosSinExamenes.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
