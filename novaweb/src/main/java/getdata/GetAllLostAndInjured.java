package getdata;

import dao.FirstResponderUpdateDao;
import dao.UserDao;
import dao.UserStatusDao;
import datamodel.FirstResponderUpdate;
import datamodel.User;
import datamodel.UserStatus;
import utilities.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetAllLostAndInjured", urlPatterns = "/getalllostandinjured")
public class GetAllLostAndInjured extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao ud = new UserDao();
        UserStatusDao usd = new UserStatusDao();

        List<UserStatus> allUserStatuses = usd.getAll(UserStatus.class);
        List<User> usersSosLost = new ArrayList<>();

        // Get list of all users that have a reported status of SOS or Lost
        for (UserStatus us : allUserStatuses) {
            if (us.getStatusCode() == 1 || us.getStatusCode() == 2) {
                User u = ud.get(User.class, us.getUserId());
                u.setUserStatus(us);
                u.getUserStatus().updateStatusName();
                usersSosLost.add(u);
            }
        }

        request.setAttribute("injured_and_lost_list", usersSosLost);
        request.setAttribute("dashboard_updated", true);

        // Forward to requester
        ServletUtil.forwardToRequester(request, response);
    }
}
