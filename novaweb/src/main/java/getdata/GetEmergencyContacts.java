package getdata;

import authentication.UserAuth;
import dao.EmergencyContactDao;
import datamodel.EmergencyContact;
import utilities.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetEmergencyContacts", urlPatterns = "/getemergencycontacts")
public class GetEmergencyContacts extends HttpServlet {

    /**
     * Processes POST request. Calls doGet() since POST should not be called for this class.
     *
     * @param request  Request to use.
     * @param response Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Processes GET request. Sets "emergency_contact_list" request attribute to the emergency contacts
     * of the current user.
     *
     * @param request  Request to use.
     * @param response Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         // Check if a user has authenticated in the request. If true, proceed with getting user data. If false,
         // redirect to caller.
        if (!UserAuth.isAuthenticated(request)) {
            response.sendRedirect(request.getHeader("Referer"));
        } else {
            // Get userID from request
            int userID = (int) request.getSession().getAttribute("user_id");

            EmergencyContactDao ecd = new EmergencyContactDao();
            List<EmergencyContact> emergencyContactList = ecd.getByField
                    (EmergencyContact.class, "userId", Integer.toString(userID));

            request.setAttribute("emergency_contact_list", emergencyContactList);

            // Forward request back to caller
            ServletUtil.forwardToRequester(request, response);
        }
    }
}
