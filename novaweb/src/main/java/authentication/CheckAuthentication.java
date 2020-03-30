package authentication;

import dao.UserDaoImpl;
import datamodel.User;
import utilities.Password;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		boolean isAuthenticated = false;

		// Get username and password from POST
		String username = request.getParameter("username");
		char[] password = request.getParameter("password").toCharArray();

		// Attempt to hash password
		try {
			String passwordHash = Password.hash(password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// Check if given username and password are valid
		UserDaoImpl udi = new UserDaoImpl();

		User user = udi.getByEmailAndPassword(username, password);

		// Remove password from memory
		Arrays.fill(password, '0');

		// User is authenticated
		if (user != null) {
			isAuthenticated = true;
		}

		// User is not authenticated
		else {
			request.getSession().setAttribute("failedAuthentication", true);
		}

		// Remove password from memory
		Arrays.fill(password, '0');

		request.getSession().setAttribute("isAuthenticated", isAuthenticated);
		request.getSession().setAttribute("UserObject", user);
		response.sendRedirect(request.getHeader("Referer"));

	}
}
