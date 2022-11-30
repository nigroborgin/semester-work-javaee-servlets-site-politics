package ru.kpfu.itis.shkalin.servlet.account;

import ru.kpfu.itis.shkalin.service.crud.AccountCrudService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "accountDeleteServlet", urlPatterns = {"/users/delete/"})

public class AccountDeleteServlet extends HttpServlet {
    AccountCrudService accountCrudService;

    @Override
    public void init() throws ServletException {
        accountCrudService = (AccountCrudService) getServletContext().getAttribute("accountCrudService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        accountCrudService.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("/politics/users/");
    }
}
