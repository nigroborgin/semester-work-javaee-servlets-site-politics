package ru.kpfu.itis.shkalin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    // TODO: Убрать хардкод
    public static final String LOGIN = "sample";
    public static final String PASSWORD = "55555";
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/view/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (LOGIN.equals(login) && PASSWORD.equals(password)) {
            logger.info("User with username = {} logged in", login);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("username", login);
            httpSession.setMaxInactiveInterval(60 * 60);

            Cookie httpCookie = new Cookie("username", login);
            httpCookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(httpCookie);


            req.setAttribute("username", httpSession.getAttribute("username"));
            req.getRequestDispatcher("/view/main.ftl").forward(req, resp);
        } else {
            resp.sendRedirect("/politics/login");
        }
    }
}
