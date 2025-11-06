package controlador;

import facade.FacultadFacade;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Facultad;

import java.io.IOException;
import java.util.List;

@WebServlet("/FacultadServlet")
public class FacultadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    private FacultadFacade facultadFacade;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        try {
            switch (action) {
                case "registrar":
                    registrarFacultad(request, response);
                    break;
                case "actualizar":
                    actualizarFacultad(request, response);
                    break;
                default:
                    listarFacultades(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        try {
            switch (action) {
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "eliminar":
                    eliminarFacultad(request, response);
                    break;
                default:
                    listarFacultades(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarFacultades(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Facultad> listaFacultades = facultadFacade.findAll();
        request.setAttribute("listaFacultades", listaFacultades);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/facultades.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/facultadForm.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idfacultad = Integer.parseInt(request.getParameter("id"));
        Facultad facultadExistente = facultadFacade.find(idfacultad);
        request.setAttribute("facultad", facultadExistente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/facultadForm.jsp");
        dispatcher.forward(request, response);
    }

    private void registrarFacultad(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nombre = request.getParameter("nombre");
        Facultad nuevaFacultad = new Facultad();
        nuevaFacultad.setNombre(nombre);
        facultadFacade.create(nuevaFacultad);
        refreshFacultadesInContext(); // Refrescar la lista en el contexto
        response.sendRedirect(request.getContextPath() + "/FacultadServlet");
    }

    private void actualizarFacultad(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int idfacultad = Integer.parseInt(request.getParameter("idfacultad"));
        String nombre = request.getParameter("nombre");
        Facultad facultad = new Facultad(idfacultad);
        facultad.setNombre(nombre);
        facultadFacade.edit(facultad);
        refreshFacultadesInContext(); // Refrescar la lista en el contexto
        response.sendRedirect(request.getContextPath() + "/FacultadServlet");
    }

    private void eliminarFacultad(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int idfacultad = Integer.parseInt(request.getParameter("id"));
        Facultad facultad = facultadFacade.find(idfacultad);
        if (facultad != null) {
            try {
                facultadFacade.remove(facultad);
                refreshFacultadesInContext(); // Refrescar la lista en el contexto si la eliminación fue exitosa
            } catch (jakarta.ejb.EJBException e) {
                request.getSession().setAttribute("errorMessage", "No se puede eliminar la facultad porque tiene carreras asociadas. Elimine primero las carreras relacionadas.");
            }
        }
        response.sendRedirect(request.getContextPath() + "/FacultadServlet");
    }

    // Método auxiliar para refrescar la lista de facultades en el ServletContext
    private void refreshFacultadesInContext() {
        getServletContext().setAttribute("facultades", facultadFacade.findAll());
    }
}
