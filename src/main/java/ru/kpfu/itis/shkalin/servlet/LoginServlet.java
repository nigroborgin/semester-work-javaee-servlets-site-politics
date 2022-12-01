package ru.kpfu.itis.shkalin.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.shkalin.dto.AccountFullDto;
import ru.kpfu.itis.shkalin.service.crud.AccountCrudService;
import ru.kpfu.itis.shkalin.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "loginServlet", urlPatterns = "/login/")
public class LoginServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    private AccountCrudService accountCrudService;

    @Override
    public void init() throws ServletException {
        accountCrudService = (AccountCrudService) getServletContext().getAttribute("accountCrudService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/view/login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        AccountFullDto user = accountCrudService.get(username);
        if (user != null
                && user.getUsername().equals(username)
                && user.getPassword().equals(PasswordUtil.encrypt(password))) {

            logger.info("User with username = {} logged in", username);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("id", user.getId());
            httpSession.setAttribute("username", username);
            httpSession.setAttribute("email", user.getEmail());
            httpSession.setAttribute("role", user.getRole().getName());

            httpSession.setMaxInactiveInterval(60 * 60);

            Cookie httpCookie = new Cookie("username", username);
            httpCookie.setMaxAge(24 * 60 * 60);

            resp.addCookie(httpCookie);
            req.setAttribute("username", username);
            req.getRequestDispatcher("/WEB-INF/view/main.ftl").forward(req, resp);
        } else {
            req.setAttribute("message", "Login error. Repeat please");
            req.getRequestDispatcher("/WEB-INF/view/login.ftl").forward(req, resp);
        }
    }
}
