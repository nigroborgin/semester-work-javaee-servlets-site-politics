package ru.kpfu.itis.shkalin.servlet;

import ru.kpfu.itis.shkalin.dto.AbstractDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    private static final List<UserDto> USERS = List.of(
            new UserDto("ivan222", "sdsd@mail.ru"),
            new UserDto("Petr1", "petro@gmail.com"),
            new UserDto("sten2001", "stepsss2010@ya.ru")
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", USERS);
        req.getRequestDispatcher("/view/users.ftl").forward(req, resp);
    }
}
