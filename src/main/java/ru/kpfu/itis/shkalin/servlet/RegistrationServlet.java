package ru.kpfu.itis.shkalin.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.shkalin.dto.AccountFullDto;
import ru.kpfu.itis.shkalin.service.crud.AccountCrudService;
import ru.kpfu.itis.shkalin.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(name = "registrationServlet", urlPatterns = {"/reg/"})
public class RegistrationServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServlet.class);
    private AccountCrudService accountCrudService;

    @Override
    public void init() throws ServletException {
        accountCrudService = (AccountCrudService) getServletContext().getAttribute("accountCrudService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/registration.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");

        if (password1.equals(password2)) {
            if (accountCrudService.get(username) == null) {
                AccountFullDto user = new AccountFullDto();
                user.setUsername(username);
                user.setPassword(PasswordUtil.encrypt(password1));
                user.setEmail(email);
                user.setPictureURL("/politics/picture/default.bmp");
                accountCrudService.create(user);
                req.setAttribute("message", "registration was successful");
                req.getRequestDispatcher("/WEB-INF/view/main.ftl").forward(req, resp);
            } else {
                req.setAttribute("message", "this username is already occupied");
                req.getRequestDispatcher("/WEB-INF/view/registration.ftl").forward(req, resp);
            }
        } else {
            req.setAttribute("message", "passwords not equal");
            req.getRequestDispatcher("/WEB-INF/view/registration.ftl").forward(req, resp);
        }
    }
}
