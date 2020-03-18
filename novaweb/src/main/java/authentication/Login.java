package authentication;

import dao.UserDao;
import datamodel.User;
import utilities.ServletUtil;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This Servlet is called to process the login form.
 */
@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {

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
     * Processes POST request. When called, checks given username and password parameters for validity. If they are
     * valid, logs user in. Otherwise, does not log user in.
     * @param request - Request to use.
     * @param response - Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get username and password from POST
        String username = request.getParameter("username");
        char[] password = request.getParameter("password").toCharArray();

        /**
         * Check to make sure username and password are not empty. If they are
         * forward to calling webpage. If they are not empty, continue with processing.
         */
        if (!username.isEmpty() || username != "" || password.length != 0) {
            // Check if given username and password are valid
            UserDao ud = new UserDao();

            // Try to find username and password match in database
            User user = ud.getByEmailAndPassword(username, password);

            // Done processing password, remove password from memory
            Arrays.fill(password, '0');

            // If user match was found and they are not in the unauthenticated group, authenticate them
            if (user != null && user.getGroupID() != 5) {
                UserAuth.authenticate(request, response, user, request.getSession().getId());
            }
            // If user match was not found, don't authenticate
            else {
                UserAuth.failAuthentication(request, response, ud.getByField(User.class, "email", username),
                        request.getSession().getId());
            }
        }
        // Redirect to requester
        ServletUtil.redirectToRequester(request, response);
    }
}
