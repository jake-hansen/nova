package webinterface;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databasetools.DBConnection;
import authentication.CheckUserAuthentication;

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

		// Get username and password from POST
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Check if given username and password are valid
		CheckUserAuthentication.check(username, password);
		
		// Get session
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
	}
}
