package tuwien.big.formel0.entities.facade;

import javax.ejb.Stateless;
import tuwien.big.formel0.entities.Player;

/**
 * PlayerDaoJPA
 *
 * @author David Pfahler
 */
@Stateless
public class PlayerDaoJPA extends EntityDaoJPA<Player>{

    public Player findByNameAndPassword(String name, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
        //TODO implement
    }
}
