package authentication;

import dao.SessionDao;
import dao.UserDao;
import datamodel.Session;
import datamodel.User;
import utilities.Cookies;
import utilities.ServletUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

/**
 * This Servlet is called on every webpage to check if there is an existing valid session. Calling this Servlet
 * enable session tracking on each webpage.
 */
@WebServlet(name = "CheckSession", urlPatterns = "/checksession")
public class CheckSession extends HttpServlet {

    /**
     * Processes POST request. When called, send 404 as POST should never be called for /checksession.
     * @param request - Request to use.
     * @param response - Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Never respond to POST requests, throw 404 error
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    /**
     * Checks if there is a current, valid session and logs user in. If the found session is not valid, remove the
     * session. If a session is not found, do nothing.
     * @param request - Request to use.
     * @param response - Response to use.
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get sessionID cookie
        Cookie sessionIDCookie = Cookies.getSessionIDCookie(request);

        // If cookie exists, check cookie validity, otherwise do nothing
        if (sessionIDCookie != null) {
            // Look for sessionID in sessions table
            SessionDao sd = new SessionDao();
            Session foundSession = sd.getBySessionID(sessionIDCookie.getValue());

            // If session is found, check validity of session and log user in if session is valid
            if (foundSession != null) {
                if (checkSessionValidity(foundSession)) {
                    UserDao ud = new UserDao();
                    int userID = foundSession.getUserID();
                    User authenticatedUser = ud.get(User.class, userID);
                    UserAuth.authenticate(request, response, authenticatedUser, foundSession.getLastSessionID());
                }
                // If the session is not valid, remove session
                else {
                    sd.delete(Session.class, foundSession);
                }
            }
        }

        // Since session has been checked, set "checkedsession" to true
        request.getSession().setAttribute("checkedsession", true);

        /**
         * If there is no calling requester attribute, redirect to homepage. If there is a calling requester
         * attribute,forward to that requester.
         */
        if (request.getAttribute("requester") == null) {
            response.sendRedirect("/novaweb");
        }
        else {
            ServletUtil.forwardToRequester(request, response);
        }
    }

    /**
     * Checks a given session for its validity.
     * @param session - Session to check for validity.
     * @return - True if session is valid, false otherwise.
     */
    private static boolean checkSessionValidity(Session session) {
        // Get current time
        long currentTime = Instant.now().toEpochMilli();

        /**
         * Check session validity. Compares the current time against the session expiry time, if the session has
         * been manually marked as valid or invalid, and compares the failed login attempts to the
         * maximum number of failed login attempts allowed.
         */
        return currentTime - session.getSessionExpiryTime() < Session.VALIDITY_LENGTH &&
                session.getSessionIsValid() &&
                session.getFailedLoginAttempts() < Session.MAX_FAILED_LOGIN_ATTEMPTS;
    }
}
