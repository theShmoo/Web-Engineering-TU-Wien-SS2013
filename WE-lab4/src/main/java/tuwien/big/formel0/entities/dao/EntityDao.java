package tuwien.big.formel0.entities.dao;

import java.util.List;

/**
 * EntityDao
 *
 * @author David Pfahler
 * 
 * TODO comments in interfaces would be nice
 */
public interface EntityDao<E> {

    public void create(E e);

    public void edit(E e);

    public void remove(E e);

    public E findById(int id);
    
    public List<E> findAll();

}
