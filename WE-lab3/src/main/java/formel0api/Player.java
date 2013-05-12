/**
 * <copyright>
 *
 * Copyright (c) 2010 http://www.big.tuwien.ac.at All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */
package formel0api;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a {@link Player} playing in a {@link Game}.
 * 
 * @author Lukas Kraenkl
 * @author David Pfahler
 * @author Johannes Deml
 */
public class Player {

    /**
     * History of the positions of the player
     */
    private List<Integer> history = new ArrayList<Integer>();
    /**
     * The name of this user
     */
    private String name;
    /**
     * The current position of the user's car
     */
    private int position;

    /**
     * The position the player would expect by rolling the dice, without knowing the field
     */
    private int expectedPosition;
    
    /**
     * A private dice, used to get a random number
     */
    private Dice dice;
    
    /**
     * Initializes a {@link Player} with the specified
     * <code>name</code>.
     *
     * @param name to set
     */
    public Player(String name) {
        super();
        this.name = name;
        this.position = 0;
        setPosition(0);
        this.dice = new Dice();
    }

    /**
     * Lets the Player roll the dice
     * 
     * @return The rolled number
     */
    public int rollDice() {
        int rollResult = dice.roll();
        expectedPosition = position + rollResult;
        return rollResult;
    }
    
    /**
     * Returns the name of this player.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the actual position of this player's car
     *
     * @return the actual position of this player's car
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Sets the actual position of this player's car
     *
     * @param pos actual position of this player's car
     */
    public void setPosition(int pos) {
        this.position = pos;
        history.add(new Integer(pos));
    }
    
    /**
     * get the exPectedPosition
     */
    public int getExpectedPosition() {
        return expectedPosition;
    }
    
    /**
     * set the exPectedPosition
     */
    public void setExpectedPosition(int expectedPosition) {
        this.expectedPosition = expectedPosition;
    }
    
    /**
     * Returns the position at time (now - t) (i.e., if t=0 returns the current
     * position, if t=1 returns last position, etc.)
     *
     * @param t position at time (now - t)
     */
    public int getPositionMinusT(int t) {
        int index = history.size() - 1 - t;
        if (index >= 0 && index < history.size()) {
            return history.get(index);
        }
        return -1;
    }
    
    /**
     * Returns a Dice, from which you find the information about the last roll
     */
    public Dice getDice() {
        return dice;
    }
}
