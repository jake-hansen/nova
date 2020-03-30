package authentication;

import dao.UserDaoImpl;
import datamodel.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get variables from POST
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        char[] password = request.getParameter("password").toCharArray();

        // Create new UserDaoImpl to create user
        UserDaoImpl udi = new UserDaoImpl();

        // Check to make sure username does not already exist
        if (udi.getByEmail(username) != null) {
            request.getSession().setAttribute("username_available", false);
            request.getSession().setAttribute("failed_register", true);

            // Send redirect
            response.sendRedirect(request.getHeader("Referer"));
        }
        else {
            // Username is available, so attempt to create account
            User newUser = new User(firstname, lastname, username, password, 4);

            // Attempt to add user to database
            udi.create(newUser);

            // Log new user in
            request.setAttribute("username", username);
            request.setAttribute("password", String.copyValueOf(password));

            // Forward to CheckAuthentication
            getServletContext().getRequestDispatcher("/checkauth").forward(request, response);
        }

        // Remove password from memory
        Arrays.fill(password, '0');
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
