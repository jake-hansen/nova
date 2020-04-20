package getdata;

import authentication.UserAuth;
import dao.GroupDao;
import datamodel.Group;
import datamodel.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetGroupData", urlPatterns = "/getgroupdata")
public class GetGroupData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Check if a user has authenticated in the request. If true, proceed with getting user data. If false,
         * redirect to caller.
         */
        if (!UserAuth.isAuthenticated(request)) response.sendRedirect(request.getHeader("Referer"));

        else {
            Integer groupID = null;

            // Try to get groupID from request
            try {
                groupID = Integer.parseInt(request.getAttribute("group_id").toString());
            }
            catch (NumberFormatException nfe) {
                System.out.println("Group ID was either not present in request or was not an integer.");
            }

            /**
             * If getting groupID was successful, get group object from database. Set request attribute "group_object"
             * to the found Group object.
             */
            if (groupID != null) {
                GroupDao gdi = new GroupDao();
                request.setAttribute("group_object", gdi.get(Group.class, groupID));
            }

            // Before forward, set forwarded parameter
            request.setAttribute("forwarded_to_getgroupdata", true);

            // Forward request back to caller
            RequestDispatcher rd = request.getRequestDispatcher((String) request.getAttribute("requester"));
            rd.forward(request, response);
        }
    }
}
