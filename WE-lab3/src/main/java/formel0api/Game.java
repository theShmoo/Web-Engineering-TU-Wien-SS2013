/**
 * <copyright>
 *
 * Copyright (c) 2013 http://www.big.tuwien.ac.at All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */
package formel0api;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Class representing a Formel 0 game
 */
public class Game {

    private static final int LAST_FIELD = 6;   
    /**
     * sorted Array of deadly fields
     * @invariant OIL_STAINS.isSorted()
     */
    private static final int[] OIL_STAINS = {2,5};
    
    private static SimpleDateFormat formatter = new SimpleDateFormat("mm:ss:SSS", Locale.getDefault());
    /**
     * Player playing the game
     */
    private Player player;
    /**
     * Computer opponent
     */
    private Player computer;
    /**
     * Specifies if the game is over (
     * <code>true</code>) or not (
     * <code>false</code)
     */
    private boolean gameOver;
    /**
     * Starting time of the game
     */
    private long gamestarttime;
    /**
     * Time already spent in this game
     */
    private long spenttime;
    /**
     * Current round
     */
    private int round;
    
    /**
     * Constructs a new {@link Game}
     */
    public Game() {
        this.player = new Player("defaultPlayer");
        this.computer = new Player("defaultComputer");
        round = 0;
        gamestarttime = System.currentTimeMillis();
        gameOver = false;
    }
    
    public Game(Player player, Player computer) {
        this();
        this.player = player;
        this.computer = computer;
    }

    /**
     * Specifies whether this game is over or not
     *
     * @return <code>true</code> if this game is over, <code>false</code>
     * otherwise
     */
    public boolean isGameOver() {
        return this.gameOver;
    }

    /**
     * Returns the time already spent on this game
     *
     * @return the time already spent on this game
     */
    public String getSpentTime() {
        if (!gameOver) {
            spenttime = System.currentTimeMillis() - this.gamestarttime;
        }
        return formatter.format(spenttime);
    }

    /**
     * Interface for JSF to roll the Dice for player and Computer
     * 
     */
    public void rollDice() {
        
        rollDiceOnePlayer(player);
        if(!gameOver) {
            rollDiceOnePlayer(computer);
        }
        round ++;
    }
    
    /**
     * Rolls the dice for a player and updates the position of the player's
     * car according to the score
     *
     * @param player Player who rolls the dice
     * @return score
     */
    private int rollDiceOnePlayer(Player player) {
        
        if (gameOver) {
            throw new IllegalArgumentException(
                    "Game is over. Rolling the dice is not allowed.");
        }

        int score = player.rollDice();

        int position = player.getPosition();

        /**
         * Move on field
         */
        int newposition = Math.min(position + score, LAST_FIELD);

        /**
         * Test if deadly field was reached
         */
        if (Arrays.binarySearch(OIL_STAINS, newposition) > 0) {
            newposition = 0;
        }
        
        player.setPosition(newposition);
        /**
         * Test if the figure of the player reached the end and the game is over
         */
        if (newposition == LAST_FIELD) { // player reached end
            gameOver = true;
        }

        return score;
    }

    /**
     * Returns the currently leading player
     *
     * @return the currently leading player
     */
    public Player getLeader() {
        if (player.getPosition() > computer.getPosition()) {
            return player;
        } else if (computer.getPosition() > player.getPosition()) {
            return computer;
        } else {
            //TODO Return "several" in the correct language
            String several = "TODO!!";
            /*FacesContext ctx = FacesContext.getCurrentInstance();
            several = ctx.getApplication()
            .getResourceBundle(ctx, "table")
            .getString("several");*/
            return new Player(several);
        }
    }

    /**
     * Returns the player
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the computer
     *
     * @return computer
     */
    public Player getComputer() {
        return computer;
    }
    
    /**
     * Returns the current round
     * 
     * @return round
     */
    public int getRound() {
        return round;
    }
    
    public void setRound(int round) {
        this.round = round;
    }
    
    public Game resetGame() {
        Player resetetPlayer = new Player(player.getName());
        Player resetetComputer = new Player(computer.getName());
        return new Game(resetetPlayer, resetetComputer);
    }
}