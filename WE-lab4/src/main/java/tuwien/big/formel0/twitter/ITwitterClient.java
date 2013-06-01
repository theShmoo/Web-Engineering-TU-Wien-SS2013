package tuwien.big.formel0.twitter;

import twitter4j.TwitterException;


/**
 * Interface for Twitter clients
 * @author pl
 *
 */
public interface ITwitterClient {

	
	public void publishUuid(TwitterStatusMessage message) throws TwitterException;
	
	
}
