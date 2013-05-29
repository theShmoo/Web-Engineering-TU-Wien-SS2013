package tuwien.big.formel0.entities;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import tuwien.big.formel0.picasa.RaceDriver;

/**
 * The entity of a player of the Fomel0 game
 *
 * @author David Pfahler
 */
@ManagedBean(name = "player")
@NoneScoped
@Entity
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50)
    @Size(max = 50)
    @NotNull
    @Pattern(regexp = "^[A-Za-z]+$")
    private String firstname = null;
    @Column(nullable = false, length = 50)
    @Size(max = 50)
    @NotNull
    @Pattern(regexp = "^[A-Za-z]+$")
    private String lastname = null;
    @Column(nullable = false, length = 50)
    @Size(max = 50)
    @NotNull
    @Pattern(regexp = "^[A-Za-z]+$")
    private String name = null;
    @Column(nullable = false, length = 50)
    @Size(max = 50)
    @NotNull
    @Pattern(regexp = "^((?=.*\\d)(?=.*[a-z,A-Z]).{1,})$")
    private String password = null;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @Past
    @NotNull
    private String birthday = null;
    @Enumerated(EnumType.ORDINAL)
    private Sex sex = null;
    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private RaceDriver avatar = null;

    /**
     * Creates a new instance of Player
     */
    public Player() {
    }

    /**
     * Returns the unique id of the player
     *
     * @return the unique id of the player
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique id of the player
     *
     * @pre this should only use the Database!
     * @param id the unique id of the player
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the sex
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Sets a new gender of the user
     *
     * @param gender (MALE || FEMALE)
     */
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    /**
     * Returns the avatar of the player
     *
     * @return the avatar of the player
     */
    public RaceDriver getAvatar() {
        return avatar;
    }

    /**
     * Set a Avatar for the player
     *
     * @param avatar the avatar to set
     */
    public void setAvatar(RaceDriver avatar) {
        this.avatar = avatar;
    }
}
