package tuwien.big.formel0.entities.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tuwien.big.formel0.picasa.RaceDriver;

/**
 * PlayerDaoJPA
 *
 * @author David Pfahler
 */
public class RaceDriverDaoJPA extends EntityDaoJPA<RaceDriver> {

    private static RaceDriverDaoJPA instance;
    /**
     * Define a logger
     */
    private static final Logger log = LoggerFactory.getLogger(PlayerDaoJPA.class);

    public RaceDriverDaoJPA() {
        super();
    }

    public static RaceDriverDaoJPA getRaceDriverDaoJPAInstance() {
        if (instance == null) {
            instance = new RaceDriverDaoJPA();
        }
        return instance;
    }
}
