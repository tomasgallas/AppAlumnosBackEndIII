package facade;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import modelo.Docente;
import java.util.List;

@Stateless
public class DocenteFacade extends AbstractFacade<Docente> {

    @PersistenceContext(unitName = "AppTomasMavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocenteFacade() {
        super(Docente.class);
    }

    @Override
    public Docente find(Object id) {
        return (Docente) em.createQuery("SELECT d FROM Docente d LEFT JOIN FETCH d.materias WHERE d.iddocente = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Docente> findAll() {
        return em.createQuery("SELECT DISTINCT d FROM Docente d LEFT JOIN FETCH d.materias ORDER BY d.nombre", Docente.class)
                .getResultList();
    }

    public List<Docente> findDocentesConMasDeDosMaterias() {
        String jpql = "SELECT d FROM Docente d " +
                      "WHERE SIZE(d.materias) > 2 " +
                      "ORDER BY d.nombre";
        return em.createQuery(jpql, Docente.class).getResultList();
    }
}
