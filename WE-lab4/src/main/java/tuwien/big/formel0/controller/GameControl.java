package tuwien.big.formel0.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import formel0api.Game;
import formel0api.GamePlayer;
import java.util.concurrent.TimeUnit;
import tuwien.big.formel0.entities.Player;
import tuwien.big.formel0.picasa.RaceDriver;
import tuwien.big.formel0.soap.HighScoreServiceImpl;

@ManagedBean(name = "gc")
@SessionScoped
public class GameControl {

    GamePlayer player;
    GamePlayer computer;
    Player entityPlayer;
    Game game;
    int playerscore = 0;
    int computerscore = 0;
    int round = 1;
    String playername;
    private String raceDriverWiki;
    private String raceDriverURL;

    public GameControl() {
        player = new GamePlayer("Susi");
        init();
    }

    /**
     * Initializes a new game.
     */
    public GameControl(Player player) {
        this.entityPlayer = player;
        init();
    }

    public void init() {
        if (entityPlayer != null) {
            player = new GamePlayer(entityPlayer.getName(), 
                    entityPlayer.getAvatar().getWikiUrl(), 
                    entityPlayer.getAvatar().getUrl());
        }
        computer = new GamePlayer("Deep Blue");
        this.game = new Game(player, computer);
        round = 1;
    }

    /**
     * Returns the time already spent on this game
     *
     * @return the time already spent on this game
     */
    public String getTime() {
        long milliseconds = game.getSpentTime();
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(milliseconds),
                (TimeUnit.MILLISECONDS.toSeconds(milliseconds)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds))));
    }

    /**
     * Specifies whether this game is over or not
     *
     * @return <code>true</code> if this game is over, <code>false</code>
     * otherwise.
     */
    public boolean isGameOver() {
        return game.isGameOver();
    }

    /**
     * Returns the rounds already played in this game
     *
     * @return the rounds already played in this game
     */
    public int getRound() {
        return round;
    }

    /**
     * Returns the currently leading player
     *
     * @return the currently leading player
     */
    public GamePlayer getLeader() {
        return game.getLeader();
    }

    /**
     * Rolls the dice for the player
     */
    public void doRound() {
        if (isGameOver()) {
            return;
        }
        this.playerscore = game.rollthedice(player);
        if (!isGameOver()) {
            this.computerscore = game.rollthedice(computer);
        } else {
            this.computerscore = 0;
        }
        ++round;
        if (isGameOver()) {
            HighScoreServiceImpl.getInstance().publishHighScore(game, entityPlayer);
        }

    }

    /**
     * Returns the score thrown by the player
     *
     * @return the score thrown by the player
     */
    public String getDiceResource() {
        return "img:wuerfel" + getPlayerScore() + ".png";
    }

    /**
     * Returns the score thrown by the player
     *
     * @return the score thrown by the player
     */
    public int getPlayerScore() {
        return this.playerscore;
    }

    /**
     * Returns the score of the computer
     *
     * @return the score of the computer-controlled opponent
     */
    public int getComputerScore() {
        return this.computerscore;
    }

    /**
     * Returns player 1 of the game
     *
     * @return player 1 of the game
     */
    public GamePlayer getPlayer1() {
        return this.player;
    }

    /**
     * Return player 2 of the game
     *
     * @return player 2 of the game
     */
    public GamePlayer getPlayer2() {
        return this.computer;
    }
}