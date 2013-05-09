/**
 * This class represents an user
 * @author Lukas Kraenkl, David Pfahler, Johannes Deml
 */
package formel0api;

import java.util.GregorianCalendar;
import javax.faces.bean.ManagedBean;
// Session scoped == Solange die Session aufrecht ist, ist auch diese Bean aufrecht
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class User {

    private String firstname;
    private String lastname;
    private GregorianCalendar birthdate;
    private String gender;
    private String username;
    private String password;
    
    public User(){
        this.firstname = "";
        this.lastname = "";
        this.birthdate = null;
        this.gender = "";
        this.username = "";
        this.password = "";
        
    }
    
    /**
     * Creates a new User
     * @param firstname != "", First name (no second name)
     * @param lastname != "", Last Name
     * @param birthdate < now
     * @param gender (MALE || FEMALE)
     * @param username 
     * @param password 
     */
    public User(String firstname,String lastname,GregorianCalendar birthdate,String gender,String username,String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }
    
    /*
     * Gets FirstName of the user
     * @return First Name
     */
    public String getFirstName(){
        return this.firstname;
    }
    
    /**
     * Gets LastName of the user
     * @return Last Name
     */
    public String getLastName(){
        return this.lastname;
    }
    
    /**
     * Gets Birthdate of the user
     * @return birthdate
     */
    public GregorianCalendar getBirthdate(){
        return this.birthdate;
    }
    
    /**
     * Gets gender of the user
     * @return male or female
     */
    public String getGender(){
        return this.gender;
    }
    
    /**
     * Gets username of the user
     * @return username
     */
    public String getUsername(){
        return this.username;
    }
    
    /**
     * Get Password of the user
     * @return password of the user
     */
    public String getPassword(){
        return this.password;
    }
    
    /**
     * Sets a new first name of the user
     * @param firstname != ""
     */
    public void setFirstName(String firstname){
        this.firstname = firstname;
    }
    
    /**
     * Sets a new last name of the user
     * @param lastname 
     */
    public void setLastName(String lastname){
        this.lastname = lastname;
    }
    
    /**
     * Sets a new Birthdate of the user
     * @param birthdate < now
     */
    public void setBirthdate(GregorianCalendar birthdate){
        this.birthdate = birthdate;
    }
    
    /**
     * Sets a new gender of the user
     * @param gender (MALE || FEMALE)
     */
    public void setGender(String gender){
        this.gender = gender;
    }
    
    /**
     * Sets a new username
     * @param username 
     */
    public void setUsername(String username){
        this.username = username;
    }
    
    /**
     * Sets a new password of the user
     * @param password 
     */
    public void setPassword(String password){
        this.password = password;
    }
}
