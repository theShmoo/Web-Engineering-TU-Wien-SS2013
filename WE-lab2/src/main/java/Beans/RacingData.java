package Beans;

import java.util.Date;

/**
 * This class represents the racing data
 * @author Johannes Deml, Lukas Kraenkl
 */
public class RacingData {
    
    private String humanPlayer;
    private String computerPlayer;
    private String leadingPlayer;
    private int round;
    private int dice; 
    private Date time;
    
    public RacingData(){
        //TODO Namen richtig anpassen, nur zum Debug Zweck mit XXX versehen
        humanPlayer = "Super Mario XXX";
        computerPlayer = "Super C";
        leadingPlayer = "mehrere";
        dice = 0;
        round = 1;
        time = new Date();
    }
    
    /**
     * Gets the Human Player
     * @return Human Player
     */
    public String getHumanPlayer(){
        return humanPlayer;
    }
    
    /**
     * Gets the Computer Player
     * @return Computer Player
     */
    public String getComputerPlayer(){
        return computerPlayer;
    }
    
    /**
     * Return the Leading Player
     * @return LeadingPlayer
     */
    public String getLeadingPlayer(){
        return leadingPlayer;
    }
    
    /**
     * Return the round
     * @return 
     */
    public int getRound(){
        return round;
    }
    
    /**
     * Return the dice
     * @return if(dice==0) -> there wasn't diced yet
     */
    public int getDice(){
        return dice;
    }
    
    /**
     * Returns the duration of the game since starting
     * @return Date of the game
     */
    public Date getTime(){
        return time;
    }
    
    /**
     * Sets the HumanPlayer
     * @param humanPlayer, not null, != "" 
     */
    public void setHumanPlayer(String humanPlayer){
        this.humanPlayer = humanPlayer;
    }
    
    /**
     * Sets the Computer Player
     * @param computerPlayer, not null, != "" 
     */
    public void setComputerPlayer(String computerPlayer){
        this.computerPlayer = computerPlayer;
    }
    
    /**
     * Sets the leading Player
     * @param leadingPlayer, not null, != "" 
     */
    public void setLeadingPlayer(String leadingPlayer){
        this.leadingPlayer = leadingPlayer;
    }
    
    /**
     * Sets the Round
     * @param round, not null, != ""
     */
    public void setRound(int round){
        this.round = round;
    }
    
    /**
     * Sets the dice
     * @param dice, 0 < dice < 7 
     */
    public void setDice(int dice){
        this.dice = dice;
    }
    
}
