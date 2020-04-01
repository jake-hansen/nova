package utilities;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletUtil {

    /**
     * Forwards given request and response back to the calling requester as specified in the requester attribute.
     * @param request - Request to use.
     * @param response - Response to use.
     * @throws IOException
     * @throws ServletException
     */
    public static void forwardToRequester(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        RequestDispatcher rd = request.getRequestDispatcher((String) request.getAttribute("requester"));
        rd.forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public static void redirectToRequester(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getHeader("referer"));
    }
}
