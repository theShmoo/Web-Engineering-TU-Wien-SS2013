package tuwien.big.formel0.soap;

import formel0api.Game;
import tuwien.big.formel0.entities.Player;


public interface IHighScoreService {
    
     public String publishHighScore(Game game,Player player);    
    
}
