package ru.kpfu.itis.shkalin.servlet.account;

import ru.kpfu.itis.shkalin.dto.AccountFullDto;
import ru.kpfu.itis.shkalin.service.crud.AccountCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "accountShowServlet", urlPatterns = {"/users/", "/profile/"})
public class AccountShowServlet extends HttpServlet {

    AccountCrudService accountCrudService;

    @Override
    public void init() throws ServletException {
        accountCrudService = (AccountCrudService) getServletContext().getAttribute("accountCrudService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id;
        AccountFullDto user;
        String uri = req.getRequestURI();
        if (uri.contains("profile")) {
            user = accountCrudService.get((Integer) req.getSession().getAttribute("id"));
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/view/profile.ftl").forward(req, resp);
        } else {
            id = req.getParameter("id");
            if (id != null) {
                user = accountCrudService.get(Integer.parseInt(id));
                if (user != null) {
                    req.setAttribute("user", user);
                } else {
                    req.setAttribute("message", "There is no such user");
                }
            } else {
                List<AccountFullDto> userList = accountCrudService.getAll();
                if (!userList.isEmpty()) {
                    req.setAttribute("userList", userList);
                }
            }

            req.getRequestDispatcher("/WEB-INF/view/users.ftl").forward(req, resp);
        }
    }
}
