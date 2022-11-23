package ru.kpfu.itis.shkalin.servlet;

import ru.kpfu.itis.shkalin.dto.UserCreateDto;
import ru.kpfu.itis.shkalin.dto.UserFullDto;
import ru.kpfu.itis.shkalin.service.сrud.UserCrudService;
import ru.kpfu.itis.shkalin.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registrationServlet", urlPatterns = {"/reg"})
public class RegistrationServlet extends HttpServlet {

    private UserCrudService userCrudService;

    @Override
    public void init() throws ServletException {
        userCrudService = (UserCrudService) getServletContext().getAttribute("userCrudService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/registration.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");

        if (password1.equals(password2)) {
            UserFullDto user = new UserFullDto();
            user.setUsername(login);
            user.setPassword(PasswordUtil.encrypt(password1));
            userCrudService.create(user);
            req.setAttribute("message", "registration was successful");
            req.getRequestDispatcher("/WEB-INF/view/main.ftl").forward(req, resp);
        } else {
            req.setAttribute("message", "passwords not equal!!");
            req.getRequestDispatcher("/WEB-INF/view/registration.ftl").forward(req, resp);
        }
    }
}
