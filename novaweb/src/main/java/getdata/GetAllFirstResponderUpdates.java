package getdata;

import dao.FirstResponderUpdateDao;
import datamodel.FirstResponderUpdate;
import utilities.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetAllFirstResponderUpdates", urlPatterns = "/getallfirstresponderupdates")
public class GetAllFirstResponderUpdates extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<FirstResponderUpdate> firstResponderUpdates = null;
        FirstResponderUpdateDao frud = new FirstResponderUpdateDao();
        firstResponderUpdates = frud.getAll(FirstResponderUpdate.class);

        // Set update
        if (!firstResponderUpdates.isEmpty()) {
            String lastUpdate = firstResponderUpdates.get(firstResponderUpdates.size() - 1).getUpdate();
            request.setAttribute("first_responder_updates_list", lastUpdate);
        }
        else {
            request.setAttribute("first_responder_updates_list", "No updates at this time...");
        }

        // Before forward, set forwarded parameter
        request.setAttribute("forwarded_to_first_responder_updates", true);

        // Forward to requester
        ServletUtil.forwardToRequester(request, response);
    }
}
