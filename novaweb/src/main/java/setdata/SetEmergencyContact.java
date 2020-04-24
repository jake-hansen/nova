package setdata;

import dao.EmergencyContactDao;
import dao.GroupDao;
import dao.UserDao;
import datamodel.EmergencyContact;
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
 * This Servlet is called when a new emergency contact is added.
 */
@WebServlet(name = "SetEmergencyContact", urlPatterns = "/setemergencycontact")
public class SetEmergencyContact extends HttpServlet {

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
     * Processes POST request. Gets form parameters for registering user and checks to make sure information is
     * valid to be registered. If information is valid, user is registered and logged in. Otherwise, sends redirect back
     * to caller.
     *
     * @param request  - Request to use.
     * @param response - Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get variables from POST
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String primaryphone = request.getParameter("primaryphone");
        String secondaryphone = request.getParameter("secondaryphone");
        String relationship = request.getParameter("relationship");
        String email = request.getParameter("email");


        // Create new Emergency Contact
        EmergencyContactDao ecd = new EmergencyContactDao();

        EmergencyContact newEmergencyContact = new EmergencyContact();
        newEmergencyContact.setFirstName(firstname);
        newEmergencyContact.setLastName(lastname);
        newEmergencyContact.setPrimaryPhone(primaryphone);
        newEmergencyContact.setSecondaryPhone(secondaryphone);
        newEmergencyContact.setRelationship(relationship);
        newEmergencyContact.setEmail(email);

        ecd.create(newEmergencyContact);

        // Forward to previous page
        ServletUtil.redirectToRequester(request, response);
    }
}
