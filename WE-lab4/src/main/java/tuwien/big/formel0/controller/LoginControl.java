package tuwien.big.formel0.controller;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import tuwien.big.formel0.entities.Player;
import tuwien.big.formel0.entities.facade.PlayerDaoJPA;
import tuwien.big.formel0.utilities.Utility;

@ManagedBean(name = "lc")
@SessionScoped
public class LoginControl {

    @ManagedProperty(value = "#{player}")
    private Player player;
    @EJB
    private PlayerDaoJPA playerDao;
    @ManagedProperty(value = "#{gc}")
    private GameControl gc;
    @ManagedProperty(value = "false")
    private boolean showloginfailed;
    private String name;
    private String password;

    /**
     * Creates a new instance of LoginControl
     */
    public LoginControl() {
    }

    public String logout() {
        HttpServletRequest hsr = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();

        /* To really remove all user specific data */
        hsr.getSession().invalidate();
        /* To do not get an error on the next page */
        hsr.getSession(true);

        return "index";
    }

    public String login() {
        player = playerDao.findByNameAndPassword(name, password);

        if (player != null) {
            gc = new GameControl(player.getName());

            return "table";
        } else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", Utility.getResourceText(ctx, "msg", "loginfailed"));
            ctx.addMessage("login", message);
            return "index";
        }
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the showloginfailed
     */
    public boolean isShowloginfailed() {
        return showloginfailed;
    }

    /**
     * @param showloginfailed the showloginfailed to set
     */
    public void setShowloginfailed(boolean showloginfailed) {
        this.showloginfailed = showloginfailed;
    }

    /**
     * @return the mc
     */
    public GameControl getGc() {
        return gc;
    }

    /**
     * @param mc the mc to set
     */
    public void setGc(GameControl gc) {
        this.gc = gc;
    }
}
