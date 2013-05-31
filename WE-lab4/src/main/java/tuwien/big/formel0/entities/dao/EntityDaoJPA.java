package tuwien.big.formel0.entities.dao;

import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * EntityDao
 *
 * @author David Pfahler
 */
public abstract class EntityDaoJPA<E> implements EntityDao<E> {

    protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4");
    protected static final Validator validator = Validation
            .buildDefaultValidatorFactory().getValidator();
    protected static EntityManager em;
    public static final String JTA_PU_NAME = "lab4";
    private Class<E> entityClass;
    private String findAllQueryString;

    public EntityDaoJPA() {

        ParameterizedType genericSuperclass = (ParameterizedType) this
                .getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass
                .getActualTypeArguments()[0];

        this.findAllQueryString = "SELECT e FROM "
                + this.entityClass.getSimpleName() + " e";

        System.out.printf("Created new Entity: %s \n", this.entityClass);
    }

    @Override
    public void create(E e) {
        em = emf.createEntityManager();
        Set<ConstraintViolation<E>> violations = validator
                .validate(e);
        if (violations.isEmpty() == false) {
            throw new ConstraintViolationException(
                    new HashSet<ConstraintViolation<?>>(violations));
        }
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();
        System.out.printf("Created new %s!", e);
    }

    @Override
    public void edit(E e) {
        em = emf.createEntityManager();
        Set<ConstraintViolation<E>> violations = validator
                .validate(e);
        if (violations.isEmpty() == false) {
            throw new ConstraintViolationException(
                    new HashSet<ConstraintViolation<?>>(violations));
        }

        em.merge(e);
        em.close();
        System.out.printf("Edited %s!", e);
    }

    @Override
    public void remove(E e) {
        em = emf.createEntityManager();
        em.remove(e);
        System.out.printf("Removed %s!", e);
        em.close();
    }

    @Override
    public E findById(int id) {
        em = emf.createEntityManager();
        E e = em.find(entityClass, id);
        em.close();
        return e;
    }

    @Override
    public List<E> findAll() {
        em = emf.createEntityManager();
        List<E> es = (List<E>) em
                .createQuery(this.findAllQueryString).getResultList();
        em.close();
        return es;
    }
}
