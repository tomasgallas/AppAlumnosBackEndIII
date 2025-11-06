package modelo.viewmodel;

// Esta clase es un ViewModel. No representa una tabla de la base de datos,
// sino que se usa para transportar datos combinados a la vista.
public class CarreraViewModel {

    private int idcarrera;
    private String nombre;
    private int facultad_idfacultad;
    private String nombreFacultad; // Campo adicional para el nombre de la facultad

    public CarreraViewModel() {
    }

    public CarreraViewModel(int idcarrera, String nombre, int facultad_idfacultad, String nombreFacultad) {
        this.idcarrera = idcarrera;
        this.nombre = nombre;
        this.facultad_idfacultad = facultad_idfacultad;
        this.nombreFacultad = nombreFacultad;
    }

    // Getters y Setters para todos los campos

    public int getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(int idcarrera) {
        this.idcarrera = idcarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFacultad_idfacultad() {
        return facultad_idfacultad;
    }

    public void setFacultad_idfacultad(int facultad_idfacultad) {
        this.facultad_idfacultad = facultad_idfacultad;
    }

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }
}
