package facade;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import modelo.Alumno;
import modelo.viewmodel.AlumnoViewModel;

import java.util.List;

@Stateless
public class AlumnoFacade extends AbstractFacade<Alumno> {

    @PersistenceContext(unitName = "AppTomasMavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlumnoFacade() {
        super(Alumno.class);
    }

    public List<AlumnoViewModel> consultar() {
        String jpql = "SELECT NEW modelo.viewmodel.AlumnoViewModel(a.idalumno, a.nombre, a.registro, a.carrera.idcarrera, a.carrera.nombre) FROM Alumno a ORDER BY a.nombre";
        return em.createQuery(jpql, AlumnoViewModel.class).getResultList();
    }

    public List<Alumno> findAlumnosSinExamenes() {
        String jpql = "SELECT a FROM Alumno a " +
                      "WHERE NOT EXISTS (" +
                      "    SELECT e FROM Examen e " +
                      "    WHERE e.alumno = a AND FUNCTION('YEAR', e.fecha) = 2025" +
                      ")";
        return em.createQuery(jpql, Alumno.class).getResultList();
    }
}
