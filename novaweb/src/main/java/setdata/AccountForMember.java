package setdata;

import dao.UserDao;
import dao.UserStatusDao;
import datamodel.User;
import datamodel.UserStatus;
import utilities.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This Servlet is called when a user accounts for another member.
 */
@WebServlet(name = "AccountForMember", urlPatterns = "/accountformember")
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
     *
     * @param request  - Request to use.
     * @param response - Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get variables from POST
        String accountFor = request.getParameter("accountForName");
        String firstName = "", lastName = "";

        //Parse out first and last name entered
        if (accountFor.contains(" ")) {
            String[] firstAndLast = accountFor.split("\\s+");
            firstName = firstAndLast[0];
            lastName = firstAndLast[1];
        }

        // Check if nothing was entered for first and last name
        if (firstName.isEmpty() || lastName.isEmpty()) {
            request.getSession().setAttribute("failed_lookup", true);
        } else {
            // Create new UserDao connection
            UserDao ud = new UserDao();

            //Check if the user returns null then inform the user that this name isn't in our database
            User user = ud.getByFirstAndLastName(firstName, lastName);

            if (user == null) {
                request.getSession().setAttribute("failed_lookup", true);
            }

            //User is in the database so set the user status
            else {
                // Get userID by user first and last name
                int userID = user.getId();

                //Create and set the looked up user status object
                UserStatus userStatus = new UserStatus();
                userStatus.setUserId(userID);

                // Accounted for status code is 3; user is now accounted for
                userStatus.setStatusCode(3);

                // Attempt to update record in database, create record if it doesn't exist
                UserStatusDao usd = new UserStatusDao();

                List<UserStatus> usl = usd.getByField(UserStatus.class, "userId", Integer.toString(userStatus.getUserId()));

                // Update existing record if found, otherwise create new record
                if (!usl.isEmpty()) {
                    userStatus.setId(usl.get(0).getId());
                    usd.update(UserStatus.class, userStatus);
                } else {
                    usd.create(userStatus);
                }

                // Set boolean on fail check:
                request.getSession().setAttribute("failed_lookup", false);
            }
        }



        // Forward to previous page
        ServletUtil.redirectToRequester(request, response);

    }
}
