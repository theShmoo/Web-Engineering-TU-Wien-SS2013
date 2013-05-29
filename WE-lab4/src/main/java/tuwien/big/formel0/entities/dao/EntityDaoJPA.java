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
        
        em = emf.createEntityManager();

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
        Set<ConstraintViolation<E>> violations = validator
                .validate(e);
        if (violations.isEmpty() == false) {
            throw new ConstraintViolationException(
                    new HashSet<ConstraintViolation<?>>(violations));
        }
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        System.out.printf("Created new %s!", e);
    }

    @Override
    public void edit(E e) {
        Set<ConstraintViolation<E>> violations = validator
                .validate(e);
        if (violations.isEmpty() == false) {
            throw new ConstraintViolationException(
                    new HashSet<ConstraintViolation<?>>(violations));
        }

        em.merge(e);
        System.out.printf("Edited %s!", e);
    }

    @Override
    public void remove(E e) {
        em.remove(em.merge(e));
        System.out.printf("Removed %s!", e);
    }

    @Override
    public E findById(int id) {
        return em.find(entityClass, id);
    }

    @Override
    public List<E> findAll() {
        return (List<E>) em
                .createQuery(this.findAllQueryString).getResultList();
    }
}
