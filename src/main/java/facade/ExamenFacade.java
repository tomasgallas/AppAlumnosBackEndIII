package facade;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import modelo.Examen;
import modelo.viewmodel.ExamenViewModel;
import modelo.viewmodel.ExamenJulioViewModel;

import java.util.List;

@Stateless
public class ExamenFacade extends AbstractFacade<Examen> {

    @PersistenceContext(unitName = "AppTomasMavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExamenFacade() {
        super(Examen.class);
    }

    public List<ExamenViewModel> consultar() {
        String jpql = "SELECT NEW modelo.viewmodel.ExamenViewModel(e.examenPK.materiaIdmateria, e.examenPK.alumnoIdalumno, e.fecha, e.nota, e.materia.nombre, e.alumno.nombre) FROM Examen e ORDER BY e.fecha DESC";
        return em.createQuery(jpql, ExamenViewModel.class).getResultList();
    }

    public List<ExamenJulioViewModel> findExamenesJulio() {
        String jpql = "SELECT NEW modelo.viewmodel.ExamenJulioViewModel(e.alumno.nombre, e.nota) " +
                      "FROM Examen e " +
                      "WHERE FUNCTION('MONTH', e.fecha) = 7 AND FUNCTION('YEAR', e.fecha) = 2025 " +
                      "ORDER BY e.fecha DESC";
        return em.createQuery(jpql, ExamenJulioViewModel.class).getResultList();
    }
}
