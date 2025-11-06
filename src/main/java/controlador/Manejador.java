package controlador;

import modelo.Facultad;
import facade.FacultadFacade;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Manejador", 
            loadOnStartup = 1, 
            urlPatterns = {"/Manejador", "/Listar", "/Facultad"})
public class Manejador extends HttpServlet {

    @EJB
    private FacultadFacade facu;

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Iniciando Servlet Manejador...");
        
        try {
            // Almacena la lista de facultades en el contexto del Servlet
            getServletContext().setAttribute("facultades", facu.findAll());
            System.out.println("Facultades cargadas y disponibles en el contexto de la aplicación. Total: " + facu.findAll().size());
        } catch (Exception e) {
            System.out.println("Error al inicializar el servlet y cargar facultades: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getServletPath();
        System.out.println("Petición recibida en Manejador para la ruta: " + action);
        
        String url = "/index.jsp";
        
        switch (action) {
            case "/Facultad":
                // Captura el parámetro facultadId
                Integer nroFacultad = Integer.parseInt(request.getParameter("codigoFacu"));
                System.out.println("Se recibió el código de facultad: " + nroFacultad);
                
                // Busca la facultad en la BD
                Facultad miFacu = facu.find(nroFacultad);
                System.out.println("Facultad encontrada en la base de datos: " + (miFacu != null ? miFacu.getNombre() : "NULL"));
                
                // Guarda la facultad en el request para que la vista la pueda usar
                request.setAttribute("facultad", miFacu);
                
                // Define la URL del JSP
                url = "/vistas/carreras.jsp";
                System.out.println("Redirigiendo la petición a la vista: " + url);
                break;
                
            default:
                url = "/index.jsp";
                break;
        }
        
        // Transfiere el control al JSP
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}