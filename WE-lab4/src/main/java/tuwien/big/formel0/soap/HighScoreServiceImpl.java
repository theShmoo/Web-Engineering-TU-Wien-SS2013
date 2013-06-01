package tuwien.big.formel0.soap;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import formel0api.Game;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.xml.ws.WebServiceRef;
import tuwien.big.formel0.soap.*;
import tuwien.big.formel0.entities.Player;

/**
 * This class publishes the highscore in the Internet
 * @author Lukas Kraenkl, David Pfahler, Johannes Deml
 */
public class HighScoreServiceImpl implements IHighScoreService {

    @WebServiceRef(wsdlLocation = "http://playground.big.tuwien.ac.at:8080/highscore/PublishHighScoreService?wsdl")
    private static PublishHighScoreService service;
    private static HighScoreServiceImpl instance;

    /**
     * Creates a new HighScoreServiceImpl
     */
    public HighScoreServiceImpl() {
        service = new PublishHighScoreService();
    }
    
    public static HighScoreServiceImpl getInstance(){
        if(instance == null){
            instance = new HighScoreServiceImpl();
        }
        return instance;
    }
    
    @Override
    public String publishHighScore(Game game, Player player) {
        if(game==null || player==null){
            throw new IllegalArgumentException("Die Werte zum publishen waren null");
        }
        HighScoreRequestType highScoreRequest = new ObjectFactory().createHighScoreRequestType();
        highScoreRequest.setUserKey("34EphAp2C4ebaswu");

        ObjectFactory universalFactory = new ObjectFactory();
        
        TournamentType tt = universalFactory.createTournamentType();
        
        // Fuer start-date, end-date und registration-date nehmen immer das aktuelle Datum
        tt.setStartDate(new XMLGregorianCalendarImpl(new GregorianCalendar()));
        tt.setEndDate(new XMLGregorianCalendarImpl(new GregorianCalendar()));
        tt.setRegistrationDeadline(new XMLGregorianCalendarImpl(new GregorianCalendar()));
        
        // Tournament -> Players
        TournamentType.Players tplayers = universalFactory.createTournamentTypePlayers();
        
        // Tournament -> Players -> GamePlayer
        TournamentType.Players.Player actualPlayer = new TournamentType.Players.Player();        
        
            // Username, Geschlecht und Geburtsdatum werden übergeben
            actualPlayer.setGender(player.getSex().getLabel());
            actualPlayer.setUsername(player.getName());
        
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            GregorianCalendar date = null;
            try {
                date = (GregorianCalendar) GregorianCalendar.getInstance();
                date.setTime(sdf.parse(player.getBirthday()));
            } catch (ParseException ex) {
                // do nothing
            }
            actualPlayer.setDateOfBirth(new XMLGregorianCalendarImpl(date));
            
        tplayers.getPlayer().add(actualPlayer);
        tt.setPlayers(tplayers);

        // Runden
        // Es wird genau eine Runde uebergeben, welche die number '0' haben muss.
        TournamentType.Rounds rounds = universalFactory.createTournamentTypeRounds();
        TournamentType.Rounds.Round round = universalFactory.createTournamentTypeRoundsRound();
        round.setNumber(0);

        // In der Runde ist genau ein Game enthalten, wobei Date wieder das aktuelle Datum ist
        GameType game1 = universalFactory.createGameType();
        game1.setDate(new XMLGregorianCalendarImpl(new GregorianCalendar()));
        game1.setStatus("finished");
        
        // Duration ist die Anzahl in Sekunden, die während des Spiels verstrichen sind
        Long tmp = game.getSpentTime() / 1000;
        game1.setDuration(BigInteger.valueOf(tmp));
        
        // Unter winner uebergeben Sie den Gewinner des Spiels (entweder 'Computer' oder Name des Spielers
        if (game.getLeader().getName().equals(player.getName())) {
            game1.setWinner(player.getName());
        } else {
            game1.setWinner("Computer");
        }

        GameType.Players.Player gamePlayer = universalFactory.createGameTypePlayersPlayer();
        GameType.Players gamePlayers = universalFactory.createGameTypePlayers();
        gamePlayer.setRef(player.getName());
        gamePlayers.getPlayer().add(gamePlayer);
        game1.setPlayers(gamePlayers);

        // Verknuepfen der Elemente
        round.getGame().add(game1);
        rounds.getRound().add(round);
        tt.setRounds(rounds);
        tt.setDescription(universalFactory.createDescriptionType());
        
        highScoreRequest.setTournament(tt);

        String uuid = null;
        try {
            uuid = service.getPublishHighScorePort().publishHighScore(highScoreRequest);
            
        } catch (Exception ex) {
            // do nothing
            throw new Error("Beim publishen ist etwas daneben gegangen");
        }
        return uuid;
    
    }
    
}
