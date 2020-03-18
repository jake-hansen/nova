package dao;

import datamodel.User;

import utilities.Password;

public class UserDao extends Dao<User> {

    public User getByEmailAndPassword(String email, char[] password) {
        User returnUser = null;
        User emailMatch = getByField(User.class, "email", email);

        if (emailMatch != null) {
            String retrievedPassword = emailMatch.getHashedPassword();
            if (Password.validate(password, retrievedPassword)) {
                returnUser = emailMatch;
            }
        }

        return returnUser;
    }
}
