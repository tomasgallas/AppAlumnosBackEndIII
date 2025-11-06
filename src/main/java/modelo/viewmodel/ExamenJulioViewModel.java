package modelo.viewmodel;

public class ExamenJulioViewModel {
    private String alumnoNombre;
    private Integer nota;

    public ExamenJulioViewModel(String alumnoNombre, Integer nota) {
        this.alumnoNombre = alumnoNombre;
        this.nota = nota;
    }

    // Getters
    public String getAlumnoNombre() { return alumnoNombre; }
    public Integer getNota() { return nota; }

    // Setters (opcional, pero buena práctica si se necesita mutabilidad)
    public void setAlumnoNombre(String alumnoNombre) { this.alumnoNombre = alumnoNombre; }
    public void setNota(Integer nota) { this.nota = nota; }
}
