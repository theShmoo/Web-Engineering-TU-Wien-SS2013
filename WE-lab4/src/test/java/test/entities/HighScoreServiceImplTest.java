package test.entities;

import formel0api.Game;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tuwien.big.formel0.entities.Player;
import tuwien.big.formel0.entities.Sex;
import tuwien.big.formel0.soap.HighScoreServiceImpl;

/**
 * This class tests a HighScoreServiceImpl
 * @author Lukas Kraenkl
 */
public class HighScoreServiceImplTest {
    
    private HighScoreServiceImpl hssi;
    
    public HighScoreServiceImplTest() {
    }
    
    @Before
    public void setUp() {
        hssi = new HighScoreServiceImpl();
    }
    
    @After
    public void tearDown() {
        hssi = null;
    }
    
    //@Test(expected=IllegalArgumentException.class)
    public void giveNullShouldReturnException(){
        //hssi.publishHighScore(null, null);
    }
    
    // TODO 
    // Testfall mit echtem Game sehr umstaendlich, deshalb hier nicht implementiert
    
    private Player getValidPlayer(){
        Player player = new Player();
        player.setName("lukas");
        player.setBirthday("25.03.1992");
        player.setSex(Sex.MALE);
        return player;
    }
    
    private Game getValidGame(){
        Game game = new Game(null,null);        
        return game;
    }
    
    
}