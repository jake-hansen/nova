package getdata;

import dao.FirstResponderUpdateDao;
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
import java.util.List;

@WebServlet(name = "GetAllLostAndInjured", urlPatterns = "/getalllostandinjured")
public class GetAllLostAndInjured extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get list of users who are lost or injured:
        UserStatusDao usd = new UserStatusDao();
        List<UserStatus> sos_users = usd.getByField(UserStatus.class, "status_code", "1");
        System.out.println("These are the users with code 1: " + sos_users);

        if (!sos_users.isEmpty()) {
            // Set list as attribute:
            request.setAttribute("injured_and_lost_list", sos_users);
        }

        // Forward to requester
        ServletUtil.forwardToRequester(request, response);
    }
}
