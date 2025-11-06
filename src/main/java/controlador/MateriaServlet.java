package controlador;

import facade.MateriaFacade;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Materia;

import java.io.IOException;
import java.util.List;

@WebServlet("/MateriaServlet")
public class MateriaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
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
                    registrarMateria(request, response);
                    break;
                case "actualizar":
                    actualizarMateria(request, response);
                    break;
                default:
                    listarMaterias(request, response);
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
                    eliminarMateria(request, response);
                    break;
                default:
                    listarMaterias(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarMaterias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Materia> listaMaterias = materiaFacade.findAll();
        request.setAttribute("listaMaterias", listaMaterias);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/materias.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/materiaForm.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idmateria = Integer.parseInt(request.getParameter("id"));
        Materia materiaExistente = materiaFacade.find(idmateria);
        request.setAttribute("materia", materiaExistente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/materiaForm.jsp");
        dispatcher.forward(request, response);
    }

    private void registrarMateria(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nombre = request.getParameter("nombre");
        Materia nuevaMateria = new Materia();
        nuevaMateria.setNombre(nombre);
        materiaFacade.create(nuevaMateria);
        response.sendRedirect(request.getContextPath() + "/MateriaServlet");
    }

    private void actualizarMateria(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int idmateria = Integer.parseInt(request.getParameter("idmateria"));
        String nombre = request.getParameter("nombre");
        Materia materia = new Materia(idmateria);
        materia.setNombre(nombre);
        materiaFacade.edit(materia);
        response.sendRedirect(request.getContextPath() + "/MateriaServlet");
    }

    private void eliminarMateria(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int idmateria = Integer.parseInt(request.getParameter("id"));
        Materia materia = materiaFacade.find(idmateria);
        if (materia != null) {
            try {
                materiaFacade.remove(materia);
            } catch (jakarta.ejb.EJBException e) {
                // Capturar la excepción de integridad referencial
                request.getSession().setAttribute("errorMessage", "No se puede eliminar la materia porque tiene exámenes o docentes asociados. Elimine primero los elementos relacionados.");
            }
        }
        response.sendRedirect(request.getContextPath() + "/MateriaServlet");
    }
}
