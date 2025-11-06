package facade;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import modelo.Carrera;
import modelo.viewmodel.CarreraViewModel;

import java.util.List;

@Stateless
public class CarreraFacade extends AbstractFacade<Carrera> {

    @PersistenceContext(unitName = "AppTomasMavenPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarreraFacade() {
        super(Carrera.class);
    }

    public List<CarreraViewModel> consultar() {
        String jpql = "SELECT NEW modelo.viewmodel.CarreraViewModel(c.idcarrera, c.nombre, c.facultad.idfacultad, c.facultad.nombre) FROM Carrera c ORDER BY c.nombre";
        return em.createQuery(jpql, CarreraViewModel.class).getResultList();
    }
}
