/**
 * Der Controller von dem das Spiel aus gesteuer wird
 * würfeln, beinhaltet beans wie game, player, dice
 * @author: Lukas Kraenkl, David Pfahler, Johannes Deml
 */
package formel0api;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="gameController")
@RequestScoped
public class GameController {

    Player player;
    Player computer;
    Game game;
    
    /**
     * Creates a new instance of GameController
     */
    public GameController() {
        player = new Player("TestPlayer");
        computer = new Player("ComputerTestPlayer");
        game = new Game(player,computer);
    }
    
    
    
    // TODO würfeln, beinhaltet beans wie game, player, dice

}
