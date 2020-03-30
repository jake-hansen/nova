package dao;

import databasetools.DBConnection;
import datamodel.User;
import utilities.Password;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    // DBConnection to use
    private static Connection connection = null;

    /**
     * Constructor instantiates database connection.
     */
    public UserDaoImpl() {
        DBConnection.connect();
        connection = DBConnection.connection;
    }

    /**
     * createUser attempts to create a new user entry in the user table.
     * @param user - New User object to insert into database.
     * @return Non-null User object of new user if successful. Null User object otherwise.
     */
    @Override
    public void create(User user) {
        // Get parameters from User object
        String firstname = user.getFirstName();
        String lastname = user.getLastName();
        String email = user.getEmail();
        int groupID = user.getGroupID();
        String password = user.getPassword();

        // Create prepared statement and attempt to execute it
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement =
                    connection.prepareStatement("INSERT INTO users (group_id, first_name, last_name, email, password) " +
                            "VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, groupID);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, lastname);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, password);

            preparedStatement.execute();
        }
        catch (SQLException se) {
            System.out.println("Error executing SQL statement.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the given User object in the database with any changed values. The user record to change is given
     * by the ID in the User object.
     * @param user - User record with changes.
     */
    @Override
    public void update(User user) {
        // Get parameters from User object
        int id = user.getId();
        String newFirstName = user.getFirstName();
        String newLastName = user.getLastName();
        String newEmail = user.getEmail();
        int newGroupID = user.getGroupID();
        String newPassword = user.getPassword();

        // Create prepared statement and attempt to execute it
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET " +
                    " first_name = ?," +
                    " last_name = ?," +
                    " email = ?," +
                    " group_id = ?," +
                    " password = ?" +
                    " WHERE id = ?");

            preparedStatement.setString(1, newFirstName);
            preparedStatement.setString(2, newLastName);
            preparedStatement.setString(3, newEmail);
            preparedStatement.setInt(4, newGroupID);
            preparedStatement.setString(5, newPassword);
            preparedStatement.setInt(6, id);

            preparedStatement.execute();
        }
        catch (SQLException se) {
            System.out.println("Error executing SQL statement.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Deletes the given User object in the database. The User to delete is given by ID.
     * @param id - User record to delete by ID.
     */
    @Override
    public void delete(int id) {
        // Create prepared statement and attempt to execute it
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE" +
                    " id = ?");
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
        }
        catch (SQLException se) {
            System.out.println("Error executing SQL statement.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the specified User object in the database by searching for record using given ID.
     * @param id - ID of user to search for. Returns null user object if User is not found.
     */
    @Override
    public User get(int id) {
        // User object to return
        User userResult = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                userResult = new User(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getInt("group_id"),
                        rs.getInt("id"));
            }
        }
        catch (SQLException se) {
            System.out.println("Error executing SQL statement.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return userResult;
    }

    /**
     * Attempts to find a User in database by matching both username and password.
     * @param email - Email of user to search for.
     * @param password - Password of user to search for. This is a hashed password.
     * @return - User object of found user if user was found. Null otherwise.
     */
    public User getByEmailAndPassword(String email, char[] password) {
        // User object to return
        User userResult = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE" +
                    " email LIKE BINARY ?");

            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();

            String retrievedPassword = null;

            while (rs.next()) {
                userResult = new User(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getInt("group_id"),
                        rs.getInt("id"));
                retrievedPassword = rs.getString("password");
            }

            // If given password does not match retrieved password, user is not a match
            if (!Password.validate(password, retrievedPassword)) userResult = null;
        }
        catch (SQLException se) {
            System.out.println("Error executing SQL statement.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return userResult;
    }

    /**
     * Retrieves user from database by email.
     * @param email - Email of user to search for.
     * @return - User object of record found. Null if not found.
     */
    public User getByEmail(String email) {
        // User object to return
        User userResult = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE" +
                    " email LIKE BINARY ?");

            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                userResult = new User(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getInt("group_id"),
                        rs.getInt("id"));
            }
        }
        catch (SQLException se) {
            System.out.println("Error executing SQL statement.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return userResult;
    }
}
