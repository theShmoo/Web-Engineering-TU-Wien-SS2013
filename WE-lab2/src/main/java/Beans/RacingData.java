package Beans;

import java.util.Date;

/**
 * This class represents the racing data
 * @author Johannes Deml, Lukas Kraenkl
 */
public class RacingData {
    
    private String humanName;
    private String computerName;
    private String leadingPlayer;

    private int posPlay1;
    private int posPlay2;    
    private int lastPosPlay1;
    private int lastPosPlay2;
    
    private int round;
    private int dice; 
    private Date time;
    
    public RacingData(){
        //TODO Namen richtig anpassen, nur zum Debug Zweck mit XXX versehen
        humanName = "Super Mario XXX";
        computerName = "Super C";
        leadingPlayer = "mehrere";
        dice = 0;
        round = 1;
        time = new Date();
        lastPosPlay1 = 1;
        lastPosPlay2 = 1;
        posPlay1 = 1;
        posPlay2 = 1;
    }
    
    /**
     * Gets the Human Player
     * @return Human Player
     */
    public String getHumanPlayer(){
        return humanName;
    }
    
    /**
     * Gets the Computer Player
     * @return Computer Player
     */
    public String getComputerPlayer(){
        return computerName;
    }
    
    /**
     * Returns the position field number current Position of Player 1
     * @return current fieldnumber of Player 1
     */
    public int getPositionPlayer1(){
        return posPlay1;
    }
    
    /**
     * Returns the position field number of the current Position of Player 2
     * @return current fieldnumber of Player 2
     */
    public int getPositionPlayer2(){
        return posPlay2;
    }
    
    /**
     * Returns the last position of Player 1
     * @return last fieldnumber of Player 1
     */
    public int getLastPositionPlayer1(){
        return lastPosPlay1;
    }
    
    /**
     * Returns the last position of Player 2
     * @return last fieldnumber of Player 2
     */
    public int getLastPositionPlayer2(){
        return lastPosPlay2;
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
        return new Date(new Date().getTime() - time.getTime());
    }
    
    /**
     * Sets the HumanPlayer
     * @param humanPlayer, not null, != "" 
     */
    public void setHumanPlayer(String humanPlayer){
        this.humanName = humanPlayer;
    }
    
    /**
     * Sets the Computer Player
     * @param computerPlayer, not null, != "" 
     */
    public void setComputerPlayer(String computerPlayer){
        this.computerName = computerPlayer;
    }
    
    /**
     * Sets the leading Player
     * @param leadingPlayer, not null, != "" 
     */
    public void setLeadingPlayer(String leadingPlayer){
        this.leadingPlayer = leadingPlayer;
    }
    
    /**
     * Sets a Field for Player 1
     * @param valid fieldnumber > 0
     */
    public void setFieldPlayer1(int fieldnumber){
        this.lastPosPlay1 = getPositionPlayer1();
        this.posPlay1 = fieldnumber;
    }
    
        /**
     * Sets a Field for Player 2
     * @param valid fieldnumber > 0
     */
    public void setFieldPlayer2(int fieldnumber){
        this.lastPosPlay2 = getPositionPlayer2();
        this.posPlay2 = fieldnumber;
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
