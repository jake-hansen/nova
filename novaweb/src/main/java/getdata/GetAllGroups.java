package getdata;

import dao.GroupDao;
import datamodel.Group;
import utilities.ServletUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@WebServlet(name = "GetAllGroups", urlPatterns = "/getallgroups")
public class GetAllGroups extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Anyone can get a list of all groups, no authentication required
        List groupList = null;
        GroupDao gd = new GroupDao();

        groupList = gd.getAll(Group.class);

        request.setAttribute("group_list", groupList);

        // Forward to requester
        ServletUtil.forwardToRequester(request, response);
    }
}
