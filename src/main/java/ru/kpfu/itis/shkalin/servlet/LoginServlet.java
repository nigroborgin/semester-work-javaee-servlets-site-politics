package ru.kpfu.itis.shkalin.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.shkalin.dto.UserFullDto;
import ru.kpfu.itis.shkalin.service.сrud.PostCrudService;
import ru.kpfu.itis.shkalin.service.сrud.UserCrudService;

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

    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    private UserCrudService userCrudService;

    @Override
    public void init() throws ServletException {
        userCrudService = (UserCrudService) getServletContext().getAttribute("userCrudService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/view/login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserFullDto user = userCrudService.get(login);
        if (user != null
                && user.getUsername().equals(login)
                && user.getPassword().equals(/*PasswordUtil.encrypt(*/password/*)*/)) {

            logger.info("User with username = {} logged in", login);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("id", user.getId());
            httpSession.setAttribute("username", login);
            httpSession.setAttribute("email", user.getEmail());

            httpSession.setMaxInactiveInterval(60 * 60);

            Cookie httpCookie = new Cookie("username", login);
            httpCookie.setMaxAge(24 * 60 * 60);

            resp.addCookie(httpCookie);
            req.setAttribute("username", login);
            req.getRequestDispatcher("/WEB-INF/view/main.ftl").forward(req, resp);
        } else {
            req.setAttribute("message", "Login error. Repeat please");
            req.getRequestDispatcher("/WEB-INF/view/login.ftl").forward(req, resp);
        }
    }
}
