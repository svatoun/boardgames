package board.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * User management
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // just display the login form
        getServletContext().getRequestDispatcher("/internal/user/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepository userDB = (UserRepository) req.getServletContext().getAttribute("userDb");

        String loginName = req.getParameter("loginName");
        User user = userDB.findUserByLoginName(loginName);

        if (user == null)
            getServletContext().getRequestDispatcher("/internal/user/user_not_found.html").forward(req, resp);
        else {
            req.getSession().setAttribute("user", user);
            getServletContext().getRequestDispatcher("/internal/user/user_logged_in.html").forward(req, resp);
        }
    }
}
