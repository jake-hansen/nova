package setdata;

import dao.UserStatusDao;
import datamodel.UserStatus;
import utilities.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        UserStatusDao usd = new UserStatusDao();
        UserStatus userStatus = new UserStatus();
        userStatus.setUserId(userID);
        if (sos != null) {
            userStatus.setStatusCode(1);
            userStatus.setLocation(location);
        }
        else if (cagm != null) {
            userStatus.setStatusCode(2);
        }
        else if (okay != null) {
            userStatus.setStatusCode(3);
        }

        usd.create(userStatus);

        // Forward to previous page
        ServletUtil.redirectToRequester(request, response);

    }
}
