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
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "userController")
@RequestScoped
public class UserController {

    @ManagedProperty(value = "#{user}")
    private User user;
    private Register storage;
    private boolean loginfailed;
    /**
     * Creates a new instance of Controller
     */
    public UserController() {
        storage = Register.getInstance();
        loginfailed = false;
    }
    
    public String login(){
        loginfailed = false;
        String username = user.getUsername();
        User tmp = storage.getUser(username);
        if(tmp==null){
            loginfailed = true;
            return "/index.xhtml";
        }
        if(tmp.getPassword().equals(user.getPassword())){
            loginfailed = false;
            return "/table.xhtml";
        }
        loginfailed = true;
        return "/index.xhtml";
    }
    
    public String register(){
        storage.addUser(user);
        loginfailed = false;
        return "/table.xhtml";
    }
    
    public boolean getFailedStatus(){
        return this.loginfailed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
