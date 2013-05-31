package test.entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
    
    @Test(expected=IllegalArgumentException.class)
    public void giveNullShouldReturnException1(){
        //foo
    }
    
    
}