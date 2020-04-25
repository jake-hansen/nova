package setdata;

import dao.FirstResponderUpdateDao;
import datamodel.FirstResponderUpdate;
import utilities.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This Servlet is called when a first responder publishes an update.
 */
@WebServlet(name = "SetFirstResponderUpdate", urlPatterns = "/setfirstresponderupdate")
public class SetFirstResponderUpdate extends HttpServlet {

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
     * Processes POST request. Gets form parameters for sending status updates and adds a new record to the database
     * with the corresponding first responder update.
     * @param request - Request to use.
     * @param response - Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get variables from POST
        String informationalUpdate = request.getParameter("informational_update");

        // Get userID from session
        int userID = (int) request.getSession().getAttribute("user_id");

        // Set informational update in database
        FirstResponderUpdateDao frud = new FirstResponderUpdateDao();

        FirstResponderUpdate fru = new FirstResponderUpdate();
        fru.setUserId(userID);
        fru.setUpdate(informationalUpdate);

        if (frud.create(fru)) {
            request.getSession().setAttribute("update_published", true);
        }
        else {
            request.getSession().setAttribute("update_published", false);
        }

        // Forward to previous page
        ServletUtil.redirectToRequester(request, response);

    }
}