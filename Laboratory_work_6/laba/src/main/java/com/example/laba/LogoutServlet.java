package com.example.laba;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * Class for logging out of an account
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    /**
     * @param req User request (to server) variable
     * @param resp Server response variable
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("AUTH", "FALSE");
        session.setAttribute("LOGIN", null);
        session.setAttribute("STATUS", null);
        session.setAttribute("FIRSTNAME", null);
        session.setAttribute("LASTNAME", null);
        session.setAttribute("GENDER", null);
        session.setAttribute("EMAIL", null);
        session.setAttribute("AGE", null);
        resp.sendRedirect("/index.jsp");
    }
}
