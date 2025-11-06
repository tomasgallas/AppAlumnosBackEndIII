package controlador;

import facade.ExamenFacade;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.viewmodel.ExamenJulioViewModel;

import java.io.IOException;
import java.util.List;

@WebServlet("/ExamenesJulioServlet")
public class ExamenesJulioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ExamenFacade examenFacade;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ExamenJulioViewModel> examenesJulio = examenFacade.findExamenesJulio();
        request.setAttribute("examenesJulio", examenesJulio);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/examenesJulio.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
