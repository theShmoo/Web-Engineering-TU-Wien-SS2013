package tuwien.big.formel0.entities.dao;

import javax.persistence.Query;
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
        }
        em.close();
        return player;
        
    }
}
