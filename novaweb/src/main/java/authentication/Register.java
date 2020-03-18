package authentication;

import dao.GroupDao;
import dao.UserDao;
import datamodel.Group;
import datamodel.User;
import utilities.Password;
import utilities.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * This Servlet is called when a user registers using the register form.
 */
@WebServlet(name = "Register", urlPatterns = "/register")
public class Register extends HttpServlet {

    /**
     * Processes GET request. When called, send 404 as GET should never be called for /login.
     * @param request - Request to use.
     * @param response - Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Never respond to GET requests, throw 404 error
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    /**
     * Processes POST request. Gets form parameters for registering user and checks to make sure information is
     * valid to be registered. If information is valid, user is registered and logged in. Otherwise, sends redirect back
     * to caller.
     * @param request - Request to use.
     * @param response - Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get variables from POST
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String groupName = request.getParameter("groupname");
        char[] password = request.getParameter("password").toCharArray();

        // Create new UserDao to create user
        UserDao ud = new UserDao();

        // Check to make sure username does not already exist
        if (ud.getByField(User.class, "email", username) != null) {
            request.getSession().setAttribute("username_available", false);
            request.getSession().setAttribute("failed_register", true);

            // Send redirect
            ServletUtil.redirectToRequester(request, response);
        }
        else {
            // Find groupID by group name
            GroupDao gd = new GroupDao();
            int groupID = gd.getByField(Group.class, "groupName", groupName).getId();

            // Username is available, so attempt to create account
            String passwordHash = null;

            // Attempt to hash password and create account
            passwordHash = Password.hash(password);
            if (passwordHash != null) {
                User newUser = new User(firstname, lastname, username, passwordHash, groupID);
                ud.create(newUser);
            }

            // Forward to Login
            getServletContext().getRequestDispatcher("/login").forward(request, response);
        }
        // Remove password from memory
        Arrays.fill(password, '0');
    }
}
