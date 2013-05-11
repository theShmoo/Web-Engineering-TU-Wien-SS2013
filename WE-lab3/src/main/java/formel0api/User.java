/**
 * This class represents an {@link User}
 * 
 * @author Lukas Kraenkl
 * @author David Pfahler
 * @author Johannes Deml
 */
package formel0api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.bean.ManagedBean;
// Session scoped == Solange die Session aufrecht ist, ist auch diese Bean aufrecht
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class User {

    private String firstname;
    private String lastname;
    private String birthdate;
    private Sex gender;
    private String username;
    private String password;
    private boolean agreedTerms;
    
    public User(){
        this.firstname = "";
        this.lastname = "";
        this.birthdate = null;
        this.gender = null;
        this.username = "";
        this.password = "";
        this.agreedTerms = false;
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
    public User(String firstname,String lastname,String birthdate,Sex gender,String username,String password,boolean agreedTerms) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.agreedTerms = agreedTerms;
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
    public String getBirthdate(){
        return this.birthdate;
    }
    
    /**
     * Gets gender of the user
     * @return male or female
     */
    public Sex getGender(){
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
    public void setBirthdate(String birthdate){
       
        this.birthdate = birthdate;
    }
    
    /**
     * Sets a new gender of the user
     * @param gender (MALE || FEMALE)
     */
    public void setGender(Sex gender){
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

    public boolean isAgreedTerms() {
        return agreedTerms;
    }

    public void setAgreedTerms(boolean agreedTerms) {
        this.agreedTerms = agreedTerms;
    }
    
    
}
