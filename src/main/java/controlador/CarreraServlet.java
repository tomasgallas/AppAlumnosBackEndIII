package controlador;

import facade.CarreraFacade;
import facade.FacultadFacade;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Carrera;
import modelo.Facultad;
import modelo.viewmodel.CarreraViewModel;

import java.io.IOException;
import java.util.List;

@WebServlet("/CarreraServlet")
public class CarreraServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    private CarreraFacade carreraFacade;
    
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
                    registrarCarrera(request, response);
                    break;
                case "actualizar":
                    actualizarCarrera(request, response);
                    break;
                default:
                    listarCarreras(request, response);
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
                    eliminarCarrera(request, response);
                    break;
                default:
                    listarCarreras(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarCarreras(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CarreraViewModel> listaCarreras = carreraFacade.consultar();
        request.setAttribute("listaCarreras", listaCarreras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/carreras.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Facultad> listaFacultades = facultadFacade.findAll();
        request.setAttribute("listaFacultades", listaFacultades);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/carreraForm.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idcarrera = Integer.parseInt(request.getParameter("id"));
        Carrera carreraExistente = carreraFacade.find(idcarrera);
        List<Facultad> listaFacultades = facultadFacade.findAll();
        request.setAttribute("carrera", carreraExistente);
        request.setAttribute("listaFacultades", listaFacultades);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/carreraForm.jsp");
        dispatcher.forward(request, response);
    }

    private void registrarCarrera(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nombre = request.getParameter("nombre");
        int idfacultad = Integer.parseInt(request.getParameter("facultad_idfacultad"));
        
        Facultad facultad = facultadFacade.find(idfacultad);
        
        Carrera nuevaCarrera = new Carrera();
        nuevaCarrera.setNombre(nombre);
        nuevaCarrera.setFacultad(facultad);
        
        carreraFacade.create(nuevaCarrera);
        response.sendRedirect(request.getContextPath() + "/CarreraServlet");
    }

    private void actualizarCarrera(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int idcarrera = Integer.parseInt(request.getParameter("idcarrera"));
        String nombre = request.getParameter("nombre");
        int idfacultad = Integer.parseInt(request.getParameter("facultad_idfacultad"));
        
        Facultad facultad = facultadFacade.find(idfacultad);
        
        Carrera carrera = new Carrera(idcarrera);
        carrera.setNombre(nombre);
        carrera.setFacultad(facultad);

        carreraFacade.edit(carrera);
        response.sendRedirect(request.getContextPath() + "/CarreraServlet");
    }

    private void eliminarCarrera(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int idcarrera = Integer.parseInt(request.getParameter("id"));
        Carrera carrera = carreraFacade.find(idcarrera);
        if (carrera != null) {
            try {
                carreraFacade.remove(carrera);
            } catch (jakarta.ejb.EJBException e) {
                request.getSession().setAttribute("errorMessage", "No se puede eliminar la carrera porque tiene alumnos asociados. Elimine primero los alumnos relacionados.");
            }
        }
        response.sendRedirect(request.getContextPath() + "/CarreraServlet");
    }
}
