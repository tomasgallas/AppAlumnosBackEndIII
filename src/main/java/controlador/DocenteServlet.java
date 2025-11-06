package controlador;

import facade.DocenteFacade;
import facade.MateriaFacade;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Docente;
import modelo.Materia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/DocenteServlet")
public class DocenteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    private DocenteFacade docenteFacade;
    
    @EJB
    private MateriaFacade materiaFacade;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        try {
            switch (action) {
                case "registrar":
                    registrarDocente(request, response);
                    break;
                case "actualizar":
                    actualizarDocente(request, response);
                    break;
                default:
                    listarDocentes(request, response);
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
                    eliminarDocente(request, response);
                    break;
                default:
                    listarDocentes(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarDocentes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Docente> listaDocentes = docenteFacade.findAll();
        request.setAttribute("listaDocentes", listaDocentes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/docentes.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Materia> listaMaterias = materiaFacade.findAll();
        request.setAttribute("listaMaterias", listaMaterias);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/docenteForm.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int iddocente = Integer.parseInt(request.getParameter("id"));
        Docente docenteExistente = docenteFacade.find(iddocente);
        List<Materia> listaMaterias = materiaFacade.findAll();
        request.setAttribute("docente", docenteExistente);
        request.setAttribute("listaMaterias", listaMaterias);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/docenteForm.jsp");
        dispatcher.forward(request, response);
    }

    private void registrarDocente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nombre = request.getParameter("nombre");
        String cargo = request.getParameter("cargo");
        String[] materiasSeleccionadasIds = request.getParameterValues("materias");

        Docente nuevoDocente = new Docente();
        nuevoDocente.setNombre(nombre);
        nuevoDocente.setCargo(cargo);
        
        List<Materia> materias = new ArrayList<>();
        if (materiasSeleccionadasIds != null) {
            for (String id : materiasSeleccionadasIds) {
                materias.add(materiaFacade.find(Integer.parseInt(id)));
            }
        }
        nuevoDocente.setMaterias(materias);

        docenteFacade.create(nuevoDocente);
        response.sendRedirect(request.getContextPath() + "/DocenteServlet");
    }

    private void actualizarDocente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int iddocente = Integer.parseInt(request.getParameter("iddocente"));
        String nombre = request.getParameter("nombre");
        String cargo = request.getParameter("cargo");
        String[] materiasSeleccionadasIds = request.getParameterValues("materias");

        Docente docente = docenteFacade.find(iddocente);
        if (docente != null) {
            docente.setNombre(nombre);
            docente.setCargo(cargo);
            
            List<Materia> materias = new ArrayList<>();
            if (materiasSeleccionadasIds != null) {
                for (String id : materiasSeleccionadasIds) {
                    materias.add(materiaFacade.find(Integer.parseInt(id)));
                }
            }
            docente.setMaterias(materias);

            docenteFacade.edit(docente);
        }
        response.sendRedirect(request.getContextPath() + "/DocenteServlet");
    }

    private void eliminarDocente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int iddocente = Integer.parseInt(request.getParameter("id"));
        Docente docente = docenteFacade.find(iddocente);
        if (docente != null) {
            try {
                docenteFacade.remove(docente);
            } catch (jakarta.ejb.EJBException e) {
                request.getSession().setAttribute("errorMessage", "No se puede eliminar el docente porque tiene materias asociadas. Elimine primero las materias relacionadas.");
            }
        }
        response.sendRedirect(request.getContextPath() + "/DocenteServlet");
    }
}
