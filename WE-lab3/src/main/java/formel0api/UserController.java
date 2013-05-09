/**
 * This controller handels the user management
 * Here there are functions like Login and other Register Functions
 * @author: Lukas Kraenkl, David Pfahler, Johannes Deml
 **/
package formel0api;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class UserController {

    private Register storage = Register.getInstance();
    /**
     * Creates a new instance of Controller
     */
    public UserController() {
    }
    
    public void register(){
        //TODO (wird ausgeloest, wenn man auf den Submit Button im Register drueckt)
    }
    //TODO: Login, Register Functions
}
