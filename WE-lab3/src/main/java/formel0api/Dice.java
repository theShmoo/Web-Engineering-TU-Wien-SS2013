package formel0api;

import java.util.Random;

/**
 * Class representing a {@link Dice}
 * 
 * @author Lukas Kraenkl
 * @author David Pfahler
 * @author Johannes Deml
 */
public class Dice {

    /**
     * Object used to generate pseudo-random generated numbers that represent
     * the score thrown by rolling this dice
     */
    private Random random;
    private int lastRoll;
    private static final String diceImage = "img/wuerfel#.png"; 
    private static final String diceDescription = "table.diceDescription"; 
    private static final String diceTitle = "table.diceTitle"; 

    /**
     * Creates a new dice
     */
    public Dice() {
        random = new Random();
        this.lastRoll = 0;
    }

    /**
     * Rolls the dice, i.e., specifies the score thrown using the dice
     *
     * @return score thrown
     */
    public int roll() {
        lastRoll = random.nextInt(3) + 1;
        return lastRoll;
        
    }
    
   /**
    * Returns the number the dice is currently showing
    */
    public int getLastRoll() {
        return lastRoll;
    }

    /**
     * Returns the location of the image of the dice
     * @return the location of the image of the dice
     */
    public String getDiceImage() {
        return diceImage.replaceFirst("#", String.valueOf(lastRoll));
    }    

    /**
     * Returns the description or the alt text of the dice
     * e.g.: Dice with two dots
     * @return the description or the alt text of the dice
     */
    public String getDiceDescription() {
        return diceDescription+lastRoll;
    }

    /**
     * Returns the title of the dice
     * e.g.: Dice showing one
     * @return the title of the dice
     */
    public String getDiceTitle() {
        return diceTitle+lastRoll;
    }
    
    
}
