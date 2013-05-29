package tuwien.big.formel0.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * This class implements the TwitterClient Interface
 * @author Lukas Kraenkl, David Pfahler, Johannes Deml
 */
public class TwitterClientImpl implements ITwitterClient{

    @Override
    public void publishUuid(TwitterStatusMessage message) throws Exception {
       
        Twitter tweet = new TwitterFactory().getInstance();
        
        RequestToken requestToken = tweet.getOAuthRequestToken();
        AccessToken accessToken = null;
        
        while (accessToken == null) {
            
            //TODO
            
        }
    
    }
    
    
    
}
