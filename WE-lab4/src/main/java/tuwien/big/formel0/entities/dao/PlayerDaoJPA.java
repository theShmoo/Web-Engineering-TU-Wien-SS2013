package tuwien.big.formel0.entities.dao;

import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tuwien.big.formel0.entities.Player;
import static tuwien.big.formel0.entities.dao.EntityDaoJPA.em;

/**
 * PlayerDaoJPA
 *
 * @author David Pfahler
 */
public class PlayerDaoJPA extends EntityDaoJPA<Player> {

    private static PlayerDaoJPA playerDaoJPA;
    private String loginQueryString;
    
    /** Define a logger */
    private static final Logger log = LoggerFactory.getLogger(PlayerDaoJPA.class);

    public PlayerDaoJPA() {
        super();

        this.loginQueryString = "SELECT p FROM Player p WHERE p.name = :name AND p.password = :password";
    }

    public static PlayerDaoJPA getPlayDaoJPAInstance() {
        if (playerDaoJPA == null) {
            playerDaoJPA = new PlayerDaoJPA();
        }
        return playerDaoJPA;
    }

    public Player findByNameAndPassword(String name, String password) {
        Player player = null;
        em = emf.createEntityManager();
        Query q = em
                .createQuery(this.loginQueryString);
        q.setParameter ("name", name);
        q.setParameter ("password", password);
        
        if (q.getResultList().size() == 1){
             player = (Player)q.getResultList().get(0);
             log.info("Player {} logged in",player.getName());
        }
        em.close();
        return player;
        
    }
}
