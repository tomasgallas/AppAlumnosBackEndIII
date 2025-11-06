package modelo.viewmodel;

import java.sql.Timestamp;

public class ExamenViewModel {

    private int materia_idmateria;
    private int alumno_idalumno;
    private Timestamp fecha;
    private int nota;
    private String nombreMateria;
    private String nombreAlumno;

    public ExamenViewModel() {
    }

    public ExamenViewModel(int materia_idmateria, int alumno_idalumno, Timestamp fecha, int nota, String nombreMateria, String nombreAlumno) {
        this.materia_idmateria = materia_idmateria;
        this.alumno_idalumno = alumno_idalumno;
        this.fecha = fecha;
        this.nota = nota;
        this.nombreMateria = nombreMateria;
        this.nombreAlumno = nombreAlumno;
    }

    // Getters y Setters

    public int getMateria_idmateria() {
        return materia_idmateria;
    }

    public void setMateria_idmateria(int materia_idmateria) {
        this.materia_idmateria = materia_idmateria;
    }

    public int getAlumno_idalumno() {
        return alumno_idalumno;
    }

    public void setAlumno_idalumno(int alumno_idalumno) {
        this.alumno_idalumno = alumno_idalumno;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }
}
