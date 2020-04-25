package getdata;

import authentication.UserAuth;
import dao.FirstResponderUpdateDao;
import dao.UserDao;
import datamodel.FirstResponderUpdate;
import datamodel.User;
import utilities.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchUserByName", urlPatterns = "/searchuserbyname")
public class SearchUserByName {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check to make sure a user has authenticated before proceeding
        if (!UserAuth.isAuthenticated(request)) {
            ServletUtil.redirectToRequester(request, response);
        } else {
            // Get request parameters
            String searchFirstName = request.getParameter("first_name_search");
            String searchLastName = request.getParameter("last_name_search");

            // Search for user
            UserDao ud = new UserDao();
            User foundUser = ud.getByFirstAndLastName(searchFirstName, searchLastName);

            if (foundUser != null) {
                // Set session attribute to user
                request.getSession().setAttribute("found_user_object", foundUser);
                request.getSession().setAttribute("search_found_user", true);
            } else {
                request.getSession().setAttribute("search_found_user", false);
            }
        }

        // Before forward, set forwarded parameter
        request.setAttribute("forwarded_to_searchuserbyname", true);

        // Redirect to requester
        ServletUtil.redirectToRequester(request, response);
    }
}
