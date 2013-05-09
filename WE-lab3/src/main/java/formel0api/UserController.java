/**
 * This controller handels the user management
 * Here there are functions like Login and other Register Functions
 * @author: Lukas Kraenkl, David Pfahler, Johannes Deml
 **/
package formel0api;

import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

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
    
    public void validateBirthday(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        String bithday = (String)value;
        if(!bithday.matches("\\d\\d?\\.\\d\\d?\\.\\d\\d\\d\\d")){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Wrong format!", null);
            //TODO right text
            throw new ValidatorException(msg);
        }
    }
    
    
}
