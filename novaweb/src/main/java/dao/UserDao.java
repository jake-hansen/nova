package dao;

import datamodel.User;

import utilities.Password;

import java.util.List;

public class UserDao extends Dao<User> {

    public User getByEmailAndPassword(String email, char[] password) {
        User returnUser = null;
        User emailMatch = null;
        List<User> userMatch = getByField(User.class, "email", email);

        if (!userMatch.isEmpty()) {
            emailMatch = userMatch.get(0);
        }

        if (emailMatch != null) {
            String retrievedPassword = emailMatch.getHashedPassword();
            if (Password.validate(password, retrievedPassword)) {
                returnUser = emailMatch;
            }
        }

        return returnUser;
    }
}
