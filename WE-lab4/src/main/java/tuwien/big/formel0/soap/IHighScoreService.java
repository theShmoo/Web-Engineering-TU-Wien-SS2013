package tuwien.big.formel0.soap;

import formel0api.Game;
import tuwien.big.formel0.entities.Player;

/**
 * High Score Service Interface
 * @author Lukas Kraenkl, David Pfahler, Johannes Deml
 */
public interface IHighScoreService {
    
    /**
     * Publishes for a specific Player and Game the highscore 
     * @param game, not null
     * @param player, not null
     * @return UUID (wichtig fuer Twitter - siehe Angabe Seite 3), null wenn Fehler aufgetreten
     * @throws IllegalArgumentException when game or player == null
     */
     public String publishHighScore(Game game,Player player);    
    
}
