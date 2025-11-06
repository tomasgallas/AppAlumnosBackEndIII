package modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "docente")
public class Docente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddocente")
    private Integer iddocente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cargo")
    private String cargo; // Ej: TITULAR, ADJUNTO, JTP

    @ManyToMany
    @JoinTable(
        name = "materia_has_docente",
        joinColumns = @JoinColumn(name = "docente_iddocente"),
        inverseJoinColumns = @JoinColumn(name = "materia_idmateria")
    )
    private List<Materia> materias;

    public Docente() {
    }

    public Integer getIddocente() {
        return iddocente;
    }

    public void setIddocente(Integer iddocente) {
        this.iddocente = iddocente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    @Override
    public String toString() {
        return "modelo.Docente[ iddocente=" + iddocente + " ]";
    }
}
