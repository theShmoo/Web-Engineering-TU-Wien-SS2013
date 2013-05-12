/**
 * Handels the current Game for on Person in a session scope. Is necessary for the
 * feature of resetting the game in a delightful way
 * #ContainerClass
 * @author Lukas Kraenkl
 * @author David Pfahler
 * @author Johannes Deml
 */
package formel0api;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="gameController")
@SessionScoped
public class GameController {
    
    @ManagedProperty(value = "#{game}")
    private Game game;
    
    public GameController() {  
        this.game = new Game(new Player("TODO"), new Player("Computer")); 
    }
    
    public void startGame(String username){
        this.game = new Game(new Player(username), new Player("Computer"));   
    }
    
    public void resetGame() {
        this.game = game.resetGame();
    }
    
    public Game getGame() {
        return game;
    }
    
    public void setGame(Game game){
        this.game = game;
    }
}
