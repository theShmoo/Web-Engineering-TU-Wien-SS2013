package tuwien.big.formel0.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterException;
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
        try{
            RequestToken requestToken = tweet.getOAuthRequestToken();
            AccessToken accessToken = null;

            while (accessToken == null) {
                try{
                    accessToken = tweet.getOAuthAccessToken(requestToken);
                }
                catch (TwitterException te){
                   if(te.getStatusCode() == 401){
                       // logger ausgabe
                   } 
                   throw te;
                }
            }    
        }
        catch(IllegalStateException ie){
            // Der Token ist bereits vorhanden oder einer der Keys ist nicht gesetzt
            // DO NOTHING
        }
        
        tweet.updateStatus(message.getTwitterPublicationString());
    
    }   
}
