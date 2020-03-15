package webinterface;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databasetools.DBConnection;

/**
 * Servlet implementation class CheckAuthentication
 */
@WebServlet("/CheckAuthentication")
public class CheckAuthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckAuthentication() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Never respond to get requests, throw 404 error
		// Need to implement this
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * Boolean to keep track of user authentication status.
		 * Set to TRUE if user is authenticated. FALSE, otherwise.
		 */
		boolean isAuthenticated = false;
		
		// Make database connection
		Connection connection = null;
		DBConnection.connect();
		connection = DBConnection.connection;
		
		// Get username from form
		String username = request.getParameter("username");
		
		/**
		 * Get password from form. NOTE: Storing password as a plaintext string
		 * is insecure and should only be used during testing!
		 */
		String password = request.getParameter("password");
		
		// Check if username exists in database
		PreparedStatement preparedStatement = null;
		
		// If username or password is empty, do not search database. User is not authenticated.
		if (username.isEmpty() || password.isEmpty()) isAuthenticated = false;
		
		// Proceed with searching database.
		else {
			try {
				preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email LIKE BINARY ? AND password LIKE BINARY ?");
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				ResultSet results = preparedStatement.executeQuery();
				
				// If a username and password match was found
				if (results.next()) {
					System.out.println("User found: " + username);
				}
				
				// Username and password match was not found, user is not authenticated
				else {
					System.out.println("User not found.");
				}
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}	
		}	
	}
}
