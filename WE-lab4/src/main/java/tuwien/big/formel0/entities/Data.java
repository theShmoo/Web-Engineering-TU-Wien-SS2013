package tuwien.big.formel0.entities;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * a builtin converter for enum from JSF
 * 
 * @author Lukas Kraenkl
 * @author David Pfahler
 * @author Johannes Deml
 */
@ManagedBean
@ApplicationScoped
public class Data {

    /**
     * Returns all possible sexes
     * @return sexes
     */
    public Sex[] getSexes() {
        return Sex.values();
    }

}