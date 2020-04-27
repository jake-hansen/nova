package getdata;

import authentication.UserAuth;
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

@WebServlet(name = "SearchUserByName", urlPatterns = "/searchuserbyname")
public class SearchUserByName extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
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
                // Set session attributes to user data
                request.getSession().setAttribute("found_user_object", foundUser);

                UserStatusDao usd = new UserStatusDao();
                List<UserStatus> userStatusList = usd.getByField(UserStatus.class, "userId", Integer.toString(foundUser.getId()));
                UserStatus us = null;
                if (!userStatusList.isEmpty()) {
                    us = userStatusList.get(0);
                }

                request.getSession().setAttribute("found_user_object_status", us);
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
