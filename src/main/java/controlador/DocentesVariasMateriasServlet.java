package controlador;

import facade.DocenteFacade;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Docente;

import java.io.IOException;
import java.util.List;

@WebServlet("/DocentesVariasMateriasServlet")
public class DocentesVariasMateriasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private DocenteFacade docenteFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Docente> docentes = docenteFacade.findDocentesConMasDeDosMaterias();
        request.setAttribute("docentesVariasMaterias", docentes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/docentesVariasMaterias.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
