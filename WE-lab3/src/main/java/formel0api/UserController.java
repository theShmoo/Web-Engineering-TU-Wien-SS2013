/**
 * This controller handels the user management
 * Here there are functions like Login and other Register Functions
 * 
 * @author Lukas Kraenkl
 * @author David Pfahler
 * @author Johannes Deml
 **/
package formel0api;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "userController")
@SessionScoped
public class UserController {

    @ManagedProperty(value = "#{user}")
    private User user;
    private Register storage;
    private boolean loggedIn;
    private boolean failedLogin;
    /**
     * Creates a new instance of Controller
     */
    public UserController() {
        storage = Register.getInstance();
        loggedIn = false;
        failedLogin = false;
    }
    
    public String login(){
        loggedIn = false;
        String username = user.getUsername();
        User tmp = storage.getUser(username);
        if(tmp==null){
            loggedIn = false;
            failedLogin = true;
            return "/index.xhtml";
        }
        if(tmp.getPassword().equals(user.getPassword())){
            loggedIn = true;
            return "/table.xhtml";
        }
        failedLogin = true;
        loggedIn = false;
        return "/index.xhtml";
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";
    }
    
    public String register(){
        storage.addUser(user);
        loggedIn = true;
        return "/table.xhtml";
    }
    
    public boolean isLoggedIn(){
        return this.loggedIn;
    }

    public boolean isFailedLogin() {
        return failedLogin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
