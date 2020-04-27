package datamodel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_status")
public class UserStatus implements Serializable{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "status_code")
    private int statusCode;

    @Column(name = "location")
    private String location;

    @Column(name = "comment")
    private String comment;

    @Transient
    private String statusName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) { this.statusName = statusName; }

    /**
     * Converts the numerical status present in object to the text equivalent
     * status and sets the variable statusName to this text.
     */
    public void updateStatusName() {
        switch (getStatusCode()) {
            case 1: setStatusName("SOS");
            break;
            case 2: setStatusName("Lost");
            break;
            case 3: setStatusName("Okay");
            break;
            default:
                setStatusName("");
        }
    }
}
