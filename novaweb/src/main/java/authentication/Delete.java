package authentication;

import dao.UserDao;
import datamodel.User;
import utilities.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This Servlet is called when a user attempts to delete an account.
 */
@WebServlet(name = "Delete", urlPatterns = "/delete")
public class Delete extends HttpServlet {

    /**
     * Processes GET request. When called, send 404 as GET should never be called.
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
     * Processes POST request. Gets form parameters for removing user and checks to make sure information is
     * valid to be removed. If information is valid, user account is deleted.
     * @param request - Request to use.
     * @param response - Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get variables from POST
        String username = request.getParameter("username");

        // Check Database for User with given email:
        UserDao ud = new UserDao();
        List<User> usersMatchingUsername = ud.getByField(User.class, "email", username);

        // Attempt to delete User:
        if (usersMatchingUsername.isEmpty()) {
            request.getSession().setAttribute("failed_delete", true);
        }
        else {
            ud.delete(User.class, usersMatchingUsername.get(0));
            request.getSession().setAttribute("failed_delete", false);
        }

        // Redirect:
        ServletUtil.redirectToRequester(request, response);
    }
}
