package getdata;

import authentication.UserAuth;
import dao.UserDao;
import datamodel.User;
import utilities.ServletUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetUserData", urlPatterns = "/getuserdata")
public class GetUserData extends HttpServlet {

    /**
     * Method called when using POST. Calls doGet() since POST should not be used with this Servlet.
     * @param request - Request to use.
     * @param response - Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Method called when using GET. Retrieves user data from database.
     * @param request - Request to use.
     * @param response - Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Check if a user has authenticated in the request. If true, proceed with getting user data. If false,
         * redirect to caller.
         */
        if (!UserAuth.isAuthenticated(request)) {
            response.sendRedirect(request.getHeader("Referer"));
        }
        else {
            // Get userID from request
            int userID = (int) request.getSession().getAttribute("user_id");

            // Create new UserDao connection
            UserDao ud = new UserDao();

            // Get User object by user id
            User user = ud.get(User.class, userID);

            // Set request attribute "user_object" to the found User object
            request.setAttribute("user_object", user);


            // Before forward, set forwarded parameter
            request.setAttribute("forwarded_to_getuserdata", true);

            // Forward request back to caller
            ServletUtil.forwardToRequester(request, response);
        }
    }
}
