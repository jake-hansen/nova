package setdata;

import dao.UserDao;
import datamodel.User;
import utilities.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This Servlet is called when a user accounts for another member.
 */
@WebServlet(name = "SelfReport", urlPatterns = "/accountmember")
public class AccountForMember extends HttpServlet {

    /**
     * Processes GET request. When called, send 404 as GET should never be called for /login.
     *
     * @param request  - Request to use.
     * @param response - Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Never respond to GET requests, throw 404 error
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    /**
     * Processes POST request. Updates the searched for member to be accounted in the database.
     * information.
     *
     * @param request  - Request to use.
     * @param response - Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get variables from POST
        String accountFor = request.getParameter("accountForName");

        // Get userID from session
        int userID = (int) request.getSession().getAttribute("user_id");
        

        // Forward to previous page
        ServletUtil.redirectToRequester(request, response);

    }
}