package tuwien.big.formel0.soap;

import formel0api.Game;
import tuwien.big.formel0.entities.Player;

/**
 *
 * @author Operator
 */
public class HighScoreServiceImpl implements IHighScoreService {

    @Override
    public String publishHighScore(Game game, Player player) {
        
        

        //Request: generate RequestType
        tuwien.big.formel0.soap.ObjectFactory requestFactory = new tuwien.big.formel0.soap.ObjectFactory();
        HighScoreRequestType highScoreRequest = requestFactory.createHighScoreRequestType();
        highScoreRequest.setUserKey("34EphAp2C4ebaswu");

        //TODO 
        
        return "";
    
    }
    
}
