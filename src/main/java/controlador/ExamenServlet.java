package controlador;

import facade.AlumnoFacade;
import facade.ExamenFacade;
import facade.MateriaFacade;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Alumno;
import modelo.Examen;
import modelo.ExamenPK;
import modelo.Materia;
import modelo.viewmodel.ExamenViewModel;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/ExamenServlet")
public class ExamenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @EJB
    private ExamenFacade examenFacade;
    
    @EJB
    private AlumnoFacade alumnoFacade;
    
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
                    registrarExamen(request, response);
                    break;
                case "actualizar":
                    actualizarExamen(request, response);
                    break;
                default:
                    listarExamenes(request, response);
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
                    eliminarExamen(request, response);
                    break;
                default:
                    listarExamenes(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listarExamenes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ExamenViewModel> listaExamenes = examenFacade.consultar();
        request.setAttribute("listaExamenes", listaExamenes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/examenes.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Alumno> listaAlumnos = alumnoFacade.findAll();
        List<Materia> listaMaterias = materiaFacade.findAll();
        request.setAttribute("listaAlumnos", listaAlumnos);
        request.setAttribute("listaMaterias", listaMaterias);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/examenForm.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idmateria = Integer.parseInt(request.getParameter("idmateria"));
        int idalumno = Integer.parseInt(request.getParameter("idalumno"));
        
        ExamenPK pk = new ExamenPK(idmateria, idalumno);
        Examen examenExistente = examenFacade.find(pk);
        
        // Para mostrar los nombres en el formulario deshabilitado
        request.setAttribute("examen", examenExistente);
        request.setAttribute("alumnoActual", examenExistente.getAlumno());
        request.setAttribute("materiaActual", examenExistente.getMateria());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/examenForm.jsp");
        dispatcher.forward(request, response);
    }

    private void registrarExamen(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            int idmateria = Integer.parseInt(request.getParameter("materia_idmateria"));
            int idalumno = Integer.parseInt(request.getParameter("alumno_idalumno"));
            Timestamp fecha = new Timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(request.getParameter("fecha")).getTime());
            int nota = Integer.parseInt(request.getParameter("nota"));

            ExamenPK pk = new ExamenPK(idmateria, idalumno);
            Examen nuevoExamen = new Examen(pk);
            nuevoExamen.setFecha(fecha);
            nuevoExamen.setNota(nota);
            
            // Es importante setear las relaciones para que JPA las maneje
            nuevoExamen.setAlumno(alumnoFacade.find(idalumno));
            nuevoExamen.setMateria(materiaFacade.find(idmateria));

            examenFacade.create(nuevoExamen);
            response.sendRedirect(request.getContextPath() + "/ExamenServlet");
        } catch (Exception e) {
            throw new ServletException("Error al parsear los datos del formulario", e);
        }
    }

    private void actualizarExamen(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            int idmateria = Integer.parseInt(request.getParameter("materia_idmateria"));
            int idalumno = Integer.parseInt(request.getParameter("alumno_idalumno"));
            Timestamp fecha = new Timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(request.getParameter("fecha")).getTime());
            int nota = Integer.parseInt(request.getParameter("nota"));

            ExamenPK pk = new ExamenPK(idmateria, idalumno);
            Examen examen = new Examen(pk);
            examen.setFecha(fecha);
            examen.setNota(nota);

            examenFacade.edit(examen);
            response.sendRedirect(request.getContextPath() + "/ExamenServlet");
        } catch (Exception e) {
            throw new ServletException("Error al parsear los datos del formulario", e);
        }
    }

    private void eliminarExamen(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int idmateria = Integer.parseInt(request.getParameter("idmateria"));
        int idalumno = Integer.parseInt(request.getParameter("idalumno"));
        ExamenPK pk = new ExamenPK(idmateria, idalumno);
        Examen examen = examenFacade.find(pk);
        if (examen != null) {
            examenFacade.remove(examen);
        }
        response.sendRedirect(request.getContextPath() + "/ExamenServlet");
    }
}
