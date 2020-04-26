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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // List of SOS and Lost Users:
        List<List<String>> injured_users = new ArrayList<List<String>>();
        List<List<String>> lost_users = new ArrayList<List<String>>();

        // Get list of users who are lost or injured:
        UserDao ud = new UserDao();
        UserStatusDao usd = new UserStatusDao();

        // Get all users:
        List<UserStatus> all_user_status_objects = usd.getAll(UserStatus.class);

        // Manual filter based on status:
        for (UserStatus user_status_object : all_user_status_objects) {
            String id = Integer.toString(user_status_object.getUserId());
            List<User> users_matching_id = ud.getByField(User.class, "id", id);

            if (!users_matching_id.isEmpty()) {

                User user = users_matching_id.get(0);

                if (user_status_object.getStatusCode() == 1) {
                    List<String> table_row = new ArrayList<String>();
                    table_row.add("SOS");
                    table_row.add(user.getFirstName() + " " + user.getLastName());
                    table_row.add(user_status_object.getLocation());
                    injured_users.add(table_row);
                }
                if (user_status_object.getStatusCode() == 2) {
                    List<String> table_row = new ArrayList<String>();
                    table_row.add("Lost");
                    table_row.add(user.getFirstName() + " " + user.getLastName());
                    table_row.add(user_status_object.getLocation());
                    lost_users.add(table_row);
                }
            }
        }

        // Combine lost and injured:
        List<List<String>> injured_and_lost_users = injured_users;
        injured_and_lost_users.addAll(lost_users);
        System.out.println("This is the emergency dashboard table: " + injured_and_lost_users);

        // Set list as attribute:
        request.setAttribute("injured_and_lost_list", injured_and_lost_users);

        // Forward to requester
        ServletUtil.redirectToRequester(request, response);
    }
}
