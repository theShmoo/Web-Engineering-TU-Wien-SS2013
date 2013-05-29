package tuwien.big.formel0.entities.facade;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import tuwien.big.formel0.entities.Player;

/**
 * EntityFacade
 *
 * @author David Pfahler
 */
public abstract class EntityFacadeImpl<E> implements EntityFacade<E> {

    @PersistenceContext(unitName = "lab4")
    protected EntityManager em;
    protected Class<E> entityClass;

    public EntityFacadeImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) this
                .getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    @Override
    public void create(E e) {
        em.persist(e);
    }

    @Override
    public void edit(E e) {
        em.merge(e);
    }

    @Override
    public void remove(E e) {
        em.remove(em.merge(e));
    }
    
    @Override
    public E findById(int id) {
        return em.find(entityClass, id);
    }
    @Override
    public List<E> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }
}
