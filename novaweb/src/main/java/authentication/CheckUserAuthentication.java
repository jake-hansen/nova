package authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import databasetools.DBConnection;

public class CheckUserAuthentication {

	/**
	 * Checks the given user against the database to see if they
	 * are authenticated. NOTE: Using String to store password is insecure.
	 * This should only be used for testing.
	 * @param username - Username to check.
	 * @param password - Password to check.
	 * @return - True if authenticated, false otherwise.
	 */
	public static boolean check(String username, String password) {
		// Boolean to return
		boolean isAuthenticated = false;

		// Make database connection
		Connection connection = null;
		DBConnection.connect();
		connection = DBConnection.connection;

		// Check if username exists in database
		PreparedStatement preparedStatement = null;

		// If username or password is empty, do not search database. User is not authenticated.
		if (username.isEmpty() || password.isEmpty()) {
			System.out.println("Got empty username or empty password. Not authenticated.");
			isAuthenticated = false;
		}

		// Proceed with searching database.
		else {
			try {
				preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email LIKE BINARY ? AND password LIKE BINARY ?");
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				ResultSet results = preparedStatement.executeQuery();

				// If a username and password match was found
				if (results.next()) {
					System.out.println("User " + username + " succesfully authenticated.");
				}

				// Username and password match was not found, user is not authenticated
				else {
					System.out.println("User " + username + " not authenticated.");
				}
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		return isAuthenticated;
	}
}
