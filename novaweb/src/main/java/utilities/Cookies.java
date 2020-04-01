package utilities;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Cookies {

    /**
     * Gets sessionID cookie from provided request.
     * @param request - Request to get sessionID cookie from.
     * @return sessionID cookie if it exists, null otherwise.
     */
    public static Cookie getSessionIDCookie(HttpServletRequest request) {
        Cookie[] cookies = getAllCookies(request);

        Cookie sessionIDCookie = null;

        // If some cookies exist, check for SessionID cookie
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("SessionID")) {
                    sessionIDCookie = c;
                }
            }
        }

        return sessionIDCookie;
    }

    /**
     * Gets all cookies from provided request.
     * @param request - Request to get cookies from.
     * @return Array of cookies if some exist, null otherwise.
     */
    public static Cookie[] getAllCookies(HttpServletRequest request) {
        return request.getCookies();
    }

    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = getAllCookies(request);

        Cookie requestedCookie = null;

        // If some cookies exist, check for cookie
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(cookieName)) {
                    requestedCookie = c;
                }
            }
        }
        return requestedCookie;
    }

    public static void removeCookie(HttpServletRequest request, String cookieName) {
        // Try to get cookie
        Cookie cookieToRemove = getCookie(request, cookieName);
        if (cookieToRemove!= null) {
            cookieToRemove.setMaxAge(0);
            cookieToRemove.setValue(null);
        }
    }
}
