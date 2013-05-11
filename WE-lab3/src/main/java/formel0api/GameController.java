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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="gameController")
@SessionScoped
public class GameController {
    private Game game;
    
    public GameController() {
        /*UserController uc = (UserController) FacesContext.getCurrentInstance().
                getExternalContext().getRequestMap().get("userController");
        String username = uc.getUser().getUsername();
        
        this.game = new Game(new Player(username), new Player("DefaultComputer"));*/
        this.game = new Game(new Player("Player"), new Player("Computer"));
    }
    
    public void resetGame() {
        this.game = game.resetGame();
    }
    
    public Game getGame() {
        return game;
    }
}
