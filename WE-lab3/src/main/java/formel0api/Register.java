/**
 * In this bean all registered users are listed and managed
 * #ContainerClass
 * @author Lukas Kraenkl
 * @author David Pfahler
 * @author Johannes Deml
 */
package formel0api;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
// Application Scoped means that the Klass will be saved during the whole Server session 
// (solange Glassfish halt noch werkelt)
import javax.faces.bean.ApplicationScoped;
import javax.faces.validator.ValidatorException;

@ManagedBean
@ApplicationScoped
public class Register {

    private static Register instance = null; 
    private static DateValidator validator= null;
    private ArrayList<User> list = new ArrayList<User>();
    
    /**
     * Creates a new Register (private -> Singleton Pattern)
     */
    private Register() {
    }
    
    /**
     * Returns or creates a instance of Register
     * @return instance of Register
     */
    public static Register getInstance(){
        if(instance==null){
            instance = new Register();
            validator = new DateValidator();
        }
        return instance;
    }
    
    /**
     * Adds a new user in the list
     * @param user 
     */
    public void addUser(User user){
        list.add(user);
    }
    
    /**
     * Returns all Users
     * @return user
     */
    public ArrayList<User> getAllUsers(){
        return this.list;
    }
    
    /**
     * Returns the user of the given username
     * @param username 
     * @return user if there is a user with that username, otherwise NULL
     */
    public User getUser(String username){
        for(User u : list){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }
    
}
