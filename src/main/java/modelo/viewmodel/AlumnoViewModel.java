package modelo.viewmodel;

public class AlumnoViewModel {
    private int idalumno;
    private String nombre;
    private int registro;
    private int carrera_idcarrera;
    private String nombreCarrera;

    public AlumnoViewModel() {
    }

    public AlumnoViewModel(int idalumno, String nombre, int registro, int carrera_idcarrera, String nombreCarrera) {
        this.idalumno = idalumno;
        this.nombre = nombre;
        this.registro = registro;
        this.carrera_idcarrera = carrera_idcarrera;
        this.nombreCarrera = nombreCarrera;
    }

    // Getters y Setters

    public int getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    public int getCarrera_idcarrera() {
        return carrera_idcarrera;
    }

    public void setCarrera_idcarrera(int carrera_idcarrera) {
        this.carrera_idcarrera = carrera_idcarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }
}
