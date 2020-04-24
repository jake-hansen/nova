package setdata;

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
@WebServlet(name = "SetAccountability", urlPatterns = "/setaccountability")
public class SetAccountability extends HttpServlet {

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
     * Processes POST request. Gets form parameters for accounting for user. Updates the status table with the provided
     * information.
     * @param request - Request to use.
     * @param response - Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get variables from POST
        String sos = request.getParameter("sos");
        String cagm = request.getParameter("cagm");
        String okay = request.getParameter("okay");
        String location = request.getParameter("location");

        // Get userID from session
        int userID = (int) request.getSession().getAttribute("user_id");

        if (sos != null) {

        }
        else if (cagm != null) {
            
        }
        else if (okay != null) {

        }
        else if (location != null) {

        }

        // Forward to previous page
        ServletUtil.redirectToRequester(request, response);

    }
}
