package datamodel;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int groupID;
    private String password;

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
        this.password = password;
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

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return(
                "First Name: " + firstName +
                "\nLast Name: " + lastName +
                "\nEmail: " + email +
                "\nGroup ID: " + groupID
                + "\nID: " + id);
    }
}
