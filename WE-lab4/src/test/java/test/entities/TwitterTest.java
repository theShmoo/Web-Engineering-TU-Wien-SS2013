package test.entities;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import tuwien.big.formel0.twitter.ITwitterClient;
import tuwien.big.formel0.twitter.TwitterClientImpl;
import tuwien.big.formel0.twitter.TwitterStatusMessage;

/**
 * Dieser Tests überprueft die Funktionalitaet von Twitter Posts
 * @author Lukas Kraenkl, David Pfahler, Johannes Deml
 */
public class TwitterTest {
    
    private ITwitterClient client;
    private TwitterStatusMessage msg;
    
    public TwitterTest() {
        client = new TwitterClientImpl();
    }
    
    @Before
    public void init(){
        msg = new TwitterStatusMessage("Funkt das wirklich ?","#YOLO",new Date());
    }
    
    //@Test
    public void postAMessage() throws Exception{
        client.publishUuid(msg);
    }
    
}