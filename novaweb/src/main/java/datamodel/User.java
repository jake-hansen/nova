package datamodel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "group_id")
    private int groupID;

    @Column(name = "password")
    private String hashedPassword;

    @Transient
    private UserStatus userStatus;

    public User(String firstName, String lastName, String email, int groupID, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.groupID = groupID;
        this.id = id;
    }

    public User(String firstName, String lastName, String email, String password, int groupID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.groupID = groupID;
        this.setHashedPassword(password);
    }

    public User() { }

    public void setId(int id) { this.id = id; }

    public int getId() {return id; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getHashedPassword() { return this.hashedPassword; }

    public void setHashedPassword(String hashedPassword) { this.hashedPassword = hashedPassword; }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return(
                "First Name: " + firstName +
                "\nLast Name: " + lastName +
                "\nEmail: " + email +
                "\nHashed Password: " + hashedPassword +
                "\nGroup ID: " + groupID
                + "\nID: " + id);
    }
}
