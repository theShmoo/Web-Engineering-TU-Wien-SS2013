/**
 * This controller handels the user management
 * Here there are functions like Login and other Register Functions
 * @author: Lukas Kraenkl, David Pfahler, Johannes Deml
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
    private Register storage = Register.getInstance();
    /**
     * Creates a new instance of Controller
     */
    public UserController() {
    }
    
    public String login(){
        String username = user.getUsername();
        User tmp = Register.getInstance().getUser(username);
        if(tmp==null){
            return "/mycomponents/login.xhtml";
        }
        if(tmp.getPassword().equals(user.getPassword())){
            return "/table.xhtml";
        }
        return "/mycomponents/login.xhtml";
    }
    
    public void register(){
        //TODO (wird ausgeloest, wenn man auf den Submit Button im Register drueckt)
        
    }
    //TODO: Login, Register Functions

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
