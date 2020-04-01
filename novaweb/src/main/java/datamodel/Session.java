package datamodel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="sessions")
public class Session implements Serializable {
    public static final int VALIDITY_LENGTH = 86400000;
    public static final int MAX_FAILED_LOGIN_ATTEMPTS = 5;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @Column(name = "user_id")
    private int userID;

    @Column(name = "last_session_id")
    private String lastSessionID;

    @Column(name = "session_expiry_time")
    private long sessionExpiryTime;

    @Column(name = "session_is_valid")
    private boolean sessionIsValid;

    @Column(name = "last_login_ip")
    private String lastLoginIP;

    @Column(name = "failed_login_attempts")
    private int failedLoginAttempts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLastSessionID() {
        return lastSessionID;
    }

    public void setLastSessionID(String lastSessionID) {
        this.lastSessionID = lastSessionID;
    }

    public long getSessionExpiryTime() {
        return sessionExpiryTime;
    }

    public void setSessionExpiryTime(long sessionExpiryTime) {
        this.sessionExpiryTime = sessionExpiryTime;
    }

    public boolean getSessionIsValid() {
        return sessionIsValid;
    }

    public void setSessionIsValid(boolean sessionIsValid) {
        this.sessionIsValid = sessionIsValid;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }
}
