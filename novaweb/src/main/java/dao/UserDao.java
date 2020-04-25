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

    public User getByFirstAndLastName(String firstName, String lastName) {
        User returnUser = null;
        List<User> userMatch = getByField(User.class, "lastName", lastName);

        // Found potentially multiple matches
        if (!userMatch.isEmpty()) {
            // Try to match first name
            for (User u : userMatch) {
                if (u.getFirstName().toUpperCase().equals(firstName.toUpperCase())) {
                    // Return first match
                    returnUser = u;
                    break;
                }
            }
        }
        return returnUser;
    }
}
