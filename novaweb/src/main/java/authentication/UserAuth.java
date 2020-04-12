package authentication;

import dao.SessionDao;
import datamodel.Session;
import datamodel.User;
import utilities.Cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Utility class that handles authentication requests.
 */
public class UserAuth {

    /**
     * Authenticates the given user against the given sessionID
     * @param request - Request to use.
     * @param response - Response to use.
     * @param user - User to authenticate.
     * @param sessionID - SessionID to authenticate with.
     */
    public static void authenticate(HttpServletRequest request, HttpServletResponse response, User user, String sessionID) {
        // Set session attributes
        request.getSession().setAttribute("user_id", user.getId());
        request.getSession().setAttribute("group_id", user.getGroupID());
        System.out.println("This is the group ID of the user: " + user.getGroupID());
        request.getSession().setAttribute("isAuthenticated", true);
        storeUpdateSession(request, response, user, sessionID, true);
    }

    /**
     * Fails the authentication request for the given user against the given sessionID.
     * @param request - Request to use.
     * @param response - Response to use.
     * @param user - User to fail authenticate.
     * @param sessionID - SessionID to fail authenticate with.
     */
    public static void failAuthentication(HttpServletRequest request, HttpServletResponse response, User user, String sessionID) {
        // Set session attributes
        request.getSession().setAttribute("failedAuthentication", true);
        request.getSession().setAttribute("isAuthenticated", false);
        storeUpdateSession(request, response, user, sessionID, false);
    }

    /**
     * Unauthenticates an already authenticated request.
     * @param request - Request to unauthenticate.
     */
    public static void unAuthenticate(HttpServletRequest request) {
        // Delete session attributes by invalidating session
        request.getSession().invalidate();

        // Remove session from sessions table
        SessionDao sd = new SessionDao();
        sd.delete(Session.class, sd.getBySessionID(Cookies.getCookie(request, "SessionID").getValue()));

        // Delete session cookie
        Cookies.removeCookie(request, "SessionID");
    }

    /**
     * 
     * @param request
     * @param response
     * @param user
     * @param sessionID
     * @param validSession
     */
    private static void storeUpdateSession(HttpServletRequest request, HttpServletResponse response, User user,
                                           String sessionID, boolean validSession) {
        // Remove old sessionID cookie from browser
        if (Cookies.getSessionIDCookie(request) != null) {
            Cookies.getSessionIDCookie(request).setMaxAge(0);
            Cookies.getSessionIDCookie(request).setValue(null);
        }

        // Store new sessionID cookie in browser
        Cookie ck = new Cookie("SessionID", request.getSession().getId());
        ck.setMaxAge(60 * 60 * 24);
        response.addCookie(ck);

        /**
         * Check if sessionID exists in sessions table. If it does, update session
         * record with new sessionID. If it doesn't, create new session record.
         */
        SessionDao sd = new SessionDao();
        Session potentialFoundSession = sd.getBySessionID(sessionID);
        if (potentialFoundSession != null) {
            potentialFoundSession.setLastSessionID(request.getSession().getId());
            if (!validSession) {
                potentialFoundSession.setFailedLoginAttempts(potentialFoundSession.getFailedLoginAttempts() + 1);
            }
            else {
                potentialFoundSession.setFailedLoginAttempts(0);
            }
            potentialFoundSession.setSessionIsValid(validSession);
            sd.update(Session.class, potentialFoundSession);
        }
        else {
            // Store new session in DB
            Session session = new Session();

            // If the user does not exist, set user to NULL entry
            if (user != null) {
                session.setUserID(user.getId());
            }
            else {
                session.setUserID(1);
            }
            session.setLastSessionID(sessionID);
            session.setSessionIsValid(validSession);
            session.setSessionExpiryTime(request.getSession().getCreationTime() + Session.VALIDITY_LENGTH);
            session.setLastLoginIP(request.getRemoteAddr());
            if (!validSession) {
                session.setFailedLoginAttempts(1);
            }
            else {
                session.setFailedLoginAttempts(0);
            }
            sd.create(session);
        }
    }

    /**
     * Checks the given request to check if a user has been authenticated.
     * @param request - Request to check for user authentication.
     * @return - True if user is authenticated, false otherwise.
     */
    public static boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute("isAuthenticated") != null &&
                (boolean) request.getSession().getAttribute("isAuthenticated");

    }
}
