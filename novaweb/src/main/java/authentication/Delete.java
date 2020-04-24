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
@WebServlet(name = "Delete", urlPatterns = "/delete")
public class Delete extends HttpServlet {

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
     * Processes POST request. Gets form parameters for removing user and checks to make sure information is
     * valid to be removed. If information is valid, user account is deleted.
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

        // Check Database for User with given email:
        UserDao ud = new UserDao();
        User user = (User) ud.getByField(User.class, "email", username);

        // Attempt to delete User:
        if (user == null) {
            request.getSession().setAttribute("failed_delete", true);
        }
        else {
            ud.delete(User.class, user);
        }

        // Redirect:
        ServletUtil.redirectToRequester(request, response);
    }
}
