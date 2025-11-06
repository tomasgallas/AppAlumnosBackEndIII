package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ExamenPK implements Serializable {

    @Column(name = "materia_idmateria")
    private int materiaIdmateria;

    @Column(name = "alumno_idalumno")
    private int alumnoIdalumno;

    public ExamenPK() {
    }

    public ExamenPK(int materiaIdmateria, int alumnoIdalumno) {
        this.materiaIdmateria = materiaIdmateria;
        this.alumnoIdalumno = alumnoIdalumno;
    }

    // Getters, Setters, hashCode y equals son importantes para las claves compuestas

    public int getMateriaIdmateria() {
        return materiaIdmateria;
    }

    public void setMateriaIdmateria(int materiaIdmateria) {
        this.materiaIdmateria = materiaIdmateria;
    }

    public int getAlumnoIdalumno() {
        return alumnoIdalumno;
    }

    public void setAlumnoIdalumno(int alumnoIdalumno) {
        this.alumnoIdalumno = alumnoIdalumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) materiaIdmateria;
        hash += (int) alumnoIdalumno;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ExamenPK)) {
            return false;
        }
        ExamenPK other = (ExamenPK) object;
        if (this.materiaIdmateria != other.materiaIdmateria) {
            return false;
        }
        if (this.alumnoIdalumno != other.alumnoIdalumno) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ExamenPK[ materiaIdmateria=" + materiaIdmateria + ", alumnoIdalumno=" + alumnoIdalumno + " ]";
    }
}
