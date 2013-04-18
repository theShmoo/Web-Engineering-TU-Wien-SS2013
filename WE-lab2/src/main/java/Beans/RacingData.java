package Beans;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * This class represents the racing data
 * @author Johannes Deml, Lukas Kraenkl
 */
public class RacingData {
    
    private String humanName;
    private String computerName;
    private String leadingPlayer;

    private int posPlayerHuman;
    private int posPlayerComputer;   
    private int expectedPosPlayerHuman;
    private int expectedPosPlayerComputer;
    private int lastPosPlayerHuman;
    private int lastPosPlayerComputer;
    
    private int round;
    private int dice; 
    private int diceComputer;
    private Date time;
    
    private static final List<Integer> OIL_SPILLS = Arrays.asList(2, 5);
    
    
    public RacingData(){
        //TODO Namen richtig anpassen, nur zum Debug Zweck mit XXX versehen
        humanName = "Super Mario XXX";
        computerName = "Super C";
        leadingPlayer = "mehrere";
        dice = 0;
        diceComputer = 0;
        round = 1;
        time = new Date();
        posPlayerHuman = 0;
        posPlayerComputer = 0;
        expectedPosPlayerHuman = 0;
        expectedPosPlayerComputer = 0;
        lastPosPlayerHuman = 0;
        lastPosPlayerComputer = 0;      
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
    public int getPositionPlayerHuman(){
        return posPlayerHuman;
    }
    
    /**
     * Returns the position field number of the current Position of Player 2
     * @return current fieldnumber of Player 2
     */
    public int getPositionPlayerComputer(){
        return posPlayerComputer;
    }
    
    /**
     * Returns the position field number expected Position of Player 1
     * @return current fieldnumber of Player 1
     */
    public int getExpectedPositionPlayerHuman(){
        return expectedPosPlayerHuman;
    }
    
    /**
     * Returns the position field number of the expected Position of Player 2
     * @return current fieldnumber of Player 2
     */
    public int getExpectedPositionPlayerComputer(){
        return expectedPosPlayerComputer;
    }
    
    /**
     * Returns the last position of Player 1
     * @return last fieldnumber of Player 1
     */
    public int getLastPositionPlayerHuman(){
        return lastPosPlayerHuman;
    }
    
    /**
     * Returns the last position of Player 2
     * @return last fieldnumber of Player 2
     */
    public int getLastPositionPlayerComputer(){
        return lastPosPlayerComputer;
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
     * @return if(dice==0) -> the dice hasn't been rolled yet
     */
    public int getDice(){
        return dice;
    }
    
    /**
     * Return the dice roll of the computer player
     * @return if(dice==0) -> the dice hasn't been rolled yet
     */
    public int getDiceComputer(){
        return diceComputer;
    }
    
    /**
     * Returns the duration of the game since starting
     * @return Date of the game
     */
    public Date getTime(){
        return new Date(new Date().getTime() - time.getTime());
    }
    
    /**
     * 
     * @return Locations, where oil spills are on the track
     */
    public List<Integer> getOilSpills() {
        return OIL_SPILLS;
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
     * Positions of players have to be refreshed, to ensure a correct result
     */
    public void setLeadingPlayer(){
        String leadingPlayer;
        if(posPlayerHuman> posPlayerComputer) {
            leadingPlayer = humanName;
        } else if(posPlayerHuman< posPlayerComputer) {
            leadingPlayer = computerName;
        } else {
            leadingPlayer = "mehere";
        }
        
        this.leadingPlayer = leadingPlayer;
    }
    
    /**
     * Sets a Field for Player 1
     * @param valid fieldnumber > 0
     */
    public void setFieldPlayerHuman(int fieldnumber){
        this.lastPosPlayerHuman = getPositionPlayerHuman();
        this.posPlayerHuman = fieldnumber;
    }
    
        /**
     * Sets a Field for Player 2
     * @param valid fieldnumber > 0
     */
    public void setFieldPlayerComputer(int fieldnumber){
        this.lastPosPlayerComputer = getPositionPlayerComputer();
        this.posPlayerComputer = fieldnumber;
    }
    
    public void setExpectedFieldHuman(int fieldnumber) {
        this.expectedPosPlayerHuman = fieldnumber;
    }
    
    public void setExpectedFieldComputer(int fieldnumber) {
        this.expectedPosPlayerComputer = fieldnumber;
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
    
    /**
     * Sets the dice
     * @param dice, 0 < dice < 7 
     */
    public void setDiceComputer(int diceComputer){
        this.diceComputer = diceComputer;
    }
}
