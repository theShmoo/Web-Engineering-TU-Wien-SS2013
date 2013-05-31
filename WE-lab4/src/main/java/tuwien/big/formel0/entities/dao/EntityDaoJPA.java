package tuwien.big.formel0.entities.dao;

import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EntityDao
 *
 * @author David Pfahler
 */
public abstract class EntityDaoJPA<E> implements EntityDao<E> {
    
    /** Define a logger */
    private static final Logger log = LoggerFactory.getLogger(EntityDaoJPA.class);

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

        log.info("Created new Entity: {}", this.entityClass);
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
        log.info("Persisted {}", e);
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
        log.info("Edited {}", e);
    }

    @Override
    public void remove(E e) {
        em = emf.createEntityManager();
        em.remove(e);
        log.info("Removed {}", e);
        em.close();
    }

    @Override
    public E findById(int id) {
        em = emf.createEntityManager();
        E e = em.find(entityClass, id);
        log.info("Found by id \"{}\" the entity {}",id,e);
        em.close();
        return e;
    }

    @Override
    public List<E> findAll() {
        em = emf.createEntityManager();
        List<E> es = (List<E>) em
                .createQuery(this.findAllQueryString).getResultList();
        log.info("Found all entities: {}",es);
        em.close();
        return es;
    }
}
