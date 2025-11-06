package controlador;

import facade.AlumnoFacade;
import facade.CarreraFacade;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Alumno;
import modelo.Carrera;
import modelo.viewmodel.AlumnoViewModel;

import java.io.IOException;
import java.util.List;

@WebServlet("/AlumnoServlet")
public class AlumnoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    private AlumnoFacade alumnoFacade;
    
    @EJB
    private CarreraFacade carreraFacade;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        try {
            switch (action) {
                case "registrar":
                    registrarAlumno(request, response);
                    break;
                case "actualizar":
                    actualizarAlumno(request, response);
                    break;
                default:
                    listarAlumnos(request, response);
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
                    eliminarAlumno(request, response);
                    break;
                default:
                    listarAlumnos(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarAlumnos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<AlumnoViewModel> listaAlumnos = alumnoFacade.consultar();
        request.setAttribute("listaAlumnos", listaAlumnos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/alumnos.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Carrera> listaCarreras = carreraFacade.findAll();
        request.setAttribute("listaCarreras", listaCarreras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/alumnoForm.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idalumno = Integer.parseInt(request.getParameter("id"));
        Alumno alumnoExistente = alumnoFacade.find(idalumno);
        List<Carrera> listaCarreras = carreraFacade.findAll();
        request.setAttribute("alumno", alumnoExistente);
        request.setAttribute("listaCarreras", listaCarreras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/alumnoForm.jsp");
        dispatcher.forward(request, response);
    }

    private void registrarAlumno(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nombre = request.getParameter("nombre");
        int registro = Integer.parseInt(request.getParameter("registro"));
        int idcarrera = Integer.parseInt(request.getParameter("carrera_idcarrera"));
        
        Carrera carrera = carreraFacade.find(idcarrera);
        
        Alumno nuevoAlumno = new Alumno();
        nuevoAlumno.setNombre(nombre);
        nuevoAlumno.setRegistro(registro);
        nuevoAlumno.setCarrera(carrera);
        
        alumnoFacade.create(nuevoAlumno);
        response.sendRedirect(request.getContextPath() + "/AlumnoServlet");
    }

    private void actualizarAlumno(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int idalumno = Integer.parseInt(request.getParameter("idalumno"));
        String nombre = request.getParameter("nombre");
        int registro = Integer.parseInt(request.getParameter("registro"));
        int idcarrera = Integer.parseInt(request.getParameter("carrera_idcarrera"));
        
        Carrera carrera = carreraFacade.find(idcarrera);

        Alumno alumno = new Alumno(idalumno);
        alumno.setNombre(nombre);
        alumno.setRegistro(registro);
        alumno.setCarrera(carrera);

        alumnoFacade.edit(alumno);
        response.sendRedirect(request.getContextPath() + "/AlumnoServlet");
    }

    private void eliminarAlumno(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int idalumno = Integer.parseInt(request.getParameter("id"));
        Alumno alumno = alumnoFacade.find(idalumno);
        if (alumno != null) {
            try {
                alumnoFacade.remove(alumno);
            } catch (jakarta.ejb.EJBException e) {
                request.getSession().setAttribute("errorMessage", "No se puede eliminar el alumno porque tiene exámenes asociados. Elimine primero los exámenes relacionados.");
            }
        }
        response.sendRedirect(request.getContextPath() + "/AlumnoServlet");
    }
}
